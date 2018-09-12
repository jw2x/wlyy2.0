package com.yihu.wlyy.figure.label.entity;



import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author lith on 2018.03.07
 *
 */

@Entity
@Table(name = "fl_label_dict")
public class FlLabelDict extends IdEntity {
    private String parentCode;
    private String labelName;
    private String labelCode;
    private String dictCode;

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
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
}
