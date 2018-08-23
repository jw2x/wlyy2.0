package com.yihu.jw.entity.specialist;/**
 * Created by nature of king on 2018/8/16.
 */

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author wangzhinan
 * @create 2018-08-16 10:36
 * @desc 服务项目修改记录
 **/
@Entity
@Table(name = "wlyy_service_item_operate_log")
public class SpecialistServiceItemOperateLogDO extends UuidIdentityEntityWithOperator implements Serializable {

    @Column(name = "saas_id")
    private String saasId;

    @Column(name = "service_item_id")
    private String serviceItemId; //服务项目id

    @Column(name = "operate_log")
    private String operateLog; //操作日志

    @Column(name = "status")
    private Integer status;//删除状态

    @Column(name = "saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "service_item_id")
    public String getServiceItemId() {
        return serviceItemId;
    }

    public void setServiceItemId(String serviceItemId) {
        this.serviceItemId = serviceItemId;
    }

    @Column(name = "operate_log")
    public String getOperateLog() {
        return operateLog;
    }

    public void setOperateLog(String operateLog) {
        this.operateLog = operateLog;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}
