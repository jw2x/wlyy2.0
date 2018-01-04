package com.yihu.iot.data_input.service;

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.iot.data_input.dao.DataProcessLogDao;
import com.yihu.jw.iot.data_input.DataProcessLogDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DataProcessLogService extends BaseJpaService<DataProcessLogDO,DataProcessLogDao> {

    @Autowired
    private DataProcessLogDao dataProcessLogDao;

    /**
     * 保存数据上传日志
     */
    public void saveDataLog(List<DataProcessLogDO> list){
        this.dataProcessLogDao.save(list);
    }

    public void saveLog(String fileName,String fileAbsPath,String dataSource,String receiveTime,String uploadTime,String processType,String status,String desc,int failCount){
        DataProcessLogDO dataProcessLog = new DataProcessLogDO();
        dataProcessLog.setFileName(fileName);
        dataProcessLog.setFileName(fileAbsPath);
        dataProcessLog.setFileName(dataSource);
        dataProcessLog.setFileName(receiveTime);
        dataProcessLog.setFileName(uploadTime);
        dataProcessLog.setProcessType(processType);
        dataProcessLog.setProcessStatus(status);
        dataProcessLog.setProcessDes(desc);
        dataProcessLog.setFileCount(failCount);
        dataProcessLogDao.save(dataProcessLog);
    }
}
