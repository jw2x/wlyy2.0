package com.yihu.jw.restmodel.base.people_num;

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
 * Administrator 	1.0  2018年09月05日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "BasePeopleNumVO", description = "基础人口基数信息")
public class BasePeopleNumVO extends UuidIdentityVOWithOperator {

    /**
	 * saas化的id
	 */
	@ApiModelProperty(value = "saas化的id", example = "模块1")
    private String saasId;

    /**
	 * 所属省代码
	 */
	@ApiModelProperty(value = "所属省代码", example = "模块1")
    private String provinceCode;

    /**
	 * 所属市代码
	 */
	@ApiModelProperty(value = "所属市代码", example = "模块1")
    private String cityCode;

    /**
	 * 所属区代码
	 */
	@ApiModelProperty(value = "所属区代码", example = "模块1")
    private String districtCode;

    /**
	 * 所属具体名称
	 */
	@ApiModelProperty(value = "所属具体名称", example = "模块1")
    private String name;

    /**
	 * 人口数
	 */
	@ApiModelProperty(value = "人口数", example = "模块1")
    private Integer num;

    /**
	 * 类别 0是省，1是市，2是区，3是机构
	 */
	@ApiModelProperty(value = "类别 0是省，1是市，2是区，3是机构", example = "模块1")
    private String type;

    /**
	 * 每年的人口数
	 */
	@ApiModelProperty(value = "每年的人口数", example = "模块1")
    private Integer year;

    /**
	 * 高血压发病数
	 */
	@ApiModelProperty(value = "高血压发病数", example = "模块1")
    private Integer gxyNum;

    /**
	 * 糖尿病发病数
	 */
	@ApiModelProperty(value = "糖尿病发病数", example = "模块1")
    private Integer tnbNum;

    /**
	 * 65岁以上老年人口数
	 */
	@ApiModelProperty(value = "65岁以上老年人口数", example = "模块1")
    private Integer sixFiveNum;

    /**
	 * 高血压任务数
	 */
	@ApiModelProperty(value = "高血压任务数", example = "模块1")
    private Integer gxyTaskNum;

    /**
	 * 糖尿病任务数
	 */
	@ApiModelProperty(value = "糖尿病任务数", example = "模块1")
    private Integer tnbTaskNum;

    /**
	 * 65岁以上老年人口任务数
	 */
	@ApiModelProperty(value = "65岁以上老年人口任务数", example = "模块1")
    private Integer sixFiveTaskNum;

    /**
	 * 户籍人口任务数
	 */
	@ApiModelProperty(value = "户籍人口任务数", example = "模块1")
    private Integer taskNum;

    /**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	@ApiModelProperty(value = "创建时间", example = "模块1")
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

    public Integer getNum() {
        return num;
    }
    public void setNum(Integer num) {
        this.num = num;
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

    public Integer getGxyNum() {
        return gxyNum;
    }
    public void setGxyNum(Integer gxyNum) {
        this.gxyNum = gxyNum;
    }

    public Integer getTnbNum() {
        return tnbNum;
    }
    public void setTnbNum(Integer tnbNum) {
        this.tnbNum = tnbNum;
    }

    public Integer getSixFiveNum() {
        return sixFiveNum;
    }
    public void setSixFiveNum(Integer sixFiveNum) {
        this.sixFiveNum = sixFiveNum;
    }

    public Integer getGxyTaskNum() {
        return gxyTaskNum;
    }
    public void setGxyTaskNum(Integer gxyTaskNum) {
        this.gxyTaskNum = gxyTaskNum;
    }

    public Integer getTnbTaskNum() {
        return tnbTaskNum;
    }
    public void setTnbTaskNum(Integer tnbTaskNum) {
        this.tnbTaskNum = tnbTaskNum;
    }

    public Integer getSixFiveTaskNum() {
        return sixFiveTaskNum;
    }
    public void setSixFiveTaskNum(Integer sixFiveTaskNum) {
        this.sixFiveTaskNum = sixFiveTaskNum;
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