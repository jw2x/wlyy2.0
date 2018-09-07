package com.yihu.jw.entity.base.dict;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.jw.entity.IntegerIdentityEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


/**
 * 健康问题字典实体
 * 
 * @author Administrator on  2018年09月05日
 *
 */
@Entity
@Table(name = "dict_health_problem")
public class DictHealthProblemDO extends IntegerIdentityEntity {

    /**
	 * saas配置id，null标识公共字典
	 */
	private String saasId;

    /**
	 * 字典编码
	 */
	private String code;

    /**
	 * 字典名称
	 */
	private String name;
    /**
     * 是否慢病 1-是，0-否
     */
    private String chronicFlag;

    /**
     * 是否传染病 1-是，0-否
     */
    private String infectiousFlag;

    /**
	 * 描述
	 */
	private String description;

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

	@Column(name = "code")
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

	@Column(name = "name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "chronic_flag")
    public String getChronicFlag() {
        return chronicFlag;
    }
    public void setChronicFlag(String chronicFlag) {
        this.chronicFlag = chronicFlag;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

	@Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



}