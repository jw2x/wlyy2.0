package com.yihu.iot.service.company;

import com.yihu.iot.dao.company.IotCompanyCertificateDao;
import com.yihu.jw.entity.iot.company.IotCompanyCertificateDO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.iot.company.IotCompanyCertificateVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import com.yihu.jw.util.date.DateUtil;
import com.yihu.mysql.query.BaseJpaService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yeshijie on 2018/1/16.
 */
@Service
public class IotCompanyCertificateService extends BaseJpaService<IotCompanyCertificateDO,IotCompanyCertificateDao> {

    @Autowired
    private IotCompanyCertificateDao iotCompanyCertificateDao;

    /**
     * 根据id查找
     * @param id
     * @return
     */
    public IotCompanyCertificateDO findById(String id){
        return iotCompanyCertificateDao.findById(id);
    }

    /**
     * 根据id查找
     * @param companyId
     * @return
     */
    public List<IotCompanyCertificateDO> findByCompanyId(String companyId){
        return iotCompanyCertificateDao.findByCompanyId(companyId);
    }

    /**
     * 分页查找
     * @param page
     * @param size
     * @param name
     * @return
     * @throws ParseException
     */
    public MixEnvelop<IotCompanyCertificateVO, IotCompanyCertificateVO> queryPage(Integer page, Integer size, String name, String companyId) throws ParseException {
        String filters = "del=1;";
        String semicolon = "";
        if(StringUtils.isNotBlank(companyId)){
            filters += "companyId="+companyId;
            semicolon = ";";
        }
        if(StringUtils.isNotBlank(name)){
            filters += semicolon+"name?"+name;
            semicolon = ";";
        }
        String sorts = "-updateTime";
        //得到list数据
        List<IotCompanyCertificateDO> list = search(null, filters, sorts, page, size);
        //获取总数
        long count = getCount(filters);

        //DO转VO
        List<IotCompanyCertificateVO> iotCompanyCertificateVOList = convertToModels(list,new ArrayList<>(list.size()));

        return MixEnvelop.getSuccessListWithPage(IotRequestMapping.Common.message_success_find_functions,iotCompanyCertificateVOList, page, size,count);
    }

    /**
     * 新增/修改
     * @param iotCompanyCertificateDO
     * @return
     */
    public IotCompanyCertificateDO create(IotCompanyCertificateDO iotCompanyCertificateDO) {
        if(StringUtils.isNotBlank(iotCompanyCertificateDO.getId())){
            //修改
            IotCompanyCertificateDO old = iotCompanyCertificateDao.findById(iotCompanyCertificateDO.getId());
            old.setManufacturerBusinessLicense(iotCompanyCertificateDO.getManufacturerBusinessLicense());
            old.setName(iotCompanyCertificateDO.getName());
            old.setManufacturerName(iotCompanyCertificateDO.getManufacturerName());
            old.setManufacturerId(iotCompanyCertificateDO.getManufacturerId());
            old.setCompanyName(iotCompanyCertificateDO.getCompanyName());
            old.setCompanyId(iotCompanyCertificateDO.getCompanyId());
            old.setLaunchCompanyBusinessLicense(iotCompanyCertificateDO.getLaunchCompanyBusinessLicense());
            old.setLaunchCompanyId(iotCompanyCertificateDO.getLaunchCompanyId());
            old.setLaunchCompanyName(iotCompanyCertificateDO.getLaunchCompanyName());
            old.setStartTime(iotCompanyCertificateDO.getStartTime());
            old.setEndTime(iotCompanyCertificateDO.getEndTime());
            old.setCertificateOfAuthorizationImg(iotCompanyCertificateDO.getCertificateOfAuthorizationImg());
            iotCompanyCertificateDao.save(old);
            return old;
        }else {
            //新增
            iotCompanyCertificateDO.setSaasId(getCode());
            iotCompanyCertificateDO.setDel(1);
            iotCompanyCertificateDao.save(iotCompanyCertificateDO);
        }
        return iotCompanyCertificateDO;
    }

    /**
     * 删除
     * @param id
     */
    public void delCompanyCert(String id){
        IotCompanyCertificateDO companyCert = iotCompanyCertificateDao.findById(id);
        companyCert.setDel(0);
        iotCompanyCertificateDao.save(companyCert);
    }

    public List<IotCompanyCertificateVO> convertToModels(List<IotCompanyCertificateDO> iotCompanyCertificateDOList,List<IotCompanyCertificateVO> voList){
        iotCompanyCertificateDOList.forEach(one -> {
            IotCompanyCertificateVO target = new IotCompanyCertificateVO();
            BeanUtils.copyProperties(one, target);
            if(one.getStartTime()!=null){
                target.setStartTime(DateUtil.dateToStrLong(one.getStartTime()));
            }
            if(one.getEndTime()!=null){
                target.setEndTime(DateUtil.dateToStrLong(one.getEndTime()));
            }
            voList.add(target);
        });
        return voList;
    }

}
