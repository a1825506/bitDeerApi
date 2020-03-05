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
import com.mengfei.fbsepjava.exception.TokenException;
import com.mengfei.fbsepjava.model.*;
import com.mengfei.fbsepjava.pojo.ApiResponse;
import com.mengfei.fbsepjava.services.interfaces.*;
import com.mengfei.fbsepjava.utils.RedisUtil;
import com.mengfei.fbsepjava.utils.Utils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
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

    @Email(message = "邮箱格式错误")
    private  String email;


    //测试全局的异常处理
    @RequestMapping(value = "/exception",method = RequestMethod.GET)
    public ApiResponse testExceptionHandle() {

        int i = 10/0;

        return new ApiResponse<String>(9999,"OK",null);
    }

    /*************************用户******************************************/

    //获取验证码
    @RequestMapping(value = "user/getCode",method = RequestMethod.POST)
    public ApiResponse getCode(@RequestBody @Validated Map<String,Object> map){
        int type = (int)map.get("type");
        if(type==0){//手机注册
            String phoneNum = (String) map.get("phoneNumber");
            if(Utils.testPhone(phoneNum)){
                DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "<accessKeyId>", "<accessSecret>");
                IAcsClient client = new DefaultAcsClient(profile);
                CommonRequest request = new CommonRequest();
                request.setMethod(MethodType.POST);
                request.setDomain("dysmsapi.aliyuncs.com");
                request.setVersion("2017-05-25");
                request.setAction("SendSms");
                request.putQueryParameter("PhoneNumbers", (String) map.get("phoneNumber"));
                request.putQueryParameter("SignName", "aaa");
                request.putQueryParameter("TemplateCode", "aaaa");
                try {
                    CommonResponse response = client.getCommonResponse(request);
                    return new ApiResponse<>(200,"获取验证码成功","1111");
                } catch (ServerException e) {
                    e.printStackTrace();
                    return new ApiResponse<>(200,"获取验证码失败","");
                } catch (ClientException e) {
                    e.printStackTrace();
                    return new ApiResponse<>(200,"获取验证码失败","");
                }
            }else {
                return new ApiResponse<>(201, "手机号码有误", "");
            }

        }else {//邮箱注册

            email = (String) map.get("email");

            emailService.sendEmailVerCode(email, Utils.generateVerCode());
            //验证码保存到redis
            redisUtil.set(email, Utils.generateVerCode());
            redisUtil.expire(email, 60 * 5);//五分钟失效
            return new ApiResponse<>(200, "获取验证码成功，青岛市邮箱查看", "");


        }



    }


    //用户注册
    @RequestMapping(value = "user/register",method = RequestMethod.POST)
    public ApiResponse register(@RequestBody Map<String,Object> map){
        User user = new User();
        user.setUser_uuid(Utils.getUUID());
        int type = (int)map.get("type");
        @Email
        String email =  (String) map.get("email");

        String code = (String)map.get("code");

        @NotNull
        String passWord = (String)map.get("passWord");

        //验证code
        if(redisUtil.get("email").equals(code)){

            return new ApiResponse<>(201,"验证码不正确","");

        }
        if(type==0){//手机注册
            user.setEmail(email);
            user.setPassWord(passWord);
        }else if(type==1){//邮箱注册
            user.setEmail(email);
            user.setPassWord(passWord);
        }

        userInterface.save(user);

        return new ApiResponse<>(200,"注册成功",user);

    }


    /************************************
     *
     * {
     *   "K" : "NAVHT4PXWT8WQBL5",
     *   "P" : "Mac 10.14",
     *   "DI" : "NjMwYWU2OGZhMzMyZWNi"
     * }
     *
     *
     * ***********************************/




    /*************************在售数字货币******************************************/
    //增加在售货币
    @RequestMapping(value = "coin/addCoin",method = RequestMethod.POST)
    public ApiResponse addCoin(@RequestBody Coin coin){
        coin.setCoin_uuid(Utils.getUUID());
        coinInterface.save(coin);
        return new ApiResponse<>(200,"增加数字货币成功",coin);
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
    /***********************************************************************************/



    /*************************在售数字货币对应套餐******************************************/
    @RequestMapping(value = "hashRate/getHashRateSale",method = RequestMethod.POST)
    public ApiResponse getHashRateSale(@RequestBody Map<String,String> map){
        //查找数字货币属所的矿机系列
        @NotNull
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
    /*************************8888888888888******************************************/






    /*********************************提交订单******************************************/

    @RequestMapping(value = "order/submitOrder",method = RequestMethod.POST)
    public ApiResponse submitOrder(@RequestBody OrderInfo order){

        order.setOrder_uuid(Utils.getUUID());
        orderInterface.save(order);
        return new ApiResponse<>(200,"订单提交成功",order);

    }

    /***********************************************************************************/



    @RequestMapping(value = "/addHashRateSale",method = RequestMethod.POST)
    public ApiResponse addHashRateSale(@RequestBody HashRateSale hashRateSale){
        hashRateSale.setHashRate_uuid(Utils.getUUID());
        hashRateSaleInterface.save(hashRateSale);
        return new ApiResponse<>(200,"增加在售算力成功",hashRateSale);
    }



    //增加矿机型号
    @RequestMapping(value = "/addMiner",method = RequestMethod.POST)
    public ApiResponse addMiner(@RequestBody Miner miner){
        miner.setMinerUuid(Utils.getUUID());
        minerInterface.save(miner);
        return new ApiResponse<>(200,"增加矿机型号成功",miner);
    }

    //测试Token验证功能
    @UserTokenCheck //在需要验证token的方法上添加此注解
    @RequestMapping(value = "/token",method = RequestMethod.GET)
    public ApiResponse<String> checkToken(){
        return new ApiResponse<String>(9999,"OK",null);
    }




}
