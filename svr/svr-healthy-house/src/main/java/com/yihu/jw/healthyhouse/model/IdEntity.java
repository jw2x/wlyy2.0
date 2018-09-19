package com.yihu.jw.healthyhouse.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 *  jpa基类
 *
 * @author HZY
 * @created 2018/9/18 17:11
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class IdEntity implements java.io.Serializable{

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    protected String id;  // 非业务主键


    @CreatedDate
    @Column(name = "create_date", nullable = false, length = 0,updatable = false)
    protected Date createDate;

    @LastModifiedDate
    @Column(name = "modify_date", nullable = false, length = 0)
    protected Date modifyDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
