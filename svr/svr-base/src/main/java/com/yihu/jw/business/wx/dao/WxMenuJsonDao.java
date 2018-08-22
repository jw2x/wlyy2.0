package com.yihu.jw.business.wx.dao;

import com.yihu.jw.entity.base.wx.WxMenuJsonDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Trick on 2018/8/13.
 */
public interface WxMenuJsonDao extends PagingAndSortingRepository<WxMenuJsonDO, String>, JpaSpecificationExecutor<WxMenuJsonDO> {

    WxMenuJsonDO findByWechatIdAndStatus(String wechatId,Integer status);
}
