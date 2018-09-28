package com.yihu.jw.restmodel.base.population;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.jw.restmodel.UuidIdentityVOWithOperator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;


/**
 * 
 * 基础人口基数信息vo
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  2018年09月26日 update
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "BasePopulationVO", description = "基础人口基数信息")
public class BasePopulationVO extends UuidIdentityVOWithOperator {

    /**
	 * saas化的id
	 */
	@ApiModelProperty(value = "saas化的id", example = "402803ee656498890165649ad2da1112")
    private String saasId;

    /**
	 * 所属省代码
	 */
	@ApiModelProperty(value = "所属省代码", example = "参考省代码")
    private String provinceCode;

    /**
	 * 所属市代码
	 */
	@ApiModelProperty(value = "所属市代码", example = "参考市代码")
    private String cityCode;

    /**
	 * 所属区代码
	 */
	@ApiModelProperty(value = "所属区代码", example = "参考区代码")
    private String districtCode;

    /**
	 * 所属具体名称
	 */
	@ApiModelProperty(value = "所属具体名称", example = "")
    private String name;

    /**
	 * 人口数
	 */
	@ApiModelProperty(value = "人口数", example = "自然数")
    private Integer populationNum;

    /**
	 * 类别 0是省，1是市，2是区，3是机构
	 */
	@ApiModelProperty(value = "类别 0是省，1是市，2是区，3是机构", example = "0")
    private String type;

    /**
	 * 每年的人口数
	 */
	@ApiModelProperty(value = "每年的人口数", example = "自然数")
    private Integer year;

    /**
	 * 高血压发病数,HBP为医学简称
	 */
	@ApiModelProperty(value = "高血压发病数,HBP为医学简称", example = "模块1")
    private Integer hbpNum;

    /**
	 * 糖尿病发病数,DM为医学简称
	 */
	@ApiModelProperty(value = "糖尿病发病数,DM为医学简称", example = "模块1")
    private Integer dmNum;

    /**
	 * 65岁以上老年人口数
	 */
	@ApiModelProperty(value = "65岁以上老年人口数", example = "自然数")
    private Integer olderThan65Num;

    /**
	 * 高血压任务数
	 */
	@ApiModelProperty(value = "高血压任务数", example = "自然数")
    private Integer hbpTaskNum;

    /**
	 * 糖尿病任务数
	 */
	@ApiModelProperty(value = "糖尿病任务数", example = "自然数")
    private Integer dmTaskNum;

    /**
	 * 65岁以上老年人口任务数
	 */
	@ApiModelProperty(value = "65岁以上老年人口任务数", example = "自然数")
    private Integer olderThan65TaskNum;

    /**
	 * 户籍人口任务数
	 */
	@ApiModelProperty(value = "户籍人口任务数", example = "自然数")
    private Integer taskNum;

    /**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	@ApiModelProperty(value = "创建时间", example = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    public String getSaasId() {
        return saasId;
    }
    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getProvinceCode() {
        return provinceCode;
    }
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }
    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulationNum() {
        return populationNum;
    }
    public void setPopulationNum(Integer populationNum) {
        this.populationNum = populationNum;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getHbpNum() {
        return hbpNum;
    }
    public void setHbpNum(Integer hbpNum) {
        this.hbpNum = hbpNum;
    }

    public Integer getDmNum() {
        return dmNum;
    }
    public void setDmNum(Integer dmNum) {
        this.dmNum = dmNum;
    }

    public Integer getOlderThan65Num() {
        return olderThan65Num;
    }
    public void setOlderThan65Num(Integer olderThan65Num) {
        this.olderThan65Num = olderThan65Num;
    }

    public Integer getHbpTaskNum() {
        return hbpTaskNum;
    }
    public void setHbpTaskNum(Integer hbpTaskNum) {
        this.hbpTaskNum = hbpTaskNum;
    }

    public Integer getDmTaskNum() {
        return dmTaskNum;
    }
    public void setDmTaskNum(Integer dmTaskNum) {
        this.dmTaskNum = dmTaskNum;
    }

    public Integer getOlderThan65TaskNum() {
        return olderThan65TaskNum;
    }
    public void setOlderThan65TaskNum(Integer olderThan65TaskNum) {
        this.olderThan65TaskNum = olderThan65TaskNum;
    }

    public Integer getTaskNum() {
        return taskNum;
    }
    public void setTaskNum(Integer taskNum) {
        this.taskNum = taskNum;
    }

    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


}