package com.yihu.iot.service.company;

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.iot.dao.company.IotCompanyCertificateChangeRecordDao;
import com.yihu.iot.dao.company.IotCompanyDao;
import com.yihu.iot.dao.company.IotCompanyTypeDao;
import com.yihu.jw.iot.company.IotCompanyCertificateChangeRecordDO;
import com.yihu.jw.iot.company.IotCompanyDO;
import com.yihu.jw.iot.company.IotCompanyTypeDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.iot.company.IotCompanyTypeVO;
import com.yihu.jw.restmodel.iot.company.IotCompanyVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import com.yihu.jw.util.date.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;

/**
 * @author yeshijie on 2018/1/15.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class IotCompanyService extends BaseJpaService<IotCompanyDO,IotCompanyDao> {

    @Autowired
    private IotCompanyDao iotCompanyDao;
    @Autowired
    private IotCompanyTypeDao iotCompanyTypeDao;
    @Autowired
    private JdbcTemplate jdbcTempalte;
    @Autowired
    private IotCompanyCertificateChangeRecordDao iotCompanyCertificateChangeRecordDao;

    /**
     * 分页查找
     * @param page
     * @param size
     * @param status
     * @param name
     * @return
     * @throws ParseException
     */
    public Envelop<IotCompanyVO> queryPage(Integer page,Integer size,String status,String name) throws ParseException {
        String filters = "del=1;";
        String semicolon = "";
        if(StringUtils.isNotBlank(name)){
            filters += "name?"+name+" g1;contactsName?"+name+" g1";
            semicolon = ";";
        }
        if(StringUtils.isNotBlank(status)){
            filters += semicolon +"status="+status;
            semicolon = ";";
        }
        String sorts = "-updateTime";
        //得到list数据
        List<IotCompanyDO> list = search(null, filters, sorts, page, size);
        list.forEach(one->{
            findType(one);
        });
        //获取总数
        long count = getCount(filters);

        //DO转VO
        List<IotCompanyVO> iotCompanyVOList = convertToModelVOs(list,new ArrayList<>(list.size()));

        return Envelop.getSuccessListWithPage(IotRequestMapping.Company.message_success_find_functions,iotCompanyVOList, page, size,count);
    }

    /**
     * 转换
     * @param sources
     * @param targets
     * @return
     */
    public List<IotCompanyVO> convertToModelVOs(Collection sources, List<IotCompanyVO> targets){
        sources.forEach(one -> {
            IotCompanyVO target = new IotCompanyVO();
            BeanUtils.copyProperties(one, target);
            List<IotCompanyTypeVO> voList = convertToModels(target.getTypeList(),new ArrayList<>(target.getTypeList().size()),IotCompanyTypeVO.class);
            target.setTypeList(voList);
            targets.add(target);
        });
        return targets;
    }

    /**
     * 单个转换
     * @return
     */
    public IotCompanyVO convertToModelVO(IotCompanyDO iotCompanyDO){
        if(iotCompanyDO==null){
            return null;
        }
        IotCompanyVO target = new IotCompanyVO();
        BeanUtils.copyProperties(iotCompanyDO, target);
        target.setBusinessEndTime(DateUtil.dateToStrShort(iotCompanyDO.getBusinessEndTime()));
        target.setBusinessStartTime(DateUtil.dateToStrShort(iotCompanyDO.getBusinessStartTime()));
        if(target.getTypeList()!=null){
            List<IotCompanyTypeVO> voList = convertToModels(target.getTypeList(),new ArrayList<>(target.getTypeList().size()),IotCompanyTypeVO.class);
            target.setTypeList(voList);
        }
        return target;
    }

    /**
     * 单个转换
     * @return
     */
    public IotCompanyDO convertToModelDO(IotCompanyVO iotCompanyVO){
        IotCompanyDO target = new IotCompanyDO();
        BeanUtils.copyProperties(iotCompanyVO, target);
        if(StringUtils.isNotBlank(iotCompanyVO.getBusinessEndTime())){
            target.setBusinessEndTime(DateUtil.strToDate(iotCompanyVO.getBusinessEndTime()));
        }
        if(StringUtils.isNotBlank(iotCompanyVO.getBusinessStartTime())){
            target.setBusinessStartTime(DateUtil.strToDate(iotCompanyVO.getBusinessStartTime()));
        }
        List<IotCompanyTypeDO> voList = convertToModels(target.getTypeList(),new ArrayList<>(target.getTypeList().size()),IotCompanyTypeDO.class);
        target.setTypeList(voList);
        return target;
    }

    /**
     * 按类型分页查找
     * @param page
     * @param size
     * @param status
     * @param name
     * @param type
     * @return
     */
    public Envelop<IotCompanyVO> queryPage(Integer page, Integer size, String status, String name, String type){
        StringBuffer sql = new StringBuffer("SELECT DISTINCT c.* from iot_company c ,iot_company_type t WHERE c.del=1 ");
        StringBuffer sqlCount = new StringBuffer("SELECT COUNT(DISTINCT c.id) count from iot_company c ,iot_company_type t WHERE c.del=1 ");
        List<Object> args = new ArrayList<>();
        if(StringUtils.isNotBlank(status)){
            sql.append(" and c.status=? ");
            sqlCount.append(" and c.status='").append(status).append("' ");
            args.add(status);
        }
        if(StringUtils.isNotBlank(name)){
            sql.append(" and (c.name like ? or c.contacts_name like ?)");
            sqlCount.append(" and (c.name like '").append(name).append("' or c.contacts_name like '").append(name).append("')");
            args.add(name);
            args.add(name);
        }
        if(StringUtils.isNotBlank(type)){
            sql.append(" and c.id = t.company_id and t.type=? ");
            sqlCount.append(" and c.id = t.company_id and t.type='").append(type).append("' ");
            args.add(type);
        }
        sql.append("order by c.update_time desc limit ").append((page-1)*size).append(",").append(size);

        List<IotCompanyDO> list = jdbcTempalte.query(sql.toString(),args.toArray(),new BeanPropertyRowMapper(IotCompanyDO.class));
        list.forEach(one->{
            findType(one);
        });
        List<Map<String,Object>> countList = jdbcTempalte.queryForList(sqlCount.toString());
        long count = Long.valueOf(countList.get(0).get("count").toString());

        //DO转VO
        List<IotCompanyVO> iotCompanyVOList = convertToModelVOs(list,new ArrayList<>(list.size()));

        return Envelop.getSuccessListWithPage(IotRequestMapping.Company.message_success_find_functions,iotCompanyVOList, page, size,count);
    }

    /**
     * 新增
     * @param iotCompany
     * @return
     */
    public IotCompanyDO create(IotCompanyDO iotCompany) {
        iotCompany.setStatus("1");
        iotCompany.setSaasId(getCode());
        iotCompany.setDel(1);
        List<IotCompanyTypeDO> list = iotCompany.getTypeList();
        iotCompany = iotCompanyDao.save(iotCompany);
        String id = iotCompany.getId();
        //新增类型
        List<IotCompanyTypeDO> companyTypes = new ArrayList<>(10);
        list.forEach(one->{
            IotCompanyTypeDO companyType = new IotCompanyTypeDO();
            companyType.setSaasId(getCode());
            companyType.setCompanyId(id);
            companyType.setType(one.getType());
            companyType.setTypeName(one.getTypeName());
            companyTypes.add(companyType);
        });
        iotCompanyTypeDao.save(companyTypes);

        return iotCompany;
    }

    /**
     * 按id查找
     * @param id
     * @return
     */
    public IotCompanyDO findById(String id) {
        IotCompanyDO company = iotCompanyDao.findById(id);
        findType(company);
        return company;
    }

    /**
     * 按营业执照号查询
     * @param businessLincense
     * @return
     */
    public IotCompanyDO findByBusinessLicense(String businessLincense){
        return iotCompanyDao.findByBusinessLicense(businessLincense);
    }

    /**
     * 添加类型
     * @param company
     */
    public void findType(IotCompanyDO company){
        if(company==null){
            return;
        }
        //查找类型
        List<IotCompanyTypeDO> companyTypes = iotCompanyTypeDao.findByCompanyId(company.getId());
        List<IotCompanyTypeDO> list = new ArrayList<>(8);
        if(companyTypes.size()>0){
            companyTypes.forEach(one->{
                IotCompanyTypeDO vo = new IotCompanyTypeDO();
                vo.setType(one.getType());
                vo.setTypeName(one.getTypeName());
                list.add(vo);
            });
        }
        company.setTypeList(list);
    }

    /**
     * 查找企业类型
     * @param companyId
     * @return
     */
    public List<IotCompanyTypeDO> findTypeByCompanyId(String companyId){
        return iotCompanyTypeDao.findByCompanyId(companyId);
    }

    /**
     * 删除
     * @param id
     */
    public void delCompany(String id){
        IotCompanyDO company = iotCompanyDao.findById(id);
        company.setDel(0);
        iotCompanyDao.save(company);
    }

    /**
     * 修改
     * @param iotCompany
     */
    public void updCompany(IotCompanyDO iotCompany){
        //更新类型
        List<IotCompanyTypeDO> typeList = iotCompanyTypeDao.findByCompanyId(iotCompany.getId());
        iotCompanyTypeDao.delete(typeList);
        List<IotCompanyTypeDO> companyTypes = new ArrayList<>(10);

        iotCompany.getTypeList().forEach(one->{
            IotCompanyTypeDO companyType = new IotCompanyTypeDO();
            companyType.setSaasId(getCode());
            companyType.setCompanyId(iotCompany.getId());
            companyType.setType(one.getType());
            companyType.setTypeName(one.getTypeName());
            companyTypes.add(companyType);
        });

        iotCompanyTypeDao.save(companyTypes);

        //记录三证变更记录
        IotCompanyDO iotCompanyOld = iotCompanyDao.findById(iotCompany.getId());
        List<IotCompanyCertificateChangeRecordDO> recordDOList = new ArrayList<>(3);
        if(compare(iotCompany.getBusinessLicense(),iotCompanyOld.getBusinessLicense())||
                compare(iotCompany.getBusinessLicenseImg(),iotCompanyOld.getBusinessLicenseImg())){
            //营业执照
            IotCompanyCertificateChangeRecordDO recordDO = new IotCompanyCertificateChangeRecordDO();
            recordDO.setCompanyId(iotCompany.getId());
            recordDO.setSaasId(getCode());
            recordDO.setType("1");
            recordDO.setCreateTime(new Date());
            recordDO.setCertificateNew(iotCompany.getBusinessLicenseImg());
            recordDO.setLicenseNew(iotCompany.getBusinessLicense());
            recordDO.setCertificateOld(iotCompanyOld.getBusinessLicenseImg());
            recordDO.setLicenseOld(iotCompanyOld.getBusinessLicense());
            recordDO.setCompanyName(iotCompany.getName());
            recordDOList.add(recordDO);
        }
        if(compare(iotCompany.getOrganizationCodeImg(),iotCompanyOld.getOrganizationCodeImg())){
            //组织机构代码
            IotCompanyCertificateChangeRecordDO recordDO = new IotCompanyCertificateChangeRecordDO();
            recordDO.setCompanyId(iotCompany.getId());
            recordDO.setSaasId(getCode());
            recordDO.setType("2");
            recordDO.setCreateTime(new Date());
            recordDO.setCertificateNew(iotCompany.getOrganizationCodeImg());
            recordDO.setCertificateOld(iotCompanyOld.getOrganizationCodeImg());
            recordDO.setCompanyName(iotCompany.getName());
            recordDOList.add(recordDO);
        }
        if(compare(iotCompany.getTaxRegistrationImg(),iotCompanyOld.getTaxRegistrationImg())){
            //税务登记证
            IotCompanyCertificateChangeRecordDO recordDO = new IotCompanyCertificateChangeRecordDO();
            recordDO.setCompanyId(iotCompany.getId());
            recordDO.setSaasId(getCode());
            recordDO.setType("3");
            recordDO.setCreateTime(new Date());
            recordDO.setCertificateNew(iotCompany.getTaxRegistrationImg());
            recordDO.setCertificateOld(iotCompanyOld.getTaxRegistrationImg());
            recordDO.setCompanyName(iotCompany.getName());
            recordDOList.add(recordDO);
        }
        if(recordDOList.size()>0){
            iotCompanyCertificateChangeRecordDao.save(recordDOList);
        }

        //修改企业记录
        iotCompanyOld.setName(iotCompany.getName());
        iotCompanyOld.setIsThreeInOne(iotCompany.getIsThreeInOne());
        iotCompanyOld.setBusinessLicense(iotCompany.getBusinessLicense());
        iotCompanyOld.setBusinessStartTime(iotCompany.getBusinessStartTime());
        iotCompanyOld.setBusinessEndTime(iotCompany.getBusinessEndTime());
        iotCompanyOld.setOrganizationAddress(iotCompany.getOrganizationAddress());
        iotCompanyOld.setOfficePhone(iotCompany.getOfficePhone());
        iotCompanyOld.setContactsName(iotCompany.getContactsName());
        iotCompanyOld.setContactsMobile(iotCompany.getContactsMobile());
        iotCompanyOld.setContactsIdcard(iotCompany.getContactsIdcard());
        iotCompanyOld.setContactsEmail(iotCompany.getContactsEmail());
        iotCompanyOld.setBusinessLicenseImg(iotCompany.getBusinessLicenseImg());
        iotCompanyOld.setOrganizationCodeImg(iotCompany.getOrganizationCodeImg());
        iotCompanyOld.setTaxRegistrationImg(iotCompany.getTaxRegistrationImg());
        iotCompanyOld.setContactsIdcardImg(iotCompany.getContactsIdcardImg());
        iotCompanyDao.save(iotCompanyOld);
    }

    /**
     * 判断三证是否修改
     * @param newStr
     * @param oldStr
     * @return
     */
    public Boolean compare(String newStr,String oldStr){
        if(newStr!=null){
            return newStr.equals(oldStr);
        }else if(oldStr!=null){
            return oldStr.equals(newStr);
        }else {
            return true;
        }
    }
}
