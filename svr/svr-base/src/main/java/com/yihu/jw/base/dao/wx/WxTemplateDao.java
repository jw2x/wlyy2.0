package com.yihu.jw.base.dao.wx;//package com.yihu.jw.business.wx.dao;
//
//import com.yihu.jw.base.wx.WxTemplateDO;
//import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.PagingAndSortingRepository;
//
//import java.util.List;
//
///**
// * Created by Administrator on 2017/5/20 0020.
// */
//public interface WxTemplateDao extends PagingAndSortingRepository<WxTemplateDO, String>, JpaSpecificationExecutor<WxTemplateDO> {
//
//
//    @Query("from WxTemplateDO w where w.id = ?1 and w.status =1")
//    WxTemplateDO findById(String id);
//
//    @Query("from WxTemplateDO w where w.wechatId = ?1 and w.status =1")
//    List<WxTemplateDO> findByWxId(String wechatId);
//}
