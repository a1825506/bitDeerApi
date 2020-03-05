//package com.mengfei.fbsepjava.controller;
//
//import com.mengfei.fbsepjava.annotation.UserTokenCheck;
//import com.mengfei.fbsepjava.model.Coin;
//import com.mengfei.fbsepjava.model.HashRateSale;
//import com.mengfei.fbsepjava.model.Miner;
//import com.mengfei.fbsepjava.model.UserInfo;
//import com.mengfei.fbsepjava.pojo.ApiResponse;
//import com.mengfei.fbsepjava.services.interfaces.CoinInterface;
//import com.mengfei.fbsepjava.services.interfaces.HashRateSaleInterface;
//import com.mengfei.fbsepjava.services.interfaces.MinerInterface;
//import com.mengfei.fbsepjava.services.interfaces.UesrInterface;
//import com.mengfei.fbsepjava.utils.Utils;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//import java.util.List;
//
//
//@CrossOrigin
//@RestController
//@RequestMapping("/api/coin")
//@Api(value = "测试swagger2", description = "数字货币相关")
//public class ApiCoinController {
//
//    @Resource
//    private CoinInterface coinInterface;
//    @Resource
//    private MinerInterface minerInterface;
//
//
//
//    @ApiOperation(value = "增加在售数字货币种类" )
//    @RequestMapping(value = "/addCoin",method = RequestMethod.POST)
//    public ApiResponse addCoin(@RequestBody Coin coin){
//        coin.setCoin_uuid(Utils.getUUID());
//        coinInterface.save(coin);
//        return new ApiResponse<>(200,"增加数字货币成功",coin);
//    }
//
//    @ApiOperation(value = "首页-查询在售数字货币种类" )
//    @RequestMapping(value = "/getSaleCoin",method = RequestMethod.GET)
//    public ApiResponse getSaleCoin(){
//        Miner miner = null;
//        List<Coin> coinList = coinInterface.findAll();
//        for(Coin coin:coinList){
//             miner =  minerInterface.findByMinerUuid(coin.getMiner_uuid());
//             if(miner!=null){
//                 coin.setMiner_nanme(miner.getMiner_model());
//                 System.out.println("miner.getMiner_model()");
//             }
//
//        }
//        return new ApiResponse<>(200,"查询在售数字货币成功",coinList);
//    }
//
//
//
//
//}
