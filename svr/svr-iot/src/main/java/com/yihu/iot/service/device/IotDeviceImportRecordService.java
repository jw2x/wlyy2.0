package com.yihu.iot.service.device;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yihu.base.fastdfs.FastDFSHelper;
import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.iot.dao.device.IotDeviceDao;
import com.yihu.iot.dao.device.IotDeviceImportRecordDao;
import com.yihu.jw.iot.device.IotDeviceDO;
import com.yihu.jw.iot.device.IotDeviceImportRecordDO;
import com.yihu.jw.iot.device.IotOrderPurchaseDO;
import com.yihu.jw.restmodel.iot.device.IotDeviceImportVO;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yeshijie on 2018/1/19.
 */
@Service
public class IotDeviceImportRecordService extends BaseJpaService<IotDeviceImportRecordDO,IotDeviceImportRecordDao> {
    private Logger logger = LoggerFactory.getLogger(IotDeviceImportRecordService.class);
    @Autowired
    private IotDeviceImportRecordDao iotDeviceImportRecordDao;
    @Autowired
    private IotDeviceDao iotDeviceDao;
    @Autowired
    private FastDFSHelper fastDFSHelper;

    /**
     * 创建 HSSFWorkbook
     * @return
     */
    private HSSFWorkbook createWb(){
        //创建HSSFWorkbook对象
        HSSFWorkbook wb = new HSSFWorkbook();
        //创建HSSFSheet对象
        HSSFSheet sheet = wb.createSheet("sheet0");
        //创建HSSFRow对象
        HSSFRow row0 = sheet.createRow(0);
        //创建HSSFCell对象
        HSSFCell cell0=row0.createCell(0);
        HSSFCell cell1=row0.createCell(1);
        HSSFCell cell2=row0.createCell(2);
        HSSFCell cell3=row0.createCell(3);
        //设置单元格的值
        cell0.setCellValue("sn码");
        cell1.setCellValue("归属社区");
        cell2.setCellValue("sim卡号");
        cell3.setCellValue("导入结果");
        return wb;
    }

    /**
     * 新增单元行
     * @param sheet
     * @param rowNum
     * @param vo
     * @param msg
     */
    private void addRow(HSSFSheet sheet,Integer rowNum,IotDeviceImportVO vo,String msg){
        HSSFRow row =sheet.createRow(rowNum);
        HSSFCell cell0 = row.createCell(0);
        HSSFCell cell1 = row.createCell(1);
        HSSFCell cell2 = row.createCell(2);
        HSSFCell cell3 = row.createCell(3);
        //设置单元格的值
        cell0.setCellValue(vo.getSn());
        cell1.setCellValue(vo.getHospital());
        cell2.setCellValue(vo.getSim());
        cell3.setCellValue(msg);
    }

    @Async
    public void importDevice(IotOrderPurchaseDO purchaseDO, List<IotDeviceImportVO> importVOList, IotDeviceImportRecordDO recordDO){

        HSSFWorkbook wb = createWb();
        HSSFSheet sheet = wb.getSheetAt(0);
        List<IotDeviceDO> deviceDOList = new ArrayList<IotDeviceDO>();
        Integer associatedNum = iotDeviceDao.countByPurchaseId(purchaseDO.getId());
        Long unAssociatedNum = purchaseDO.getPurchaseNum()-associatedNum;//未关联数量
        Integer count = 0;
        Integer rowNum = 0;
        Map<String,String> snMap = new HashedMap(importVOList.size());
        for(IotDeviceImportVO iotDeviceImportVO:importVOList){
            rowNum++;

            try{
                String errorMsg = null;
                if(unAssociatedNum<=count){
                    errorMsg = "入库SN码数量超过采购量";
                    addRow(sheet,rowNum,iotDeviceImportVO,errorMsg);
                    continue;
                }
                String sn = iotDeviceImportVO.getSn();
                String hos = iotDeviceImportVO.getHospital();
                String sim = iotDeviceImportVO.getSim();

                //分割字符串--思明区莲前街道社区卫生服务中心(3502030400)
                String[] ho = hos.split("\\(");
                String hospital = ho.length>1?ho[1].replace("\\)",""):null;
                String hospitalName = ho.length>1?ho[0]:null;
                //验证
                if(StringUtils.isBlank(sn)){
                    errorMsg = "sn码不能为空";
                    addRow(sheet,rowNum,iotDeviceImportVO,errorMsg);
                    continue;
                }
                if(snMap.containsKey(sn)){
                    errorMsg = "SN码重复，并不允许新增";
                    addRow(sheet,rowNum,iotDeviceImportVO,errorMsg);
                    continue;
                }else {
                    snMap.put(sn,sn);
                }
                IotDeviceDO device = iotDeviceDao.findByDeviceSn(sn);
//                if(iotDeviceDao.findByDeviceSn(sn)!=null){
//                    errorMsg = "SN码重复，并不允许新增";
//                    HSSFCell cell = row.createCell(3);
//                    cell.setCellValue(errorMsg);
//                    continue;
//                }
                if(StringUtils.isNotBlank(sim)&&iotDeviceDao.findByDeviceSn(sim)!=null){
                    errorMsg = "SIM卡号重复，并不允许新增";
                    addRow(sheet,rowNum,iotDeviceImportVO,errorMsg);
                    continue;
                }
                if(device==null){
                    device = new IotDeviceDO();
                    device.setSaasId(getCode());
                }
                device.setDel(1);
                device.setOrderNo(purchaseDO.getOrderNo());
                device.setProductId(purchaseDO.getProductId());
                device.setStatus(IotDeviceDO.DeviceStatus.normal.getValue());
                device.setDeviceSn(sn);
                device.setDeviceSource(IotDeviceDO.DeviceSource.purchaese.getValue());
                device.setHospital(hospital);
                device.setHospitalName(hospitalName);
//                device.setIsComposite(1);
//                device.setIsPlatform(0);
                device.setManufacturerId(purchaseDO.getManufacturerId());
                device.setManufacturerName(purchaseDO.getManufacturerName());
                device.setName(purchaseDO.getDeviceName());
                device.setSimNo(sim);
                device.setSupplierId(purchaseDO.getSupplierId());
                device.setSupplierName(purchaseDO.getSupplierName());

                deviceDOList.add(device);
                addRow(sheet,rowNum,iotDeviceImportVO,"新增成功");
            }catch (Exception e){
                e.printStackTrace();
                addRow(sheet,rowNum,iotDeviceImportVO,"新增失败："+e.getMessage());
            }
        }
        //保存结果
        iotDeviceDao.save(deviceDOList);
        recordDO.setStatus(IotDeviceImportRecordDO.DeviceImportRecordStatus.complete.getValue());
        ByteArrayOutputStream os = null;
        try{
            //结果上传到fastDFS
            os = new ByteArrayOutputStream();
            wb.write(os);
            ObjectNode node = fastDFSHelper.uploadByByte(os.toByteArray(),"xls","");

            recordDO.setResultUrl(node.get("fid").toString().replaceAll("\"", ""));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭流
            try {
                os.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        iotDeviceImportRecordDao.save(recordDO);
    }

    /**
     * 获取cell的值
     * @param row
     * @param num
     * @return
     */
    private String getStringCellValue(HSSFRow row,Integer num){
        HSSFCell cel = row.createCell(num);
        if(cel == null){
            return null;
        }
        return cel.getStringCellValue();
    }
}
