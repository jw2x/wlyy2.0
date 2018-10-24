package com.yihu.jw.base.endpoint.notice;

import com.yihu.jw.base.service.notice.UserNoticeService;
import com.yihu.jw.entity.base.notice.NoticeDO;
import com.yihu.jw.restmodel.base.notice.NoticeVO;
import com.yihu.jw.restmodel.base.notice.UserNoticeVO;
import com.yihu.jw.restmodel.web.ObjEnvelop;
import com.yihu.jw.restmodel.web.PageEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.base.BaseRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author yeshijie on 2018/10/9.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.UserNotice.PREFIX)
@Api(value = "用户公告通知管理", description = "用户公告通知管理服务接口", tags = {"基础服务 - 用户公告通知管理服务接口"})
public class UserNoticeEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private UserNoticeService userNoticeService;


    @GetMapping(value = BaseRequestMapping.Module.FINDBYID)
    @ApiOperation(value = "查看文章详情")
    public ObjEnvelop<NoticeVO> findById(
            @ApiParam(name = "id", value = "id", required = true)
            @RequestParam(value = "id") String id) {
        NoticeDO noticeDO = userNoticeService.findNoticeDetail(id);
        return success(noticeDO, NoticeVO.class);
    }

    @GetMapping(value = BaseRequestMapping.Module.PAGE)
    @ApiOperation(value = "获取分页") 
    public PageEnvelop<UserNoticeVO> page(
            @ApiParam(name = "page", value = "分页大小", required = true, defaultValue = "1")
            @RequestParam(value = "page") int page,
            @ApiParam(name = "size", value = "页码", required = true, defaultValue = "15")
            @RequestParam(value = "size") int size) throws Exception {
        String userId = getUID();
        if(StringUtils.isBlank(userId)){
            return failed("用户信息获取失败！",PageEnvelop.class);
        }
        return userNoticeService.queryPage(page, size, userId);
    }

}
