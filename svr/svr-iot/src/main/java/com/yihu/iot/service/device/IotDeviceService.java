package com.yihu.iot.service.device;

import com.yihu.base.fastdfs.FastDFSHelper;
import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.iot.dao.device.IotDeviceDao;
import com.yihu.iot.dao.device.IotDeviceImportRecordDao;
import com.yihu.iot.dao.device.IotOrderPurchaseDao;
import com.yihu.jw.iot.device.IotDeviceDO;
import com.yihu.jw.iot.device.IotDeviceImportRecordDO;
import com.yihu.jw.iot.device.IotOrderPurchaseDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.iot.device.IotDeviceImportRecordVO;
import com.yihu.jw.restmodel.iot.device.IotDeviceImportVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yeshijie on 2017/12/8.
 */
@Service
public class IotDeviceService extends BaseJpaService<IotDeviceDO,IotDeviceDao> {

    @Autowired
    private IotDeviceDao iotDeviceDao;
    @Autowired
    private FastDFSHelper fastDFSHelper;
    @Value("${fastDFS.fastdfs_file_url}")
    private String fastdfs_file_url;
    @Autowired
    private IotDeviceImportRecordDao iotDeviceImportRecordDao;
    @Autowired
    private JdbcTemplate jdbcTempalte;
    @Autowired
    private IotOrderPurchaseDao iotOrderPurchaseDao;
    @Autowired
    private IotDeviceImportRecordService importRecordService;

    /**
     * 新增
     * @param iotDevice
     * @return
     */
    public IotDeviceDO create(IotDeviceDO iotDevice) {

        iotDevice.setSaasId(getCode());
        iotDevice.setDel(1);
        return iotDeviceDao.save(iotDevice);
    }

    /**
     * 按id查找
     * @param id
     * @return
     */
    public IotDeviceDO findById(String id) {
        return iotDeviceDao.findById(id);
    }

    /**
     * 按sim卡号查找
     * @param simNo
     * @return
     */
    public IotDeviceDO findBySimNo(String simNo) {
        return iotDeviceDao.findBySimNo(simNo);
    }

    /**
     * 设备注册及绑定
     * @param iotDeviceDO
     */
    @Transactional
    public void bindUser(List<IotDeviceDO> iotDeviceDO){
        this.batchInsert(iotDeviceDO);
    }

    /**
     * 根据设备序列号判断设备是否存在
     * @param deviceSn
     * @return
     */
    public int countByDeviceSn(String deviceSn) {
        return iotDeviceDao.countByDeviceSn(deviceSn);
    }

    /**
     * 根据设备序列号查找设备
     * @param deviceSn
     * @return
     */
    public IotDeviceDO findByDeviceSn(String deviceSn) {
        return iotDeviceDao.findByDeviceSn(deviceSn);
    }

    /**
     * 设备导入
     */
    public IotDeviceImportRecordVO importDevice(String fileUrl, String fileName, String purcharseId, List<IotDeviceImportVO> importVOList){
        IotDeviceImportRecordDO recordDO = new IotDeviceImportRecordDO();
        IotOrderPurchaseDO purchaseDO = iotOrderPurchaseDao.findById(purcharseId);
        recordDO.setDel(1);
        recordDO.setOrderId(purchaseDO.getOrderId());
        recordDO.setPurchaseId(purcharseId);
        recordDO.setSaasId(getCode());
        recordDO.setFileName(fileName);
        recordDO.setFileUrl(fileUrl);
        recordDO.setStatus(IotDeviceImportRecordDO.DeviceImportRecordStatus.create.getValue());

        iotDeviceImportRecordDao.save(recordDO);
        IotDeviceImportRecordVO vo = convertToModel(recordDO,IotDeviceImportRecordVO.class);

        //批量导入 异步操作
        importRecordService.importDevice(purchaseDO,importVOList,recordDO);

        return vo;
    };

    /**
     * 分页查找导入记录
     * @param page
     * @param size
     * @param purcharseId
     * @return
     */
    public Envelop<IotDeviceImportRecordVO> queryImportRecordPage(Integer page, Integer size, String purcharseId){
        StringBuffer sql = new StringBuffer("SELECT c.* from iot_device_import_record c  WHERE c.del=1 ");
        StringBuffer sqlCount = new StringBuffer("SELECT COUNT(c.id) count from iot_device_import_record c WHERE c.del=1 ");
        List<Object> args = new ArrayList<>();

        if(StringUtils.isNotBlank(purcharseId)){
            sql.append(" and c.purcharse_id=? ");
            sqlCount.append(" and c.purcharse_id='").append(purcharseId).append("' ");
            args.add(purcharseId);
        }
        sql.append("order by c.update_time desc limit ").append((page-1)*size).append(",").append(size);

        List<IotDeviceImportRecordDO> list = jdbcTempalte.query(sql.toString(),args.toArray(),new BeanPropertyRowMapper(IotDeviceImportRecordDO.class));
        List<Map<String,Object>> countList = jdbcTempalte.queryForList(sqlCount.toString());
        long count = Long.valueOf(countList.get(0).get("count").toString());

        //DO转VO
        List<IotDeviceImportRecordVO> importRecordVOList = convertToModels(list,new ArrayList<>(list.size()),IotDeviceImportRecordVO.class);

        return Envelop.getSuccessListWithPage(IotRequestMapping.Common.message_success_find_functions,importRecordVOList, page, size,count);
    }
}
