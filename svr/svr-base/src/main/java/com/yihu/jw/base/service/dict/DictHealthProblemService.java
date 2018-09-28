package com.yihu.jw.base.service.dict;

import com.alibaba.fastjson.JSONObject;
import com.yihu.jw.base.dao.dict.DictHealthProblemDao;
import com.yihu.jw.base.dao.dict.DictJobTitleDao;
import com.yihu.jw.entity.base.dict.DictIcd10DO;
import com.yihu.mysql.query.BaseJpaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yihu.jw.entity.base.dict.DictHealthProblemDO;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 健康问题字典服务service
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
public class DictHealthProblemService extends BaseJpaService<DictHealthProblemDO, DictHealthProblemDao> {

    @Autowired
    private DictJobTitleDao dictJobTitleDao;
    /**
     * 查询某一租户下的健康问题字典信息，如果saadId为空表示当前用户角色为超级管理员，超级管理员可以看到所有数据
     * @param saasId
     * @return
     */
    public String queryAll(String saasId){
        JSONObject jsonObject = new JSONObject();
        List<DictIcd10DO> list = new ArrayList<>();
        if(StringUtils.isEmpty(saasId)){
            list = dictJobTitleDao.findCodeAndName();
        }else{
            list = dictJobTitleDao.findCodeAndNameBySaasId(saasId);
        }
        jsonObject.put("healthProDict",list);
        return jsonObject.toJSONString();
    }

}
