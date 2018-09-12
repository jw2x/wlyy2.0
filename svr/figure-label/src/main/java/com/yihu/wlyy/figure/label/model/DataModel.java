package com.yihu.wlyy.figure.label.model;

/**
 * jboConfig配置里sql查询出的数据
 */
public class DataModel {

    /**
     * 这些id用作记录数据来源，当有多个表时，记录多个表的id，并依据fl_job_config里的sourceTyep,source来区别
     */
    private Integer id;
    private Integer id1;
    private Integer id2;
    private Integer id3;
    private Integer id4;

    /**
     * 有些表的id为uuid，是string类型，所以为了兼容所有的，此种类型id命名为idstr，进行数据抽取时需多注意
     */
    private String idstr;
    private String idstr1;
    private String idstr2;
    /**
     * 居民身份证号
     */
    private String idcard;

    /**
     * 父类标签码
     */
    private String parentCode;
    /**
     * 父类标签名
     */
    private String parentName;
    /**
     * 标签码
     */
    private String labelCode;
    /**
     * 标签名
     */
    private String labelName;
    /**
     * 标签值，某个单独的值，如及时回复中的问题id
     */
    private String labelValue;
    /**
     * 转换类
     */
    private String convertClazz;
    /**
     * 定时任务的表id
     */
    private String jobId;
    /**
     * 原数据来源时间
     */
    private String sourceTime;

    /**
     * 代预约的医生姓名
     */
    private String doctor;

    /**
     * 健康教育文章一级类别名称
     */
    private String firstLevelCategoryName;

    /**
     * 健康教育文章二级类别名称
     */
    private String secondLevelCategoryName;

    /**
     * 健康教育文章推送人名称
     */
    private String sendName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId1() {
        return id1;
    }

    public void setId1(Integer id1) {
        this.id1 = id1;
    }

    public Integer getId2() {
        return id2;
    }

    public void setId2(Integer id2) {
        this.id2 = id2;
    }

    public Integer getId3() {
        return id3;
    }

    public void setId3(Integer id3) {
        this.id3 = id3;
    }

    public Integer getId4() {
        return id4;
    }

    public void setId4(Integer id4) {
        this.id4 = id4;
    }

    public String getIdstr() {
        return idstr;
    }

    public void setIdstr(String idstr) {
        this.idstr = idstr;
    }

    public String getIdstr1() {
        return idstr1;
    }

    public void setIdstr1(String idstr1) {
        this.idstr1 = idstr1;
    }

    public String getIdstr2() {
        return idstr2;
    }

    public void setIdstr2(String idstr2) {
        this.idstr2 = idstr2;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getLabelCode() {
        return labelCode;
    }

    public void setLabelCode(String labelCode) {
        this.labelCode = labelCode;
    }

    public String getConvertClazz() {
        return convertClazz;
    }

    public void setConvertClazz(String convertClazz) {
        this.convertClazz = convertClazz;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getSourceTime() {
        return sourceTime;
    }

    public void setSourceTime(String sourceTime) {
        this.sourceTime = sourceTime;
    }

    public String getLabelValue() {
        return labelValue;
    }

    public void setLabelValue(String labelValue) {
        this.labelValue = labelValue;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getFirstLevelCategoryName() {
        return firstLevelCategoryName;
    }

    public void setFirstLevelCategoryName(String firstLevelCategoryName) {
        this.firstLevelCategoryName = firstLevelCategoryName;
    }

    public String getSecondLevelCategoryName() {
        return secondLevelCategoryName;
    }

    public void setSecondLevelCategoryName(String secondLevelCategoryName) {
        this.secondLevelCategoryName = secondLevelCategoryName;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }
}
