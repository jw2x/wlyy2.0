package com.yihu.jw.restmodel.specialist;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Trick on 2018/7/4.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(value = "专科医生团队成员", description = "专科医生团队成员")
public class AdminTeamMemberVO {

    @ApiModelProperty("医生code")
    private String doctorCode;
    @ApiModelProperty("医生姓名")
    private String doctorName;

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}
