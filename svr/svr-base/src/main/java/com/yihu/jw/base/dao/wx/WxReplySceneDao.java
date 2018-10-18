package com.yihu.jw.base.dao.wx;

import com.yihu.jw.entity.base.wx.WxReplySceneDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Trick on 2018/8/16.
 */
public interface WxReplySceneDao extends PagingAndSortingRepository<WxReplySceneDO, String>, JpaSpecificationExecutor<WxReplySceneDO> {


    List<WxReplySceneDO> findByAppOriginIdAndMsgTypeAndStatus(String appOriginId, String msgType, Integer status);

    List<WxReplySceneDO> findByAppOriginIdAndMsgTypeAndEventAndStatus(String appOriginId, String msgType, String event, Integer status);

    //text类型，判断同关键字的事件是否存在
    List<WxReplySceneDO> findByWechatIdAndMsgTypeAndContentAndStatus(String wechatId,String msgType,String content,Integer status);

    //关注事件，判断关注事件是否存在
    List<WxReplySceneDO> findByWechatIdAndMsgTypeAndEventAndStatus(String wechatId,String msgType,String event,Integer status);

    //点击事件，扫码事件，是否存在相同场景值
    List<WxReplySceneDO> findByWechatIdAndMsgTypeAndEventAndSceneAndStatus(String wechatId,String msgType,String event,String scene,Integer status);


}
