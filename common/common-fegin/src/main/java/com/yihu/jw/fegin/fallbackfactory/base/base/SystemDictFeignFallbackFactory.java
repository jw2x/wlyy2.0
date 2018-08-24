//package com.yihu.jw.fegin.fallbackfactory.base.base;
//
//import com.yihu.jw.exception.business.JiWeiException;
//import com.yihu.jw.fegin.base.base.SystemDictFeign;
//import com.yihu.jw.restmodel.common.Envelop;
//import feign.hystrix.FallbackFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.sleuth.Tracer;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Component
//public class SystemDictFeignFallbackFactory implements FallbackFactory<SystemDictFeign> {
//
//    @Autowired
//    private Tracer tracer;
//
//    @Override
//    public SystemDictFeign create(Throwable e) {
//        return new SystemDictFeign() {
//            @Override
//            public Envelop create(@RequestBody String jsonData) throws JiWeiException {
//                tracer.getCurrentSpan().logEvent("创建系統字典失败:原因:"+e.getMessage());
//                tracer.getCurrentSpan().logEvent("jsonData:"+jsonData);
//                throw new JiWeiException(e);
//            }
//
//            @Override
//            public Envelop update(@RequestBody String jsonData) throws JiWeiException {
//                tracer.getCurrentSpan().logEvent("更新系統字典失败:原因:"+e.getMessage());
//                tracer.getCurrentSpan().logEvent("jsonData:"+jsonData);
//                throw new JiWeiException(e);
//            }
//
//            @Override
//            public Envelop delete(@PathVariable String codes, @RequestParam String userCode, @RequestParam String userName) throws JiWeiException {
//                tracer.getCurrentSpan().logEvent("删除系統字典失败:原因:"+e.getMessage());
//                tracer.getCurrentSpan().logEvent("codes:"+codes);
//                throw new JiWeiException(e);
//            }
//
//            @Override
//            public Envelop findById(String code) throws JiWeiException {
//                tracer.getCurrentSpan().logEvent("查找系統字典失败:原因:"+e.getMessage());
//                tracer.getCurrentSpan().logEvent("code:"+code);
//                throw new JiWeiException(e);
//            }
//
//            @Override
//            public Envelop getList(String fields, String filterStr, String sorts, int size, int page) throws JiWeiException {
//                tracer.getCurrentSpan().logEvent("分页查找系統字典失败:原因:"+e.getMessage());
//                tracer.getCurrentSpan().logEvent("fields:" + fields);
//                tracer.getCurrentSpan().logEvent("filters:" + filterStr);
//                tracer.getCurrentSpan().logEvent("sorts:" + sorts);
//                tracer.getCurrentSpan().logEvent("size:" + size);
//                tracer.getCurrentSpan().logEvent("page:" + page);
//                throw new JiWeiException(e);
//            }
//
//            @Override
//            public Envelop getListNoPage(String fields, String filters, String sorts) throws JiWeiException {
//                tracer.getCurrentSpan().logEvent("查找系統字典列表失败:原因:"+e.getMessage());
//                tracer.getCurrentSpan().logEvent("fields:" + fields);
//                tracer.getCurrentSpan().logEvent("filters:" + filters);
//                tracer.getCurrentSpan().logEvent("sorts:" + sorts);
//                throw new JiWeiException(e);
//            }
//
//        };
//    }
//}
