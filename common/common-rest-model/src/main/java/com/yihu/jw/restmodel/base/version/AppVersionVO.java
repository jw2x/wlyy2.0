package com.yihu.jw.restmodel.base.version;

import com.yihu.jw.restmodel.IntegerIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 
 * app版本号表vo
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  2018年09月07日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "AppVersionVO", description = "app版本号表")
public class AppVersionVO extends IntegerIdentityVO{

    /**
	 * 版本编码
	 */
	@ApiModelProperty(value = "版本编码", example = "app_doc1")
    private String code;

    /**
	 * 版本名称
	 */
	@ApiModelProperty(value = "版本名称", example = "医生端版本号")
    private String name;

    /**
	 * 数字版本号
	 */
	@ApiModelProperty(value = "数字版本号", example = "5")
    private double versionInt;

    /**
	 * 字符串版本号
	 */
	@ApiModelProperty(value = "字符串版本号", example = "1.4.8.1")
    private String versionStr;

    /**
	 * 更新链接地址
	 */
	@ApiModelProperty(value = "更新链接地址", example = "http://ehr.yihu.com/wlyy/app/ssgg_doctor.apk")
    private String url;

    /**
	 * 版本信息
	 */
	@ApiModelProperty(value = "版本信息", example = "1.新增若干功能<br>2.界面优化<br>3.修复已知Bug")
    private String info;

    /**
	 * 升级包大小，单位M
	 */
	@ApiModelProperty(value = "升级包大小，单位M", example = "5")
    private double size;


    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getVersionInt() {
        return versionInt;
    }
    public void setVersionInt(double versionInt) {
        this.versionInt = versionInt;
    }

    public String getVersionStr() {
        return versionStr;
    }
    public void setVersionStr(String versionStr) {
        this.versionStr = versionStr;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }

    public double getSize() {
        return size;
    }
    public void setSize(double size) {
        this.size = size;
    }


}