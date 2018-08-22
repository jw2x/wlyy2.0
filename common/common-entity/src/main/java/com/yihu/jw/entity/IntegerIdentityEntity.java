package com.yihu.jw.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Integer类型的主键基类
 * Created by progr1mmer on 2018/8/13.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class IntegerIdentityEntity implements Serializable {

    protected Integer id;

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "identity")
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
