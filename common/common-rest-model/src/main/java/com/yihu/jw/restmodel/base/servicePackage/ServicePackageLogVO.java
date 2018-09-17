package com.yihu.jw.restmodel.base.servicePackage;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yeshijie on 2018/8/30.
 */
@ApiModel(value = "ServicePackageLogVO", description = "服务包日志")
public class ServicePackageLogVO implements Serializable {

    public enum Flag {
        success("成功", 0),
        failure("失败", 1);
        private String name;
        private int value;

        Flag(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public enum UserType {
        patient("居民", "1"),
        doctor("医生", "2");
        private String name;
        private String value;

        UserType(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    @ApiModelProperty(value = "uuid")
    private String id;//uuid
    @ApiModelProperty(value = "时间")
    private Date createTime;//时间
    @ApiModelProperty(value = "saasid")
    private String saasId;//saasid
    @ApiModelProperty(value = "服务包id")
    private String sevicePackageId;//服务包id
    @ApiModelProperty(value = "服务包明细id")
    private String sevicePackageDetailId;//服务包明细id
    @ApiModelProperty(value = "医生或者患者code")
    private String userCode;//医生或者患者code
    @ApiModelProperty(value = "医生或者患者name")
    private String userName;//医生或者患者name
    @ApiModelProperty(value = "1 患者 2医生")
    private String userType;// 1 患者 2医生
    @ApiModelProperty(value = "关联类型（1健康指导，2健康文章，3代预约）")
    private String relationType;//关联类型（1健康指导，2健康文章，3代预约）
    @ApiModelProperty(value = "关联id")
    private String relationId;//关联id
    @ApiModelProperty(value = "操作说明")
    private String message;//操作说明
    @ApiModelProperty(value = "操作是否成功 1成功 0失败")
    private Integer flag;//操作是否成功 1成功 0失败
    @ApiModelProperty(value = "完成项目数")
    private Integer finish;//完成项目数
    @ApiModelProperty(value = "扩展字段存json")
    private String ext;//扩展字段存json

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getSevicePackageId() {
        return sevicePackageId;
    }

    public void setSevicePackageId(String sevicePackageId) {
        this.sevicePackageId = sevicePackageId;
    }

    public String getSevicePackageDetailId() {
        return sevicePackageDetailId;
    }

    public void setSevicePackageDetailId(String sevicePackageDetailId) {
        this.sevicePackageDetailId = sevicePackageDetailId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getFinish() {
        return finish;
    }

    public void setFinish(Integer finish) {
        this.finish = finish;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}
