package com.yihu.jw.entity.health.bank;/**
 * Created by nature of king on 2018/6/8.
 */

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author wangzhinan
 * @create 2018-06-08 14:40
 * @desc 活动商品
 **/
@Entity
@Table(name = "wlyy_health_bank_task_goods")
public class TaskGoodsDO extends UuidIdentityEntityWithOperator implements Serializable {

    @Column(name = "saas_id")
    private String saasId;//环境id

    @Column(name = "task_id")
    private String taskId;//任务id

    @Column(name = "img")
    private String img;//商品图片

    @Column(name = "name")
    private String name;//商品名称

    @Column(name = "integrate")
    private int integrate;//积分

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIntegrate() {
        return integrate;
    }

    public void setIntegrate(int integrate) {
        this.integrate = integrate;
    }
}
