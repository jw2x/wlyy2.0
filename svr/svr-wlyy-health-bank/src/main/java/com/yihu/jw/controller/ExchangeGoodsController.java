package com.yihu.jw.controller;/**
 * Created by nature of king on 2018/5/3.
 */

import com.yihu.jw.entity.health.bank.ExchangeGoodsDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.rm.health.bank.HealthBankMapping;
import com.yihu.jw.service.ExchangeGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangzhinan
 * @create 2018-05-03 16:17
 * @desc exchange goods Controller
 **/
@RestController
@RequestMapping(HealthBankMapping.api_health_bank_common)
@Api(tags = "商品兑换相关操作",description = "商品兑换相关操作")
public class ExchangeGoodsController extends EnvelopRestController {

    @Autowired
    private ExchangeGoodsService service;
    @Autowired
    private Tracer tracer;


    /**
     * find exchange goods
     *
     * @param exchangeGoods
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = HealthBankMapping.healthBank.findExchangeGoods)
    @ApiOperation(value = "查看兑换商品信息")
    public Envelop<ExchangeGoodsDO> getExchangeGoods(@ApiParam(name = "exchangeGoods",value = "兑换商品JSON")
                                           @RequestParam(value = "exchangeGoods",required = false)String exchangeGoods,
                                           @ApiParam(name = "page", value = "第几页，从1开始")
                                           @RequestParam(value = "page", defaultValue = "1",required = false)Integer page,
                                           @ApiParam(name = "size",defaultValue = "10",value = "，每页分页大小")
                                           @RequestParam(value = "size", required = false)Integer size){
        try{
            ExchangeGoodsDO exchangeGoodsDO = toEntity(exchangeGoods,ExchangeGoodsDO.class);
            return service.findByCondition(exchangeGoodsDO,page,size);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }

   /* *//**
     * exchange goods
     *
     * @param exchangeGoods
     * @return
     *//*
    @PostMapping(value = HealthBankMapping.healthBank.exchangeGoods)
    @ApiOperation(value = "兑换商品")
    public Envelop<Boolean> exchangeGoods(@ApiParam(name = "exchangeGoods",value = "兑换商品JSON")
                                          @RequestParam(value = "exchangeGoods")String exchangeGoods){
        try {
            ExchangeGoodsDO exchangeGoodsDO =toEntity(exchangeGoods,ExchangeGoodsDO.class);
            return service.insert(exchangeGoodsDO);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }*/
}
