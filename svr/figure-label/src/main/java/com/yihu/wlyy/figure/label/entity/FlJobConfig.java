
package com.yihu.wlyy.figure.label.entity;


import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author lith on 2018.03.07
 *
 */

@Entity
@Table(name = "fl_job_config")
public class FlJobConfig extends IdEntity {
    private String jobName;
    private String jobClass;
    private String query_sql;
    private String countSql;
    private Integer sqlFieldType;
    private String sqlFieldValue;
    private String sqlField;
    private String quartzCron;
    private String status;
    private String del;
    private String sourceType;
    private String source;
    private String datasource;
    private String extractField;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobClass() {
        return jobClass;
    }

    public void setJobClass(String jobClass) {
        this.jobClass = jobClass;
    }

    public String getQuery_sql() {
        return query_sql;
    }

    public void setQuery_sql(String query_sql) {
        this.query_sql = query_sql;
    }

    public Integer getSqlFieldType() {
        return sqlFieldType;
    }

    public void setSqlFieldType(Integer sqlFieldType) {
        this.sqlFieldType = sqlFieldType;
    }

    public String getSqlFieldValue() {
        return sqlFieldValue;
    }

    public void setSqlFieldValue(String sqlFieldValue) {
        this.sqlFieldValue = sqlFieldValue;
    }

    public String getSqlField() {
        return sqlField;
    }

    public void setSqlField(String sqlField) {
        this.sqlField = sqlField;
    }

    public String getQuartzCron() {
        return quartzCron;
    }

    public void setQuartzCron(String quartzCron) {
        this.quartzCron = quartzCron;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }

    public String getExtractField() {
        return extractField;
    }

    public void setExtractField(String extractField) {
        this.extractField = extractField;
    }

    public String getCountSql() {
        return countSql;
    }

    public void setCountSql(String countSql) {
        this.countSql = countSql;
    }
}
