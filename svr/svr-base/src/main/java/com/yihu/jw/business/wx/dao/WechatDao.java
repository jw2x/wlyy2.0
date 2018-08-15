package com.yihu.jw.business.wx.dao;

import com.yihu.jw.base.wx.WxWechatDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/5/20 0020.
 */
public interface WechatDao extends PagingAndSortingRepository<WxWechatDO, String>, JpaSpecificationExecutor<WxWechatDO> {

    @Query("from WxWechatDO w where w.appId = ?1 and w.status!=-1")
    WxWechatDO findByAppId(String appId);

    @Query("from WxWechatDO w where w.appId = ?1 and w.id!= ?2 and w.status!=-1")
    WxWechatDO findByAppIdExcludeId(String appId, String id);

    @Query("from WxWechatDO w where w.id = ?1 and w.status!=-1")
    WxWechatDO findById(String id);

    @Query("from WxWechatDO w where w.status!=-1")
    List<WxWechatDO> findAll();
}
