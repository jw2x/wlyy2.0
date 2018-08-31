package com.yihu.jw.entity.base.servicePackage;

import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 服务包sku表
 * @author yeshijie on 2018/8/29.
 */
@Entity
@Table(name = "base_service_package_sku")
public class ServicePackageSkuDO extends UuidIdentityEntity implements Serializable {

    private String saasId;//
    private String servicePackageId;//服务包id',
    private String properties;//sku的销售属性组合字符串（颜色，大小，等等，可通过类目API获取某类目下的销售属性）,格式是p1:v1;p2:v2',
    private Long quantity;//属于这个sku的商品的数量，',
    private Long price;//属于这个sku的商品的价格 取值范围:0-100000000;单位:分。如:200，表示:2元。',
    private String propertiesName;//sku所对应的销售属性的中文名字串，格式如：pid1:vid1:pid_name1:vid_name1;pid2:vid2:pid_name2:vid_name2……',
    private String barcode;//商品级别的条形码',
    private Long minPrice;//促销价',
    private Date createTime;//sku创建日期 时间格式：yyyy-MM-dd HH:mm:ss',
    private Date updateTime;//sku最后修改日期 时间格式：yyyy-MM-dd HH:mm:ss',
    private Integer del;//sku状态。 1:正常 ；0:删除',

    @Column(name = "saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "service_package_id")
    public String getServicePackageId() {
        return servicePackageId;
    }

    public void setServicePackageId(String servicePackageId) {
        this.servicePackageId = servicePackageId;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Column(name = "properties_name")
    public String getPropertiesName() {
        return propertiesName;
    }

    public void setPropertiesName(String propertiesName) {
        this.propertiesName = propertiesName;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @Column(name = "min_price")
    public Long getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Long minPrice) {
        this.minPrice = minPrice;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "update_time")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}
