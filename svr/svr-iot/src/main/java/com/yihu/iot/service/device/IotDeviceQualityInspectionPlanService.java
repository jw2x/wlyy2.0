package com.yihu.iot.service.device;

import com.yihu.iot.dao.device.IotDeviceDao;
import com.yihu.iot.dao.device.IotDeviceOrderDao;
import com.yihu.iot.dao.device.IotDeviceQualityInspectionPlanDao;
import com.yihu.iot.dao.device.IotOrderPurchaseDao;
import com.yihu.iot.service.dict.IotSystemDictService;
import com.yihu.jw.entity.iot.device.IotDeviceQualityInspectionPlanDO;
import com.yihu.jw.entity.iot.device.IotOrderPurchaseDO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.iot.device.IotDeviceQualityInspectionPlanVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import com.yihu.jw.util.date.DateUtil;
import com.yihu.mysql.query.BaseJpaService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
@Transactional
public class IotDeviceQualityInspectionPlanService extends BaseJpaService<IotDeviceQualityInspectionPlanDO,IotDeviceQualityInspectionPlanDao> {

    @Autowired
    private IotDeviceQualityInspectionPlanDao iotDeviceQualityInspectionPlanDao;
    @Autowired
    private IotOrderPurchaseService iotOrderPurchaseService;
    @Autowired
    private IotOrderPurchaseDao iotOrderPurchaseDao;
    @Autowired
    private IotDeviceOrderService iotDeviceOrderService;
    @Autowired
    private IotDeviceOrderDao iotDeviceOrderDao;
    @Autowired
    private JdbcTemplate jdbcTempalte;
    @Autowired
    private IotSystemDictService iotSystemDictService;
    @Autowired
    private IotDeviceDao iotDeviceDao;

    /**
     * 新增
     * @param iotDeviceQualityInspectionPlan
     * @return
     */
    @Transactional
    public IotDeviceQualityInspectionPlanDO create(IotDeviceQualityInspectionPlanDO iotDeviceQualityInspectionPlan) {

        IotOrderPurchaseDO purchaseDO = iotOrderPurchaseDao.findById(iotDeviceQualityInspectionPlan.getPurchaseId());

        iotDeviceQualityInspectionPlan.setOrderNo(purchaseDO.getOrderNo());
        iotDeviceQualityInspectionPlan.setDeviceId(purchaseDO.getProductId());
        iotDeviceQualityInspectionPlan.setDeviceName(purchaseDO.getDeviceName());
        iotDeviceQualityInspectionPlan.setOrderId(purchaseDO.getOrderId());
        iotDeviceQualityInspectionPlan.setPurchaseNum(purchaseDO.getPurchaseNum());
        iotDeviceQualityInspectionPlan.setSaasId(getCode());
        iotDeviceQualityInspectionPlan.setStatus(IotDeviceQualityInspectionPlanDO.QualityPlanStatus.create.getValue());
        iotDeviceQualityInspectionPlan.setDel(1);
        //更新采购清单的质检信息
        if(StringUtils.isBlank(purchaseDO.getQualityStatus())||purchaseDO.getQualityStatus().equals(IotDeviceQualityInspectionPlanDO.QualityPlanStatus.complete.getValue())
                ||(iotDeviceQualityInspectionPlan.getPlanTime().getTime()-purchaseDO.getNextQualityTime().getTime())<0){
            purchaseDO.setQualityStatus(IotDeviceQualityInspectionPlanDO.QualityPlanStatus.create.getValue());
            purchaseDO.setNextQualityTime(iotDeviceQualityInspectionPlan.getPlanTime());
            purchaseDO.setQualityLeader(iotDeviceQualityInspectionPlan.getQualityLeader());
            iotOrderPurchaseDao.save(purchaseDO);

            iotDeviceDao.updateQualityTime(iotDeviceQualityInspectionPlan.getPlanTime(),purchaseDO.getId());
        }

        return iotDeviceQualityInspectionPlanDao.save(iotDeviceQualityInspectionPlan);
    }

    /**
     * 按id查找
     * @param id
     * @return
     */
    public IotDeviceQualityInspectionPlanDO findById(String id) {
        return iotDeviceQualityInspectionPlanDao.findById(id);
    }

    /**
     * 删除
     * @param id
     */
    public void delPlan(String id){
        IotDeviceQualityInspectionPlanDO planDO = iotDeviceQualityInspectionPlanDao.findById(id);
        planDO.setDel(0);
        iotDeviceQualityInspectionPlanDao.save(planDO);
        updatePurchase(planDO.getPurchaseId());
    }

    /**
     * 完成质检计划
     * @param purchaseId
     */
    public void completePlanByPurchaseId(String purchaseId,String time){
        IotDeviceQualityInspectionPlanDO planDO = iotDeviceQualityInspectionPlanDao.findFirstByPurchaseId(purchaseId,IotDeviceQualityInspectionPlanDO.QualityPlanStatus.create.getValue());
        if(planDO!=null){
            planDO.setStatus(IotDeviceQualityInspectionPlanDO.QualityPlanStatus.complete.getValue());
            planDO.setActualTime(DateUtil.strToDate(time));

            iotDeviceQualityInspectionPlanDao.save(planDO);

            updatePurchase(purchaseId);
        }
    }


    /**
     * 更新采购清单的质检信息
     * @param purchaseId
     */
    private void updatePurchase(String purchaseId){
        List<IotDeviceQualityInspectionPlanDO> list = iotDeviceQualityInspectionPlanDao.
                findListByPurchaseId(purchaseId,IotDeviceQualityInspectionPlanDO.QualityPlanStatus.create.getValue());
        if(list==null||list.size()==0){
            IotDeviceQualityInspectionPlanDO last = iotDeviceQualityInspectionPlanDao.findLastByPurchaseId(purchaseId,IotDeviceQualityInspectionPlanDO.QualityPlanStatus.complete.getValue());
            IotOrderPurchaseDO purchaseDO = iotOrderPurchaseDao.findById(purchaseId);
            if(last==null){
                purchaseDO.setNextQualityTime(null);
                purchaseDO.setQualityLeader(null);
                purchaseDO.setQualityStatus(null);
            }else{
                purchaseDO.setNextQualityTime(last.getPlanTime());
                purchaseDO.setQualityLeader(last.getQualityLeader());
                purchaseDO.setQualityStatus(IotDeviceQualityInspectionPlanDO.QualityPlanStatus.complete.getValue());
            }
            iotOrderPurchaseDao.save(purchaseDO);
            iotDeviceDao.updateQualityTime(purchaseDO.getNextQualityTime(),purchaseDO.getId());
        }else {
            IotDeviceQualityInspectionPlanDO last = list.get(0);
            IotOrderPurchaseDO purchaseDO = iotOrderPurchaseDao.findById(purchaseId);
            purchaseDO.setQualityStatus(IotDeviceQualityInspectionPlanDO.QualityPlanStatus.create.getValue());
            purchaseDO.setNextQualityTime(last.getPlanTime());
            purchaseDO.setQualityLeader(last.getQualityLeader());
            iotOrderPurchaseDao.save(purchaseDO);
            iotDeviceDao.updateQualityTime(purchaseDO.getNextQualityTime(),purchaseDO.getId());
        }
    }

    /**
     * 完成质检计划
     * @param id 质检id
     */
    public void completePlan(String id,String time){
        IotDeviceQualityInspectionPlanDO planDO = iotDeviceQualityInspectionPlanDao.findById(id);
        if(planDO!=null){
            planDO.setStatus(IotDeviceQualityInspectionPlanDO.QualityPlanStatus.complete.getValue());
            planDO.setActualTime(DateUtil.strToDate(time));

            iotDeviceQualityInspectionPlanDao.save(planDO);

            updatePurchase(planDO.getPurchaseId());
        }
    }

    /**
     * 字典翻译
     * @param planDO
     * @return
     */
    public IotDeviceQualityInspectionPlanVO transforOne(IotDeviceQualityInspectionPlanDO planDO){
        if(planDO==null){
            return null;
        }
        IotDeviceQualityInspectionPlanVO planVO = convertToModel(planDO,IotDeviceQualityInspectionPlanVO.class);
        if(planDO.getPlanTime()!=null){
            planVO.setPlanTime(DateUtil.dateToStrShort(planDO.getPlanTime()));
        }
        if(planDO.getActualTime()!=null){
            planVO.setActualTime(DateUtil.dateToStrShort(planDO.getActualTime()));
        }
        if(StringUtils.isNotBlank(planDO.getStatus())){
            Map<String,String> qualityStatusMap = iotSystemDictService.findByDictName("QUALITY_STATUS");
            planVO.setStatusName(qualityStatusMap.get(planDO.getStatus()));
        }
        return planVO;
    }

    /**
     * 字典翻译
     * @param list
     * @return
     */
    public List<IotDeviceQualityInspectionPlanVO> transforList(List<IotDeviceQualityInspectionPlanDO> list){
        List<IotDeviceQualityInspectionPlanVO> qualityInspectionPlanVOList = new ArrayList<>();
        if(list!=null&&list.size()>0){
            Map<String,String> qualityStatusMap = iotSystemDictService.findByDictName("QUALITY_STATUS");
            list.forEach(planDO->{
                IotDeviceQualityInspectionPlanVO planVO = convertToModel(planDO,IotDeviceQualityInspectionPlanVO.class);
                if(planDO.getPlanTime()!=null){
                    planVO.setPlanTime(DateUtil.dateToStrShort(planDO.getPlanTime()));
                }
                if(planDO.getActualTime()!=null){
                    planVO.setActualTime(DateUtil.dateToStrShort(planDO.getActualTime()));
                }
                if(StringUtils.isNotBlank(planDO.getStatus())){
                    planVO.setStatusName(qualityStatusMap.get(planDO.getStatus()));
                }
                qualityInspectionPlanVOList.add(planVO);
            });
        }
        return qualityInspectionPlanVOList;
    }

    /**
     * 分页查找
     * @param page
     * @param size
     * @param purcharseId
     * @return
     */
    public MixEnvelop<IotDeviceQualityInspectionPlanVO, IotDeviceQualityInspectionPlanVO> queryPage(String purcharseId, String orderNo, String startTime, String endTime, Integer page, Integer size){
        StringBuffer sql = new StringBuffer("SELECT c.* from iot_device_quality_inspection_plan c  WHERE c.del=1 ");
        StringBuffer sqlCount = new StringBuffer("SELECT COUNT(c.id) count from iot_device_quality_inspection_plan c WHERE c.del=1 ");
        List<Object> args = new ArrayList<>();

        if(StringUtils.isNotBlank(purcharseId)){
            sql.append(" and c.purchase_id=? ");
            sqlCount.append(" and c.purchase_id='").append(purcharseId).append("' ");
            args.add(purcharseId);
        }
        if(StringUtils.isNotBlank(orderNo)){
            sql.append(" and c.order_no=? ");
            sqlCount.append(" and c.order_no='").append(orderNo).append("' ");
            args.add(orderNo);
        }
        if(StringUtils.isNotBlank(startTime)){
            sql.append(" and c.plan_time>=? ");
            sqlCount.append(" and c.plan_time>='").append(startTime).append("' ");
            args.add(startTime);
        }
        if(StringUtils.isNotBlank(endTime)){
            sql.append(" and c.plan_time<=? ");
            sqlCount.append(" and c.plan_time<='").append(endTime).append("' ");
            args.add(endTime);
        }
        sql.append("order by c.update_time desc limit ").append((page-1)*size).append(",").append(size);

        List<IotDeviceQualityInspectionPlanDO> list = jdbcTempalte.query(sql.toString(),args.toArray(),new BeanPropertyRowMapper(IotDeviceQualityInspectionPlanDO.class));
        List<Map<String,Object>> countList = jdbcTempalte.queryForList(sqlCount.toString());
        long count = Long.valueOf(countList.get(0).get("count").toString());

        //DO转VO
        List<IotDeviceQualityInspectionPlanVO> qualityInspectionPlanVOList = transforList(list);

        return MixEnvelop.getSuccessListWithPage(IotRequestMapping.Common.message_success_find_functions,qualityInspectionPlanVOList, page, size,count);
    }
}
