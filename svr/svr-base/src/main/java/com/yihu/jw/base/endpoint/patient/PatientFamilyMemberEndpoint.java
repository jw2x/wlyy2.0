package com.yihu.jw.base.endpoint.patient;

import com.yihu.jw.base.service.patient.PatientFamilyMemberService;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.base.BaseRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Trick on 2018/9/3.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.PatientMember.PREFIX)
@Api(value = "家庭成员", description = "家庭成员管理", tags = {"wlyy基础服务 - 家庭成员管理"})
public class PatientFamilyMemberEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private PatientFamilyMemberService patientMemberService;

    @PostMapping(value = BaseRequestMapping.PatientMember.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建")
    public Envelop createFamilyMember(@ApiParam(name = "patient", value = "用户id", required = true)
                                      @RequestParam(value = "patient")String patient,
                                      @ApiParam(name = "member", value = "家庭成员id", required = true)
                                      @RequestParam(value = "member")String member,
                                      @ApiParam(name = "relation", value = "关系 1父亲 2母亲 3老公 4老婆 5儿子 6女儿 7其他", required = true)
                                      @RequestParam(value = "relation")Integer relation)throws Exception{
        patientMemberService.createFamilyMember(patient,member,relation);
        return success("success");
    }

    @PostMapping(value = BaseRequestMapping.PatientMember.DELETE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "删除")
    public Envelop delFamilyMember(@ApiParam(name = "patient", value = "用户id", required = true)
                                      @RequestParam(value = "patient")String patient,
                                      @ApiParam(name = "member", value = "家庭成员id", required = true)
                                      @RequestParam(value = "member")String member)throws Exception{
        patientMemberService.delFamilyMember(patient,member);
        return success("success");
    }
}
