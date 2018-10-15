package com.yihu.jw.entity.base.population;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by zdm on 2018/10/12.
 */
@Entity
@Table(name = "base_year")
public class BaseYearDO extends UuidIdentityEntityWithOperator {

    //年份
    @Column(name = "year",nullable = false)
    private String  year;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
