package com.yihu.jw.restmodel.base.dict;

import com.yihu.jw.restmodel.IntegerIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;


/**
 * 
 * 区县字典vo
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  2018年08月31日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "BaseTownVO", description = "区县字典")
public class BaseTownVO extends IntegerIdentityVO{

    /**
	省编码	*/
    private String province;
    /**
	城市编码	*/
    private String city;
    /**
	区县编码	*/
    private String code;
    /**
	区县名称	*/
    private String name;
    /**
	创建时间	*/
    private Date createTime;

	@ApiModelProperty(value = "省编码", example = "模块1")
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }

	@ApiModelProperty(value = "城市编码", example = "模块1")
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

	@ApiModelProperty(value = "区县编码", example = "模块1")
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

	@ApiModelProperty(value = "区县名称", example = "模块1")
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