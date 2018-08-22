package com.yihu.jw.fegin.fallbackfactory.base.user;

import com.yihu.jw.exception.business.JiWeiException;
import com.yihu.jw.fegin.base.user.BaseRoleFeign;
import com.yihu.jw.restmodel.web.MixEnvelop;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by LiTaohong on 2017/11/28.
 */
@Component
public class BaseRoleFeignFallbackFactory implements FallbackFactory<BaseRoleFeign> {

    @Autowired
    private Tracer tracer;

    @Override
    public BaseRoleFeign create(Throwable e) {
        return new BaseRoleFeign() {
            @Override
            public MixEnvelop create(@RequestBody String jsonData) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("创建角色失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:"+jsonData);
                throw new JiWeiException(e);
            }

            @Override
            public MixEnvelop update(@RequestBody String jsonData) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("更新角色失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:"+jsonData);
                throw new JiWeiException(e);
            }

            @Override
            public MixEnvelop delete(@PathVariable String id) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("删除角色失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("id:"+id);
                throw new JiWeiException(e);
            }

            @Override
            public MixEnvelop findById(String id) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("查找单个角色失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("id:"+id);
                throw new JiWeiException(e);
            }

            @Override
            public MixEnvelop getList(String fields, String filterStr, String sorts, int size, int page) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("分页查找角色列表失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("fields:" + fields);
                tracer.getCurrentSpan().logEvent("filters:" + filterStr);
                tracer.getCurrentSpan().logEvent("sorts:" + sorts);
                tracer.getCurrentSpan().logEvent("size:" + size);
                tracer.getCurrentSpan().logEvent("page:" + page);
                throw new JiWeiException(e);
            }

            @Override
            public MixEnvelop getListNoPage(String fields, String filters, String sorts) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("不分页查找角色列表失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("fields:" + fields);
                tracer.getCurrentSpan().logEvent("filters:" + filters);
                tracer.getCurrentSpan().logEvent("sorts:" + sorts);
                throw new JiWeiException(e);
            }

            @Override
            public MixEnvelop createRoleMenus(@RequestBody String jsonData) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("创建角色菜单失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:"+jsonData);
                throw new JiWeiException(e);
            }

            @Override
            public MixEnvelop updateRoleMenus(@RequestBody String jsonData) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("修改角色菜单失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:"+jsonData);
                throw new JiWeiException(e);
            }

            @Override
            public MixEnvelop deleteRoleMenus(@RequestBody String jsonData) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("删除角色菜单失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:"+jsonData);
                throw new JiWeiException(e);
            }

            @Override
            public MixEnvelop getMenuList(@RequestBody String jsonData) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("获取角色菜单列表失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:"+jsonData);
                throw new JiWeiException(e);
            }

            @Override
            public MixEnvelop getPhoneAndSaasId(String phone, String saasId) {
                return null;
            }

        };
    }
}
