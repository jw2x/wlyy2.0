package com.yihu.wlyy.statistics.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/8/16.
 */
public class WlyyQuotaVO implements Serializable {
    private static final long serialVersionUID = 2L;
    private String id;
    private String name;//指标名称
    private String cycle;//统计周期( 1:天 )
    private String type;//尺度(1 新增 2是累计)
    private Date createTime;//创建时间
    private Date modifyTime;//修改时间
    private String del;//1: 正常 0： 删除
    private String sql;//数据库查询语句
    private String countSql;//数据库数据数量查询语句
    private String level1;//1级维度 (1:患者性别 2：患者分组 3：患者年龄)
    private String level2;//2级维度 (1:患者性别 2：患者分组 3：患者年龄)
    private String level3;//3级维度 (1:患者性别 2：患者分组 3：患者年龄)
    private String level4;//4级维度 (1:患者性别 2：患者分组 3：患者年龄)


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel1() {
        return level1;
    }

    public void setLevel1(String level1) {
        this.level1 = level1;
    }

    public String getLevel2() {
        return level2;
    }

    public void setLevel2(String level2) {
        this.level2 = level2;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }

    @Override
    public String toString() {
        return "WlyyQuotaVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", level1='" + level1 + '\'' +
                ", level2='" + level2 + '\'' +
                ", cycle='" + cycle + '\'' +
                ", type='" + type + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", del='" + del + '\'' +
                ", sql='" + sql + '\'' +
                '}';
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getCountSql() {
        return countSql;
    }

    public void setCountSql(String countSql) {
        this.countSql = countSql;
    }

    public String getLevel4() {
        return level4;
    }

    public void setLevel4(String level4) {
        this.level4 = level4;
    }

    public String getLevel3() {
        return level3;
    }

    public void setLevel3(String level3) {
        this.level3 = level3;
    }
}
