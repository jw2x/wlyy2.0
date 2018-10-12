package com.yihu.jw.base.service.org;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.yihu.jw.base.dao.org.BaseOrgDao;
import com.yihu.jw.rm.base.BaseRequestMapping;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yihu.jw.entity.base.org.BaseOrgDO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 机构信息服务service
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong    1.0  2018年08月31日 Created
 *
 * </pre>
 * @since 1.
 */
@Service
public class BaseOrgService extends BaseJpaService<BaseOrgDO, BaseOrgDao> {

    @Autowired
    private BaseOrgDao baseOrgDao;

    /**
     * 机构基础信息列表
     * @param orgCode
     * @param orgName
     * @param orgStatus
     * @return
     */
    public List<Map<String,Object>> queryOrgBaseInfoList(String orgCode,String orgName,String orgStatus,int page,int size,String sorts){
        getOrgByArea();
        List<Map<String,Object>> result = new ArrayList<>();
        if(StringUtils.endsWithIgnoreCase("1",orgStatus)){
            if(!StringUtils.isEmpty(orgCode) ){
                result = baseOrgDao.findByCodeAndDel(orgCode,orgStatus,creatPage(page,size,sorts));
            }else if(!StringUtils.isEmpty(orgCode)){
                result = baseOrgDao.findByNameAndDel(orgName,orgStatus,creatPage(page,size,sorts));
            }else{
                result = baseOrgDao.findBaseInfoByDel(orgStatus,creatPage(page,size,sorts));
            }
        }else{
            if(!StringUtils.isEmpty(orgCode) ){
                result = baseOrgDao.findByCode(orgCode,creatPage(page,size,sorts));
            }else if(!StringUtils.isEmpty(orgCode)){
                result = baseOrgDao.findByName(orgName,creatPage(page,size,sorts));
            }else{
                result = baseOrgDao.findBaseInfo(creatPage(page,size,sorts));
            }
        }
        return result;
    }


    /**
     * 构建机构区域树形结构
     * @return
     */
    public JSONObject getOrgByArea(){
        JSONObject result = new JSONObject();

        JSONArray provinceArray = new JSONArray();
        JSONArray cityArray = new JSONArray();
        JSONArray townArray = new JSONArray();
        JSONArray orgArray = new JSONArray();

        JSONObject proJson = new JSONObject();
        JSONObject cityJson = new JSONObject();
        JSONObject townJson = new JSONObject();
        JSONObject orgJson = new JSONObject();

        List<BaseOrgDO> list = baseOrgDao.findOrgByArea();
        for(BaseOrgDO baseOrgDO : list){
          /*  if(!proJson.containsKey(baseOrgDO.getProvinceCode())){
                provinceArray.add(proJson);
            }
            if(!cityJson.containsKey(baseOrgDO.getCityCode())){
                proJson.put("city",cityJson);
                cityArray.add(cityJson);
                cityJson.put("town", townJson);
            }
            if(!townJson.containsKey(baseOrgDO.getTownCode())){
                townArray.add(townJson);
            }
            if(!orgJson.containsKey(baseOrgDO.getCode())){
                townJson.put("org",orgJson);
                orgArray.add(orgJson);
            }*/
            proJson.put("provinceCode",baseOrgDO.getProvinceCode());
            proJson.put("provinceName",baseOrgDO.getProvinceName());

            cityJson.put("cityCode",baseOrgDO.getCityCode());
            cityJson.put("cityName",baseOrgDO.getCityName());

            townJson.put("townCode",baseOrgDO.getTownCode());
            townJson.put("townName",baseOrgDO.getTownName());

            orgJson.put("orgCode",baseOrgDO.getCode());
            orgJson.put("orgName",baseOrgDO.getName());

            provinceArray.add(proJson);
            cityArray.add(cityJson);
            townArray.add(townJson);
            orgArray.add(orgJson);

            proJson.put("city",cityJson);
            cityJson.put("town", townJson);
            townJson.put("org",orgJson);
        }
        result.put("province",provinceArray);
        return result;
    }
}
