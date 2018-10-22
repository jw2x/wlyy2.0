package com.yihu.jw.base.dao.wx;


import com.yihu.jw.entity.base.wx.WxTemplateConfigDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Trick on 2018/8/21.
 */
public interface WxTemplateConfigDao extends PagingAndSortingRepository<WxTemplateConfigDO, String>, JpaSpecificationExecutor<WxTemplateConfigDO> {

    WxTemplateConfigDO findByWechatIdAndTemplateNameAndSceneAndStatus(String wechatId, String templateName, String scene,Integer status);
    WxTemplateConfigDO findByWechatIdAndTemplateNameAndScene(String wechatId, String templateName, String scene);
    List<WxTemplateConfigDO> findByWechatIdAndScene(String wechatId,String scene);

}
