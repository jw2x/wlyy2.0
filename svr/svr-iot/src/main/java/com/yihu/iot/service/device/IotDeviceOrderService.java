package com.yihu.iot.service.device;

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.iot.dao.company.IotCompanyTypeDao;
import com.yihu.iot.dao.device.IotDeviceDao;
import com.yihu.iot.dao.device.IotDeviceOrderDao;
import com.yihu.iot.dao.device.IotOrderPurchaseDao;
import com.yihu.jw.iot.company.IotCompanyTypeDO;
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

    /**
     * 新增
     * @param iotOrderVO
     * @return
     */
    public IotDeviceOrderDO create(IotOrderVO iotOrderVO) {

        IotDeviceOrderVO iotDeviceOrderVO = iotOrderVO.getIotDeviceOrderVO();
        List<IotOrderPurchaseVO> iotOrderPurchaseVOList = iotOrderVO.getPurchaseVOList();

        IotDeviceOrderDO iotDeviceOrderDO = convertToModel(iotDeviceOrderVO,IotDeviceOrderDO.class);
        List<IotOrderPurchaseDO> orderPurchaseDOList =
                convertToModels(iotOrderPurchaseVOList,new ArrayList<>(iotOrderPurchaseVOList.size()),IotOrderPurchaseDO.class);

        String time = DateUtil.dateToStr(new Date(),DateUtil.YYYYMMDD);
        List<IotDeviceOrderDO> doList = iotDeviceOrderDao.findByYmd(time);
        String orderNo = String.format("%05d",doList.size());
        iotDeviceOrderDO.setOrderNo(orderNo);
        iotDeviceOrderDO.setOrderStatus(IotDeviceOrderDO.DeviceOrderStatus.create.getValue());
        iotDeviceOrderDO.setSaasId(getCode());
        iotDeviceOrderDO.setDel(1);
        iotDeviceOrderDO.setYmd(time);
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
     * 删除
     * @param id
     */
    public void delOrder(String id){
        IotDeviceOrderDO order = iotDeviceOrderDao.findById(id);
        order.setDel(0);
        iotDeviceOrderDao.save(order);
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
        iotDeviceOrderDao.save(iotDeviceOrderDO);
        //采购清单
        orderPurchaseDOList.forEach(purchase->{
            IotOrderPurchaseDO purchaseDOOld = iotOrderPurchaseDao.findById(purchase.getId());
            purchase.setDel(1);
            purchase.setOrderId(purchaseDOOld.getOrderId());
            purchase.setOrderNo(purchaseDOOld.getOrderNo());
            purchase.setSaasId(purchaseDOOld.getSaasId());
        });
        iotOrderPurchaseDao.save(orderPurchaseDOList);
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
        List<IotDeviceOrderVO> iotDeviceOrderVOList = convertToModels(list,new ArrayList<>(list.size()),IotDeviceOrderVO.class);
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
            sql.append(" and (c.supplier_name like ? or c.purchaser_name like ?)");
            sqlCount.append(" and (c.supplier_name like '").append(name).append("' or c.purchaser_name like '").append(name).append("')");
            args.add(name);
            args.add(name);
        }
        if(StringUtils.isNotBlank(type)){
            sql.append(" and t.type=? ");
            sqlCount.append(" and t.type='").append(type).append("' ");
            args.add(type);
        }
        sql.append("order by c.update_time desc limit ").append((page-1)*size).append(",").append(size);

        List<IotDeviceOrderDO> list = jdbcTempalte.query(sql.toString(),args.toArray(),new BeanPropertyRowMapper(IotDeviceOrderDO.class));
        List<Map<String,Object>> countList = jdbcTempalte.queryForList(sqlCount.toString());
        long count = Long.valueOf(countList.get(0).get("count").toString());

        //DO转VO
        List<IotDeviceOrderVO> iotDeviceOrderVOList = convertToModels(list,new ArrayList<>(list.size()),IotDeviceOrderVO.class);
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
        if("1".equals(type)){
            iotOrderPurchaseVOList.forEach(purchase->{
                //计算已关联设备数量
                Integer num = iotDeviceDao.countByPurchaseId(purchase.getId());
                Long unNum = (purchase.getPurchaseNum()-num)>0? (purchase.getPurchaseNum()-num):0;
                purchase.setAssociatedNum(Long.valueOf(num));
                purchase.setUnAssociatedNum(unNum);
            });
        }

        return Envelop.getSuccessListWithPage(IotRequestMapping.Common.message_success_find_functions,iotOrderPurchaseVOList, page, size,count);
    }
}
