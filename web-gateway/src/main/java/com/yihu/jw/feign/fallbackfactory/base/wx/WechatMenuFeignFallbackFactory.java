package com.yihu.jw.feign.fallbackfactory.base.wx;

import com.yihu.jw.exception.business.JiWeiException;
import com.yihu.jw.feign.base.wx.WechatMenuFeign;
import com.yihu.jw.restmodel.common.Envelop;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by Administrator on 2017/5/31 0031.
 */
@Component
public class WechatMenuFeignFallbackFactory implements FallbackFactory<WechatMenuFeign> {

    @Autowired
    private Tracer tracer;

    @Override
    public WechatMenuFeign create(Throwable e) {
        return new WechatMenuFeign() {
            @Override
            public Envelop createWxMenu(String jsonData) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("创建微信菜单失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:"+jsonData);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop updateWxMenu(String jsonData) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("更新微信菜单失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:"+jsonData);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop deleteWxMenu(String codes,String userCode,String userName) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("删除微信菜单失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("codes:"+codes);
                tracer.getCurrentSpan().logEvent("操作人userCode:"+userCode);
                throw new JiWeiException(e);
            }


            @Override
            public Envelop findById(String id) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("查找微信菜单失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("id:"+id);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop getWxMenuNoPage(String fields, String filters, String sorts) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("查找微信菜单列表失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("fields:" + fields);
                tracer.getCurrentSpan().logEvent("filters:" + filters);
                tracer.getCurrentSpan().logEvent("sorts:" + sorts);
                throw new JiWeiException(e);
            }


            @Override
            public Envelop createWechatMenu(String wechatCode) {
                return null;
            }

            @Override
            public Envelop getParentMenu(@PathVariable String wechatCode) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("根据微信code查找父菜单失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("wechatCode:"+wechatCode);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop getChildMenus(@PathVariable(value = "parentCode", required = true) String parentCode) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("根据父级菜单code查找子菜单失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("parentCode:"+parentCode);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop getWxMenus(String fields, String filters, String sorts, int size, int page) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("分页查找微信菜单失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("fields:" + fields);
                tracer.getCurrentSpan().logEvent("filters:" + filters);
                tracer.getCurrentSpan().logEvent("sorts:" + sorts);
                tracer.getCurrentSpan().logEvent("size:" + size);
                tracer.getCurrentSpan().logEvent("page:" + page);
                throw new JiWeiException(e);
            }
        };
    }
}
