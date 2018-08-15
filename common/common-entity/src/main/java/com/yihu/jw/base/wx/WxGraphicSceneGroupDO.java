package com.yihu.jw.base.wx;

import com.yihu.jw.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Trick on 2018/8/15.
 */
@Entity
@Table(name = "wx_graphic_scene_group")
public class WxGraphicSceneGroupDO extends IdEntity implements java.io.Serializable{

    private String wechat_id;//微信Id
    private String scene;//场景值(根据场景值组装图文消息)
    private String graphic_id;//场景关联微信图文素材id
}
