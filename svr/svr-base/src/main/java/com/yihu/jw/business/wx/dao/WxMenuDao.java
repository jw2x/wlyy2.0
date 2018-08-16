package com.yihu.jw.business.wx.dao;

import com.yihu.jw.base.wx.WxMenuDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/5/19 0019.
 */
public interface WxMenuDao  extends PagingAndSortingRepository<WxMenuDO, String>, JpaSpecificationExecutor<WxMenuDO> {

    @Query("FROM WxMenuDO w WHERE w.wechatId =?1 AND w.status ='1' AND w.supMenuid IS NULL ORDER BY w.sort ASC")
    List<WxMenuDO> findParentRootByWechatId(String wechatId);

    @Query("FROM WxMenuDO w WHERE w.wechatId =?1 AND w.status ='1' AND w.supMenuid IS NOT NULL ORDER BY w.sort ASC")
    List<WxMenuDO> findChilrenByWechatId(String wechatId);
}
