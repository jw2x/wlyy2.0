package com.yihu.jw.restmodel.base.dict;

import com.yihu.jw.restmodel.IntegerIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;


/**
 * 
 * 街道字典vo
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  2018年08月31日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "BaseStreetVO", description = "街道字典")
public class BaseStreetVO extends IntegerIdentityVO{

    /**
	省标识	*/
    private String province;
    /**
	市标识	*/
    private String city;
    /**
	区县标识	*/
    private String town;
    /**
	街道标识	*/
    private String code;
    /**
	街道名称	*/
    private String name;
    /**
	创建时间	*/
    private Date createTime;

	@ApiModelProperty(value = "省标识", example = "模块1")
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }

	@ApiModelProperty(value = "市标识", example = "模块1")
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

	@ApiModelProperty(value = "区县标识", example = "模块1")
    public String getTown() {
        return town;
    }
    public void setTown(String town) {
        this.town = town;
    }

	@ApiModelProperty(value = "街道标识", example = "模块1")
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

	@ApiModelProperty(value = "街道名称", example = "模块1")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

	@ApiModelProperty(value = "创建时间", example = "模块1")
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



}