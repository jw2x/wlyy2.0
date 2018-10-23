package com.yihu.jw.base.service.doctor.excelImport;


import com.yihu.jw.util.date.DateUtil;
import com.yihu.jw.util.excel.ExcelUtil;
import com.yihu.jw.util.excel.Validation;
import com.yihu.jw.util.excel.annotation.Location;
import com.yihu.jw.util.excel.annotation.Row;
import com.yihu.jw.util.excel.annotation.Title;
import com.yihu.jw.util.excel.annotation.ValidRepeat;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 *  基础人口信息列表-excel实体类
 * @author lith
 * Created at 2018/10/22.
 */
@Row(start = 1)
@Title(names= "{'姓名', '状态(1生效，0失效)','性别（1男2女）', '身份证号', '联系方式', '机构/部门/职务', '职称','归属业务模块角色','是否名医','专长','简介'}")
public class BaseDoctorExcelDO extends ExcelUtil implements Validation {

    @Location(x=0)
    @ValidRepeat
    String name;
    @Location(x=1)
    @ValidRepeat
    String del;
    @Location(x=2)
    @ValidRepeat
    String sex;
    @Location(x=3)
    @ValidRepeat
    String idcard;
    @Location(x=4)
    @ValidRepeat
    String mobile;
    @Location(x=5)
    @ValidRepeat
    String hospitalInfo;
    @Location(x=6)
    @ValidRepeat
    Integer roleInfo;
    @Location(x=7)
    @ValidRepeat
    String isFamous;
    @Location(x=8)
    @ValidRepeat
    Integer expertise;
    @Location(x=9)
    @ValidRepeat
    String brief;

    private String  saasId;
    //租户创建时间
    private Date saasCreateTime;
    //所属省代码
    private String  provinceCode;
    //省份名称
    private String  provinceName;
    //城市编码
    private String  cityCode;
    //城市名称
    private String  cityName;
    //所属区代码
    private String  districtCode;
    //区名
    private String  districtName;
    //慢病人数
    private Integer ncdNum;
    int yearNow = DateUtil.getNowYear();

    @Override
    public int validate(Map<String, Set> repeatMap) {
        int rs = 1;
        return rs;
    }

}
