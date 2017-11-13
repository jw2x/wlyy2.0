package com.yihu.jw.business.wx.dao;

import com.yihu.jw.base.wx.WxAccessToken;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18 0018.
 */
public interface WxAccessTokenDao extends PagingAndSortingRepository<WxAccessToken, String>, JpaSpecificationExecutor<WxAccessToken> {

    @Query("from WxAccessToken w where w.wechatCode =?1 order by w.addTimestamp desc")
    List<WxAccessToken> getWxAccessTokenByCode(String wechatCode);
}
