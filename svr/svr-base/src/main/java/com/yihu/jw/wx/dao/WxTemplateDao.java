package com.yihu.jw.wx.dao;

import com.yihu.jw.wx.model.WxMenu;
import com.yihu.jw.wx.model.WxTemplate;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/5/20 0020.
 */
public interface WxTemplateDao extends PagingAndSortingRepository<WxTemplate, Long>, JpaSpecificationExecutor<WxTemplate> {

    @Query("from WxTemplate w where w.code = ?1 and w.status =1")
    WxTemplate findByCode(String code);

    @Query("from WxTemplate w where w.id = ?1 and w.status =1")
    WxTemplate findById(Long id);

    @Query("from WxTemplate w where w.wechatCode = ?1 and w.status =1")
    List<WxTemplate> findByWxCode(String code);
}
