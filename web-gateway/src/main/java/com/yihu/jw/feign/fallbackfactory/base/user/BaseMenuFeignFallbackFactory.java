package com.yihu.jw.feign.fallbackfactory.base.user;

import com.yihu.jw.exception.business.JiWeiException;
import com.yihu.jw.feign.base.user.BaseMenuFeign;
import com.yihu.jw.feign.base.user.BaseRoleFeign;
import com.yihu.jw.restmodel.common.Envelop;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by LiTaohong on 2017/11/28.
 */
public class BaseMenuFeignFallbackFactory implements FallbackFactory<BaseMenuFeign> {

    @Autowired
    private Tracer tracer;

    @Override
    public BaseMenuFeign create(Throwable e) {
        return new BaseMenuFeign() {
            @Override
            public Envelop create(@RequestBody String jsonData) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("创建菜单失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:"+jsonData);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop update(@RequestBody String jsonData) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("更新菜单失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:"+jsonData);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop delete(@PathVariable String id) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("删除菜单失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("id:"+id);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop findById(String id) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("查找单个菜单失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("id:"+id);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop getList(String fields, String filterStr, String sorts, int size, int page) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("分页查找菜单失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("fields:" + fields);
                tracer.getCurrentSpan().logEvent("filters:" + filterStr);
                tracer.getCurrentSpan().logEvent("sorts:" + sorts);
                tracer.getCurrentSpan().logEvent("size:" + size);
                tracer.getCurrentSpan().logEvent("page:" + page);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop getListNoPage(String fields, String filters, String sorts) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("查找菜单列表失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("fields:" + fields);
                tracer.getCurrentSpan().logEvent("filters:" + filters);
                tracer.getCurrentSpan().logEvent("sorts:" + sorts);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop getChildren(String saasId, String parentId) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("查找子菜单列表失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("filters:" + saasId);
                tracer.getCurrentSpan().logEvent("sorts:" + parentId);
                throw new JiWeiException(e);
            }

        };
    }
}
