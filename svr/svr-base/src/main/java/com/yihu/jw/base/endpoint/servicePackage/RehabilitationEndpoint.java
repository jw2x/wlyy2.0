package com.yihu.jw.base.endpoint.servicePackage;

import com.yihu.jw.base.service.servicePackage.ServicePackageService;
import com.yihu.jw.entity.base.servicePackage.ServicePackageDO;
import com.yihu.jw.entity.base.servicePackage.ServicePackageSignRecordDO;
import com.yihu.jw.restmodel.base.servicePackage.RehabilitationVO;
import com.yihu.jw.restmodel.base.servicePackage.ServicePackageLogVO;
import com.yihu.jw.restmodel.base.servicePackage.ServicePackageVO;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.base.BaseRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author yeshijie on 2018/8/30.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.BaseRehabilitation.PREFIX, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "康复计划管理", description = "康复计划管理服务接口", tags = {"wlyy基础服务 - 康复计划管理服务接口"})
public class RehabilitationEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private ServicePackageService servicePackageService;

    @PostMapping(value = BaseRequestMapping.BaseRehabilitation.CREATE)
    @ApiOperation(value = "创建")
    public Envelop create (
            @ApiParam(name = "jsonData", value = "Json数据", required = true)
            @RequestBody String jsonData) {
        try {
            RehabilitationVO rehabilitationVO = toEntity(jsonData, RehabilitationVO.class);
            ServicePackageSignRecordDO signRecordDO = convertToModel(rehabilitationVO.getSignRecordVO(), ServicePackageSignRecordDO.class);
            ServicePackageDO servicePackageDO = servicePackageService.addRehabilitation(rehabilitationVO,signRecordDO);
            return success(convertToModel(servicePackageDO, ServicePackageVO.class));
        }catch (Exception e){
            e.printStackTrace();
            return Envelop.getError("创建失败");
        }
    }

    @PostMapping(value = BaseRequestMapping.BaseRehabilitation.CREATELOG)
    @ApiOperation(value = "新增服务包日志")
    public Envelop addRehabilitationLog(@ApiParam(name = "jsonData", value = "Json数据", required = true)
                          @RequestBody String jsonData){
        try{
            ServicePackageLogVO logVO = toEntity(jsonData,ServicePackageLogVO.class);
            return success(servicePackageService.addRehabilitationLog(logVO));
        }catch (Exception e){
            e.printStackTrace();
            return Envelop.getError("新增失败");
        }
    }

    @GetMapping(value = BaseRequestMapping.BaseRehabilitation.FINDBYID)
    @ApiOperation(value = "查找完成度")
    public Envelop getFinish(@ApiParam(name = "servicePackId", value = "服务包id", required = true)
                             @RequestParam(value = "servicePackId") String servicePackId){
        try {
            return success(servicePackageService.getFinish(servicePackId));
        }catch (Exception e){
            e.printStackTrace();
            return Envelop.getError("获取失败");
        }
    }
}
