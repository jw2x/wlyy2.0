package com.yihu.iot.datainput.service;

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.iot.datainput.dao.DataStandardDao;
import com.yihu.jw.iot.data_input.DataStandardDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataStandardService extends BaseJpaService<DataStandardDO,DataStandardDao> {

    @Autowired
    private DataStandardDao dataStandardDao;

    List<DataStandardDO> getList(){
        List<DataStandardDO> list = new ArrayList<>();
        list = dataStandardDao.getList();
        return list;
    }
}
