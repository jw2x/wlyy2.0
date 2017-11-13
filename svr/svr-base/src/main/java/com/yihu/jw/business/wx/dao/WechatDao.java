package com.yihu.jw.business.wx.dao;

import com.yihu.jw.base.wx.WxWechat;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/5/20 0020.
 */
public interface WechatDao extends PagingAndSortingRepository<WxWechat, String>, JpaSpecificationExecutor<WxWechat> {

    @Query("from WxWechat w where w.appId = ?1 and w.status!=-1")
    WxWechat findByAppId(String appId);


    @Query("from WxWechat w where w.appId = ?1 and w.id!= ?2 and w.status!=-1")
    WxWechat findByAppIdExcludeId(String appId, String id);

    @Query("from WxWechat w where w.id = ?1 and w.status!=-1")
    WxWechat findById(String id);

    @Query("from WxWechat w where w.status!=-1")
    List<WxWechat> findAll();
}
