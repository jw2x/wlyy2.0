package com.yihu.iot.datainput.service;

import com.yihu.iot.datainput.dao.DataStandardDao;
import com.yihu.jw.entity.iot.datainput.DataStandardDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataStandardService extends BaseJpaService<DataStandardDO,DataStandardDao> {

    @Autowired
    private DataStandardDao dataStandardDao;

    List<DataStandardDO> getList(String baseName){
        List<DataStandardDO> list = new ArrayList<>();
        list = dataStandardDao.getList(baseName);
        return list;
    }
}
