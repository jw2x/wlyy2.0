package com.yihu.jw.base.service.notice;

import com.yihu.jw.base.dao.notice.NoticeDao;
import com.yihu.jw.base.dao.notice.UserNoticeDao;
import com.yihu.jw.base.dao.user.UserDao;
import com.yihu.jw.base.util.threadPool.ThreadPoolUtil;
import com.yihu.jw.base.util.delay.DelayService;
import com.yihu.jw.base.util.delay.Notice;
import com.yihu.jw.base.util.delay.OnDelayedListener;
import com.yihu.jw.base.util.delay.RedisLock;
import com.yihu.jw.entity.base.notice.NoticeDO;
import com.yihu.jw.entity.base.notice.UserNoticeDO;
import com.yihu.jw.entity.base.user.UserDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.yihu.jw.entity.base.notice.NoticeDO.Status.released;

/**
 * 公告通知
 * @author yeshijie on 2018/9/30.
 */
@Service
public class NoticeService extends BaseJpaService<NoticeDO, NoticeDao> {

    @Autowired
    private NoticeDao noticeDao;
    @Autowired
    private UserNoticeDao userNoticeDao;
    @Autowired
    private DelayService delayService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ThreadPoolUtil threadPoolUtil;

    /**
     * redis锁的key
     */
    private final String rediskey = "notice:";

    /**
     * 发布公告
     * @param noticeDO
     */
    @Transactional(rollbackFor = Exception.class)
    public void release(NoticeDO noticeDO){
        noticeDO.setStatus(NoticeDO.Status.released.getValue());
        noticeDO.setSendTime(new Date());

        List<UserDO> userDOList = userDao.findByEnabled(true);
        List<UserNoticeDO> userNoticeDOs = new ArrayList<>(userDOList.size());
        userDOList.forEach(userDO -> {
            UserNoticeDO userNoticeDO = new UserNoticeDO();
            userNoticeDO.setDel(1);
            userNoticeDO.setIsRead(UserNoticeDO.Read.unRead.getValue());
            userNoticeDO.setNoticeId(noticeDO.getId());
            userNoticeDO.setUserId(userDO.getId());
            userNoticeDOs.add(userNoticeDO);
        });
        userNoticeDao.save(userNoticeDOs);
        noticeDao.save(noticeDO);
    }

    /**
     * 按id查找
     * @param id
     * @return
     */
    public NoticeDO findById(String id){
        return noticeDao.findOne(id);
    }

    /**
     * 新增消息公告
     * @param noticeDO
     * @return
     */
    public NoticeDO addNotice(NoticeDO noticeDO){
        noticeDO.setDel(1);
        noticeDO.setStatus(NoticeDO.Status.wait.getValue());
        noticeDao.save(noticeDO);
        //自动发布
        if(NoticeDO.SendType.automatic_release.getValue().equals(noticeDO.getSendType())){
            Notice notice = new Notice(noticeDO.getId(), noticeDO.getSendTime().getTime());
            delayService.add(notice);
        }
        return noticeDO;
    }

    /**
     * 修改公告
     * @param noticeDO
     * @return
     */
    public NoticeDO updateNotice(NoticeDO noticeDO){
        NoticeDO noticeOld = noticeDao.findOne(noticeDO.getId());
        if(released.getValue().equals(noticeDO.getStatus())){
            //已发布的不能修改
            return noticeDO;
        }
        if(!noticeOld.getSendType().equals(noticeDO.getSendType())){
            if(NoticeDO.SendType.automatic_release.getValue().equals(noticeDO.getSendType())){
                //自动发布
                Notice notice = new Notice(noticeDO.getId(), noticeDO.getSendTime().getTime());
                delayService.add(notice);
            }else {
                //手动发布
                delayService.remove(noticeDO.getId());
            }

        }
        noticeOld.setContent(noticeDO.getContent());
        noticeOld.setTitle(noticeDO.getTitle());
        noticeOld.setSendTime(noticeDO.getSendTime());
        noticeOld.setSendType(noticeDO.getSendType());
        noticeDao.save(noticeOld);

        return noticeDO;
    }

    /**
     * 删除
     * @param noticeId
     */
    public void deleteNotice(String noticeId){
        NoticeDO noticeDO = noticeDao.findOne(noticeId);
        noticeDO.setDel(0);
        noticeDao.save(noticeDO);
    }

    /**
     * 初始化延迟队列，项目启动的时候吧数据库的数据重新加入到队列中
     */
    public void initDelayNotice(){
        //启动监听
        delayService.start(new OnDelayedListener() {
            @Override
            public void onDelayedArrived(final Notice notice) {
                //异步来做
                threadPoolUtil.execute(new Runnable() {
                    @Override
                    public void run() {
                        String noticeId = notice.getNoticeId();
                        //分布式事务锁，防止代码在多台机器上执行
                        RedisLock lock = new RedisLock(redisTemplate, rediskey+noticeId, 10000, 20000);
                        try {
                            if(lock.lock()) {
                                //需要加锁的代码
                                NoticeDO noticeDO = noticeDao.findOne(noticeId);
                                if (NoticeDO.Status.wait.getValue().equals(noticeDO.getStatus())
                                        &&NoticeDO.SendType.automatic_release.getValue().equals(noticeDO.getSendType())){
                                    release(noticeDO);
                                }
                            }
                        } catch (InterruptedException e){
                            e.printStackTrace();
                        } finally {
                            lock.unlock();
                        }
                    }
                });
            }
        });

        //查找需要加入队列的数据
        List<NoticeDO> noticeDOList = noticeDao.findByStatusAndSendType(NoticeDO.Status.wait.getValue(),
                NoticeDO.SendType.automatic_release.getValue());
        noticeDOList.forEach(noticeDO -> {
            Notice notice = new Notice(noticeDO.getId(), noticeDO.getSendTime().getTime());
            delayService.add(notice);
        });
    }

}
