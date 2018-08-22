package com.yihu.jw.fegin.fallbackfactory.base.wx;

import com.yihu.jw.exception.business.JiWeiException;
import com.yihu.jw.fegin.base.wx.WechatMenuFeign;
import com.yihu.jw.restmodel.web.MixEnvelop;
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
            public MixEnvelop createWxMenu(String jsonData) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("创建微信菜单失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:"+jsonData);
                throw new JiWeiException(e);
            }

            @Override
            public MixEnvelop updateWxMenu(String jsonData) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("更新微信菜单失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:"+jsonData);
                throw new JiWeiException(e);
            }

            @Override
            public MixEnvelop deleteWxMenu(String codes, String userCode, String userName) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("删除微信菜单失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("codes:"+codes);
                tracer.getCurrentSpan().logEvent("操作人userCode:"+userCode);
                throw new JiWeiException(e);
            }


            @Override
            public MixEnvelop findById(String id) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("查找微信菜单失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("id:"+id);
                throw new JiWeiException(e);
            }

            @Override
            public MixEnvelop getWxMenuNoPage(String fields, String filters, String sorts) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("查找微信菜单列表失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("fields:" + fields);
                tracer.getCurrentSpan().logEvent("filters:" + filters);
                tracer.getCurrentSpan().logEvent("sorts:" + sorts);
                throw new JiWeiException(e);
            }


            @Override
            public MixEnvelop createWechatMenu(String wechatCode) {
                return null;
            }

            @Override
            public MixEnvelop getParentMenu(@PathVariable String wechatCode) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("根据微信code查找父菜单失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("wechatCode:"+wechatCode);
                throw new JiWeiException(e);
            }

            @Override
            public MixEnvelop getChildMenus(@PathVariable(value = "parentCode", required = true) String parentCode) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("根据父级菜单code查找子菜单失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("parentCode:"+parentCode);
                throw new JiWeiException(e);
            }

            @Override
            public MixEnvelop getWxMenus(String fields, String filters, String sorts, int size, int page) throws JiWeiException {
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
