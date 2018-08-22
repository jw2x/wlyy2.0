package com.yihu.jw.fegin.archives;

import com.yihu.jw.fegin.fallbackfactory.archives.ArchivesFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.archives.PatientArchivesInfoVO;
import com.yihu.jw.restmodel.archives.PatientArchivesVO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.rm.archives.PatientArchivesMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Trick on 2018/2/12.
 */
@FeignClient(
        name = CommonContants.svr_archives // name值是eurika的实例名字
        ,fallbackFactory  = ArchivesFallbackFactory.class
)
@RequestMapping(PatientArchivesMapping.api_archives_common)
public interface ArchivesFeign {

    @GetMapping(value = PatientArchivesMapping.Archives.findPatientArchives)
    public MixEnvelop<PatientArchivesVO, PatientArchivesVO> findPatientArchives(@RequestParam(value = "name", required = false)String name,
                                                             @RequestParam(value = "status", required = false)String status,
                                                             @RequestParam(value = "cancelReseanType", required = false)String cancelReseanType,
                                                             @RequestParam(value = "page", required = false)Integer page,
                                                             @RequestParam(value = "size", required = false )Integer size);

    @GetMapping(value = PatientArchivesMapping.Archives.findPatientArchivesInfos)
    public MixEnvelop<PatientArchivesInfoVO, PatientArchivesInfoVO> queryPatientArchivesInfoPage(@RequestParam(value = "code", required = false)String code);

    @PostMapping(value = PatientArchivesMapping.Archives.createPatientArchives)
    public MixEnvelop<Boolean, Boolean> createPatientArchives(@RequestParam(value = "patientArchives", required = true)String patientArchives,
                                                     @RequestParam(value = "list", required = true)String list);

    @PutMapping(value = PatientArchivesMapping.Archives.updatePatientArchives)
    public MixEnvelop<Boolean, Boolean> updatePatientArchives(@RequestParam(value = "patientArchives", required = true)String patientArchives,
                                                     @RequestParam(value = "list", required = true)String list);
}
