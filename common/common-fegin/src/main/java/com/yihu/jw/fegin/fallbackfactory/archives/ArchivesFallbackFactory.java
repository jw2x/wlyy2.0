package com.yihu.jw.fegin.fallbackfactory.archives;

import com.yihu.jw.fegin.archives.ArchivesFeign;
import com.yihu.jw.restmodel.archives.PatientArchivesInfoVO;
import com.yihu.jw.restmodel.archives.PatientArchivesVO;
import com.yihu.jw.restmodel.common.Envelop;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Trick on 2018/2/22.
 */
@Component
public class ArchivesFallbackFactory implements FallbackFactory<ArchivesFeign> {
    @Autowired
    private Tracer tracer;

    @Override
    public ArchivesFeign create(Throwable e){
        return new ArchivesFeign() {
            @Override
            public Envelop<PatientArchivesVO> findPatientArchives(@RequestParam(value = "name", required = false)String name,
                                                                  @RequestParam(value = "status", required = false)String status,
                                                                  @RequestParam(value = "cancelReseanType", required = false)String cancelReseanType,
                                                                  @RequestParam(value = "page", required = false)Integer page,
                                                                  @RequestParam(value = "size", required = false )Integer size){
                tracer.getCurrentSpan().logEvent("查找档案列表失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("name:" + name);
                tracer.getCurrentSpan().logEvent("status:" + status);
                tracer.getCurrentSpan().logEvent("cancelReseanType:" + cancelReseanType);
                tracer.getCurrentSpan().logEvent("page:" + page);
                tracer.getCurrentSpan().logEvent("size:" + size);
                return null;
            }
            @Override
            public Envelop<PatientArchivesInfoVO> queryPatientArchivesInfoPage(@RequestParam(value = "code", required = false)String code){
                tracer.getCurrentSpan().logEvent("查找档案详情接口失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("code:" + code);
                return null;
            }

            @Override
            public Envelop<Boolean> createPatientArchives(@RequestParam(value = "patientArchives", required = true)String patientArchives,
                                                          @RequestParam(value = "list", required = true)String list){
                tracer.getCurrentSpan().logEvent("创建失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("patientArchives:" + patientArchives);
                tracer.getCurrentSpan().logEvent("list:" + list);
                return null;
            }

            @Override
            public Envelop<Boolean> updatePatientArchives(@RequestParam(value = "patientArchives", required = true)String patientArchives,
                                                          @RequestParam(value = "list", required = true)String list){
                tracer.getCurrentSpan().logEvent("更新档案失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("patientArchives:" + patientArchives);
                tracer.getCurrentSpan().logEvent("list:" + list);
                return null;
            }
        };
    }
}
