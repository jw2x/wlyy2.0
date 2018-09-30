package com.yihu.jw.base.dao.notice;

import com.yihu.jw.entity.base.notice.NoticeDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 公告通知
 * @author yeshijie on 2018/9/30.
 */
public interface NoticeDao extends PagingAndSortingRepository<NoticeDO, String>, JpaSpecificationExecutor<NoticeDO> {


}
