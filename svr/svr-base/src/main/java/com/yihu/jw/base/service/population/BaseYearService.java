package com.yihu.jw.base.service.population;

import com.yihu.jw.base.dao.population.BaseYearDao;
import com.yihu.jw.entity.base.module.ModuleDO;
import com.yihu.jw.entity.base.module.SaasTypeModuleDO;
import com.yihu.jw.entity.base.population.BaseYearDO;
import com.yihu.jw.entity.base.saas.SaasTypeDictDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zdm on 2018/10/12.
 */
@Service
public class BaseYearService extends BaseJpaService<BaseYearDO, BaseYearDao> {
    @Autowired
    private BaseYearDao baseYearDao;

    public boolean save(int year) {
        BaseYearDO baseYearDo;
        List<BaseYearDO> baseYearDOList=new ArrayList<>();
        for( int i=year;i<1990;i--){
            baseYearDo=new BaseYearDO();
            String uuid=getCode();
            baseYearDo.setId(uuid);
            baseYearDo.setYear(year+"年");
            baseYearDOList.add(baseYearDo);
        }
        baseYearDao.save(baseYearDOList);
        return true;
    }

    public List<String> getYearList(){
      return baseYearDao.findYears();
    }

}
