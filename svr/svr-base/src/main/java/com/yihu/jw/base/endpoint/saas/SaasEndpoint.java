package com.yihu.jw.base.endpoint.saas;

import com.yihu.jw.base.contant.CommonContant;
import com.yihu.jw.base.service.module.SaasModuleService;
import com.yihu.jw.base.service.saas.BaseEmailTemplateConfigService;
import com.yihu.jw.base.service.saas.SaasService;
import com.yihu.jw.base.service.saas.SaasTypeDictService;
import com.yihu.jw.base.service.user.UserService;
import com.yihu.jw.base.util.ErrorCodeUtil;
import com.yihu.jw.base.util.ValidateUtil;
import com.yihu.jw.entity.base.module.ModuleDO;
import com.yihu.jw.entity.base.module.SaasModuleDO;
import com.yihu.jw.entity.base.saas.BaseEmailTemplateConfigDO;
import com.yihu.jw.entity.base.saas.SaasDO;
import com.yihu.jw.entity.base.saas.SaasTypeDictDO;
import com.yihu.jw.entity.base.user.UserDO;
import com.yihu.jw.exception.code.BaseErrorCode;
import com.yihu.jw.restmodel.base.module.SaasModuleVO;
import com.yihu.jw.restmodel.base.saas.SaasVO;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.ListEnvelop;
import com.yihu.jw.restmodel.web.ObjEnvelop;
import com.yihu.jw.restmodel.web.PageEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.base.BaseRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Endpoint - SAAS
 * Created by progr1mmer on 2018/8/14.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.Saas.PREFIX)
@Api(value = "Saas管理", description = "Saas管理服务接口", tags = {"wlyy基础服务 - Saas管理服务接口"})
public class SaasEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private SaasService saasService;
    @Autowired
    private UserService userService;
    @Autowired
    private SaasTypeDictService saasTypeDictService;
    @Autowired
    private ErrorCodeUtil errorCodeUtil;
    @Autowired
    private BaseEmailTemplateConfigService baseEmailTemplateConfigService;
    @Autowired
    private SaasModuleService saasModuleService;
    @Autowired
    JavaMailSender jms;
    @Value("${spring.mail.username}")
    private String username;

    @PostMapping(value = BaseRequestMapping.Saas.CREATE)
    @ApiOperation(value = "创建-基本信息")
    public Envelop create (
            @ApiParam(name = "jsonSaas", value = "租户数据", required = true)
            @RequestParam String jsonSaas) throws Exception {
        SaasDO saasDO = toEntity(jsonSaas, SaasDO.class);
        if(!ValidateUtil.isValidMobileNo(saasDO.getMobile())){
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.Saas.MOBILE_IS_EXIST), Envelop.class);
        }
        if(!ValidateUtil.isValidEmail(saasDO.getEmail())){
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.Saas.EMAIL_IS_EXIST), Envelop.class);
        }
        if (saasService.search("name=" + saasDO.getName()).size() > 0) {
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.Saas.NAME_IS_EXIST), Envelop.class);
        }
        if (userService.search("mobile=" + saasDO.getMobile()).size() > 0) {
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.Saas.MOBILE_IS_EXIST), Envelop.class);
        }
        if (userService.search("username=" + saasDO.getEmail()).size() > 0) {
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.Saas.EMAIL_IS_EXIST), Envelop.class);
        }
        saasService.create(saasDO);

        return success("创建成功",saasDO);
    }

    @PostMapping(value = BaseRequestMapping.Saas.SYSTEM_CONFIGURATION)
    @ApiOperation(value = "创建-系统配置")
    public Envelop createSystemConfig (
            @ApiParam(name = "jsonSaas", value = "租户数据", required = true)
            @RequestParam String jsonSaas) throws Exception {
        SaasDO saasDO = toEntity(jsonSaas, SaasDO.class);
        saasService.saveSystemConfig(saasDO);
        return success("创建成功",saasDO);
    }

    @PostMapping(value = BaseRequestMapping.Saas.THEME_STYLE)
    @ApiOperation(value = "创建-主题风格")
    public Envelop createThemeConfig (
            @ApiParam(name = "jsonSaas", value = "租户数据", required = true)
            @RequestParam String jsonSaas) throws Exception {
        SaasDO saasDO = toEntity(jsonSaas, SaasDO.class);
        saasService.createThemeConfig(saasDO);
        return success("创建成功");
    }

    @PostMapping(value = BaseRequestMapping.Saas.RESET_SECRET)
    @ApiOperation(value = "重置密钥")
    public Envelop resetSecret(
            @ApiParam(name = "id", value = "saasId", required = true)
            @RequestParam(value = "id") String id) {
        SaasDO oldSaas = saasService.findById(id);
        oldSaas.setAppSecret(UUID.randomUUID().toString().replaceAll("-", ""));
        oldSaas.setAppId(UUID.randomUUID().toString().replaceAll("-", ""));
        saasService.save(oldSaas);
        return success("重置成功",oldSaas);
    }

    @PostMapping(value = BaseRequestMapping.Saas.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
            @RequestParam(value = "ids") String ids) {
        saasService.delete(ids.split(","));
        return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.Saas.STATUS)
    @ApiOperation(value = "修改状态")
    public Envelop status(
            @ApiParam(name = "id", value = "saas类型Json数据")
            @RequestParam(value = "id", required = true) String id,
            @ApiParam(name = "status", value = "status")
            @RequestParam(value = "status", required = true) SaasDO.Status status) throws Exception {
        saasService.updateStatus(id, status);
        return success("修改成功");
    }

    @PostMapping(value = BaseRequestMapping.Saas.UPDATE)
    @ApiOperation(value = "更新")
    public Envelop update (
            @ApiParam(name = "jsonData", value = "Json数据", required = true)
            @RequestParam String jsonData) throws Exception {
        SaasDO saasDO = toEntity(jsonData, SaasDO.class);
        if (null == saasDO.getId()) {
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.Common.ID_IS_NULL), Envelop.class);
        }

        SaasDO oldSaas = saasService.findById(saasDO.getId());
        UserDO userDO = userService.findById(oldSaas.getManager());

        if (!oldSaas.getName().equals(saasDO.getName())&&saasService.search("name=" + saasDO.getName()).size() > 0) {
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.Saas.NAME_IS_EXIST), Envelop.class);
        }
        if (!userDO.getMobile().equals(saasDO.getMobile())&&userService.search("mobile=" + saasDO.getMobile()).size() > 0) {
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.Saas.MOBILE_IS_EXIST), Envelop.class);
        }
        if (!userDO.getEmail().equals(saasDO.getEmail())&&userService.search("username=" + saasDO.getEmail()).size() > 0) {
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.Saas.EMAIL_IS_EXIST), Envelop.class);
        }
        saasService.updateSaas(saasDO,oldSaas,userDO);
        return success("修改成功");
    }

    @GetMapping(value = BaseRequestMapping.Saas.PAGE)
    @ApiOperation(value = "获取分页")
    public PageEnvelop<SaasVO> page (
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "page", value = "分页大小", required = true, defaultValue = "1")
            @RequestParam(value = "page") int page,
            @ApiParam(name = "size", value = "页码", required = true, defaultValue = "15")
            @RequestParam(value = "size") int size) throws Exception {
        List<SaasDO> saasDOS = saasService.search(fields, filters, sorts, page, size);
        saasDOS.forEach(saas->{
            SaasTypeDictDO saasTypeDictDO = saasTypeDictService.findById(saas.getType());
            saas.setTypeName(saasTypeDictDO.getName());
        });
        int count = (int)saasService.getCount(filters);
        return success(saasDOS, count, page, size, SaasVO.class);
    }

    @GetMapping(value = BaseRequestMapping.Saas.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<SaasVO> list (
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<SaasDO> saasDOS = saasService.search(fields, filters, sorts);
        return success(saasDOS, SaasVO.class);
    }

    @PostMapping(value = BaseRequestMapping.Saas.FINDBYID)
    @ApiOperation(value = "租户审核：根据租户id获取租户信息")
    public ObjEnvelop<SaasVO> audit(
            @ApiParam(name = "id", value = "SaasId", required = true)
            @RequestParam(value = "id") String id) throws Exception {
        SaasDO saasDO = saasService.retrieve(id);
        if (null == saasDO) {
            return failed("无相关SAAS配置", ObjEnvelop.class);
        }
        SaasVO saasVO = convertToModel(saasDO, SaasVO.class);
        //根据租户类型编码，获取租户类型名称
        SaasTypeDictDO saasTypeDictDO = saasTypeDictService.findById(saasVO.getType());
        saasVO.setTypeName(null == saasTypeDictDO ? "" : saasTypeDictDO.getName());
        return success(saasVO);
    }

    @GetMapping(value = BaseRequestMapping.Saas.FIND_MODULE_BY_SAASID)
    @ApiOperation(value = "获取租户的模块列表")
    public ListEnvelop<SaasModuleVO> findModuleBySaasId (
            @ApiParam(name = "saasId", value = "saasId")
            @RequestParam(value = "saasId", required = true) String saasId) throws Exception {
        String filters = "status="+ ModuleDO.Status.available.getValue()+";";
        if(StringUtils.isNotBlank(saasId)){
            filters = "saasId="+saasId;
        }
        List<SaasModuleDO> modules = saasModuleService.search(null, filters, null);
        List<SaasModuleVO> moduleVOs = convertToModels(modules,new ArrayList<>(modules.size()),SaasModuleVO.class);
        Map<String,List<SaasModuleVO>> map = moduleVOs.stream().collect(Collectors.groupingBy(SaasModuleVO::getParentModuleId));
        moduleVOs.forEach(module->{
            List<SaasModuleVO> tmp = map.get(module.getId());
            module.setChildren(tmp);
        });
        moduleVOs = moduleVOs.stream()
                .filter(module -> CommonContant.DEFAULT_PARENTID.equals(module.getParentModuleId()))
                .collect(Collectors.toList());

        return success(moduleVOs);
    }

    @PostMapping(value = BaseRequestMapping.Saas.AUDIT)
    @ApiOperation(value = "审核")
    public ObjEnvelop<SaasDO> audit(
            @ApiParam(name = "id", value = "SaasId", required = true)
            @RequestParam(value = "id") String id,
            @ApiParam(name = "status", value = "状态", required = true)
            @RequestParam(value = "status") SaasDO.Status status,
            @ApiParam(name = "auditFailedReason", value = "审核不通过的原因（非必填）")
            @RequestParam(value = "auditFailedReason", required = false) String auditFailedReason) throws Exception {
        SaasDO saasDO = saasService.retrieve(id);
        if (null == saasDO) {
            return failed("无相关SAAS配置", ObjEnvelop.class);
        }
        saasDO.setStatus(status);
        saasDO.setAuditFailedReason(auditFailedReason);
        //用户信息初始化
        UserDO userDO = new UserDO();
        userDO.setEmail(saasDO.getEmail());
        userDO.setMobile(saasDO.getMobile());
        userDO.setName(saasDO.getManagerName());
        userDO.setUsername(userDO.getEmail());
        //初始化租户信息
        saasService.save(saasDO);
        saasDO = saasService.saasAudit(saasDO, userDO);
        return send(saasDO);
    }

    @GetMapping("/sendEmail")
    @ApiOperation(value = "邮件发送")
    public ObjEnvelop<SaasDO> send(SaasDO saasDO) {
        try {
            SaasDO.Status status = saasDO.getStatus();
            String password = saasDO.getMobile().substring(0, 6);
            BaseEmailTemplateConfigDO baseEmailTemplateConfigDO = baseEmailTemplateConfigService.findByCode(status.name());
            if (null == baseEmailTemplateConfigDO) {
                failed(status.name() + "邮件模板不存在！");
            }
            //建立邮件消息
            SimpleMailMessage mainMessage = new SimpleMailMessage();
            //发送者
            mainMessage.setFrom(username);
            //接收者
            mainMessage.setTo(saasDO.getEmail());
            //发送的标题
            mainMessage.setSubject(baseEmailTemplateConfigDO.getTemplateName());
            //发送的内容
            StringBuffer content = new StringBuffer();
            content.append(baseEmailTemplateConfigDO.getFirst() + "\n").append(baseEmailTemplateConfigDO.getKeyword1() + "\n");
            content.append(baseEmailTemplateConfigDO.getKeyword2() + "\n");
            if (status.equals(SaasDO.Status.auditPassed)) {
                //账号
                content.append(baseEmailTemplateConfigDO.getKeyword3() + saasDO.getMobile() + "\n");
                //密码
                content.append(baseEmailTemplateConfigDO.getKeyword4() + password + "\n");
            } else if (status.equals(SaasDO.Status.auditNotPassed)) {
                //审核未通过的原因
                content.append(saasDO.getAuditFailedReason() + "\n");
            }
            content.append(baseEmailTemplateConfigDO.getKeyword5() + baseEmailTemplateConfigDO.getUrl() + "\n");
            content.append(baseEmailTemplateConfigDO.getRemark());
            mainMessage.setText(content.toString());
            jms.send(mainMessage);
        } catch (MailException e) {
            e.printStackTrace();
            return failed("邮件发送失败！"+e.getMessage(),ObjEnvelop.class);
        }
        return success("审核完成", saasDO);
    }

}
