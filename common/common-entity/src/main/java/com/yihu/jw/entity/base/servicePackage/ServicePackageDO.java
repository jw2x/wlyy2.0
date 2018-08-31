package com.yihu.jw.entity.base.servicePackage;

import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 服务包表
 * @author yeshijie on 2018/8/17.
 */
@Entity
@Table(name = "base_service_package")
public class ServicePackageDO extends UuidIdentityEntity implements Serializable {

    public enum Level {
        system("系统", "0"),
        doctor("医生", "1"),
        team("团队", "2"),
        hospital("社区", "3"),
        town("区", "4");
        private String name;
        private String value;

        Level(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public enum Status {
        waiting("待审核", "0"),
        pass("审核通过", "1"),
        unpass("审核不通过", "2");
        private String name;
        private String value;

        Status(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public enum Type {
        rehabilitation("康复计划", "1");
        private String name;
        private String value;

        Type(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    private String saasId;
    private String name;//服务包名称
    private String type;//服务包类型
    private Integer num;//服务项数量
    private Long price;//预售价//单位分
    private String level;//服务包级别（0系统，1.医生，2团队，3社区，4区）
    private String levelCode;//关联code
    private String creater;//创建者
    private Date createTime;//创建时间
    private String introduce;//服务介绍
    private String status;//审核状态（预留字段0待审核，1审核通过，2审核不通过）
    private Integer del;//是否有效（1有效，0失效）

    private List<ServicePackageDetailsDO> detailsDOList;//服务项

    @Column(name = "saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Column(name = "level_code")
    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    @Transient
    public List<ServicePackageDetailsDO> getDetailsDOList() {
        return detailsDOList;
    }

    public void setDetailsDOList(List<ServicePackageDetailsDO> detailsDOList) {
        this.detailsDOList = detailsDOList;
    }
}
