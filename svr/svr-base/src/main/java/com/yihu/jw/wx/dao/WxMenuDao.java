package com.yihu.jw.wx.dao;

import com.yihu.jw.wx.model.WxAccessToken;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Administrator on 2017/5/19 0019.
 */
public interface WxMenuDao extends PagingAndSortingRepository<WxAccessToken, Long>, JpaSpecificationExecutor<WxAccessToken> {

}
