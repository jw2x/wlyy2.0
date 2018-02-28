package com.yihu.iot.service.product;

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.iot.dao.product.*;
import com.yihu.jw.iot.product.*;
import com.yihu.jw.restmodel.iot.product.*;
import com.yihu.jw.util.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yeshijie on 2018/1/16.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class IotProductBaseInfoService extends BaseJpaService<IotProductBaseInfoDO,IotProductBaseInfoDao> {

    @Autowired
    private IotProductBaseInfoDao iotProductBaseInfoDao;
    @Autowired
    private IotProductMeasuredDataDao iotProductMeasuredDataDao;
    @Autowired
    private IotProductAttachmentDao iotProductAttachmentDao;
    @Autowired
    private IotProductDataTransmissionDao iotProductDataTransmissionDao;
    @Autowired
    private IotProductExtendInfoDao iotProductExtendInfoDao;

    /**
     * 按id查找产品详情
     * @param id
     * @return
     */
    public IotProductVO findProductById(String id){
        IotProductVO iotProductVO = new IotProductVO();
        IotProductBaseInfoDO baseInfoDO = iotProductBaseInfoDao.findById(id);
        IotProductExtendInfoDO extendInfoDO = iotProductExtendInfoDao.findByProductId(id);
        List<IotProductAttachmentDO> attachmentDOList = iotProductAttachmentDao.findByProductId(id);
        List<IotProductMeasuredDataDO> measuredDataDOList = iotProductMeasuredDataDao.findByProductId(id);
        List<IotProductDataTransmissionDO> dataTransmissionDOList = iotProductDataTransmissionDao.findByProductId(id);

        //数据转换
        IotProductBaseInfoVO baseInfoVO = convertToModel(baseInfoDO,IotProductBaseInfoVO.class);
        //日期单独处理
        baseInfoVO.setStartTime(DateUtil.dateToStrShort(baseInfoDO.getStartTime()));
        baseInfoVO.setEndTime(DateUtil.dateToStrShort(baseInfoDO.getEndTime()));

        IotProductExtendInfoVO extendInfoVO = convertToModel(extendInfoDO,IotProductExtendInfoVO.class);
        List<IotProductAttachmentVO> attachmentVOList =
                convertToModels(attachmentDOList,new ArrayList<>(attachmentDOList.size()),IotProductAttachmentVO.class);
        List<IotProductMeasuredDataVO> measuredDataVOList =
                convertToModels(measuredDataDOList,new ArrayList<>(measuredDataDOList.size()),IotProductMeasuredDataVO.class);
        List<IotProductDataTransmissionVO> dataTransmissionVOList =
                convertToModels(dataTransmissionDOList,new ArrayList<>(dataTransmissionDOList.size()),IotProductDataTransmissionVO.class);
        baseInfoVO.setDataTransmissionVOList(dataTransmissionVOList);
        extendInfoVO.setAttachmentVOList(attachmentVOList);

        iotProductVO.setIotProductBaseInfo(baseInfoVO);
        iotProductVO.setIotProductExtendInfo(extendInfoVO);
        iotProductVO.setMeasuredDataVOList(measuredDataVOList);

        return iotProductVO;
    }

    /**
     * 根据产品id查找维护单位
     * @param id
     */
    public List<IotMaintenanceUnitVO> maintenanceUnitById(String id){
        List<IotMaintenanceUnitVO> voList = new ArrayList<IotMaintenanceUnitVO>();
        IotProductBaseInfoDO baseInfoDO = iotProductBaseInfoDao.findById(id);
        //厂商
        IotMaintenanceUnitVO vo1 = new IotMaintenanceUnitVO();
        vo1.setMaintenanceUnitId(baseInfoDO.getSupplierId());
        vo1.setMaintenanceUnitName(baseInfoDO.getSupplierName());
        voList.add(vo1);
        if("2".equals(baseInfoDO.getProductClassify())){
            //代理产品
            IotMaintenanceUnitVO vo2 = new IotMaintenanceUnitVO();
            vo2.setMaintenanceUnitId(baseInfoDO.getAgentId());
            vo2.setMaintenanceUnitName(baseInfoDO.getAgentName());
            voList.add(vo2);
        }
        return voList;
    }

    /**
     * 删除产品
     * @param id
     */
    public void delProduct(String id){
        IotProductBaseInfoDO baseInfoDO = iotProductBaseInfoDao.findById(id);
        IotProductExtendInfoDO extendInfoDO = iotProductExtendInfoDao.findByProductId(id);

        baseInfoDO.setDel(0);
        extendInfoDO.setDel(0);

        iotProductBaseInfoDao.save(baseInfoDO);
        iotProductExtendInfoDao.save(extendInfoDO);
    }

    /**
     * 修改产品
     * @param iotProductVO
     */
    public void updProduct(IotProductVO iotProductVO){
        IotProductBaseInfoVO baseInfoVO = iotProductVO.getIotProductBaseInfo();
        IotProductExtendInfoVO extendInfoVO = iotProductVO.getIotProductExtendInfo();
        List<IotProductAttachmentVO> attachmentVOList = extendInfoVO.getAttachmentVOList();
        List<IotProductMeasuredDataVO> measuredDataVOList = iotProductVO.getMeasuredDataVOList();
        List<IotProductDataTransmissionVO> dataTransmissionVOList = baseInfoVO.getDataTransmissionVOList();

        //数据转换
        IotProductBaseInfoDO baseInfoDO = convertToModel(baseInfoVO,IotProductBaseInfoDO.class);
        //日期单独处理
        baseInfoDO.setStartTime(DateUtil.strToDate(baseInfoVO.getStartTime()));
        baseInfoDO.setEndTime(DateUtil.strToDate(baseInfoVO.getEndTime()));

        IotProductExtendInfoDO extendInfoDO = convertToModel(extendInfoVO,IotProductExtendInfoDO.class);
        List<IotProductAttachmentDO> attachmentDOList =
                convertToModels(attachmentVOList,new ArrayList<>(attachmentVOList.size()),IotProductAttachmentDO.class);
        List<IotProductMeasuredDataDO> measuredDataDOList =
                convertToModels(measuredDataVOList,new ArrayList<>(measuredDataVOList.size()),IotProductMeasuredDataDO.class);
        List<IotProductDataTransmissionDO> dataTransmissionDOList =
                convertToModels(dataTransmissionVOList,new ArrayList<>(dataTransmissionVOList.size()),IotProductDataTransmissionDO.class);

        //基础信息
        String productId = baseInfoDO.getId();
        IotProductBaseInfoDO baseInfoDOOld = iotProductBaseInfoDao.findById(productId);
        baseInfoDO.setDel(1);
        baseInfoDO.setSaasId(baseInfoDOOld.getSaasId());
        iotProductBaseInfoDao.save(baseInfoDO);
        //扩展信息
        IotProductExtendInfoDO extendInfoDOOld = iotProductExtendInfoDao.findByProductId(productId);
        extendInfoDO.setSaasId(extendInfoDOOld.getSaasId());
        extendInfoDO.setDel(1);
        extendInfoDO.setProductId(productId);
        iotProductExtendInfoDao.save(extendInfoDO);
        //附件
        List<IotProductAttachmentDO> attachmentDOOldList = iotProductAttachmentDao.findByProductId(productId);
        iotProductAttachmentDao.delete(attachmentDOOldList);
        String extendId = extendInfoDO.getId();
        attachmentDOList.forEach(one->{
            one.setProductId(productId);
            one.setSaasId(getCode());
            one.setDel(1);
            one.setProductExtendId(extendId);
        });
        iotProductAttachmentDao.save(attachmentDOList);
        //测量数据
        List<IotProductMeasuredDataDO> measuredDataDOOldList = iotProductMeasuredDataDao.findByProductId(productId);
        iotProductMeasuredDataDao.delete(measuredDataDOOldList);
        measuredDataDOList.forEach(one->{
            one.setProductId(productId);
            one.setSaasId(getCode());
            one.setDel(1);
        });
        iotProductMeasuredDataDao.save(measuredDataDOList);
        //数据传输方式
        List<IotProductDataTransmissionDO> dataTransmissionDOOldList = iotProductDataTransmissionDao.findByProductId(productId);
        iotProductDataTransmissionDao.delete(dataTransmissionDOOldList);
        dataTransmissionDOList.forEach(one->{
            one.setDel(1);
            one.setSaasId(getCode());
            one.setProductId(productId);
        });
        iotProductDataTransmissionDao.save(dataTransmissionDOList);
    }

    /**
     * 新增产品
     * @param iotProductVO
     */
    public void addProduct(IotProductVO iotProductVO){
        IotProductBaseInfoVO baseInfoVO = iotProductVO.getIotProductBaseInfo();
        IotProductExtendInfoVO extendInfoVO = iotProductVO.getIotProductExtendInfo();
        List<IotProductAttachmentVO> attachmentVOList = extendInfoVO.getAttachmentVOList();
        List<IotProductMeasuredDataVO> measuredDataVOList = iotProductVO.getMeasuredDataVOList();
        List<IotProductDataTransmissionVO> dataTransmissionVOList = baseInfoVO.getDataTransmissionVOList();

        //数据转换
        IotProductBaseInfoDO baseInfoDO = convertToModel(baseInfoVO,IotProductBaseInfoDO.class);
        //日期单独处理
        baseInfoDO.setStartTime(DateUtil.strToDate(baseInfoVO.getStartTime()));
        baseInfoDO.setEndTime(DateUtil.strToDate(baseInfoVO.getEndTime()));
        IotProductExtendInfoDO extendInfoDO = convertToModel(extendInfoVO,IotProductExtendInfoDO.class);
        List<IotProductAttachmentDO> attachmentDOList =
                convertToModels(attachmentVOList,new ArrayList<>(attachmentVOList.size()),IotProductAttachmentDO.class);
        List<IotProductMeasuredDataDO> measuredDataDOList =
                convertToModels(measuredDataVOList,new ArrayList<>(measuredDataVOList.size()),IotProductMeasuredDataDO.class);
        List<IotProductDataTransmissionDO> dataTransmissionDOList =
                convertToModels(dataTransmissionVOList,new ArrayList<>(dataTransmissionVOList.size()),IotProductDataTransmissionDO.class);

        baseInfoDO.setDel(1);
        baseInfoDO.setSaasId(getCode());
        iotProductBaseInfoDao.save(baseInfoDO);
        String productId = baseInfoDO.getId();
        extendInfoDO.setSaasId(getCode());
        extendInfoDO.setDel(1);
        extendInfoDO.setProductId(productId);
        iotProductExtendInfoDao.save(extendInfoDO);
        String extendId = extendInfoDO.getId();
        attachmentDOList.forEach(one->{
            one.setProductId(productId);
            one.setSaasId(getCode());
            one.setDel(1);
            one.setProductExtendId(extendId);
        });
        iotProductAttachmentDao.save(attachmentDOList);
        measuredDataDOList.forEach(one->{
            one.setProductId(productId);
            one.setSaasId(getCode());
            one.setDel(1);
        });
        iotProductMeasuredDataDao.save(measuredDataDOList);
        dataTransmissionDOList.forEach(one->{
            one.setDel(1);
            one.setSaasId(getCode());
            one.setProductId(productId);
        });
        iotProductDataTransmissionDao.save(dataTransmissionDOList);
    }


}
