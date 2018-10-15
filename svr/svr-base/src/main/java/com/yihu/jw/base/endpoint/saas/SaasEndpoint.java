package com.yihu.jw.base.endpoint.saas;

import com.yihu.jw.base.service.saas.SaasTypeDictService;
import com.yihu.jw.base.service.saas.SaasService;
import com.yihu.jw.base.service.user.UserService;
import com.yihu.jw.base.util.ErrorCodeUtil;
import com.yihu.jw.entity.base.saas.SaasDO;
import com.yihu.jw.base.service.saas.SaasService;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    JavaMailSender jms;
    @Value("${spring.mail.username}")
    private String username;

    @PostMapping(value = BaseRequestMapping.Saas.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建")
    public Envelop create(
            @ApiParam(name = "saasDO", value = "Json数据", required = true)
            @RequestParam(value = "saasDO") SaasDO saasDO,
            @ApiParam(name = "userDO", value = "Json数据", required = true)
            @RequestParam(value = "userDO") UserDO userDO) throws Exception {
        if (saasService.search("name=" + userDO.getName()).size() > 0) {
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.Saas.NAME_IS_EXIST), Envelop.class);
        }
        if (userService.search("mobile=" + userDO.getMobile()).size() > 0) {
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.Saas.MOBILE_IS_EXIST), Envelop.class);
        }
        if (userService.search("username=" + userDO.getEmail()).size() > 0) {
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.Saas.EMAIL_IS_EXIST), Envelop.class);
        }
        userDO.setUsername(userDO.getEmail());
        saasService.save(saasDO, userDO);
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
    public Envelop audit(
            @ApiParam(name = "id", value = "SaasId", required = true)
            @RequestParam(value = "id") String id,
            @ApiParam(name = "status", value = "状态", required = true)
            @RequestParam(value = "status") SaasDO.Status status,
            @ApiParam(name = "auditFailedReason", value = "审核不通过的原因（非必填）")
            @RequestParam(value = "auditFailedReason", required = false) String auditFailedReason, HttpServletRequest request) throws Exception {
        SaasDO saasDO = saasService.retrieve(id);

        if (null == saasDO) {
            return failed("无相关SAAS配置", Envelop.class);
        }
        saasDO.setStatus(status);
        saasDO.setAuditFailedReason(auditFailedReason);
        saasService.save(saasDO);
        return success("操作成功");
    }

    @GetMapping("/sendEmail")
    @ApiOperation(value = "邮件发送")
    public void send() throws Exception {
        //建立邮件消息
        SimpleMailMessage mainMessage = new SimpleMailMessage();
        //发送者
        mainMessage.setFrom(username);
        //接收者
        mainMessage.setTo("763558454@qq.com");
        //发送的标题
        mainMessage.setSubject("租户审核");
        //发送的内容
        String content =  "您好！\n"+ "感谢您注册健康之路城市i健康。\n";
        if (true) {
            content = content + "您提交的是租户注册信息已审核通过，登录账号、密码如下所示：\n";
            content = content + "账号：test"+"\n";
            content = content + "密码：123456"+"\n";
            content = content + "点击以下链接进入健康之路城市i健康综合管理平台："+ "http://www.baidu.com \n";
            content = content + "如果以上链接无法点击，请将上面的地址复制到你的浏览器(如IE)的地址栏进入健康之路城市i健康综合管理平台\n";
        }else {
            content = content + "您提交的是租户注册信息审核未通过，审核未通过原因如下：\n";
            content = content + "营业执照图片模糊，不清晰。\n";
            content = content + "请点击以下链接修改注册信息并重新提交审核："+ "http://www.baidu.com \n";
            content = content + "如果以上链接无法点击，请将上面的地址复制到你的浏览器(如IE)的地址栏进入租户注册信息修改。\n";
        }
        mainMessage.setText(content);
        jms.send(mainMessage);
    }

}
