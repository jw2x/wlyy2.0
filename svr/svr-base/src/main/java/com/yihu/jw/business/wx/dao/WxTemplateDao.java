package com.yihu.jw.business.wx.dao;

import com.yihu.jw.base.wx.WxTemplate;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/5/20 0020.
 */
public interface WxTemplateDao extends PagingAndSortingRepository<WxTemplate, Long>, JpaSpecificationExecutor<WxTemplate> {


    @Query("from WxTemplate w where w.id = ?1 and w.status =1")
    WxTemplate findById(String id);

    @Query("from WxTemplate w where w.wechatId = ?1 and w.status =1")
    List<WxTemplate> findByWxCode(String code);
}
