package com.yihu.jw.base.dao.wx;

import com.yihu.jw.entity.base.wx.WxGraphicSceneGroupDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Trick on 2018/8/16.
 */
public interface WxGraphicSceneGroupDao extends PagingAndSortingRepository<WxGraphicSceneGroupDO, String>, JpaSpecificationExecutor<WxGraphicSceneGroupDO> {

    List<WxGraphicSceneGroupDO> findByWechatIdAndScene(String wechatId, String scene);

    WxGraphicSceneGroupDO findByWechatIdAndSceneAndGraphicId(String wechatId, String scene,String graphicId);
}
