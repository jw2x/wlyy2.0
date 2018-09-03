package com.yihu.jw.entity.base.statistics;

import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 统计指标表
 * @author yeshijie on 2018/8/31.
 */
@Entity
@Table(name = "base_quota")
public class QuotaDO extends UuidIdentityEntity implements Serializable {

    private String code;//指标code',
    private String name;//指标名称',
    private String createTime;
    private String updateTime;
    private Integer del;//1: 正常 0： 删除

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "create_time")
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Column(name = "update_time")
    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}
