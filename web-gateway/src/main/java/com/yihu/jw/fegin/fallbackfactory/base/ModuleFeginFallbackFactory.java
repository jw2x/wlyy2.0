package com.yihu.jw.fegin.fallbackfactory.base;

import com.yihu.jw.fegin.base.ModuleFegin;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.exception.business.JiWeiException;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class ModuleFeginFallbackFactory implements FallbackFactory<ModuleFegin> {


    @Autowired
    private Tracer tracer;

    @Override
    public ModuleFegin create(Throwable e) {
        return new ModuleFegin() {
            @Override
            public Envelop create(@RequestBody String jsonData) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("创建模块失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:"+jsonData);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop update(@RequestBody String jsonData) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("更新模块失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:"+jsonData);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop delete(@PathVariable String codes, @RequestParam String userCode, @RequestParam String userName) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("删除模块失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("codes:"+codes);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop findByCode(String code) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("查找模块失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("code:"+code);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop getList(String fields, String filterStr, String sorts, int size, int page) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("分页查找模块失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("fields:" + fields);
                tracer.getCurrentSpan().logEvent("filters:" + filterStr);
                tracer.getCurrentSpan().logEvent("sorts:" + sorts);
                tracer.getCurrentSpan().logEvent("size:" + size);
                tracer.getCurrentSpan().logEvent("page:" + page);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop getListNoPage(String fields, String filters, String sorts) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("查找模块列表失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("fields:" + fields);
                tracer.getCurrentSpan().logEvent("filters:" + filters);
                tracer.getCurrentSpan().logEvent("sorts:" + sorts);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop getChildren(String code) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("查找模块子项失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("code:" + code);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop getExistFunc(@PathVariable(value = "code") String code) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("查找模块已关联的功能失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("code:" + code);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop changeFun(@RequestBody String jsonData) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("更新已有模块功能失败:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                throw new JiWeiException(e);
            }
        };
    }
}
