package com.yihu.jw.base.endpoint.errorCode;

import com.yihu.jw.base.service.errorCode.ErrorCodeService;
import com.yihu.jw.base.util.ErrorCodeUtil;
import com.yihu.jw.entity.base.dict.ErrorCodeDO;
import com.yihu.jw.exception.code.BaseErrorCode;
import com.yihu.jw.restmodel.base.dict.ErrorCodeVO;
import com.yihu.jw.restmodel.web.ListEnvelop;
import com.yihu.jw.restmodel.web.ObjEnvelop;
import com.yihu.jw.restmodel.web.PageEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.base.BaseRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yeshijie on 2018/9/26.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.ErrorCode.PREFIX)
@Api(value = "错误码管理", description = "错误码管理服务接口", tags = {"基础服务 - 错误码管理服务接口"})
public class ErrorCodeEndpoint extends EnvelopRestEndpoint {
    @Autowired
    private ErrorCodeService errorCodeService;
    @Autowired
    private ErrorCodeUtil errorCodeUtil;

    @PostMapping(value = BaseRequestMapping.Menu.CREATE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建")
    public ObjEnvelop<ErrorCodeVO> create (
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        ErrorCodeDO errorCodeDO = toEntity(jsonData, ErrorCodeDO.class);
        if(errorCodeService.isExistsErrorCode(errorCodeDO.getErrorCode())>0){
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.ErrorCode.IS_EXIST), ObjEnvelop.class);
        }
        errorCodeDO = errorCodeService.save(errorCodeDO);
        return success(errorCodeDO, ErrorCodeVO.class);
    }

    @PostMapping(value = BaseRequestMapping.Menu.UPDATE)
    @ApiOperation(value = "更新")
    public ObjEnvelop<ErrorCodeVO> update (
            @ApiParam(name = "id", value = "id")
            @RequestParam(value = "id", required = true) String id,
            @ApiParam(name = "errorMsg", value = "错误码")
            @RequestParam(value = "errorMsg", required = true) String errorMsg) throws Exception {

        if (null == id) {
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.Common.ID_IS_NULL), ObjEnvelop.class);
        }
        ErrorCodeDO errorCodeDO = errorCodeService.updateMsg(id, errorMsg);
        return success(errorCodeDO, ErrorCodeVO.class);
    }

    @GetMapping(value = BaseRequestMapping.Menu.PAGE)
    @ApiOperation(value = "获取分页")
    public PageEnvelop<ErrorCodeVO> page (
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "page", value = "分页大小", required = true, defaultValue = "1")
            @RequestParam(value = "page") int page,
            @ApiParam(name = "size", value = "页码", required = true, defaultValue = "15")
            @RequestParam(value = "size") int size) throws Exception {
        List<ErrorCodeDO> errorCodeDOS = errorCodeService.search(fields, filters, sorts, page, size);
        int count = (int)errorCodeService.getCount(filters);
        return success(errorCodeDOS, count, page, size, ErrorCodeVO.class);
    }

    @GetMapping(value = BaseRequestMapping.Menu.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<ErrorCodeVO> list (
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<ErrorCodeDO> errorCodeDOS = errorCodeService.search(fields, filters, sorts);
        return success(errorCodeDOS, ErrorCodeVO.class);
    }
}
