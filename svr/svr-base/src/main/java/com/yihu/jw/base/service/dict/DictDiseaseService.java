package com.yihu.jw.base.service.dict;

import com.alibaba.fastjson.JSONObject;
import com.yihu.jw.base.dao.dict.DictDiseaseDao;
import com.yihu.jw.base.dao.dict.DictJobTitleDao;
import com.yihu.jw.base.enums.SystemDictEnum;
import com.yihu.jw.entity.base.dict.DictDiseaseDO;
import com.yihu.mysql.query.BaseJpaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * 病种字典服务service
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * Administrator    1.0  2018年09月05日 Created
 *
 * </pre>
 * @since 1.
 */
@Service
public class DictDiseaseService extends BaseJpaService<DictDiseaseDO, DictDiseaseDao> {

    @Autowired
    private DictDiseaseDao dictDiseaseDao;
    /**
     * 查询某一租户下的病种字典信息，如果saadId为空表示当前用户角色为超级管理员，超级管理员可以看到所有数据
     * @param saasId
     * @return
     */
    public JSONObject queryAll(String saasId, Pageable pageable){
        JSONObject jsonObject = new JSONObject();
        List<Map<String,Object>> list = new ArrayList<>();
        if(StringUtils.isEmpty(saasId)){
            list = dictDiseaseDao.findCodeAndName(pageable);
        }else{
            list = dictDiseaseDao.findCodeAndNameBySaasId(saasId,pageable);
        }
        jsonObject.put(SystemDictEnum.DiseaseDict.toString(),list);
        return jsonObject;
    }
    public void deleteById(Integer id){
        dictDiseaseDao.delete(id);
    }
}
