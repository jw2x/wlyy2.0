package com.yihu.wlyy.figure.label.entity;


import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author lith on 2018.03.07
 *
 */

@Entity
@Table(name = "fl_label_dict_job")
public class FlLabelDictJob extends IdEntity {
    private Long jobId;
    private String labelType;
    private String sql;
    private String convertClazz;
    private Long categoryId;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getLabelType() {
        return labelType;
    }

    public void setLabelType(String labelType) {
        this.labelType = labelType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getConvertClazz() {
        return convertClazz;
    }

    public void setConvertClazz(String convertClazz) {
        this.convertClazz = convertClazz;
    }
}
