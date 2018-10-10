package com.yihu.jw.base.service.notice;

import com.yihu.jw.base.dao.notice.NoticeDao;
import com.yihu.jw.base.dao.notice.UserNoticeDao;
import com.yihu.jw.entity.base.notice.NoticeDO;
import com.yihu.jw.entity.base.notice.UserNoticeDO;
import com.yihu.jw.restmodel.base.notice.UserNoticeVO;
import com.yihu.jw.restmodel.web.PageEnvelop;
import com.yihu.mysql.query.BaseJpaService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户公告通知
 * @author yeshijie on 2018/9/30.
 */
@Service
public class UserNoticeService extends BaseJpaService<UserNoticeDO, UserNoticeDao> {

    @Autowired
    private UserNoticeDao userNoticeDao;
    @Autowired
    private NoticeDao noticeDao;
    @Autowired
    private JdbcTemplate jdbcTempalte;

    /**
     * 查看文章详情
     * @param id
     * @return
     */
    public NoticeDO findNoticeDetail(String id){
        UserNoticeDO userNoticeDO = userNoticeDao.findOne(id);
        if(UserNoticeDO.Read.unRead.getValue().equals(userNoticeDO.getIsRead())){
            userNoticeDO.setIsRead(UserNoticeDO.Read.read.getValue());
            userNoticeDao.save(userNoticeDO);
        }
        NoticeDO noticeDO = noticeDao.findOne(userNoticeDO.getNoticeId());
        return noticeDO;
    }

    /**
     * 按类型分页查找
     * @param page
     * @param size
     * @param userId
     * @return
     */
    public PageEnvelop<UserNoticeVO> queryPage(Integer page, Integer size, String userId){
        StringBuffer sql = new StringBuffer("SELECT n.id noticeId,n.title,n.send_time sendTime,u.id,u.is_read isRead,u.user_id userId from base_notice n , base_user_notice u WHERE n.del = '1' ");
        StringBuffer sqlCount = new StringBuffer("SELECT COUNT(*) count from base_notice n , base_user_notice u WHERE n.del = '1' ");
        List<Object> args = new ArrayList<>();
        if(StringUtils.isNotBlank(userId)){
            sql.append(" and u.user_id=? ");
            sqlCount.append(" and u.user_id='").append(userId).append("' ");
            args.add(userId);
        }
        sql.append(" and u.notice_id = n.id and u.del = '1' ");
        sqlCount.append(" and u.notice_id = n.id and u.del = '1'");
        sql.append(" ORDER BY n.send_time desc limit ").append((page-1)*size).append(",").append(size);

        List<UserNoticeVO> list = jdbcTempalte.query(sql.toString(),args.toArray(),new BeanPropertyRowMapper(UserNoticeVO.class));
        List<Map<String,Object>> countList = jdbcTempalte.queryForList(sqlCount.toString());
        long count = Long.valueOf(countList.get(0).get("count").toString());

        return PageEnvelop.getSuccessListWithPage("查找成功",list, page, size,count);
    }



}
