package com.yihu.jw.entity.base.peopel_num;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.jw.entity.UuidIdentityEntityWithOperator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


/**
* 基础人口基数信息实体
*
* @author Administrator on  2018年09月05日
*
*/
@Entity
@Table(name = "base_people_num")
public class BasePeopleNumDO extends UuidIdentityEntityWithOperator {

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
	private Integer num;

    /**
	 * 类别 0是省，1是市，2是区，3是机构
	 */
	private String type;

    /**
	 * 每年的人口数
	 */
	private Integer year;

    /**
	 * 高血压发病数
	 */
	private Integer gxyNum;

    /**
	 * 糖尿病发病数
	 */
	private Integer tnbNum;

    /**
	 * 65岁以上老年人口数
	 */
	private Integer sixFiveNum;

    /**
	 * 高血压任务数
	 */
	private Integer gxyTaskNum;

    /**
	 * 糖尿病任务数
	 */
	private Integer tnbTaskNum;

    /**
	 * 65岁以上老年人口任务数
	 */
	private Integer sixFiveTaskNum;

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

	@Column(name = "num")
    public Integer getNum() {
        return num;
    }
    public void setNum(Integer num) {
        this.num = num;
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

	@Column(name = "gxy_num")
    public Integer getGxyNum() {
        return gxyNum;
    }
    public void setGxyNum(Integer gxyNum) {
        this.gxyNum = gxyNum;
    }

	@Column(name = "tnb_num")
    public Integer getTnbNum() {
        return tnbNum;
    }
    public void setTnbNum(Integer tnbNum) {
        this.tnbNum = tnbNum;
    }

	@Column(name = "six_five_num")
    public Integer getSixFiveNum() {
        return sixFiveNum;
    }
    public void setSixFiveNum(Integer sixFiveNum) {
        this.sixFiveNum = sixFiveNum;
    }

	@Column(name = "gxy_task_num")
    public Integer getGxyTaskNum() {
        return gxyTaskNum;
    }
    public void setGxyTaskNum(Integer gxyTaskNum) {
        this.gxyTaskNum = gxyTaskNum;
    }

	@Column(name = "tnb_task_num")
    public Integer getTnbTaskNum() {
        return tnbTaskNum;
    }
    public void setTnbTaskNum(Integer tnbTaskNum) {
        this.tnbTaskNum = tnbTaskNum;
    }

	@Column(name = "six_five_task_num")
    public Integer getSixFiveTaskNum() {
        return sixFiveTaskNum;
    }
    public void setSixFiveTaskNum(Integer sixFiveTaskNum) {
        this.sixFiveTaskNum = sixFiveTaskNum;
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