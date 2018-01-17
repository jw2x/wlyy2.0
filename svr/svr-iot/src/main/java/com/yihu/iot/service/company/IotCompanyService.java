package com.yihu.iot.service.company;

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.iot.dao.company.IotCompanyDao;
import com.yihu.iot.dao.company.IotCompanyTypeDao;
import com.yihu.jw.iot.company.IotCompanyDO;
import com.yihu.jw.iot.company.IotCompanyTypeDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.iot.company.IotCompanyTypeVO;
import com.yihu.jw.restmodel.iot.company.IotCompanyVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    /**
     * 分页查找
     * @param page
     * @param size
     * @param status
     * @param name
     * @return
     * @throws ParseException
     */
    public Envelop queryPage(Integer page,Integer size,String status,String name) throws ParseException {
        String filters = "";
        String semicolon = "";
        if(StringUtils.isNotBlank(name)){
            filters = "name?"+name+" g1;contactsName?"+name+" g1";
            semicolon = ";";
        }
        if(StringUtils.isNotBlank(status)){
            filters += semicolon +"status="+status;
            semicolon = ";";
        }
        if(StringUtils.isBlank(filters)){
            filters+= semicolon + "del=1";
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
        List<IotCompanyVO> iotCompanyVOList = convertToModels(list,new ArrayList<>(list.size()),IotCompanyVO.class);

        return Envelop.getSuccessListWithPage(IotRequestMapping.Company.message_success_find_functions,iotCompanyVOList, page, size,count);
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
        StringBuffer sql = new StringBuffer("SELECT DISTINCT c.* from iot_company c ,iot_company_type t WHERE del=1 ");
        StringBuffer sqlCount = new StringBuffer("SELECT COUNT(DISTINCT c.id) count from iot_company c ,iot_company_type t WHERE del=1 ");
        List<Object> args = new ArrayList<>();
        if(StringUtils.isNotBlank(status)){
            sql.append(" and c.status=? ");
            sqlCount.append(" and c.status='").append(status).append("' ");
            args.add(status);
        }
        if(StringUtils.isNotBlank(name)){
            sql.append(" and (c.name like ? or c.contactsName like ?)");
            sqlCount.append(" and (c.name like '").append(name).append("' or c.contactsName like '").append(name).append("')");
            args.add(name);
            args.add(name);
        }
        if(StringUtils.isNotBlank(type)){
            sql.append(" and t.type=? ");
            sqlCount.append(" and t.type='").append(type).append("' ");
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
        List<IotCompanyVO> iotCompanyVOList = convertToModels(list,new ArrayList<>(list.size()),IotCompanyVO.class);

        return Envelop.getSuccessListWithPage(IotRequestMapping.Company.message_success_find_functions,iotCompanyVOList, page, size,count);
    }

    /**
     * 新增
     * @param iotCompany
     * @return
     */
    public IotCompanyDO create(IotCompanyDO iotCompany) {

        iotCompany.setSaasId(getCode());
        iotCompany.setDel(1);
        List<IotCompanyTypeVO> list = iotCompany.getTypeList();
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
     * 添加类型
     * @param company
     */
    public void findType(IotCompanyDO company){
        //查找类型
        List<IotCompanyTypeDO> companyTypes = iotCompanyTypeDao.findByCompanyId(company.getId());
        List<IotCompanyTypeVO> list = new ArrayList<>(8);
        if(companyTypes.size()>0){
            companyTypes.forEach(one->{
                IotCompanyTypeVO vo = new IotCompanyTypeVO();
                vo.setType(one.getType());
                vo.setTypeName(one.getTypeName());
                list.add(vo);
            });
        }
        company.setTypeList(list);
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

        iotCompanyDao.save(iotCompany);
    }
}
