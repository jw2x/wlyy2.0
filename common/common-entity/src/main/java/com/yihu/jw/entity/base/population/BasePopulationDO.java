package com.yihu.jw.entity.base.population;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.jw.entity.UuidIdentityEntityWithOperator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


/**
* 基础人口基数信息实体
*
* @author litaohong on  2018年09月26日
*
*/
@Entity
@Table(name = "base_population")
public class BasePopulationDO extends UuidIdentityEntityWithOperator {

    /**
	 * saas化的id
	 */
	private String saasId;

    /**
	 * 所属省代码
	 */
	private String provinceCode;

    /**
	 * 所属市代码
	 */
	private String cityCode;

    /**
	 * 所属区代码
	 */
	private String districtCode;

    /**
	 * 所属具体名称
	 */
	private String name;

    /**
	 * 人口数
	 */
	private Integer populationNum;

    /**
	 * 类别 0是省，1是市，2是区，3是机构
	 */
	private String type;

    /**
	 * 每年的人口数
	 */
	private Integer year;

    /**
	 * 高血压发病数,HBP为医学简称
	 */
	private Integer hbpNum;

    /**
	 * 糖尿病发病数,DM为医学简称
	 */
	private Integer dmNum;

    /**
	 * 65岁以上老年人口数
	 */
	private Integer olderThan65Num;

    /**
	 * 高血压任务数
	 */
	private Integer hbpTaskNum;

    /**
	 * 糖尿病任务数
	 */
	private Integer dmTaskNum;

    /**
	 * 65岁以上老年人口任务数
	 */
	private Integer olderThan65TaskNum;

    /**
	 * 户籍人口任务数
	 */
	private Integer taskNum;

    /**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date createTime;


	@Column(name = "saas_id")
    public String getSaasId() {
        return saasId;
    }
    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

	@Column(name = "province_code")
    public String getProvinceCode() {
        return provinceCode;
    }
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

	@Column(name = "city_code")
    public String getCityCode() {
        return cityCode;
    }
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

	@Column(name = "district_code")
    public String getDistrictCode() {
        return districtCode;
    }
    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

	@Column(name = "name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

	@Column(name = "population_num")
    public Integer getPopulationNum() {
        return populationNum;
    }
    public void setPopulationNum(Integer populationNum) {
        this.populationNum = populationNum;
    }

	@Column(name = "type")
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

	@Column(name = "year")
    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }

	@Column(name = "hbp_num")
    public Integer getHbpNum() {
        return hbpNum;
    }
    public void setHbpNum(Integer hbpNum) {
        this.hbpNum = hbpNum;
    }

	@Column(name = "dm_num")
    public Integer getDmNum() {
        return dmNum;
    }
    public void setDmNum(Integer dmNum) {
        this.dmNum = dmNum;
    }

	@Column(name = "older_than_65_num")
    public Integer getOlderThan65Num() {
        return olderThan65Num;
    }
    public void setOlderThan65Num(Integer olderThan65Num) {
        this.olderThan65Num = olderThan65Num;
    }

	@Column(name = "hbp_task_num")
    public Integer getHbpTaskNum() {
        return hbpTaskNum;
    }
    public void setHbpTaskNum(Integer hbpTaskNum) {
        this.hbpTaskNum = hbpTaskNum;
    }

	@Column(name = "dm_task_num")
    public Integer getDmTaskNum() {
        return dmTaskNum;
    }
    public void setDmTaskNum(Integer dmTaskNum) {
        this.dmTaskNum = dmTaskNum;
    }

	@Column(name = "older_than_65_task_num")
    public Integer getOlderThan65TaskNum() {
        return olderThan65TaskNum;
    }
    public void setOlderThan65TaskNum(Integer olderThan65TaskNum) {
        this.olderThan65TaskNum = olderThan65TaskNum;
    }

	@Column(name = "task_num")
    public Integer getTaskNum() {
        return taskNum;
    }
    public void setTaskNum(Integer taskNum) {
        this.taskNum = taskNum;
    }

	@Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



}