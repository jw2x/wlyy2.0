package com.yihu.jw.fegin.fallbackfactory.base.user;

import com.yihu.jw.exception.business.JiWeiException;
import com.yihu.jw.fegin.base.user.EmployFeign;
import com.yihu.jw.restmodel.common.Envelop;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by chenweida on 2017/11/29.
 */
@Component
public class EmployeeFeignFallbackFactory  implements FallbackFactory<EmployFeign> {
    @Autowired
    private Tracer tracer;

    @Override
    public EmployFeign create(Throwable e) {
        return new EmployFeign() {
            @Override
            public Envelop create(@RequestBody String jsonData) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("创建用户失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:"+jsonData);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop update(@RequestBody String jsonData) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("更新用户失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:"+jsonData);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop delete(@PathVariable String id) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("删除用户失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("id:"+id);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop findById(String id) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("查找单个用户失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("id:"+id);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop getList(String fields, String filterStr, String sorts, int size, int page) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("分页查找用户失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("fields:" + fields);
                tracer.getCurrentSpan().logEvent("filters:" + filterStr);
                tracer.getCurrentSpan().logEvent("sorts:" + sorts);
                tracer.getCurrentSpan().logEvent("size:" + size);
                tracer.getCurrentSpan().logEvent("page:" + page);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop getListNoPage(String fields, String filters, String sorts) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("不分页查找用户列表失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("fields:" + fields);
                tracer.getCurrentSpan().logEvent("filters:" + filters);
                tracer.getCurrentSpan().logEvent("sorts:" + sorts);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop createEmployRoles(@RequestBody String jsonData) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("新增用户角色列表失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:"+jsonData);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop updateEmployRoles(@RequestBody String jsonData) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("修改用户角色失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:"+jsonData);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop deleteEmployRoles(@RequestBody String jsonData) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("删除用户角色失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:"+jsonData);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop getRoleList(@RequestBody String jsonData) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("获取用户角色列表失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:"+jsonData);
                throw new JiWeiException(e);
            }

        };
    }
}
