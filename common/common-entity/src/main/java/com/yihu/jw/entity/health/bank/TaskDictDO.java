package com.yihu.jw.entity.health.bank;/**
 * Created by nature of king on 2018/6/8.
 */

import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author wangzhinan
 * @create 2018-06-08 14:32
 * @desc 任务字典
 **/
@Entity
@Table(name = "wlyy_health_bank_task_dict" )
public class TaskDictDO extends IdEntityWithOperation implements Serializable {

    @Column(name = "saas_id")
    private  String saasId;//环境id

    @Column(name = "dictType")
    private String dictType;//字典类型

    @Column(name = "dict_code")
    private String dictCode;//字典code

    @Column(name = "dict_name")
    private String dictName;//字典名称

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }
}
