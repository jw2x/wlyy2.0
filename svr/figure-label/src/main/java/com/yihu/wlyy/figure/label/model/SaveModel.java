package com.yihu.wlyy.figure.label.model;

import io.searchbox.annotations.JestId;

import java.io.Serializable;

/**
 * Created by chenweida on 2018/3/7.
 */
public class SaveModel implements Serializable{

    @JestId
    /**
     * 存入elasticsearch的id由 idcard，parentCode，labelName，labelCode MD5加密而成。不用es自动生成的_id，实现数据插入排重
     */
    private String id;

    /**
     * 身份证
     */
    private String idcard;
    /**
     * 标签字典码
     */
    private String dictCode;

    /**
     * 标签类型
     */
    private String labelType;
    /**
     * 标签code
     */
    private String labelCode;

    /**
     * 标签名称
     */
    private String labelName;

    /**
     * 标签值，仅当标签只有一个分类的时候才有此值，比如生日，体重等
     */
    private String labelValue;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 当前标签是否激活
     */

    /**
     * 标签生成来源
     */
    private String source;

    /**
     * 标签源数据来源时间
     */
    private String sourceTime;

    /**
     * 代预约的医生姓名
     */
    private String doctor;

    /**
     * 健康教育文章推送人名称
     */
    private String sendName;

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getLabelType() {
        return labelType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public void setLabelType(String labelType) {
        this.labelType = labelType;
    }

    public String getLabelCode() {
        return labelCode;
    }

    public void setLabelCode(String labelCode) {
        this.labelCode = labelCode;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getLabelValue() {
        return labelValue;
    }

    public void setLabelValue(String labelValue) {
        this.labelValue = labelValue;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceTime() {
        return sourceTime;
    }

    public void setSourceTime(String sourceTime) {
        this.sourceTime = sourceTime;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }
}
