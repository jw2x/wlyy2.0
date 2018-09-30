package com.yihu.jw.base.dao.notice;

import com.yihu.jw.entity.base.notice.UserNoticeDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 用户公告通知
 * @author yeshijie on 2018/9/30.
 */
public interface UserNoticeDao extends PagingAndSortingRepository<UserNoticeDO, String>, JpaSpecificationExecutor<UserNoticeDO> {


}
