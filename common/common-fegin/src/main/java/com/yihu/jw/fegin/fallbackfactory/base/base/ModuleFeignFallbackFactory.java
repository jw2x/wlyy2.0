package com.yihu.jw.fegin.fallbackfactory.base.base;

import com.yihu.jw.exception.business.JiWeiException;
import com.yihu.jw.fegin.base.base.ModuleFeign;
import com.yihu.jw.restmodel.web.MixEnvelop;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class ModuleFeignFallbackFactory implements FallbackFactory<ModuleFeign> {


    @Autowired
    private Tracer tracer;

    @Override
    public ModuleFeign create(Throwable e) {
        return new ModuleFeign() {
            @Override
            public MixEnvelop create(@RequestBody String jsonData) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("创建模块失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:"+jsonData);
                throw new JiWeiException(e);
            }

            @Override
            public MixEnvelop update(@RequestBody String jsonData) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("更新模块失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:"+jsonData);
                throw new JiWeiException(e);
            }

            @Override
            public MixEnvelop delete(@PathVariable String codes, @RequestParam String userCode, @RequestParam String userName) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("删除模块失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("codes:"+codes);
                throw new JiWeiException(e);
            }

            @Override
            public MixEnvelop findById(String id) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("查找模块失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("id:"+id);
                throw new JiWeiException(e);
            }

            @Override
            public MixEnvelop getList(String fields, String filterStr, String sorts, int size, int page) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("分页查找模块失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("fields:" + fields);
                tracer.getCurrentSpan().logEvent("filters:" + filterStr);
                tracer.getCurrentSpan().logEvent("sorts:" + sorts);
                tracer.getCurrentSpan().logEvent("size:" + size);
                tracer.getCurrentSpan().logEvent("page:" + page);
                throw new JiWeiException(e);
            }

            @Override
            public MixEnvelop getListNoPage(String fields, String filters, String sorts) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("查找模块列表失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("fields:" + fields);
                tracer.getCurrentSpan().logEvent("filters:" + filters);
                tracer.getCurrentSpan().logEvent("sorts:" + sorts);
                throw new JiWeiException(e);
            }

            @Override
            public MixEnvelop getChildren(String code) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("查找模块子项失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("code:" + code);
                throw new JiWeiException(e);
            }

            @Override
            public MixEnvelop getExistFunc(@PathVariable(value = "code") String code) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("查找模块已关联的功能失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("code:" + code);
                throw new JiWeiException(e);
            }

            @Override
            public MixEnvelop changeFun(@RequestBody String jsonData) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("更新已有模块功能失败:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                throw new JiWeiException(e);
            }
        };
    }
}
