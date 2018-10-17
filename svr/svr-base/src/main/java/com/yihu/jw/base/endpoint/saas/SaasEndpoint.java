package com.yihu.jw.base.endpoint.saas;

import com.yihu.jw.base.service.saas.BaseEmailTemplateConfigService;
import com.yihu.jw.base.service.saas.SaasService;
import com.yihu.jw.base.service.saas.SaasTypeDictService;
import com.yihu.jw.base.service.user.UserService;
import com.yihu.jw.base.util.ErrorCodeUtil;
import com.yihu.jw.entity.base.saas.BaseEmailTemplateConfigDO;
import com.yihu.jw.entity.base.saas.SaasDO;
import com.yihu.jw.entity.base.saas.SaasTypeDictDO;
import com.yihu.jw.entity.base.user.UserDO;
import com.yihu.jw.exception.code.BaseErrorCode;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    JavaMailSender jms;
    @Value("${spring.mail.username}")
    private String username;

    @PostMapping(value = BaseRequestMapping.Saas.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建-基本信息")
    public Envelop create(
            @ApiParam(name = "jsonSaas", value = "租户数据", required = true)
            @RequestParam String jsonSaas) throws Exception {
        SaasDO saasDO = toEntity(jsonSaas, SaasDO.class);
        UserDO userDO = new UserDO();
        userDO.setEmail(saasDO.getEmail());
        userDO.setMobile(saasDO.getMobile());
        userDO.setName(saasDO.getManagerName());
        userDO.setUsername(userDO.getEmail());
        if (saasService.search("name=" + saasDO.getName()).size() > 0) {
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.Saas.NAME_IS_EXIST), Envelop.class);
        }
        if (userService.search("mobile=" + userDO.getMobile()).size() > 0) {
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.Saas.MOBILE_IS_EXIST), Envelop.class);
        }
        if (userService.search("username=" + userDO.getEmail()).size() > 0) {
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.Saas.EMAIL_IS_EXIST), Envelop.class);
        }
        saasService.save(saasDO, userDO);
        return success("创建成功");
    }

    @PostMapping(value = BaseRequestMapping.Saas.SYSTEM_CONFIGURATION, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建-系统配置")
    public Envelop createSystemConfig(
            @ApiParam(name = "saasDO", value = "Json数据", required = true)
            @RequestParam(value = "saasDO") SaasDO saasDO) throws Exception {

        saasService.saveSystemConfig(saasDO);
        return success("创建成功");
    }

    @PostMapping(value = BaseRequestMapping.Saas.THEME_STYLE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建-主题风格")
    public Envelop createThemeConfig(
            @ApiParam(name = "saasDO", value = "Json数据", required = true)
            @RequestParam(value = "saasDO") SaasDO saasDO) throws Exception {

        saasService.createThemeConfig(saasDO);
        return success("创建成功");
    }


    @PostMapping(value = BaseRequestMapping.Saas.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
            @RequestParam(value = "ids") String ids) {
        saasService.delete(ids.split(","));
        return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.Saas.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public Envelop update(
            @ApiParam(name = "json", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        SaasDO saasDO = toEntity(jsonData, SaasDO.class);
        if (null == saasDO.getId()) {
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.Common.ID_IS_NULL), Envelop.class);
        }
        saasDO = saasService.save(saasDO);
        return success(saasDO);
    }

    @GetMapping(value = BaseRequestMapping.Saas.PAGE)
    @ApiOperation(value = "获取分页")
    public PageEnvelop<SaasVO> page(
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
        int count = (int) saasService.getCount(filters);
        return success(saasDOS, count, page, size, SaasVO.class);
    }

    @GetMapping(value = BaseRequestMapping.Saas.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<SaasVO> list(
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
        SaasTypeDictDO saasTypeDictDO = saasTypeDictService.findByCode(saasVO.getType());
        saasVO.setTypeName(null == saasTypeDictDO ? "" : saasTypeDictDO.getName());
        return success(saasVO);
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
        saasDO= send(saasDO);
        return success("审核完成",saasDO);
    }

    @GetMapping("/sendEmail")
    @ApiOperation(value = "邮件发送")
    public SaasDO send(SaasDO saasDO) throws Exception {
        SaasDO.Status status = saasDO.getStatus();
        //用户信息初始化
        UserDO userDO = new UserDO();
        userDO.setEmail(saasDO.getEmail());
        userDO.setMobile(saasDO.getMobile());
        userDO.setName(saasDO.getManagerName());
        userDO.setUsername(userDO.getEmail());
         String  password = userDO.getMobile().substring(0, 6);
        BaseEmailTemplateConfigDO baseEmailTemplateConfigDO = baseEmailTemplateConfigService.findByCode(status.toString());
        //建立邮件消息
        SimpleMailMessage mainMessage = new SimpleMailMessage();
        //发送者
        mainMessage.setFrom(username);
        //接收者
        mainMessage.setTo("763558454@qq.com");
//        mainMessage.setTo(saasDO.getEmail());
        //发送的标题
        mainMessage.setSubject(baseEmailTemplateConfigDO.getTemplateName());
        //发送的内容
        StringBuffer content = new StringBuffer();
        content.append(baseEmailTemplateConfigDO.getFirst() + "\n").append(baseEmailTemplateConfigDO.getKeyword1() + "\n");
        content.append(baseEmailTemplateConfigDO.getKeyword2() + "\n");
        if (status.equals(SaasDO.Status.auditPassed)) {
            //账号
            content.append(baseEmailTemplateConfigDO.getKeyword3()+userDO.getMobile() + "\n");
            //密码
            content.append(baseEmailTemplateConfigDO.getKeyword4()+password + "\n");
        } else if (status.equals(SaasDO.Status.auditNotPassed)) {
            //审核未通过的原因
            content.append(saasDO.getAuditFailedReason() + "\n");
        }
        content.append(baseEmailTemplateConfigDO.getKeyword5() + baseEmailTemplateConfigDO.getUrl() + "\n");
        content.append(baseEmailTemplateConfigDO.getRemark());
        mainMessage.setText(content.toString());
        jms.send(mainMessage);
        //发送成功后，初始化租户信息
//        saasDO = saasService.saasAudit(saasDO, userDO);
        return saasDO;
    }

}
