package com.yihu.jw.restmodel.base.servicePackage;

import com.yihu.jw.restmodel.UuidIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

/**
 *
 * @author yeshijie on 2018/8/17.
 */
@ApiModel(value = "ServicePackageVO", description = "服务包表")
public class ServicePackageVO extends UuidIdentityVO  {

    @ApiModelProperty(value = "saasId", example = "xsaasdaqq")
    private String saasId;
    @ApiModelProperty(value = "服务包名称", example = "服务")
    private String name;//服务包名称
    @ApiModelProperty(value = "服务包类型", example = "1")
    private String type;//服务包类型
    @ApiModelProperty(value = "服务项数量", example = "20")
    private Integer num;//服务项数量
    @ApiModelProperty(value = "预售价", example = "50000")
    private Long price;//预售价//单位分
    @ApiModelProperty(value = "服务包级别（0系统，1.医生，2团队，3社区，4区）", example = "1")
    private String level;//服务包级别（0系统，1.医生，2团队，3社区，4区）
    @ApiModelProperty(value = "关联code", example = "xsaasdaqq")
    private String levelCode;//关联code
    @ApiModelProperty(value = "创建者", example = "张武1")
    private String creater;//创建者
    @ApiModelProperty(value = "创建时间")
    private Date createTime;//创建时间
    @ApiModelProperty(value = "服务介绍", example = "xsaasdaqq")
    private String introduce;//服务介绍
    @ApiModelProperty(value = "审核状态", example = "1")
    private String status;//审核状态（预留字段0待审核，1审核通过，2审核不通过）
    @ApiModelProperty(value = "是否有效（1有效，0失效）", example = "1")
    private Integer del;//是否有效（1有效，0失效）
    @ApiModelProperty(value = "服务项")
    private List<ServicePackageDetailsVO> detailsVOList;

    @Column(name = "saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Column(name = "level_code")
    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public List<ServicePackageDetailsVO> getDetailsVOList() {
        return detailsVOList;
    }

    public void setDetailsVOList(List<ServicePackageDetailsVO> detailsVOList) {
        this.detailsVOList = detailsVOList;
    }
}
