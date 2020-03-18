package com.mengfei.admApijava.controller;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.mengfei.admApijava.annotation.UserTokenCheck;
import com.mengfei.admApijava.model.*;
import com.mengfei.admApijava.model.retruns.PageInfo;
import com.mengfei.admApijava.model.retruns.PageReturn;
import com.mengfei.admApijava.services.interfaces.*;
import com.mengfei.admApijava.model.*;
import com.mengfei.admApijava.model.retruns.RevenueAddressRetrun;
import com.mengfei.admApijava.pojo.ApiResponse;
import com.mengfei.admApijava.services.interfaces.*;
import com.mengfei.admApijava.utils.RedisUtil;
import com.mengfei.admApijava.utils.Utils;
import org.apache.http.util.TextUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
@RequestMapping("/adminApi")
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


    /*********************************增加矿机******************************************/
    @RequestMapping(value = "miner/addMiner",method = RequestMethod.POST)
    public ApiResponse addMiner(@RequestBody Miner miner){
        miner.setMinerUuid(Utils.getUUID());
        minerInterface.save(miner);
        return new ApiResponse<>(200,"增加矿机型号成功",miner);
    }

    /*************************增加在售算力******************************************/
    @RequestMapping(value = "coin/addCoin",method = RequestMethod.POST)
    public ApiResponse addCoin(@RequestBody Coin coin){
        coin.setCoinUuid(Utils.getUUID());
        coinInterface.save(coin);
        return new ApiResponse<>(200,"增加数字货币成功",coin);
    }



    /*************************查询在售算力******************************************/
    @RequestMapping(value = "coin/getSaleCoin",method = RequestMethod.GET)
    public ApiResponse getSaleCoin(){
        //查找数字货币
        List<Coin> coinList = coinInterface.findAll();

        for(Coin coin:coinList){
            //查找数字货币对应的矿机系列
            Miner  miner =  minerInterface.findByMinerUuid(coin.getMiner_uuid());
            if(miner!=null){
                coin.setMiner_nanme(miner.getMinerModel());
            }

        }
        return new ApiResponse<>(200,"查询在售数字货币成功",coinList);
    }

    /*************************查询在售数字货币对应套餐******************************************/
    @RequestMapping(value = "hashRate/getHashRateSale",method = RequestMethod.POST)
    public ApiResponse getHashRateSale(@RequestBody Map<String,Object> map){
        //查找数字货币属所的矿机系列
        String coinUuid = (String)map.get("coinUuid");
        String minerUUids = (String)map.get("minerUUids");
        String[] minerUUid = minerUUids.split(",");

        List<HashRateSale> hashRateSaleList;
        SalePackage salePackage;
        List<SalePackage> salePackageList = new ArrayList<>();
        for(String mineruuid:minerUUid){
            Miner miner = minerInterface.findByMinerUuid(mineruuid);
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

    /*********************************增加在售算力套餐******************************************/
    @RequestMapping(value = "hashRate/addHashRateSale",method = RequestMethod.POST)
    public ApiResponse addHashRateSale(@RequestBody HashRateSale hashRateSale){
        hashRateSale.setHashRate_uuid(Utils.getUUID());
        hashRateSaleInterface.save(hashRateSale);
        return new ApiResponse<>(200,"增加在售算力成功",hashRateSale);
    }

    /*********************************管理员获取订单******************************************/
    @RequestMapping(value = "order/getUserOrder",method = RequestMethod.POST)
    public ApiResponse getUserOrder(@RequestBody HashMap<String,Object> map){
        int pageNum = (int)map.get("pageNum");
        int pageSize = 10;
        if(map.size()>1){
             pageSize = (int)map.get("pageSize");
        }

        // 排序方式，这里是以“recordNo”为标准进行降序
//        Sort sort = new Sort(Sort.Direction.DESC, "recordNo");  // 这里的"recordNo"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段

        Pageable pageable = new PageRequest(pageNum - 1, pageSize); // （当前页， 每页记录数， 排序方式）
        Page<OrderInfo> page = orderInterface.findAll(pageable);
        List<OrderInfo> orderInfos = page.getContent();

        PageInfo pageInfo = new PageInfo();

        pageInfo.setFristPage(page.isFirst());

        pageInfo.setLastPagee(page.isLast());

        pageInfo.setTotalPage(page.getTotalPages());

        pageInfo.setSize(page.getTotalElements());

        PageReturn pageReturn =new PageReturn();

        pageReturn.setContent(orderInfos);

        pageReturn.setPageInfo(pageInfo);
//       List<OrderInfo> orderInfos =  orderInterface.findAll();

        return new ApiResponse<>(200,"查询订单成功",pageReturn);
    }



    /*********************************获取新闻、博客******************************************/
    @RequestMapping(value = "/news/getNews",method = RequestMethod.POST)
    public ApiResponse getNews(@RequestBody News news){

        int news_type = news.getNewsType();

        List<News> news1 =  newsInterface.findByNewsType(news_type);

        return new ApiResponse<>(200,"获取新闻成功",news1);
    }

    /*********************************添加新闻、博客******************************************/
    @RequestMapping(value = "/news/addNews",method = RequestMethod.POST)
    public ApiResponse addNews(@RequestBody News news){

        return new ApiResponse<>(200,"获取新闻成功",newsInterface.save(news));

    }




    /*********************************获取礼券******************************************/
    @RequestMapping(value = "/GiftCertificate",method = RequestMethod.POST)
    public ApiResponse GiftCertificate(@RequestBody HashMap<String,Object> map){
        int pageNum = (int)map.get("pageNum");

        Pageable pageable = new PageRequest(pageNum - 1, 6); // （当前页， 每页记录数， 排序方式）
        Page<GiftCertificate> list = giftCertificateInterface.findAll(pageable);
        return new ApiResponse<>(200,"查询礼券成功",list);
    }

    /*********************************删除礼券******************************************/
    @RequestMapping(value = "/deletGiftCertificate",method = RequestMethod.POST)
    public ApiResponse deletGiftCertificate(@RequestBody HashMap<String,Object> map){
        String giftUuid = (String)map.get("giftUuid");
        GiftCertificate giftCertificate = giftCertificateInterface.findByGiftUuid(giftUuid);
        giftCertificateInterface.delete(giftCertificate);
        return new ApiResponse<>(200,"删除礼券成功",null);
    }

    //测试Token验证功能
    @UserTokenCheck //在需要验证token的方法上添加此注解
    @RequestMapping(value = "/token",method = RequestMethod.GET)
    public ApiResponse<String> checkToken(){
        return new ApiResponse<String>(9999,"OK",null);
    }
}
