package com.yihu.jw.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by progr1mmer on 2018/8/13.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class IntegerIdentityEntity implements Serializable {

    protected Integer id;

    @Id
    @GeneratedValue(generator = "Generator")
    @GenericGenerator(name = "Generator", strategy = "identity")
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
