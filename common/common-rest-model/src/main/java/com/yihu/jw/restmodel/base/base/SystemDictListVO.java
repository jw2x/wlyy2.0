package com.yihu.jw.restmodel.base.base;

public class SystemDictListVO {

    private String dictName;
    private String chineseName;
    private String pyCode;
    private String pid;
    private String remark;
    private String relationTable;
    private String relationColCode;
    private String relationColValue;
    private String relationColExtend;

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getPyCode() {
        return pyCode;
    }

    public void setPyCode(String pyCode) {
        this.pyCode = pyCode;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRelationTable() {
        return relationTable;
    }

    public void setRelationTable(String relationTable) {
        this.relationTable = relationTable;
    }

    public String getRelationColCode() {
        return relationColCode;
    }

    public void setRelationColCode(String relationColCode) {
        this.relationColCode = relationColCode;
    }

    public String getRelationColValue() {
        return relationColValue;
    }

    public void setRelationColValue(String relationColValue) {
        this.relationColValue = relationColValue;
    }

    public String getRelationColExtend() {
        return relationColExtend;
    }

    public void setRelationColExtend(String relationColExtend) {
        this.relationColExtend = relationColExtend;
    }
}
