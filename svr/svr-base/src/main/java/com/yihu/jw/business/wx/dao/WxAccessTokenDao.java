package com.yihu.jw.business.wx.dao;

import com.yihu.jw.base.wx.WxAccessTokenDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18 0018.
 */
public interface WxAccessTokenDao extends PagingAndSortingRepository<WxAccessTokenDO, String>, JpaSpecificationExecutor<WxAccessTokenDO> {

    @Query("from WxAccessTokenDO w where w.wechatId =?1 order by w.addTimestamp desc")
    List<WxAccessTokenDO> getWxAccessTokenById(String wechatId);
}
