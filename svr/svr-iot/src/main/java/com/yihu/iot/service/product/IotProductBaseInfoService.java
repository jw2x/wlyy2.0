package com.yihu.iot.service.product;

import com.yihu.iot.dao.device.IotOrderPurchaseDao;
import com.yihu.iot.dao.product.*;
import com.yihu.iot.service.dict.IotSystemDictService;
import com.yihu.jw.entity.iot.product.*;
import com.yihu.jw.restmodel.iot.product.*;
import com.yihu.jw.util.date.DateUtil;
import com.yihu.mysql.query.BaseJpaService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private IotSystemDictService iotSystemDictService;
    @Autowired
    private IotOrderPurchaseDao iotOrderPurchaseDao;


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
        //字典翻译
        translateDictForOne(baseInfoVO);

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
            vo2.setMaintenanceUnitId(baseInfoDO.getCompanyId());
            vo2.setMaintenanceUnitName(baseInfoDO.getCompanyName());
            voList.add(vo2);
        }
        return voList;
    }

    /**
     * 删除产品
     * @param id
     */
    public Integer delProduct(String id){
        if(iotOrderPurchaseDao.countByProductId(id)>0){
            return -1;
        }
        IotProductBaseInfoDO baseInfoDO = iotProductBaseInfoDao.findById(id);
        IotProductExtendInfoDO extendInfoDO = iotProductExtendInfoDao.findByProductId(id);

        baseInfoDO.setDel(0);
        extendInfoDO.setDel(0);

        iotProductBaseInfoDao.save(baseInfoDO);
        iotProductExtendInfoDao.save(extendInfoDO);
        return 0;
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
        extendInfoDOOld.setContraindication(extendInfoDO.getContraindication());
        extendInfoDOOld.setDescription(extendInfoDO.getDescription());
        extendInfoDOOld.setProductImg(extendInfoDO.getProductImg());
        extendInfoDOOld.setSpecification(extendInfoDO.getSpecification());
        extendInfoDOOld.setStandard(extendInfoDO.getStandard());
        extendInfoDOOld.setUseRange(extendInfoDO.getUseRange());
        extendInfoDOOld.setVersion(extendInfoDO.getVersion());
        iotProductExtendInfoDao.save(extendInfoDOOld);
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

    /**
     * 产品字典翻译
     * @param iotCompanyVOList
     */
    public void translateDictForList(List<IotProductBaseInfoVO> iotCompanyVOList){
        if(iotCompanyVOList.size()>0){
            //字典翻译
            Map<String,String> product68Map = iotSystemDictService.findByDictName("PRODUCT_68_TYPE");
            Map<String,String> productTypeMap = iotSystemDictService.findByDictName("PRODUCT_TYPE");
            Map<String,String> originTypeMap = iotSystemDictService.findByDictName("ORIGIN_TYPE");
            Map<String,String> productSmallMap = iotSystemDictService.findByDictName("PRODUCT_SMALL_TYPE");
            iotCompanyVOList.forEach(infoVO->{
                if(StringUtils.isNotBlank(infoVO.getType())){
                    infoVO.setTypeName(productTypeMap.get(infoVO.getType()));
                }
                if(StringUtils.isNotBlank(infoVO.getInstrumentClassify())){
                    infoVO.setInstrumentClassifyName(product68Map.get(infoVO.getInstrumentClassify()));
                }
                if(StringUtils.isNotBlank(infoVO.getProductSubclass())){
                    infoVO.setProductSubclassName(productSmallMap.get(infoVO.getProductSubclass()));
                }
                if(infoVO.getOriginType()!=null){
                    infoVO.setOriginTypeName(originTypeMap.get(infoVO.getOriginType().toString()));
                }
            });
        }
    }

    /**
     * 产品字典翻译
     * @param infoVO
     */
    public void translateDictForOne(IotProductBaseInfoVO infoVO){
        if(infoVO!=null){
            //字典翻译
            Map<String,String> product68Map = iotSystemDictService.findByDictName("PRODUCT_68_TYPE");
            Map<String,String> productTypeMap = iotSystemDictService.findByDictName("PRODUCT_TYPE");
            Map<String,String> productSmallMap = iotSystemDictService.findByDictName("PRODUCT_SMALL_TYPE");
            Map<String,String> originTypeMap = iotSystemDictService.findByDictName("ORIGIN_TYPE");
            if(StringUtils.isNotBlank(infoVO.getType())){
                infoVO.setTypeName(productTypeMap.get(infoVO.getType()));
            }
            if(StringUtils.isNotBlank(infoVO.getInstrumentClassify())){
                infoVO.setInstrumentClassifyName(product68Map.get(infoVO.getInstrumentClassify()));
            }
            if(StringUtils.isNotBlank(infoVO.getProductSubclass())){
                infoVO.setProductSubclassName(productSmallMap.get(infoVO.getProductSubclass()));
            }
            if(infoVO.getOriginType()!=null){
                infoVO.setOriginTypeName(originTypeMap.get(infoVO.getOriginType().toString()));
            }
        }
    }

}
