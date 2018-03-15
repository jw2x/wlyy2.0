package com.yihu.iot.service.device;

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.iot.dao.company.IotCompanyTypeDao;
import com.yihu.iot.dao.device.IotDeviceDao;
import com.yihu.iot.dao.device.IotDeviceOrderDao;
import com.yihu.iot.dao.device.IotDeviceQualityInspectionPlanDao;
import com.yihu.iot.dao.device.IotOrderPurchaseDao;
import com.yihu.iot.service.dict.IotSystemDictService;
import com.yihu.jw.iot.company.IotCompanyTypeDO;
import com.yihu.jw.iot.device.IotDeviceDO;
import com.yihu.jw.iot.device.IotDeviceOrderDO;
import com.yihu.jw.iot.device.IotOrderPurchaseDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.iot.company.IotCompanyTypeVO;
import com.yihu.jw.restmodel.iot.device.IotDeviceOrderVO;
import com.yihu.jw.restmodel.iot.device.IotOrderPurchaseVO;
import com.yihu.jw.restmodel.iot.device.IotOrderVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import com.yihu.jw.util.date.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yeshijie on 2017/12/8.
 */
@Service
public class IotDeviceOrderService extends BaseJpaService<IotDeviceOrderDO,IotDeviceOrderDao> {

    @Autowired
    private IotDeviceOrderDao iotDeviceOrderDao;
    @Autowired
    private IotOrderPurchaseDao iotOrderPurchaseDao;
    @Autowired
    private IotCompanyTypeDao iotCompanyTypeDao;
    @Autowired
    private JdbcTemplate jdbcTempalte;
    @Autowired
    private IotDeviceDao iotDeviceDao;
    @Autowired
    private IotDeviceQualityInspectionPlanDao iotDeviceQualityInspectionPlanDao;
    @Autowired
    private IotSystemDictService iotSystemDictService;

    /**
     * 查找采购清单
     * @param id
     * @return
     */
    public IotOrderPurchaseDO findPurchaseById(String id){
        return iotOrderPurchaseDao.findById(id);
    }


    /**
     * 新增
     * @param iotOrderVO
     * @return
     */
    public IotDeviceOrderDO create(IotOrderVO iotOrderVO) {

        IotDeviceOrderVO iotDeviceOrderVO = iotOrderVO.getIotDeviceOrderVO();
        List<IotOrderPurchaseVO> iotOrderPurchaseVOList = iotOrderVO.getPurchaseVOList();

        IotDeviceOrderDO iotDeviceOrderDO = convertToModel(iotDeviceOrderVO,IotDeviceOrderDO.class);
        iotDeviceOrderDO.setPurchaseTime(DateUtil.strToDate(iotDeviceOrderVO.getPurchaseTime()));
        List<IotOrderPurchaseDO> orderPurchaseDOList =
                convertToModels(iotOrderPurchaseVOList,new ArrayList<>(iotOrderPurchaseVOList.size()),IotOrderPurchaseDO.class);

        String time = DateUtil.dateToStr(new Date(),DateUtil.YYYYMMDD);
        List<IotDeviceOrderDO> doList = iotDeviceOrderDao.findByYmd(time);
        String orderNo = time + String.format("%05d",doList.size());
        iotDeviceOrderDO.setOrderNo(orderNo);
        iotDeviceOrderDO.setOrderStatus(IotDeviceOrderDO.DeviceOrderStatus.create.getValue());
        iotDeviceOrderDO.setSaasId(getCode());
        iotDeviceOrderDO.setDel(1);
        iotDeviceOrderDO.setYmd(time);
        iotDeviceOrderDO.setPurchaseTime(DateUtil.strToDate(iotDeviceOrderVO.getPurchaseTime()));
        iotDeviceOrderDao.save(iotDeviceOrderDO);
        String orderId = iotDeviceOrderDO.getId();
        //采购清单
        orderPurchaseDOList.forEach(purchase->{
            purchase.setDel(1);
            purchase.setOrderId(orderId);
            purchase.setOrderNo(orderNo);
            purchase.setSaasId(getCode());
        });
        iotOrderPurchaseDao.save(orderPurchaseDOList);
        //质检计划
//        List<IotDeviceQualityInspectionPlanDO> planDOList = new ArrayList<>();
//        orderPurchaseDOList.forEach(purchase->{
//            //质检计划
//            IotDeviceQualityInspectionPlanDO planDO = new IotDeviceQualityInspectionPlanDO();
//            planDO.setPurchaseNum(purchase.getPurchaseNum());
//            planDO.setOrderNo(orderNo);
//            planDO.setOrderId(orderId);
//            planDO.setSaasId(getCode());
//            planDO.setStatus(IotDeviceQualityInspectionPlanDO.QualityPlanStatus.create.getValue());
//            planDO.setDeviceName(purchase.getDeviceName());
//            planDO.setPurchaseId(purchase.getId());
////            planDO.set
//
//            planDOList.add(planDO);
//        });
//        iotDeviceQualityInspectionPlanDao.save(planDOList);

        return iotDeviceOrderDO;
    }

    /**
     * 按id查找
     * @param id
     * @return
     */
    public IotDeviceOrderDO findById(String id) {
        return iotDeviceOrderDao.findById(id);
    }

    /**
     * 删除采购订单
     * @param id
     */
    public int delPurchase(String id){
        int re = 1;
        IotOrderPurchaseDO purchaseDO = iotOrderPurchaseDao.findById(id);
        List<IotDeviceDO> deviceDOList = iotDeviceDao.findListByPurchaseId(id);
        if(deviceDOList!=null&&deviceDOList.size()>0){
            //有关联设备不能删除
            re = -1;
        }else {
            purchaseDO.setDel(0);
            iotOrderPurchaseDao.save(purchaseDO);
        }
        return re;
    }

    /**
     * 删除订单
     * @param id
     */
    public int delOrder(String id){
        int re = 1;
        IotDeviceOrderDO order = iotDeviceOrderDao.findById(id);
        List<IotDeviceDO> deviceDOList = iotDeviceDao.findListByOrderId(id);
        if(deviceDOList!=null&&deviceDOList.size()>0){
            //有关联设备不能删除
            re = -1;
        }else {
            order.setDel(0);
            iotDeviceOrderDao.save(order);
        }
       return re;
    }

    /**
     * 修改订单
     * @param iotOrderVO
     */
    public void updOrder(IotOrderVO iotOrderVO){
        IotDeviceOrderVO iotDeviceOrderVO = iotOrderVO.getIotDeviceOrderVO();
        List<IotOrderPurchaseVO> iotOrderPurchaseVOList = iotOrderVO.getPurchaseVOList();

        IotDeviceOrderDO iotDeviceOrderDO = convertToModel(iotDeviceOrderVO,IotDeviceOrderDO.class);
        List<IotOrderPurchaseDO> orderPurchaseDOList =
                convertToModels(iotOrderPurchaseVOList,new ArrayList<>(iotOrderPurchaseVOList.size()),IotOrderPurchaseDO.class);

        IotDeviceOrderDO iotDeviceOrderDOOld = iotDeviceOrderDao.findById(iotDeviceOrderDO.getId());
        iotDeviceOrderDO.setOrderNo(iotDeviceOrderDOOld.getOrderNo());
        iotDeviceOrderDO.setOrderStatus(iotDeviceOrderDOOld.getOrderStatus());
        iotDeviceOrderDO.setSaasId(iotDeviceOrderDOOld.getSaasId());
        iotDeviceOrderDO.setDel(1);
        iotDeviceOrderDO.setYmd(iotDeviceOrderDOOld.getYmd());
        iotDeviceOrderDO.setPurchaseTime(DateUtil.strToDate(iotDeviceOrderVO.getPurchaseTime()));
        iotDeviceOrderDao.save(iotDeviceOrderDO);
        //采购清单
        orderPurchaseDOList.forEach(purchase->{
            purchase.setDel(1);
            purchase.setOrderId(iotDeviceOrderDOOld.getId());
            purchase.setOrderNo(iotDeviceOrderDOOld.getOrderNo());
            if(StringUtils.isNotBlank(purchase.getId())){
                IotOrderPurchaseDO purchaseDOOld = iotOrderPurchaseDao.findById(purchase.getId());
                purchase.setSaasId(purchaseDOOld.getSaasId());
            }else {
                purchase.setSaasId(getCode());
            }
        });
        iotOrderPurchaseDao.save(orderPurchaseDOList);
    }

    /**
     * 转换
     * @param sources
     * @param targets
     * @return
     */
    public List<IotDeviceOrderVO> convertToModelVOs(List<IotDeviceOrderDO> sources, List<IotDeviceOrderVO> targets){
        sources.forEach(one -> {
            IotDeviceOrderVO target = new IotDeviceOrderVO();
            BeanUtils.copyProperties(one, target);
            if(one.getPurchaseTime()!=null){
                target.setPurchaseTime(DateUtil.dateToStrShort(one.getPurchaseTime()));
            }
            targets.add(target);
        });
        return targets;
    }

    /**
     * 分页查找
     * @param page
     * @param size
     * @param name
     * @return
     * @throws ParseException
     */
    public Envelop<IotDeviceOrderVO> queryPage(Integer page, Integer size, String name) throws ParseException {
        String filters = "";
        String semicolon = "";
        if(StringUtils.isNotBlank(name)){
            filters = "supplierName?"+name+" g1;purchaserName?"+name+" g1";
            semicolon = ";";
        }
        if(StringUtils.isBlank(filters)){
            filters+= semicolon + "del=1";
        }
        String sorts = "-updateTime";
        //得到list数据
        List<IotDeviceOrderDO> list = search(null, filters, sorts, page, size);

        //获取总数
        long count = getCount(filters);

        //DO转VO
        List<IotDeviceOrderVO> iotDeviceOrderVOList = new ArrayList<>();
        convertToModelVOs(list,iotDeviceOrderVOList);
        iotDeviceOrderVOList.forEach(one->{
            findType(one);
        });

        return Envelop.getSuccessListWithPage(IotRequestMapping.Common.message_success_find_functions,iotDeviceOrderVOList, page, size,count);
    }

    /**
     * 添加类型
     * @param deviceOrderVO
     */
    public void findType(IotDeviceOrderVO deviceOrderVO){
        //查找类型
        List<IotCompanyTypeDO> companyTypes = iotCompanyTypeDao.findByCompanyId(deviceOrderVO.getSupplierId());
        List<IotCompanyTypeVO> list = new ArrayList<>(8);
        if(companyTypes.size()>0){
            companyTypes.forEach(one->{
                IotCompanyTypeVO vo = new IotCompanyTypeVO();
                vo.setType(one.getType());
                vo.setTypeName(one.getTypeName());
                list.add(vo);
            });
        }
        deviceOrderVO.setTypeList(list);
    }

    /**
     * 按类型分页查找
     * @param page
     * @param size
     * @param name
     * @param type
     * @return
     */
    public Envelop<IotDeviceOrderVO> queryPage(Integer page, Integer size, String name, String type){
        StringBuffer sql = new StringBuffer("SELECT DISTINCT c.* from iot_device_order c ,iot_company_type t WHERE c.del=1 ");
        StringBuffer sqlCount = new StringBuffer("SELECT COUNT(DISTINCT c.id) count from iot_device_order c ,iot_company_type t WHERE c.del=1 ");
        List<Object> args = new ArrayList<>();
        if(StringUtils.isNotBlank(name)){
            sql.append(" and (c.supplier_name like '%").append(name).append("%' or c.purchaser_name like '%").append(name).append("%')");
            sqlCount.append(" and (c.supplier_name like '%").append(name).append("%' or c.purchaser_name like '%").append(name).append("%')");
        }
        if(StringUtils.isNotBlank(type)){
            sql.append(" and c.supplier_id=t.company_id and t.type=? ");
            sqlCount.append(" and c.supplier_id=t.company_id and t.type='").append(type).append("' ");
            args.add(type);
        }
        sql.append("order by c.update_time desc limit ").append((page-1)*size).append(",").append(size);

        List<IotDeviceOrderDO> list = jdbcTempalte.query(sql.toString(),args.toArray(),new BeanPropertyRowMapper(IotDeviceOrderDO.class));
        List<Map<String,Object>> countList = jdbcTempalte.queryForList(sqlCount.toString());
        long count = Long.valueOf(countList.get(0).get("count").toString());

        //DO转VO
        List<IotDeviceOrderVO> iotDeviceOrderVOList = new ArrayList<>();
        convertToModelVOs(list,iotDeviceOrderVOList);
        iotDeviceOrderVOList.forEach(one->{
            findType(one);
        });

        return Envelop.getSuccessListWithPage(IotRequestMapping.Common.message_success_find_functions,iotDeviceOrderVOList, page, size,count);
    }

    /**
     * 按类型分页查找
     * @param page
     * @param size
     * @param orderId
     * @param type (1返回已关联数量，2不返回已关联数量)
     * @return
     */
    public Envelop<IotOrderPurchaseVO> queryPurcharsePage(Integer page, Integer size, String orderId, String type){
        StringBuffer sql = new StringBuffer("SELECT c.* from iot_order_purchase c  WHERE c.del=1 ");
        StringBuffer sqlCount = new StringBuffer("SELECT COUNT(c.id) count from iot_order_purchase c WHERE c.del=1 ");
        List<Object> args = new ArrayList<>();

        if(StringUtils.isNotBlank(orderId)){
            sql.append(" and c.order_id=? ");
            sqlCount.append(" and c.order_id='").append(orderId).append("' ");
            args.add(orderId);
        }
        sql.append("order by c.update_time desc limit ").append((page-1)*size).append(",").append(size);

        List<IotOrderPurchaseDO> list = jdbcTempalte.query(sql.toString(),args.toArray(),new BeanPropertyRowMapper(IotOrderPurchaseDO.class));
        List<Map<String,Object>> countList = jdbcTempalte.queryForList(sqlCount.toString());
        long count = Long.valueOf(countList.get(0).get("count").toString());

        //DO转VO
        List<IotOrderPurchaseVO> iotOrderPurchaseVOList = convertToModels(list,new ArrayList<>(list.size()),IotOrderPurchaseVO.class);
        iotOrderPurchaseVOList.forEach(purchase->{
            //计算已关联设备数量
            if("1".equals(type)){
                Integer num = iotDeviceDao.countByPurchaseId(purchase.getId());
                Long unNum = (purchase.getPurchaseNum()-num)>0? (purchase.getPurchaseNum()-num):0;
                purchase.setAssociatedNum(Long.valueOf(num));
                purchase.setUnAssociatedNum(unNum);
            }
        });
        translateForList(iotOrderPurchaseVOList);

        return Envelop.getSuccessListWithPage(IotRequestMapping.Common.message_success_find_functions,iotOrderPurchaseVOList, page, size,count);
    }

    /**
     * 字典翻译
     * @param iotOrderPurchaseVOList
     */
    public void translateForList(List<IotOrderPurchaseVO> iotOrderPurchaseVOList){
        if(iotOrderPurchaseVOList!=null&&iotOrderPurchaseVOList.size()>0){
            Map<String,String> qualityStatusMap = iotSystemDictService.findByDictName("QUALITY_STATUS");
            iotOrderPurchaseVOList.forEach(one->{
                if(StringUtils.isNotBlank(one.getQualityStatus())){
                    one.setQualityStatusName(qualityStatusMap.get(one.getQualityStatus()));
                }
            });
        }
    }

    /**
     * 字典翻译
     * @param list
     */
    public List<IotOrderPurchaseVO> transForList(List<IotOrderPurchaseDO> list){
        List<IotOrderPurchaseVO> iotOrderPurchaseVOList = new ArrayList<>();
        if(list!=null&&list.size()>0){
            Map<String,String> qualityStatusMap = iotSystemDictService.findByDictName("QUALITY_STATUS");
            list.forEach(one->{
                IotOrderPurchaseVO orderPurchaseVO = convertToModel(one,IotOrderPurchaseVO.class);
                if(one.getNextQualityTime()!=null){
                    orderPurchaseVO.setNextQualityTime(DateUtil.dateToStrShort(one.getNextQualityTime()));
                }
                if(StringUtils.isNotBlank(one.getQualityStatus())){
                    orderPurchaseVO.setQualityStatusName(qualityStatusMap.get(one.getQualityStatus()));
                }
                iotOrderPurchaseVOList.add(orderPurchaseVO);
            });
        }

        return iotOrderPurchaseVOList;
    }

    /**
     * 按类型分页查找
     * @param page
     * @param size
     * @return
     */
    public Envelop<IotOrderPurchaseVO> queryPurcharsePage(Integer page, Integer size,
              String qualityStatus,String orderNo,String startTime,String endTime){
        StringBuffer sql = new StringBuffer("SELECT c.* from iot_order_purchase c  WHERE c.del=1 ");
        StringBuffer sqlCount = new StringBuffer("SELECT COUNT(c.id) count from iot_order_purchase c WHERE c.del=1 ");
        List<Object> args = new ArrayList<>();

        if(StringUtils.isNotBlank(qualityStatus)){
            sql.append(" and c.quality_status=? ");
            sqlCount.append(" and c.quality_status='").append(qualityStatus).append("' ");
            args.add(qualityStatus);
        }
        if(StringUtils.isNotBlank(orderNo)){
            sql.append(" and c.order_no like '%").append(orderNo).append("%' ");
            sqlCount.append(" and c.order_no like '%").append(orderNo).append("%' ");
        }
        if(StringUtils.isNotBlank(startTime)){
            sql.append(" and c.next_quality_time>=? ");
            sqlCount.append(" and c.next_quality_time>='").append(startTime).append("' ");
            args.add(startTime);
        }
        if(StringUtils.isNotBlank(endTime)){
            sql.append(" and c.next_quality_time<=? ");
            sqlCount.append(" and c.next_quality_time<='").append(endTime).append("' ");
            args.add(endTime);
        }
        sql.append("order by c.update_time desc limit ").append((page-1)*size).append(",").append(size);

        List<IotOrderPurchaseDO> list = jdbcTempalte.query(sql.toString(),args.toArray(),new BeanPropertyRowMapper(IotOrderPurchaseDO.class));
        List<Map<String,Object>> countList = jdbcTempalte.queryForList(sqlCount.toString());
        long count = Long.valueOf(countList.get(0).get("count").toString());

        //DO转VO
        List<IotOrderPurchaseVO> iotOrderPurchaseVOList = transForList(list);

        return Envelop.getSuccessListWithPage(IotRequestMapping.Common.message_success_find_functions,iotOrderPurchaseVOList, page, size,count);
    }

}
