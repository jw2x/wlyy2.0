package com.yihu.iot.service.device;

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.iot.dao.device.IotDeviceOrderDao;
import com.yihu.iot.dao.device.IotDeviceQualityInspectionPlanDao;
import com.yihu.iot.dao.device.IotOrderPurchaseDao;
import com.yihu.jw.iot.device.IotDeviceQualityInspectionPlanDO;
import com.yihu.jw.iot.device.IotOrderPurchaseDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.iot.device.IotDeviceQualityInspectionPlanVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import com.yihu.jw.util.date.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yeshijie on 2017/12/8.
 */
@Service
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

    /**
     * 新增
     * @param iotDeviceQualityInspectionPlan
     * @return
     */
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
        if(StringUtils.isNotBlank(purchaseDO.getQualityStatus())
                ||(iotDeviceQualityInspectionPlan.getPlanTime().getTime()-purchaseDO.getNextQualityTime().getTime())<0){
            purchaseDO.setQualityStatus(IotDeviceQualityInspectionPlanDO.QualityPlanStatus.create.getValue());
            purchaseDO.setNextQualityTime(iotDeviceQualityInspectionPlan.getPlanTime());
            purchaseDO.setQualityLeader(iotDeviceQualityInspectionPlan.getQualityLeader());
            iotOrderPurchaseDao.save(purchaseDO);
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
    }

    /**
     * 完成质检计划
     * @param id
     */
    public void completePlan(String id,String time){
        IotDeviceQualityInspectionPlanDO planDO = iotDeviceQualityInspectionPlanDao.findById(id);
        planDO.setStatus(IotDeviceQualityInspectionPlanDO.QualityPlanStatus.complete.getValue());
        planDO.setActualTime(DateUtil.strToDate(time));
        //更新采购清单的质检信息
        List<IotDeviceQualityInspectionPlanDO> list = iotDeviceQualityInspectionPlanDao.
                findListByPurchaseId(planDO.getPurchaseId(),IotDeviceQualityInspectionPlanDO.QualityPlanStatus.create.getValue());
        if(list==null||list.size()==0){
            IotDeviceQualityInspectionPlanDO last = iotDeviceQualityInspectionPlanDao.findLastByPurchaseId(planDO.getPurchaseId());
            IotOrderPurchaseDO purchaseDO = iotOrderPurchaseDao.findById(planDO.getPurchaseId());
            purchaseDO.setQualityStatus(IotDeviceQualityInspectionPlanDO.QualityPlanStatus.complete.getValue());
            purchaseDO.setNextQualityTime(last.getPlanTime());
            purchaseDO.setQualityLeader(last.getQualityLeader());
            iotOrderPurchaseDao.save(purchaseDO);
        }

        iotDeviceQualityInspectionPlanDao.save(planDO);
    }

    /**
     * 分页查找
     * @param page
     * @param size
     * @param purcharseId
     * @return
     */
    public Envelop<IotDeviceQualityInspectionPlanVO> queryPage(String purcharseId,String orderNo,String startTime,String endTime,Integer page,Integer size){
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
        List<IotDeviceQualityInspectionPlanVO> qualityInspectionPlanVOList = convertToModels(list,new ArrayList<>(list.size()),IotDeviceQualityInspectionPlanVO.class);

        return Envelop.getSuccessListWithPage(IotRequestMapping.Common.message_success_find_functions,qualityInspectionPlanVOList, page, size,count);
    }
}
