package com.yihu.iot.controller.label;

import com.yihu.iot.service.label.FigureLabelSerachService;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.iot.IotRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import iot.device.FigureLabelDataModelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author lith on 2018/03/16
 */
@RestController
@RequestMapping(IotRequestMapping.Common.figereLabel)
@Api(tags = "居民标签查询控制器", description = "居民标签查询控制器")
public class FigureLabelSearchController extends EnvelopRestEndpoint {

    @Autowired
    private FigureLabelSerachService figureLabelSerachService;

    @PostMapping(value = IotRequestMapping.FigureLabelInfo.api_getByIdcard,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据idcard查询居民标签", notes = "根据idcard查询居民标签")
    public MixEnvelop<List<FigureLabelDataModelVO>, List<FigureLabelDataModelVO>> findByIdcard(@ApiParam(name = "jsonData", value = "", defaultValue = "") @RequestBody String jsonData) {
        try {
            return MixEnvelop.getSuccess(IotRequestMapping.FigureLabelInfo.message_success_find, figureLabelSerachService.getFigureLabelByIdcard(jsonData));
        } catch (Exception e) {
            return MixEnvelop.getError(e.getMessage());
        }
    }


    @PostMapping(value = IotRequestMapping.FigureLabelInfo.api_getByTypeAndCode,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据type和code居民标签", notes = "根据type和code居民标签")
    public MixEnvelop<List<FigureLabelDataModelVO>, List<FigureLabelDataModelVO>> findByTypeAndCode(@ApiParam(name = "jsonData", value = "", defaultValue = "") @RequestBody String jsonData) {
        try {
            return MixEnvelop.getSuccess(IotRequestMapping.FigureLabelInfo.message_success_find, figureLabelSerachService.getFigureLabelByLabel(jsonData));
        } catch (Exception e) {
            return MixEnvelop.getError(e.getMessage());
        }
    }

}
