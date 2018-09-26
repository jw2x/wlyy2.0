package com.yihu.jw.healthyhouse.model.user;


import com.yihu.jw.entity.UuidIdentityEntityWithOperator;
import org.hibernate.annotations.Formula;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 账号申诉
 * @author zdm
 * @version 1.0
 * @created 2018.09.26
 */
@Entity
@Table(name = "appeal")
public class Appeal extends UuidIdentityEntityWithOperator {
    //账号申诉类型：0手机号无法使用，1收不到验证码，3其他
    @Column(name = "type")
    private String type;
    //问题和建议
    @Column(name = "content")
    private String content;
    //用户电话号码
    @Column(name = "user_telephone")
    private String userTelephone;
    //图片路径
    @Column(name = "img_path")
    private String imgPath;
    // 处理状态：0未阅，1待处理，2已处理，3废弃
    @Column(name = "flag")
    private Integer flag;
    //处理备注
    @Column(name = "reply_content")
    private String replyContent;
    //账号申诉类型值：0手机号无法使用，1收不到验证码，3其他
    private String typeValue;
    // 处理状态值：0未阅，1待处理，2已处理，3废弃
    private String flagValue;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserTelephone() {
        return userTelephone;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }
    @Formula("( SELECT a.value FROM system_dict_entries a WHERE a.dict_id = 9 AND a.code = type )")
    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }
    @Formula("( SELECT a.value FROM system_dict_entries a WHERE a.dict_id = 10 AND a.code = flag )")
    public String getFlagValue() {
        return flagValue;
    }

    public void setFlagValue(String flagValue) {
        this.flagValue = flagValue;
    }
}
