package com.yihu.jw.base.service.population;

import com.yihu.jw.base.dao.population.BasePopulationDao;
import com.yihu.jw.base.endpoint.common.populationBatchImport.PopulationMsg;
import com.yihu.jw.entity.base.population.BasePopulationDO;
import com.yihu.jw.exception.business.ManageException;
import com.yihu.mysql.query.BaseJpaService;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基础人口基数信息服务service
 *
 * @version <pre>
 *                            Author	Version		Date		Changes
 *                            litaohong    1.0  2018年09月26日 update
 *
 *                            </pre>
 * @since 1.
 */
@Service
public class BasePopulationService extends BaseJpaService<BasePopulationDO, BasePopulationDao> {
    @Autowired
    private BasePopulationDao basePopulationDao;

    public BasePopulationDO findById(String id) {
        return basePopulationDao.findOne(id);
    }

    /**
     * 批量导入基础人口信息的集合
     *
     * @param populations 基础人口信息
     */
    public Map<String, Object> batchInsertPopulation(List<PopulationMsg> populations) throws ManageException {
        Map<String, Object> result = new HashMap<>();
        //批量存储的集合
        int correctCount = 0;
        List<BasePopulationDO> corrects = new ArrayList<>();
        BasePopulationDO basePopulationDO;
        //批量存储
        for (PopulationMsg populationMsg : populations) {
            basePopulationDO = new BasePopulationDO();
            basePopulationDO.setId(getCode());
            basePopulationDO.setSaasId(populationMsg.getSaasId());
            basePopulationDO.setSaasName(populationMsg.getSaasName());
            basePopulationDO.setSaasCreateTime(populationMsg.getSaasCreateTime());
            basePopulationDO.setYear(populationMsg.getYear());
            basePopulationDO.setProvinceCode(populationMsg.getProvinceCode());
            basePopulationDO.setProvinceName(populationMsg.getProvinceName());
            basePopulationDO.setCityCode(populationMsg.getCityCode());
            basePopulationDO.setCityName(populationMsg.getCityName());
            basePopulationDO.setDistrictCode(populationMsg.getDistrictCode());
            basePopulationDO.setDistrictName(populationMsg.getDistrictName());
            basePopulationDO.setRegisPopulationNum(populationMsg.getRegisPopulationNum());
            basePopulationDO.setPopulationNum(populationMsg.getPopulationNum());
            //糖尿病人数
            basePopulationDO.setDmNum(null == populationMsg.getDmNum() ? 0 : populationMsg.getDmNum());
            //高血压人数
            basePopulationDO.setHbpNum(null == populationMsg.getHbpNum() ? 0 : populationMsg.getHbpNum());
            basePopulationDO.setTaskNum(populationMsg.getTaskNum());
            //慢病人数
            basePopulationDO.setNcdNum(basePopulationDO.getHbpNum() + basePopulationDO.getDmNum());
            corrects.add(basePopulationDO);
            if (corrects.size() > 100) {
                basePopulationDao.save(corrects);
                correctCount += corrects.size();
                corrects.clear();
            }
        }
        if (!corrects.isEmpty()) {
            basePopulationDao.save(corrects);
            correctCount += corrects.size();
        }
        result.put("correctCount", correctCount);
        return result;
    }


    public List<String> getFacilityCodeByServerType() {
        Session s = currentSession();
        String hql = "SELECT concat(p.saas_name,p.year)  FROM  base_population p ";
        Query query = s.createSQLQuery(hql);
        return query.list();
    }

    public Boolean checkPopulationName(String id,String saasId, String year) {
        String hql = "SELECT count(1)  FROM  base_population p WHERE p.saas_id=:saasId AND  p.year=:year AND  p.id != :id";
        SQLQuery sqlQuery = currentSession().createSQLQuery(hql);
        sqlQuery.setParameter("id", id);
        sqlQuery.setParameter("saasId", saasId);
        sqlQuery.setParameter("year", year);
        BigInteger count = (BigInteger) sqlQuery.uniqueResult();
        return count.compareTo(new BigInteger("0")) > 0;
    }

}
