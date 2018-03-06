package com.yihu.iot.service.device;

import com.yihu.base.fastdfs.FastDFSHelper;
import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.iot.dao.device.*;
import com.yihu.jw.iot.device.*;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.base.BaseEnvelop;
import com.yihu.jw.restmodel.iot.device.IotDeviceImportRecordVO;
import com.yihu.jw.restmodel.iot.device.IotDeviceImportVO;
import com.yihu.jw.restmodel.iot.device.IotDeviceVO;
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
    @Autowired
    private IotPatientDeviceDao iotPatientDeviceDao;
    @Autowired
    private IotDeviceQualityInspectionPlanDao iotDeviceQualityInspectionPlanDao;

    /**
     * 新增
     * @param iotDevice
     * @return
     */
    public IotDeviceDO create(IotDeviceDO iotDevice) {

        if(iotDevice.getPurchaseId()!=null){
            IotOrderPurchaseDO purchaseDO = iotOrderPurchaseDao.findById(iotDevice.getPurchaseId());
            iotDevice.setName(purchaseDO.getDeviceName());
            iotDevice.setOrderNo(purchaseDO.getOrderNo());
            iotDevice.setProductId(purchaseDO.getProductId());
            iotDevice.setSupplierId(purchaseDO.getSupplierId());
            iotDevice.setSupplierName(purchaseDO.getSupplierName());
            iotDevice.setManufacturerId(purchaseDO.getManufacturerId());
            iotDevice.setManufacturerName(purchaseDO.getManufacturerName());
            iotDevice.setStatus(IotDeviceDO.DeviceStatus.normal.getValue());
            iotDevice.setDeviceSource(IotDeviceDO.DeviceSource.purchaese.getValue());

            //获取质检信息
            IotDeviceQualityInspectionPlanDO planDO = iotDeviceQualityInspectionPlanDao.findLastByPurchaseId(iotDevice.getPurchaseId());
            if(planDO!=null){
                iotDevice.setNextQualityTime(planDO.getPlanTime());//下次质检时间
            }
        }

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
     * 修改sim卡号
     * @param sim
     * @param id
     */
    public BaseEnvelop updSim(String sim, String id){
        if(findBySimNo(sim)!=null){
            return BaseEnvelop.getError("sim卡号已存在");
        }

        IotDeviceDO deviceDO = findById(id);
        deviceDO.setSimNo(sim);
        iotDeviceDao.save(deviceDO);
        return BaseEnvelop.getSuccess("修改成功");
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
     * 查询
     * @param sn
     * @param hospital
     * @param orderId
     * @param purcharseId
     * @param page
     * @param size
     * @return
     */
    public Envelop<IotDeviceVO> queryPage(String sn,String hospital,String orderId,String purcharseId,Integer page,Integer size) throws Exception{
        String filters = "";
        String semicolon = "";
        if(StringUtils.isNotBlank(orderId)){
            filters += semicolon +"orderId="+orderId;
            semicolon = ";";
        }
        if(StringUtils.isNotBlank(purcharseId)){
            filters += semicolon +"purchaseId="+purcharseId;
            semicolon = ";";
        }
        if(StringUtils.isNotBlank(hospital)){
            filters += semicolon +"hospital="+hospital;
            semicolon = ";";
        }
        if(StringUtils.isNotBlank(sn)){
            filters = "deviceSn?"+sn+" g1;name?"+sn+" g1";
            semicolon = ";";
        }
        if(StringUtils.isBlank(filters)){
            filters+= semicolon + "del=1";
        }
        String sorts = "-updateTime";
        //得到list数据
        List<IotDeviceDO> list = search(null, filters, sorts, page, size);
        //获取总数
        long count = getCount(filters);

        //DO转VO
        List<IotDeviceVO> iotDeviceVOList = convertToModels(list,new ArrayList<>(list.size()),IotDeviceVO.class);
        iotDeviceVOList.forEach(one->{
            String deviceSn = one.getDeviceSn();
            List<IotPatientDeviceDO>  deviceDOList = iotPatientDeviceDao.findByDeviceSn(deviceSn);
            if(deviceDOList!=null&&deviceDOList.size()>0){
                one.setIsBinding(1);
            }else {
                one.setIsBinding(2);
            }
        });

        return Envelop.getSuccessListWithPage(IotRequestMapping.Company.message_success_find_functions,iotDeviceVOList, page, size,count);
    }

    /**
     * 是否绑定查询
     * @param sn
     * @param hospital
     * @param orderId
     * @param purcharseId
     * @param isBinding
     * @param page
     * @param size
     * @return
     */
    public Envelop<IotDeviceVO> queryPage(String sn,String hospital,String orderId,String purcharseId,Integer isBinding,Integer page,Integer size){
        StringBuffer sql = new StringBuffer("SELECT DISTINCT c.* from iot_device c ,iot_patient_device t WHERE c.del=1 and t.del=1 ");
        StringBuffer sqlCount = new StringBuffer("SELECT COUNT(DISTINCT c.id) count from iot_device c ,iot_patient_device t WHERE c.del=1 and t.del=1 ");
        List<Object> args = new ArrayList<>();

        if(StringUtils.isNotBlank(orderId)){
            sql.append(" and c.order_id = ? ");
            sqlCount.append(" and c.order_id = '").append(orderId).append("'");
            args.add(orderId);
        }
        if(StringUtils.isNotBlank(purcharseId)){
            sql.append(" and c.purcharse_id = ? ");
            sqlCount.append(" and c.purcharse_id = '").append(purcharseId).append("'");
            args.add(purcharseId);
        }
        if(StringUtils.isNotBlank(hospital)){
            sql.append(" and c.hospital = ? ");
            sqlCount.append(" and c.hospital = '").append(hospital).append("'");
            args.add(hospital);
        }
        if(StringUtils.isNotBlank(sn)){
            sql.append(" and (c.device_sn like ? or c.name like ?)");
            sqlCount.append(" and (c.device_sn like '").append(sn).append("' or c.name like '").append(sn).append("')");
            args.add(sn);
            args.add(sn);
        }
        sql.append(" and c.device_sn = t.device_sn ");
        sqlCount.append("  and c.device_sn = t.device_sn '");

        sql.append("order by c.update_time desc limit ").append((page-1)*size).append(",").append(size);

        List<IotDeviceDO> list = jdbcTempalte.query(sql.toString(),args.toArray(),new BeanPropertyRowMapper(IotDeviceDO.class));
        List<Map<String,Object>> countList = jdbcTempalte.queryForList(sqlCount.toString());
        long count = Long.valueOf(countList.get(0).get("count").toString());

        //DO转VO
        List<IotDeviceVO> iotDeviceVOList = convertToModels(list,new ArrayList<>(list.size()),IotDeviceVO.class);
        iotDeviceVOList.forEach(one->{
            one.setIsBinding(isBinding);
        });

        return Envelop.getSuccessListWithPage(IotRequestMapping.Common.message_success_find_functions,iotDeviceVOList, page, size,count);
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
