package com.mengfei.fbsepjava.controller;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.mengfei.fbsepjava.annotation.UserTokenCheck;
import com.mengfei.fbsepjava.model.*;
import com.mengfei.fbsepjava.model.retruns.RevenueAddressRetrun;
import com.mengfei.fbsepjava.pojo.ApiResponse;
import com.mengfei.fbsepjava.services.interfaces.*;
import com.mengfei.fbsepjava.utils.RedisUtil;
import com.mengfei.fbsepjava.utils.Utils;
import org.apache.http.util.TextUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("/api")
public class ApiController {

    @Resource
    private HashRateSaleInterface hashRateSaleInterface;
    @Resource
    private CoinInterface coinInterface;
    @Resource
    private MinerInterface minerInterface;
    @Resource
    private OrderInfoInterface orderInterface;
    @Resource
    private EmailService emailService;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private UserInterface userInterface;
    @Resource
    private RevenueAddressInterface revenueAddressInterface;
    @Resource
    private NewsInterface newsInterface;

    @Resource
    private GiftCertificateInterface giftCertificateInterface;


    /*************************获取验证码*****************************************/
    @RequestMapping(value = "user/getCode",method = RequestMethod.POST)
    public ApiResponse getCode(@RequestBody @Validated User user){
        int type = user.getType();
        @Email(message = "邮箱格式错误")
        String email;
        if(type==0){//手机注册
            String phoneNum = user.getPhoneNum();
            if(Utils.testPhone(phoneNum)){
                DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "<accessKeyId>", "<accessSecret>");
                IAcsClient client = new DefaultAcsClient(profile);
                CommonRequest request = new CommonRequest();
                request.setMethod(MethodType.POST);
                request.setDomain("dysmsapi.aliyuncs.com");
                request.setVersion("2017-05-25");
                request.setAction("SendSms");
                request.putQueryParameter("PhoneNumbers", phoneNum);
                request.putQueryParameter("SignName", "aaa");
                request.putQueryParameter("TemplateCode", "aaaa");
                try {
                    CommonResponse response = client.getCommonResponse(request);
                    return new ApiResponse<>(200,"获取验证码成功","1111");
                } catch (ServerException e) {
                    e.printStackTrace();
                    return new ApiResponse<>(400,"获取验证码失败","");
                } catch (ClientException e) {
                    e.printStackTrace();
                    return new ApiResponse<>(400,"获取验证码失败","");
                }
            }else {
                return new ApiResponse<>(400, "手机号码有误", "");
            }

        }else {//邮箱注册

            email = user.getEmail();

            emailService.sendEmailVerCode(email, Utils.generateVerCode());
            //验证码保存到redis
            redisUtil.set(email, Utils.generateVerCode());
            redisUtil.expire(email, 60 * 5);//五分钟失效
            return new ApiResponse<>(200, "获取验证码成功，青岛市邮箱查看", "");


        }



    }


    /*************************用户注册*****************************************/
    @RequestMapping(value = "user/register",method = RequestMethod.POST)
    public ApiResponse register(@RequestBody Map<String,Object> map){
        User user = new User();
        user.setUser_uuid(Utils.getUUID());
        int type = (int)map.get("type");

        int code = (int)map.get("code");

        // todo 验证redis 缓存code
//        if(redisUtil.get("email").equals(code)){
        if (1111 == code) {


            if (type == 0) {//手机注册
                String phoneNumber = (String)map.get("phoneNum");
                if(Utils.testPhone(phoneNumber)){
                    return new  ApiResponse<>(400,"手机号码不正确","");
                }
                user.setPhoneNum(phoneNumber);

            } else if (type == 1) {//邮箱注册
                String passWord = (String)map.get("passWord");

                String email =  (String) map.get("email");

                user.setEmail(email);
                user.setPassWord(passWord);
            }

            userInterface.save(user);

            return new ApiResponse<>(200, "注册成功", user);

        } else
            return new ApiResponse<>(400, "验证码不正确", "");


    }


    /*************************用户登录*****************************************/
    @RequestMapping(value = "user/login",method = RequestMethod.POST)
    public ApiResponse login(@RequestBody @Validated User user){

        int type = user.getType();//0为手机登录、1为邮箱登录

        if(type==0){//手机登录需要先获取验证码，
            //查看手机号是否已经注册

            int code = user.getCode();

            String phoneNumber = user.getPhoneNum();

            if(!Utils.testPhone(phoneNumber)){
                return new  ApiResponse<>(400,"手机号码不正确","");
            }

           User user1 =  userInterface.findByPhoneNum(phoneNumber);
            if(user1!=null){
                //  todo 验证code redis缓存
                if(1111==code){
                    return new  ApiResponse<>(200,"登录成功",user1);
                }else
                    return new  ApiResponse<>(400,"验证码不正确","");

            }else
                return new  ApiResponse<>(400,"该号码尚未注册","");

        }else{//邮箱注册


            String email =  user.getEmail();

            String passWord = user.getPassWord();

            User user2 =  userInterface.findByEmail(email);
            if(user2!=null){
                if(passWord.equals(user2.getPassWord())){
                    return new  ApiResponse<>(200,"登录成功",user2);
                }else
                    return new  ApiResponse<>(400,"密码错误","");
            }else
                return new  ApiResponse<>(400,"该号码尚未注册","");

        }
    }

    /*************************帐号管理--绑定状态查询*****************************************/
    @RequestMapping(value = "user/getBindStatus",method = RequestMethod.POST)
    public ApiResponse getBindStatus(@RequestBody User user){

        String uuid = user.getUser_uuid();

        User user_status =  userInterface.findByUserUuid(uuid);

        HashMap<String,String> map = new HashMap<>();

        map.put("passWord",user_status.getPassWord());

        map.put("email",user_status.getEmail());

        map.put("phoneNum",user_status.getPhoneNum());

        map.put("google_authentication",user_status.getGoogle_authentication());

        return new ApiResponse<>(200, "查询绑定状态成功", map);

    }

    /*************************绑定密码*****************************************/
    @RequestMapping(value = "user/bindPassword",method = RequestMethod.POST)
    public ApiResponse bindPassword(@RequestBody Map<String,Object> map){

        String uuid = (String)map.get("user_uuid");

        String passWord = (String)map.get("passWord");

        int code = (int)map.get("code");

        // todo 验证redis 缓存code
//        if(redisUtil.get("email").equals(code)){
        if (1111 == code) {

            User user_old =  userInterface.findByUserUuid(uuid);

            user_old.setPassWord(passWord);

            userInterface.save(user_old);


            return new ApiResponse<>(200, "密码绑定成功", null);

        }else
            return new ApiResponse<>(400, "验证码不正确", null);













    }



    /*************************修改密码*****************************************/
    @RequestMapping(value = "user/updatePassword",method = RequestMethod.POST)
    public ApiResponse updatePassword(@RequestBody Map<String,String> map){

        String uuid = map.get("user_uuid");

        String passWord = map.get("passWord_new");

        String passWord_old = map.get("passWord_old");

        User user_old =  userInterface.findByUserUuid(uuid);

        if(!TextUtils.isEmpty(passWord_old)&&passWord_old.equals(user_old.getPassWord())){

            user_old.setPassWord(passWord);

            userInterface.save(user_old);

            return new ApiResponse<>(200, "更新密码成功", null);
        }else{
            return new ApiResponse<>(400, "原密码不正确", null);
        }





    }


    /*************************绑定手机号*****************************************/
    @RequestMapping(value = "user/bindPhone",method = RequestMethod.POST)
    public ApiResponse bindPhone(@RequestBody Map<String,Object> map){

        String uuid = (String) map.get("user_uuid");

        String phoneNum = (String) map.get("phoneNum");

        int code = (int) map.get("code");


        // todo 验证redis 缓存code
//        if(redisUtil.get("phoneNum_new").equals(code_new)){
        if (1111 == code) {

                User user_old =  userInterface.findByUserUuid(uuid);
                user_old.setPhoneNum(phoneNum);
                return new ApiResponse<>(200, "手机号绑定成功", null);



        }else{
            return new ApiResponse<>(400, "验证码不正确", null);
        }


    }


    /*************************修改手机号*****************************************/
    @RequestMapping(value = "user/updatePhone",method = RequestMethod.POST)
    public ApiResponse updatePhone(@RequestBody Map<String,Object> map){

        String uuid = (String) map.get("user_uuid");

        String phoneNum_new = (String) map.get("phoneNum_new");

        int code_new = (int) map.get("code_new");

        String phoneNum_old = (String) map.get("phoneNum_old");

        int code_old = (int) map.get("code_old");

        User user_old =  userInterface.findByUserUuid(uuid);

        if(phoneNum_old.equals(user_old.getPhoneNum())){

            if(phoneNum_new.equals(phoneNum_old)){
                return new ApiResponse<>(400, "手机号相同", null);
            }else{

                // todo 验证redis 缓存code
//        if(redisUtil.get("phoneNum_new").equals(code_new)){
                if (1111 == code_old) {
//            if(redisUtil.get("phoneNum_old").equals(code_old)){
                    if (1111 == code_new) {
                        user_old.setPhoneNum(phoneNum_new);
                        userInterface.save(user_old);
                        return new ApiResponse<>(200, "手机号修改成功", null);

                    }else{
                        return new ApiResponse<>(400, "新验证码不正确", null);
                    }

                }else{
                    return new ApiResponse<>(400, "原验证码不正确", null);
                }

            }

        }else{

            return new ApiResponse<>(400, "手机号尚未注册", null);

    }








    }


    /*************************绑定邮箱*****************************************/
    @RequestMapping(value = "user/bindEmail",method = RequestMethod.POST)
    public ApiResponse bindEmail(@RequestBody Map<String,Object> map){

        String uuid = (String) map.get("user_uuid");

        String email = (String) map.get("email");

        int code = (int) map.get("code");


        // todo 验证redis 缓存code
//        if(redisUtil.get(email).equals(code)){
        if (1111 == code) {

            User user_old =  userInterface.findByUserUuid(uuid);
            user_old.setEmail(email);
            userInterface.save(user_old);
            return new ApiResponse<>(200, "邮箱绑定成功", null);



        }else{
            return new ApiResponse<>(400, "验证码不正确", null);
        }


    }


    /*************************修改邮箱*****************************************/
    @RequestMapping(value = "user/updateEmail",method = RequestMethod.POST)
    public ApiResponse updateEmail(@RequestBody Map<String,Object> map){

        String uuid = (String) map.get("user_uuid");

        String email_new = (String) map.get("email_new");

        int code_new = (int) map.get("code_new");

        String email_old = (String) map.get("email_old");

        int code_old = (int) map.get("code_old");

        User user_old =  userInterface.findByUserUuid(uuid);

        if(email_old.equals(user_old.getEmail())){

            if(email_new.equals(email_old)){
                return new ApiResponse<>(400, "邮箱号相同", null);
            }else{

                // todo 验证redis 缓存code
//        if(redisUtil.get("phoneNum_new").equals(code_new)){
                if (1111 == code_old) {
//            if(redisUtil.get("phoneNum_old").equals(code_old)){
                    if (1111 == code_new) {


                        user_old.setEmail(email_new);
                        userInterface.save(user_old);
                        return new ApiResponse<>(200, "邮箱修改成功", null);

                    }else{
                        return new ApiResponse<>(400, "新验证码不正确", null);
                    }

                }else{
                    return new ApiResponse<>(400, "原验证码不正确", null);
                }

            }

        }else{

            return new ApiResponse<>(400, "邮箱尚未注册", null);

        }

    }

    /*************************绑定google身份验证器*****************************************/
    @RequestMapping(value = "user/bindAuthentication", method = RequestMethod.POST)
    public ApiResponse bindAuthentication(@RequestBody Map<String, Object> map) {

        String uuid = (String) map.get("user_uuid");

        String authentication = (String) map.get("authentication");

        int code = (int) map.get("code");

        String phoneNum = (String) map.get("phoneNum");

        User user_old = userInterface.findByUserUuid(uuid);

        if (phoneNum.equals(user_old.getPhoneNum())) {


//            if(redisUtil.get("phoneNum_old").equals(code_old)){
            if (1111 == code) {

                user_old.setGoogle_authentication(authentication);
                userInterface.save(user_old);
                return new ApiResponse<>(200, "google身份验证器绑定成功", null);

            } else {
                return new ApiResponse<>(400, "新验证码不正确", null);
            }


        } else {

            return new ApiResponse<>(400, "该号码尚未注册或绑定", null);

        }


    }


    /*************************设置收益地址*****************************************/
    @RequestMapping(value = "user/setRevenueAddress", method = RequestMethod.POST)
    public ApiResponse setRevenueAddress(@RequestBody RevenueAddress revenueAddress) {

        revenueAddressInterface.save(revenueAddress);
        
        return new ApiResponse<>(200, "收益地址增加成功", null);

    }

    /*************************获取收益地址类型****************************************/
    @RequestMapping(value = "user/getRevenueAddressType", method = RequestMethod.POST)
    public ApiResponse getRevenueAddressType() {

        List<Map> getRevenueAddressType = new ArrayList<>();

        Map revenueAddress = null;



        for(int i=0;i<9;i++){
            switch(i){
                case 0:
                    revenueAddress = new HashMap();
                    revenueAddress.put("support_pool","AntPool、BTC.com、F2Pool、ViaBTC、BTC.TOP");
                    revenueAddress.put("coin_symbol","BTC");
                    break;
                case 1:
                    revenueAddress = new HashMap();
                    revenueAddress.put("support_pool","AntPool、BTC.com、ViaBTC、BTC.TOP");
                    revenueAddress.put("coin_symbol","BCH");
                    break;
                case 2:

                    revenueAddress = new HashMap();
                    revenueAddress.put("support_pool","AntPool、BTC.com");
                    revenueAddress.put("coin_symbol","LTC");
                    break;
                case 3:


                    revenueAddress = new HashMap();
                    revenueAddress.put("support_pool","AntPool、BTC.com");
                    revenueAddress.put("coin_symbol","ETH");

                    break;
                case 4:
                    revenueAddress = new HashMap();
                    revenueAddress.put("support_pool","AntPool、BTC.com");
                    revenueAddress.put("coin_symbol","DCR");
                    break;
                case 5:
                    revenueAddress = new HashMap();
                    revenueAddress.put("support_pool","AntPool、F2Pool");
                    revenueAddress.put("coin_symbol","ZEH");
                    break;

                

            }
            getRevenueAddressType.add(revenueAddress);

        }


        return new ApiResponse<>(200, "获取地址种类成功", getRevenueAddressType);

    }

    /*************************获取收益地址*****************************************/
    @RequestMapping(value = "user/getRevenueAddress", method = RequestMethod.POST)
    public ApiResponse getRevenueAddress(@RequestBody RevenueAddress revenueAddress) {

        String user_uuid = revenueAddress.getUser_uuid();

        String coin_symbol = revenueAddress.getCoin_symbol();


        List<RevenueAddress> revenueAddresses1 = revenueAddressInterface.findByUserUuidAndCoinSymbol(user_uuid,coin_symbol);


        return new ApiResponse<>(200, "获取地址增加成功", revenueAddresses1);

    }

    /*************************修改收益地址备注*****************************************/
    @RequestMapping(value = "user/updateRevenueAddress", method = RequestMethod.POST)
    public ApiResponse updateRevenueAddress(@RequestBody RevenueAddress revenueAddress) {

        String user_uuid = revenueAddress.getUser_uuid();

        String address = revenueAddress.getAddress();

        String info = revenueAddress.getInfo();

        List<RevenueAddress>  revenueAddress1 = revenueAddressInterface.findByUserUuidAndAddress(user_uuid,address);

        RevenueAddress revenueAddress2 = revenueAddress1.get(0);

        revenueAddress2.setInfo(info);

        RevenueAddress revenueAddress3= revenueAddressInterface.save(revenueAddress2);


        return new ApiResponse<>(200, "修改地址备注成功", revenueAddressInterface.save(revenueAddress2));

    }

    /*************************删除收益地址*****************************************/
    @RequestMapping(value = "user/deletRevenueAddress", method = RequestMethod.POST)
    public ApiResponse deletRevenueAddress(@RequestBody RevenueAddress revenueAddress) {

        String user_uuid = revenueAddress.getUser_uuid();

        String address = revenueAddress.getAddress();

        List<RevenueAddress>  revenueAddress1 = revenueAddressInterface.findByUserUuidAndAddress(user_uuid,address);

        for(RevenueAddress revenueAddress2:revenueAddress1){
            revenueAddressInterface.delete(revenueAddress2);
        }

        return new ApiResponse<>(200, "删除地址成功", null);


    }


    /*************************获取提醒设置*****************************************/
    @RequestMapping(value = "user/getRemindSetting", method = RequestMethod.POST)
    public ApiResponse getRemindSetting(@RequestBody HashMap<String,String> map) {

        String user_uuid = map.get("user_uuid");

        User user = userInterface.findByUserUuid(user_uuid);

        int remindStatus = user.getRemind_type();

        Map<String,Integer> map_return = new HashMap<>();

        map_return.put("remind_type",remindStatus);


        return new ApiResponse<>(200, "获取提醒设置成功", map_return);


    }

    /*************************提醒设置*****************************************/
    @RequestMapping(value = "user/setRemindSetting", method = RequestMethod.POST)
    public ApiResponse setRemindSetting(@RequestBody HashMap<String,Object> map) {

        String user_uuid = (String) map.get("user_uuid");

        int remind_type = (int) map.get("remind_type");

        User user = userInterface.findByUserUuid(user_uuid);

        user.setRemind_type(remind_type);

        userInterface.save(user);

        return new ApiResponse<>(200, "提醒设置成功", null);


    }


    /*************************我的算力*****************************************/
    @RequestMapping(value = "user/getUserHashRate",method = RequestMethod.POST)
    public ApiResponse getUserHashRate(@RequestBody Map<String, Object> map) {

        String user_uuid = (String) map.get("user_uuid");

        List<OrderInfo> orderInfos = orderInterface.findByUserUuid(user_uuid);

        List<UserHashRate> userHashRates = new ArrayList<>();

        List<String> coins = new ArrayList<>();

        UserHashRate userHashRate;

        HashList hashList;


        for (OrderInfo orderInfo : orderInfos) {
            //筛选出币的种类
            if (!coins.contains(orderInfo.getCoin_uuid())) {

                coins.add(orderInfo.getCoin_uuid());

            }
        }

            for (String coin : coins) {

                userHashRate = new UserHashRate();

                userHashRate.setPackagesNum(orderInfos.size());

                userHashRate.setCoin_name(coin);

                userHashRate.setTotal_hashRate("3000 TH/S");

                userHashRate.setTotal_output("0.0001BTC");

                List<HashList> hashListList = new ArrayList<>();

                    for (OrderInfo orderInfo : orderInfos) {

                        if (coin.equals(orderInfo.getCoin_uuid())) {

                            hashList = new HashList();

                            hashList.setOrder_uuid(orderInfo.getOrder_uuid());
                            hashList.setHash_info(orderInfo.getHashRate_name());
                            hashList.setPoor(orderInfo.getMiningPool_uuid());
                            hashList.setElemaining_electricity_day("");
                            hashList.setCumulative_output("");
                            hashList.setStatus(orderInfo.getOrderStatus());
                            hashListList.add(hashList);

                        }

                    }


                userHashRate.setHashLists(hashListList);

                userHashRates.add(userHashRate);
            }


        return new ApiResponse<>(200, "查询算力成功", userHashRates);
    }

    /*************************我的订单******************************************/
    @RequestMapping(value = "user/getUserOrder",method = RequestMethod.POST)
    public ApiResponse getUserOrder(@RequestBody  Map<String,Object> map){
        String user_uuid = (String) map.get("user_uuid");
        int order_status = (int)map.get("order_status");
        int order_type = (int) map.get("order_type");//订单类型 0：算力订单 1：电费订单  100：全部订单


        List<OrderInfo> orderInfos;
        List<UserOrder> userOrders = new ArrayList<>();
        if(order_status==100){
            orderInfos =  orderInterface.findByUserUuid(user_uuid);
        }else{
            orderInfos = orderInterface.findByUserUuidAndOrderStatus(user_uuid,order_status);
        }

        for(OrderInfo orderInfo:orderInfos){
            if(order_type==100){
                //返回全部订单
                UserOrder return_order = new UserOrder();
                return_order.setOrder_uuid(orderInfo.getOrder_uuid());
                return_order.setOrder_date(orderInfo.getOrder_date());
                return_order.setOrder_amount(orderInfo.getOrder_amount());
                return_order.setOrder_status(orderInfo.getOrderStatus());
                return_order.setHashRate("");
                userOrders.add(return_order);
            }else if(order_type==0){
                //返回算力订单
                if(orderInfo.getOrder_type()==0){
                    UserOrder return_order = new UserOrder();
                    return_order.setOrder_uuid(orderInfo.getOrder_uuid());
                    return_order.setOrder_date(orderInfo.getOrder_date());
                    return_order.setOrder_amount(orderInfo.getOrder_amount());
                    return_order.setOrder_status(orderInfo.getOrderStatus());
                    return_order.setHashRate("");
                    userOrders.add(return_order);

                }
            }else{
                //返回电费订单
                if(orderInfo.getOrder_type()==1){
                    UserOrder return_order = new UserOrder();
                    return_order.setOrder_uuid(orderInfo.getOrder_uuid());
                    return_order.setOrder_date(orderInfo.getOrder_date());
                    return_order.setOrder_amount(orderInfo.getOrder_amount());
                    return_order.setOrder_status(orderInfo.getOrderStatus());
                    return_order.setHashRate("");
                    userOrders.add(return_order);

                }
            }

        }


        return new ApiResponse<>(200,"查询订单成功",userOrders);
    }


    //查询在售货币
    @RequestMapping(value = "coin/getSaleCoin",method = RequestMethod.GET)
    public ApiResponse getSaleCoin(){
        Miner miner = null;
        //查找数字货币
        List<Coin> coinList = coinInterface.findAll();

        for(Coin coin:coinList){
            //查找数字货币对应的矿机系列
            miner =  minerInterface.findByMinerUuid(coin.getMiner_uuid());
            if(miner!=null){
                coin.setMiner_nanme(miner.getMinerModel());
            }

        }
        return new ApiResponse<>(200,"查询在售数字货币成功",coinList);
    }



    /*************************查询在售数字货币对应套餐******************************************/
    @RequestMapping(value = "hashRate/getHashRateSale",method = RequestMethod.POST)
    public ApiResponse getHashRateSale(@RequestBody Map<String,String> map){
        //查找数字货币属所的矿机系列
        String coinUuid = map.get("coinUuid");
        System.out.println("coinUuid:"+coinUuid);
        List<HashRateSale> hashRateSaleList;
        SalePackage salePackage;
        List<SalePackage> salePackageList = new ArrayList<>();
        List<Miner> minerList = minerInterface.findAllByCoinUuid(coinUuid);
        for(Miner miner:minerList){
            salePackage = new SalePackage();
            //查找该币在该矿机系列下的在售套餐
            hashRateSaleList = hashRateSaleInterface.findByMinerUuidAndCoinUuid(miner.getMinerUuid(),coinUuid);

            for(HashRateSale hashRateSale:hashRateSaleList){
                hashRateSale.setMinerName(miner.getMinerModel());
                salePackage.setHashRateSales(hashRateSaleList);
            }

            //返回前端所需数据结构
            if(hashRateSaleList.size()!=0){
                salePackage.setMiner(miner);
                salePackageList.add(salePackage);
            }

        }

        return new ApiResponse<>(200,"查询在售数字货币成功",salePackageList);
    }



    /*********************************提交订单******************************************/

    @RequestMapping(value = "order/submitOrder",method = RequestMethod.POST)
    public ApiResponse submitOrder(@RequestBody OrderInfo order){

        order.setOrder_uuid(Utils.getUUID());
        orderInterface.save(order);
        return new ApiResponse<>(200,"订单提交成功",order.getCoin_uuid());
    }



    /*********************************获取新闻、博客******************************************/
    @RequestMapping(value = "/news/getNews",method = RequestMethod.POST)
    public ApiResponse getNews(@RequestBody News news){

        int news_type = news.getNewsType();

        List<News> news1 =  newsInterface.findByNewsType(news_type);

        return new ApiResponse<>(200,"获取新闻成功",news1);
    }

    /*********************************获取礼券******************************************/
    @RequestMapping(value = "/GiftCertificate",method = RequestMethod.POST)
    public ApiResponse GiftCertificate(@RequestBody HashMap<String,Object> map){
        int pageNum = (int)map.get("pageNum");

        Pageable pageable = new PageRequest(pageNum - 1, 6); // （当前页， 每页记录数， 排序方式）
        Page<GiftCertificate> list = giftCertificateInterface.findAll(pageable);
        return new ApiResponse<>(200,"查询礼券成功",list);
    }





    //测试Token验证功能
    @UserTokenCheck //在需要验证token的方法上添加此注解
    @RequestMapping(value = "/token",method = RequestMethod.GET)
    public ApiResponse<String> checkToken(){
        return new ApiResponse<String>(9999,"OK",null);
    }



    //测试全局的异常处理
    @RequestMapping(value = "/exception",method = RequestMethod.GET)
    public ApiResponse testExceptionHandle() {

        int i = 10/0;

        return new ApiResponse<String>(9999,"OK",null);
    }







}
