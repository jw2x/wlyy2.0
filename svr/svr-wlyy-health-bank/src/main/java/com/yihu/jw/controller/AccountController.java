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
     * @param account
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
     * @param creditsDetail
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
}
