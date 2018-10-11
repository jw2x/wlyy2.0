package com.yihu.jw.base.dao.wx;//package com.yihu.jw.business.wx.dao;

import com.yihu.jw.entity.base.wx.WxGraphicMessageDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Administrator on 2017/5/20 0020.
 */
public interface WxGraphicMessageDao extends PagingAndSortingRepository<WxGraphicMessageDO, String>, JpaSpecificationExecutor<WxGraphicMessageDO> {

}
