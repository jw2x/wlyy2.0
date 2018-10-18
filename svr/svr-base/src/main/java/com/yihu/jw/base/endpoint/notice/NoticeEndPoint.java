package com.yihu.jw.base.endpoint.notice;

import com.yihu.jw.base.service.notice.NoticeService;
import com.yihu.jw.base.util.ErrorCodeUtil;
import com.yihu.jw.entity.base.notice.NoticeDO;
import com.yihu.jw.exception.code.BaseErrorCode;
import com.yihu.jw.restmodel.base.notice.NoticeVO;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.ListEnvelop;
import com.yihu.jw.restmodel.web.ObjEnvelop;
import com.yihu.jw.restmodel.web.PageEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.base.BaseRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 公告通知
 * @author yeshijie on 2018/10/9.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.Notice.PREFIX)
@Api(value = "公告通知管理", description = "公告通知管理服务接口", tags = {"基础服务 - 公告通知管理服务接口"})
public class NoticeEndPoint extends EnvelopRestEndpoint {
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private ErrorCodeUtil errorCodeUtil;

    @PostMapping(value = BaseRequestMapping.Notice.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建")
    public ObjEnvelop<NoticeVO> create (
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestParam String jsonData) throws Exception {
        NoticeDO noticeDO = toEntity(jsonData, NoticeDO.class);
        if(StringUtils.isBlank(noticeDO.getTitle())||noticeDO.getTitle().length()>50){
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.Notice.LIMIT_TITLE), ObjEnvelop.class);
        }
        noticeDO = noticeService.addNotice(noticeDO);
        return success(noticeDO, NoticeVO.class);
    }

    @PostMapping(value = BaseRequestMapping.Notice.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
            @ApiParam(name = "id", value = "id", required = true)
            @RequestParam(value = "id") String id) {
        noticeService.deleteNotice(id);
        return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.Notice.RELEASE)
    @ApiOperation(value = "发布")
    public Envelop release(
            @ApiParam(name = "id", value = "id", required = true)
            @RequestParam(value = "id") String id) {

        NoticeDO noticeDO = noticeService.findById(id);
        if (NoticeDO.Status.released.getValue().equals(noticeDO.getStatus())){
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.Notice.HAD_RELEASE), Envelop.class);
        }
        if (NoticeDO.SendType.automatic_release.getValue().equals(noticeDO.getSendType())){
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.Notice.AUTO_RELEASE), Envelop.class);
        }
        noticeService.release(noticeDO);
        return success("发布成功");
    }

    @PostMapping(value = BaseRequestMapping.Notice.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public ObjEnvelop<NoticeVO> update (
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestParam String jsonData) throws Exception {
        NoticeDO noticeDO = toEntity(jsonData, NoticeDO.class);
        if (null == noticeDO.getId()) {
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.Common.ID_IS_NULL), ObjEnvelop.class);
        }
        if(StringUtils.isBlank(noticeDO.getTitle())||noticeDO.getTitle().length()>50){
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.Notice.LIMIT_TITLE), ObjEnvelop.class);
        }
        noticeDO = noticeService.updateNotice(noticeDO);
        return success(noticeDO, NoticeVO.class);
    }

    @GetMapping(value = BaseRequestMapping.Notice.PAGE)
    @ApiOperation(value = "获取分页")
    public PageEnvelop<NoticeVO> page (
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
        List<NoticeDO> noticeDOs = noticeService.search(fields, filters, sorts, page, size);
        int count = (int)noticeService.getCount(filters);
        return success(noticeDOs, count, page, size, NoticeVO.class);
    }

    @GetMapping(value = BaseRequestMapping.Notice.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<NoticeVO> list (
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<NoticeDO> noticeDOs = noticeService.search(fields, filters, sorts);
        return success(noticeDOs, NoticeVO.class);
    }

}
