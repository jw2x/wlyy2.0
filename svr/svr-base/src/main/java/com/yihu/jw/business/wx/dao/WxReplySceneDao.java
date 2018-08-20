package com.yihu.jw.business.wx.dao;

import com.yihu.jw.entity.base.wx.WxReplySceneDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Trick on 2018/8/16.
 */
public interface WxReplySceneDao extends PagingAndSortingRepository<WxReplySceneDO, String>, JpaSpecificationExecutor<WxReplySceneDO> {


    List<WxReplySceneDO> findByAppOriginIdAndMsgTypeAndStatus(String appOriginId,String msgType,Integer status);

    List<WxReplySceneDO> findByAppOriginIdAndMsgTypeAndEventAndStatus(String appOriginId,String msgType,String event,Integer status);
}
