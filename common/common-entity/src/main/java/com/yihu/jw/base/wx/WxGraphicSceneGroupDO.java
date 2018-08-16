package com.yihu.jw.base.wx;

import com.yihu.jw.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Trick on 2018/8/15.
 */
@Entity
@Table(name = "wx_graphic_scene_group")
public class WxGraphicSceneGroupDO extends IdEntity implements java.io.Serializable{

    private String wechatId;//微信Id
    private String scene;//场景值(根据场景值组装图文消息)
    private String graphicId;//场景关联微信图文素材id
    private Integer sort; //排序字段

    @Column(name = "wechat_id")
    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    @Column(name = "scene")
    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    @Column(name = "graphic_id")
    public String getGraphicId() {
        return graphicId;
    }

    public void setGraphicId(String graphicId) {
        this.graphicId = graphicId;
    }

    @Column(name = "sort")
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
