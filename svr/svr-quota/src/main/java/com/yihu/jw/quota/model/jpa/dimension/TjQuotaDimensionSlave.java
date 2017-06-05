package com.yihu.jw.quota.model.jpa.dimension;// default package

import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import com.yihu.jw.quota.etl.Contant;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * TjQuotaDimensionSlave entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tj_quota_dimension_slave")
public class TjQuotaDimensionSlave implements java.io.Serializable {
    private Logger logger= LoggerFactory.getLogger(TjQuotaDimensionSlave.class);
    // Fields

    private Integer id;
    private String quotaCode;
    private String slaveCode;
    private String dictSql;//字典的sql
    private String convertClazz;//转换的类
    private String type;

    private String getOneDictSql;//得到分子的
    private String getTwoDictSql;//得到分母的
    private String getOneConvertClazz;//得到分子的
    private String getTwoConvertClazz;//得到分母的


    // Constructors

    /**
     * default constructor
     */
    public TjQuotaDimensionSlave() {
    }

    /**
     * full constructor
     */
    public TjQuotaDimensionSlave(String quotaCode, String slaveCode) {
        this.quotaCode = quotaCode;
        this.slaveCode = slaveCode;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "quota_code", length = 100)
    public String getQuotaCode() {
        return this.quotaCode;
    }

    public void setQuotaCode(String quotaCode) {
        this.quotaCode = quotaCode;
    }

    @Column(name = "slave_code", length = 100)
    public String getSlaveCode() {
        return this.slaveCode;
    }

    public void setSlaveCode(String slaveCode) {
        this.slaveCode = slaveCode;
    }

    public String getDictSql() {
        return dictSql;
    }

    public void setDictSql(String dictSql) {
        this.dictSql = dictSql;
    }

    @Transient
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getConvertClazz() {
        return convertClazz;
    }

    public void setConvertClazz(String convertClazz) {
        this.convertClazz = convertClazz;
    }

    @Transient
    public String getGetOneDictSql() {
        try {
            return JSONObject.fromObject(dictSql).getString(Contant.slave_dimension_key.one);
        } catch (Exception e) {
            logger.warn("OneDictSql error");
        }
        return null;
    }

    public void setGetOneDictSql(String getOneDictSql) {
        this.getOneDictSql = getOneDictSql;
    }

    @Transient
    public String getGetTwoDictSql() {
        try {
            return JSONObject.fromObject(dictSql).getString(Contant.slave_dimension_key.two);
        } catch (Exception e) {
            logger.warn("GetTwoDictSql error");
        }
        return null;
    }

    public void setGetTwoDictSql(String getTwoDictSql) {
        this.getTwoDictSql = getTwoDictSql;
    }

    @Transient
    public String getGetOneConvertClazz() {
        try {
            return JSONObject.fromObject(convertClazz).getString(Contant.slave_dimension_key.one);
        } catch (Exception e) {
            logger.warn("GetOneConvertClazz error");
        }
        return null;
    }

    public void setGetOneConvertClazz(String getOneConvertClazz) {
        this.getOneConvertClazz = getOneConvertClazz;
    }

    @Transient
    public String getGetTwoConvertClazz() {
        try {
            return JSONObject.fromObject(convertClazz).getString(Contant.slave_dimension_key.two);
        } catch (Exception e) {
            logger.warn("TwoConvertClazz error");
        }
        return null;
    }

    public void setGetTwoConvertClazz(String getTwoConvertClazz) {
        this.getTwoConvertClazz = getTwoConvertClazz;
    }
}