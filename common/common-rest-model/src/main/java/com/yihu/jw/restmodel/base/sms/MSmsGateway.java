package com.yihu.jw.restmodel.base.sms;

/**
 * Created by chenweida on 2017/5/22.
 */
public class MSmsGateway {
    private Long id;
    private String code; //业务code
    private String name;//名称
    private String saasId; //关联 base_saas code
    private String orgCode; //机构code
    private String ip; // 短信接口的ip地址
    private String username;  //短信接口的账号
    private String password;	//短信接口的密码
    private String url;	//短信接口的url

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
