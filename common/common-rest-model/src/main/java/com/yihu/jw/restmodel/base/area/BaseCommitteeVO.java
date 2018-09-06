package com.yihu.jw.restmodel.base.area;

import com.yihu.jw.restmodel.UuidIdentityVOWithOperator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 
 * 居委会vo
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * Administrator 	1.0  2018年09月05日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "BaseCommitteeVO", description = "居委会")
public class BaseCommitteeVO extends UuidIdentityVOWithOperator {

    /**
	 * 省标识
	 */
	@ApiModelProperty(value = "省标识", example = "模块1")
    private String province;

    /**
	 * 市标识
	 */
	@ApiModelProperty(value = "市标识", example = "模块1")
    private String city;

    /**
	 * 区县标识
	 */
	@ApiModelProperty(value = "区县标识", example = "模块1")
    private String town;

    /**
	 * 街道标识
	 */
	@ApiModelProperty(value = "街道标识", example = "模块1")
    private String street;

    /**
	 * 居委会标识
	 */
	@ApiModelProperty(value = "居委会标识", example = "模块1")
    private String code;

    /**
	 * 居委会名称
	 */
	@ApiModelProperty(value = "居委会名称", example = "模块1")
    private String name;


    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getTown() {
        return town;
    }
    public void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

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


}