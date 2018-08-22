package com.yihu.jw.controller;/**
 * Created by nature of king on 2018/4/27.
 */

import com.yihu.jw.entity.health.bank.GoodsDO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.health.bank.HealthBankMapping;
import com.yihu.jw.service.GoodsService;
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
 * @create 2018-04-27 16:42
 * @desc goods service
 **/
@RestController
@RequestMapping(HealthBankMapping.api_health_bank_common)
@Api(tags = "健康服务商品相关操作",description = "健康服务商品相关操作")
public class GoodsController extends EnvelopRestEndpoint {
    @Autowired
    private GoodsService service;
    @Autowired
    private Tracer tracer;
    /**
     * patient publish  gooods
     *
     * @param goods 商品对象
     * @return
     */
    @PostMapping(value = HealthBankMapping.healthBank.createGoods)
    @ApiOperation(value = "上架服务商品")
    public MixEnvelop<Boolean, Boolean> publishGoods(@ApiParam(name = "goods",value = "健康服务商品JSON")
                                           @RequestParam(value = "goods",required = true)String goods){
        try {
            GoodsDO goodsDO = toEntity(goods,GoodsDO.class);
            service.insert(goodsDO);
            return service.insert(goodsDO);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    /**
     * find health goods
     *
     * @param goods 商品对象
     * @param page 页码
     * @param size 每页大小
     * @return
     */
    @PostMapping(value = HealthBankMapping.healthBank.findGoods)
    @ApiOperation(value = "查看健康服务商品")
    public MixEnvelop<GoodsDO, GoodsDO> getActivityInfo(@ApiParam(name = "goods",value = "健康服务商品JSON")
                                                   @RequestParam(value = "goods",required = false)String goods,
                                               @ApiParam(name = "page", value = "第几页，从1开始")
                                                   @RequestParam(value = "page", defaultValue = "1",required = false)Integer page,
                                               @ApiParam(name = "size",defaultValue = "10",value = "，每页分页大小")
                                                   @RequestParam(value = "size", required = false)Integer size){
        try{
            GoodsDO goodsDO = toEntity(goods,GoodsDO.class);
            return service.findByCondition(goodsDO,page,size);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    /**
     * patient update goods status
     *
     * @param goods 商品对象
     * @return
     */
    @PostMapping(value = HealthBankMapping.healthBank.updateGoods)
    @ApiOperation(value = "更新健康服务商品")
    public MixEnvelop<Boolean, Boolean> updateActivity(@ApiParam(name = "goods",value = "健康服务商品JSON")
                                           @RequestParam(value = "goods",required = true)String goods){
        try {
            GoodsDO goodsDO = toEntity(goods,GoodsDO.class);
            return service.update(goodsDO);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }
}
