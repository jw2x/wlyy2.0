package com.yihu.jw.manage.aop.annotation;

import java.lang.annotation.*;

/**
 * Created by chenweida on 2017/6/24.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ManageLog {
}
