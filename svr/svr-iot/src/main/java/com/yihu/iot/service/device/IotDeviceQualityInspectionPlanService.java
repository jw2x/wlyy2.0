package com.yihu.iot.service.device;

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.iot.dao.device.IotDeviceOrderDao;
import com.yihu.iot.dao.device.IotDeviceQualityInspectionPlanDao;
import com.yihu.iot.dao.device.IotOrderPurchaseDao;
import com.yihu.jw.iot.device.IotDeviceQualityInspectionPlanDO;
import com.yihu.jw.iot.device.IotOrderPurchaseDO;
import com.yihu.jw.util.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        iotDeviceQualityInspectionPlanDao.save(planDO);
    }
}
