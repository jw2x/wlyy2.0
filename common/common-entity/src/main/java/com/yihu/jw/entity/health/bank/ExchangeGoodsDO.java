package com.yihu.jw.entity.health.bank;/**
 * Created by nature of king on 2018/5/3.
 */

import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @author wangzhinan
 * @create 2018-05-03 15:17
 * @desc exchange goods
 **/
@Entity
@Table(name = "wlyy_health_bank_exchange_goods")
public class ExchangeGoodsDO extends IdEntityWithOperation implements Serializable {

    @Column(name = "saas_id")
    private String saasId;

    @Column(name = "goods_id")
    private String goodsId;

    @Column(name = "integrate")
    private String integrate;

    @Column(name = "patient_id")
    private String patientId;

    @Transient
    private GoodsDO goodsDO;


    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getIntegrate() {
        return integrate;
    }

    public void setIntegrate(String integrate) {
        this.integrate = integrate;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public GoodsDO getGoodsDO() {
        return goodsDO;
    }

    public void setGoodsDO(GoodsDO goodsDO) {
        this.goodsDO = goodsDO;
    }
}
