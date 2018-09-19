package com.yihu.jw.healthyhouse.model.dict;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.jw.healthyhouse.util.PinyinUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *系统字典项。
 *
 * @author zdm
 * @version 1.0
 * @created 2018.09.18
 */
@Entity
@Table(name = "system_dict_entries")
@Access(value = AccessType.PROPERTY)
@Embeddable
@IdClass(DictEntryKey.class)
public class SystemDictEntry  implements Serializable {
    private static final long serialVersionUID = 1L;

    String dictId;
    String code;
    String value;
    Integer sort;
    String phoneticCode;
    String catalog;
    String pcode;//上级id
    /** 创建日期 */
    private Date createDate;
    /** 创建者 */
    private String creator;
    /** 修改日期 */
    private Date modifyDate;
    /** 修改者 */
    private String modifier;

    public SystemDictEntry(){
    }

    @Id
    @Column(name = "code", unique = true, nullable = false ,insertable = false, updatable = false)
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    @Id
    @Column(name = "dictId", unique = true, nullable = false ,insertable = false, updatable = false)
    public String getDictId() {
        return dictId;
    }
    public void setDictId(String dictId) {
        this.dictId = dictId;
    }


    @Column(name = "value", nullable = true)
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
        this.phoneticCode = PinyinUtil.getPinYinHeadChar(value, true);
    }

    @Column(name = "phoneticCode", nullable = true)
    public String getPhoneticCode() {
        return phoneticCode;
    }
    public void setPhoneticCode(String phoneticCode) {
        this.phoneticCode = phoneticCode;
    }

    @Column(name = "sort", nullable = true)
    public Integer getSort() {
        return sort;
    }
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Column(name = "catalog", nullable = true)
    public String getCatalog() {
        return catalog;
    }
    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_date", nullable = false, updatable = false)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "creator")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "modify_date", nullable = false)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Column(name = "modifier")
    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    @Column(name = "pcode")
    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }
}
