package com.yihu.jw.wx.dao;

import com.yihu.jw.wx.model.WxWechat;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/5/20 0020.
 */
public interface WechatDao extends PagingAndSortingRepository<WxWechat, Long>, JpaSpecificationExecutor<WxWechat> {

    @Query("from WxWechat w where w.appId = ?1 and w.status!=-1")
    WxWechat findByAppId(String appId);


    @Query("from WxWechat w where w.appId = ?1 and w.code!= ?2 and w.status!=-1")
    WxWechat findByAppIdExcludeCode(String appId, String code);

    @Query("from WxWechat w where w.code =?1 and w.status!=-1")
    WxWechat findByCode(String code);

    @Query("from WxWechat w where w.id = ?1 and w.status!=-1")
    WxWechat findById(Long id);

    @Query("from WxWechat w where w.status!=-1")
    List<WxWechat> findAll();
}
