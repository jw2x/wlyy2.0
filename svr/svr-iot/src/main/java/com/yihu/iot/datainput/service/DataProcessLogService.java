package com.yihu.iot.datainput.service;

import com.yihu.iot.datainput.dao.DataProcessLogDao;
import com.yihu.jw.iot.datainput.DataProcessLogDO;
import com.yihu.mysql.query.BaseJpaService;
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

    public void saveLog(String fileName,String fileAbsPath,String dataSource,String receiveTime,String uploadTime,String processType,String status,String processInterface,String desc,int failCount){
        DataProcessLogDO dataProcessLog = new DataProcessLogDO();
        dataProcessLog.setFileName(fileName);
        dataProcessLog.setFileAbsoultePath(fileAbsPath);
        dataProcessLog.setDataSource(dataSource);
        dataProcessLog.setReceiveTime(receiveTime);
        dataProcessLog.setUploadTime(uploadTime);
        dataProcessLog.setProcessType(processType);
        dataProcessLog.setProcessStatus(status);
//        dataProcessLog.setProcessInterface(processInterface);
        dataProcessLog.setProcessDes(desc);
        dataProcessLog.setFileCount(failCount);
        dataProcessLogDao.save(dataProcessLog);
    }
}
