package com.yihu.jw.base.endpoint.open.register;

import com.yihu.jw.base.dao.saas.BaseEmailTemplateConfigDao;
import com.yihu.jw.base.service.saas.SaasService;
import com.yihu.jw.base.service.saas.SaasTypeDictService;
import com.yihu.jw.base.service.user.UserService;
import com.yihu.jw.base.util.ErrorCodeUtil;
import com.yihu.jw.entity.base.saas.BaseEmailTemplateConfigDO;
import com.yihu.jw.entity.base.saas.SaasDO;
import com.yihu.jw.entity.base.saas.SaasTypeDictDO;
import com.yihu.jw.exception.code.BaseErrorCode;
import com.yihu.jw.restmodel.base.saas.SaasTypeDictVO;
import com.yihu.jw.restmodel.base.saas.SaasVO;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.ListEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.base.BaseRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author yeshijie on 2018/10/16.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.RegisterSaas.PREFIX)
@Api(value = "注册Saas管理", description = "注册Saas管理服务接口", tags = {"wlyy基础服务 - 注册Saas管理服务接口"})
public class RegisterEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private SaasService saasService;
    @Autowired
    private UserService userService;
    @Autowired
    private ErrorCodeUtil errorCodeUtil;
    @Autowired
    private JavaMailSender jms;
    @Value("${spring.mail.username}")
    private String username;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private SaasTypeDictService saasTypeDictService;
    @Autowired
    private BaseEmailTemplateConfigDao emailTemplateConfigDao;

    /**
     * 验证码redis前缀
     */
    private final String redisPrefix = "verificationCode:";

    @PostMapping(value = BaseRequestMapping.RegisterSaas.REGISTER, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "注册租户")
    public Envelop create (
            @ApiParam(name = "jsonSaas", value = "租户数据", required = true)
            @RequestParam String jsonSaas,
            @ApiParam(name = "captcha", value = "验证码", required = true)
            @RequestParam String captcha) throws Exception {
        SaasDO saasDO = toEntity(jsonSaas, SaasDO.class);

        String redisKey = redisPrefix + saasDO.getEmail();
        String verificationCode = redisTemplate.opsForValue().get(redisKey);
        if(!captcha.equals(verificationCode)){
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.Saas.CAPTCHA_IS_ERROR), Envelop.class);
        }
        if(StringUtils.isBlank(saasDO.getId())){
            //新增
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
        }else {
            //修改
            SaasDO oldSaas = saasService.findById(saasDO.getId());
            if (!oldSaas.getName().equals(saasDO.getName())&&saasService.search("name=" + saasDO.getName()).size() > 0) {
                return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.Saas.NAME_IS_EXIST), Envelop.class);
            }
            if (!oldSaas.getMobile().equals(saasDO.getMobile())&&userService.search("mobile=" + saasDO.getMobile()).size() > 0) {
                return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.Saas.MOBILE_IS_EXIST), Envelop.class);
            }
            if (!oldSaas.getEmail().equals(saasDO.getEmail())&&userService.search("username=" + saasDO.getEmail()).size() > 0) {
                return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.Saas.EMAIL_IS_EXIST), Envelop.class);
            }
            saasService.create(saasDO,oldSaas);
        }
        //注册成功后 吧key删除
        redisTemplate.delete(redisKey);
        return success("注册申请成功");
    }

    @GetMapping(value = BaseRequestMapping.RegisterSaas.SAAS_TYPE_DICT)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<SaasTypeDictVO> list() throws Exception {
        List<SaasTypeDictDO> saasTypeDictDOS = saasTypeDictService.search(null, "status="+SaasTypeDictDO.Status.effective, null);
        return success(saasTypeDictDOS, SaasTypeDictVO.class);
    }

    @PostMapping(value = BaseRequestMapping.RegisterSaas.FIND_BY_ID)
    @ApiOperation(value = "查找（审核不通过的）租户")
    public Envelop findById(
            @ApiParam(name = "id", value = "租户id", required = true)
            @RequestParam String id) throws Exception{
        SaasDO saasDO = saasService.findById(id);
        if(SaasDO.Status.auditNotPassed.equals(saasDO.getStatus())){
            return success(saasDO,SaasVO.class);
        }
        return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.Saas.IS_NOT_AUDITNOTPASSED), Envelop.class);
    }

    @PostMapping(value = BaseRequestMapping.RegisterSaas.NAME_IS_EXIST)
    @ApiOperation(value = "租户名称是否存在")
    public Envelop create (
            @ApiParam(name = "name", value = "租户名称", required = true)
            @RequestParam String name) throws Exception {
        if (saasService.search("name=" + name).size() > 0) {
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.Saas.NAME_IS_EXIST2), Envelop.class);
        }
        return success("查询成功");
    }

    @PostMapping(value = BaseRequestMapping.RegisterSaas.SEND_EMAIL)
    @ApiOperation(value = "邮件发送")
    public Envelop send(@ApiParam(name = "email", value = "邮箱地址", required = true)
                         @RequestParam String email) throws Exception {
        //建立邮件消息
        SimpleMailMessage mainMessage = new SimpleMailMessage();
        //发送者
        mainMessage.setFrom(username);
        //接收者
        mainMessage.setTo(email);
        //发送的标题
        //查找发送模板
        BaseEmailTemplateConfigDO emailTemplateConfigDO = emailTemplateConfigDao.findByTemplateName("租户注册-验证码");
        StringBuilder content = null;
        String captcha = String.valueOf(Math.random()).substring(2, 8);
        if(emailTemplateConfigDO == null){
            //发送的内容
            content =  new StringBuilder("您好！\n感谢您注册健康之路城市i健康。\n");
            content.append("您的验证码是：").append(captcha);
            content.append("。 (验证码10分钟内有效)");
        }else {
            //发送的内容
            content =  new StringBuilder(emailTemplateConfigDO.getFirst());
            content.append("\n").append(emailTemplateConfigDO.getKeyword1()).append("\n")
                .append(emailTemplateConfigDO.getKeyword2()).append(captcha)
                .append(emailTemplateConfigDO.getKeyword3()).append("\n")
                .append(emailTemplateConfigDO.getKeyword5()).append(emailTemplateConfigDO.getUrl())
                .append("\n").append("\n").append(emailTemplateConfigDO.getRemark());
        }
        mainMessage.setSubject("租户注册-验证码");

        mainMessage.setText(content.toString());
        jms.send(mainMessage);
        redisTemplate.opsForValue().set(redisPrefix + email, captcha, 10, TimeUnit.MINUTES);
        return success("发送成功");
    }
}
