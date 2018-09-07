package com.yihu.jw.entity.base.version;

import com.yihu.jw.entity.IntegerIdentityEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * app版本号表实体
 * 
 * @author litaohong on  2018年09月07日
 *
 */
@Entity
@Table(name = "app_version")
public class AppVersionDO extends IntegerIdentityEntity {

    /**
	 * 版本编码
	 */
	private String code;

    /**
	 * 版本名称
	 */
	private String name;

    /**
	 * 数字版本号
	 */
	private double versionInt;

    /**
	 * 字符串版本号
	 */
	private String versionStr;

    /**
	 * 更新链接地址
	 */
	private String url;

    /**
	 * 版本信息
	 */
	private String info;

    /**
	 * 升级包大小，单位M
	 */
	private double size;


	@Column(name = "code")
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

	@Column(name = "name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

	@Column(name = "version_int")
    public double getVersionInt() {
        return versionInt;
    }
    public void setVersionInt(double versionInt) {
        this.versionInt = versionInt;
    }

	@Column(name = "version_str")
    public String getVersionStr() {
        return versionStr;
    }
    public void setVersionStr(String versionStr) {
        this.versionStr = versionStr;
    }

	@Column(name = "url")
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

	@Column(name = "info")
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }

	@Column(name = "size")
    public double getSize() {
        return size;
    }
    public void setSize(double size) {
        this.size = size;
    }



}