package com.yihu.jw.base.dao.wx;

import com.yihu.jw.entity.base.wx.WxGraphicSceneDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Administrator on 2018/10/9.
 */
public interface WxGraphicSceneDao extends PagingAndSortingRepository<WxGraphicSceneDO, String>, JpaSpecificationExecutor<WxGraphicSceneDO> {

    List<WxGraphicSceneDO> findByWechatIdAndScene(String wechatId,String scene);
}
