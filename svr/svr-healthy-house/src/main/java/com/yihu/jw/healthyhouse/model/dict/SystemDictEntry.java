package com.yihu.jw.healthyhouse.model.dict;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.jw.entity.UuidIdentityEntityWithOperator;
import com.yihu.jw.healthyhouse.util.PinyinUtil;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

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
public class SystemDictEntry implements Serializable {
    private static final long serialVersionUID = 1L;
    String dictId;
    String code;
    String value;
    Integer sort;
    String phoneticCode;
    String catalog;
    String pcode;//上级id
    //创建时间
    protected Date createTime;
    //创建者
    protected String createUser;
    //创建者
    protected String createUserName;
    //更新时间
    protected Date updateTime;
    //更新者
    protected String updateUser;
    //更新者
    protected String updateUserName;

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

    @Column(name = "pcode")
    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    @Column(name = "create_time", length = 0,updatable = false)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @CreatedBy
    @Column(name = "create_user",updatable = false)
    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @CreatedBy
    @Column(name = "create_user_name",updatable = false)
    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    @Column(name = "update_time", length = 0)
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @LastModifiedBy
    @Column(name = "update_user")
    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @LastModifiedBy
    @Column(name = "update_user_name")
    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }
}
