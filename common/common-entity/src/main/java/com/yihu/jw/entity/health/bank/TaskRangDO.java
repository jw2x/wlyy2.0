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
 * @create 2018-06-08 14:43
 * @desc 活动范围
 **/
@Entity
@Table(name = "wlyy_health_bank_task_rang")
public class TaskRangDO extends UuidIdentityEntityWithOperator implements Serializable {

    @Column(name = "saas_id")
    private String saasId;//环境id

    @Column(name = "task_id")
    private String taskId;//任务id

    @Column(name = "key")
    private String key;//范围KEY

    @Column(name = "value")
    private String value;// 范围value

    @Column(name = "status")
    private int status;//状态

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
