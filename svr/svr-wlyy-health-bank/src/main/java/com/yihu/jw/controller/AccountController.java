package com.yihu.jw.controller;/**
 * Created by nature of king on 2018/5/10.
 */

import com.yihu.jw.entity.health.bank.AccountDO;
import com.yihu.jw.entity.health.bank.CreditsDetailDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.rm.health.bank.HealthBankMapping;
import com.yihu.jw.service.AccountService;
import com.yihu.jw.service.CreditsDetailService;
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
 * @create 2018-05-10 11:44
 * @desc account Controller
 **/

@RestController
@RequestMapping(HealthBankMapping.api_health_bank_common)
@Api(tags = "银行账户相关操作",description = "银行账户相关操作")
public class AccountController extends EnvelopRestController {

    @Autowired
    private AccountService service;
    @Autowired
    private CreditsDetailService creditsDetailService;
    @Autowired
    private Tracer tracer;


    /**
     * 添加账户
     * @param account 实体类account
     *
     * @return
     */
    @PostMapping(value = HealthBankMapping.healthBank.createAccount)
    @ApiOperation(value = "添加账户")
    public Envelop<Boolean> insert(@ApiParam(name = "account",value = "账户JSON")
                                   @RequestParam(value = "account",required = true)String account){
        try {
            AccountDO accountDO = toEntity(account,AccountDO.class);
            return service.insert(accountDO);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }


    /**
     * 获取账户
     *
     * @param creditsDetail 积分对象
     * @return
     */
    @PostMapping(value = HealthBankMapping.healthBank.selectAccount)
    @ApiOperation(value = "获取账户信息")
    public Envelop<AccountDO> selectByAccount(@ApiParam(name = "creditsDetail",value = "积分JSON")
                                   @RequestParam(value = "creditsDetail",required = false)String creditsDetail){
        try {
            CreditsDetailDO creditsDetailDO = toEntity(creditsDetail,CreditsDetailDO.class);
            return creditsDetailService.findByTradeDirection(creditsDetailDO);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }

    /**
     *  获取银行账户
     *
     * @param account 银行账户对象
     * @param page 页码
     * @param size 分页大小
     * @return
     */
    @PostMapping(value = HealthBankMapping.healthBank.findAccount)
    @ApiOperation(value = "获取银行账户信息")
    public Envelop<AccountDO> select(@ApiParam(name = "account",value = "账户JSON")
                                     @RequestParam(value = "account",required = false)String account,
                                     @ApiParam(name = "page", value = "第几页，从1开始")
                                     @RequestParam(value = "page", defaultValue = "1",required = false)Integer page,
                                     @ApiParam(name = "size",defaultValue = "10",value = "，每页分页大小")
                                     @RequestParam(value = "size", required = false)Integer size){
        try{
            AccountDO accountDO = toEntity(account,AccountDO.class);
            return service.findByCondition(accountDO,page,size);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }


    /**
     * 查看银行账户信息
     *
     * @param account 账户信息对象
     * @param page 页码
     * @param size 每页大小
     * @return
     */
/*    @PostMapping(value = HealthBankMapping.healthBank.findAccount)
    @ApiOperation(value = "查看账户信息")
    public Envelop<AccountDO> getAccount(@ApiParam(name = "account",value = "账户JSON")
                                           @RequestParam(value = "account",required = false)String account,
                                           @ApiParam(name = "page", value = "第几页，从1开始")
                                           @RequestParam(value = "page", defaultValue = "1",required = false)Integer page,
                                           @ApiParam(name = "size",defaultValue = "10",value = "，每页分页大小")
                                           @RequestParam(value = "size", required = false)Integer size){
        try{
            AccountDO accountDO = toEntity(account,AccountDO.class);
            return service.findByCondition(accountDO,page,size);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }*/

}
