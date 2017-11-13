package com.yihu.jw.business.wx.dao;

import com.yihu.jw.base.wx.WxGraphicMessage;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Administrator on 2017/5/20 0020.
 */
public interface WxGraphicMessageDao extends PagingAndSortingRepository<WxGraphicMessage, String>, JpaSpecificationExecutor<WxGraphicMessage> {

    @Query("from WxWechat w where w.appId = ?1 and w.status!=-1")
    WxGraphicMessage findByAppId(String appId);


    @Query("from WxGraphicMessage w where w.id =?1 and w.status!=-1")
    WxGraphicMessage findById(String id);
}
