package com.yihu.jw.wlyy.hospital;



import com.yihu.jw.UuidIdentityEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2017/6/7 0007.
 */
@Entity
@Table(name = "base_org_hospital")
public class BaseOrgHospitalDO extends UuidIdentityEntityWithOperation {

    private static final long serialVersionUID = 5463913446686402252L;
    private String orgId;//
    private String roadCode;
    private String centerSite;
    private String ascriptionType;
    private String levelId;
    private String levelName;

    @Column(name = "org_id")
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }


    
    @Column(name = "road_code")
    public String getRoadCode() {
        return roadCode;
    }

    public void setRoadCode(String roadCode) {
        this.roadCode = roadCode;
    }

    
    @Column(name = "center_site")
    public String getCenterSite() {
        return centerSite;
    }

    public void setCenterSite(String centerSite) {
        this.centerSite = centerSite;
    }

    
    @Column(name = "ascription_type")
    public String getAscriptionType() {
        return ascriptionType;
    }

    public void setAscriptionType(String ascriptionType) {
        this.ascriptionType = ascriptionType;
    }

    
    @Column(name = "level_id")
    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    
    @Column(name = "level_name")
    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

}
