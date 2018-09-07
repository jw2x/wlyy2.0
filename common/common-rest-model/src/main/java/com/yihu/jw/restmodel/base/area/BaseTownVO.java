package com.yihu.jw.restmodel.base.area;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * Administrator 	1.0  2018年09月05日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "BaseTownVO", description = "区县字典")
public class BaseTownVO extends IntegerIdentityVO{

    /**
	 * 省编码
	 */
	@ApiModelProperty(value = "省编码", example = "110000")
    private String province;

    /**
	 * 城市编码
	 */
	@ApiModelProperty(value = "城市编码", example = "110100")
    private String city;

    /**
	 * 区县编码
	 */
	@ApiModelProperty(value = "区县编码", example = "110101")
    private String code;

    /**
	 * 区县名称
	 */
	@ApiModelProperty(value = "区县名称", example = "东城区")
    private String name;

    /**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	@ApiModelProperty(value = "创建时间", example = "yyyy-MM-dd HH:mm:ss/该字段可不填")
    private Date createTime;


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

    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


}