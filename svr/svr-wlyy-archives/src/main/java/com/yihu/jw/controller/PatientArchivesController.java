package com.yihu.jw.controller;

import com.yihu.jw.entity.archives.PatientArchives;
import com.yihu.jw.restmodel.archives.Test;
import com.yihu.jw.rm.archives.PatientArchivesMapping;
import com.yihu.jw.rm.iot.IotRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Trick on 2018/2/7.
 */
@RestController
@RequestMapping(PatientArchivesMapping.api_archives_common)
@Api(tags = "居民建档相关操作", description = "居民建档相关操作")
public class PatientArchivesController {

    @GetMapping(value = "test")
    @ApiOperation(value = "测试")
    public Test test(){
        Test t = new Test();
        t.setFileName("11");
        t.setFileType("1");
        return t;
    }
}
