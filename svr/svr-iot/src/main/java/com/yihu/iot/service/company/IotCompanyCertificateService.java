package com.yihu.iot.service.company;

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.iot.dao.company.IotCompanyCertificateDao;
import com.yihu.jw.iot.company.IotCompanyCertificateDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.iot.company.IotCompanyCertificateVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import org.apache.commons.lang.StringUtils;
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
    public Envelop<IotCompanyCertificateVO> queryPage(Integer page, Integer size,String name) throws ParseException {
        String filters = "";
        String semicolon = "";
        if(StringUtils.isNotBlank(name)){
            filters = "name?"+name;
            semicolon = ";";
        }
        if(StringUtils.isBlank(filters)){
            filters+= semicolon + "del=1";
        }
        String sorts = "-updateTime";
        //得到list数据
        List<IotCompanyCertificateDO> list = search(null, filters, sorts, page, size);
        //获取总数
        long count = getCount(filters);

        //DO转VO
        List<IotCompanyCertificateVO> iotCompanyCertificateVOList = convertToModels(list,new ArrayList<>(list.size()),IotCompanyCertificateVO.class);

        return Envelop.getSuccessListWithPage(IotRequestMapping.Common.message_success_find_functions,iotCompanyCertificateVOList, page, size,count);
    }

    /**
     * 新增
     * @param iotCompanyCertificateDO
     * @return
     */
    public IotCompanyCertificateDO create(IotCompanyCertificateDO iotCompanyCertificateDO) {

        iotCompanyCertificateDO.setSaasId(getCode());
        iotCompanyCertificateDO.setDel(1);
        iotCompanyCertificateDao.save(iotCompanyCertificateDO);

        return iotCompanyCertificateDO;
    }

}
