package com.yihu.jw.entity.base.user;

import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by progr1mmer on 2018/8/14.
 */
@Entity
@Table(name = "base_employ_hide_module_function")
public class EmployHideModuleFunctionDO extends UuidIdentityEntity {

    //用户ID
    private String employId;
    //模块ID
    private String moduleId;
    //功能ID（该字段为空则直接隐藏上级模块）
    private String functionId;

}
