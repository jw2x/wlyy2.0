package com.yihu.jw.entity.base.user;

import com.yihu.jw.entity.IntegerIdentityEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by progr1mmer on 2018/8/14.
 */
@Entity
@Table(name = "base_employ_hide_module_function")
public class UserHideModuleFunctionDO extends IntegerIdentityEntity {

    //用户ID
    private String employId;
    //模块ID
    private Integer moduleId;
    //功能ID（该字段为空则直接隐藏上级模块）
    private Integer functionId;

}
