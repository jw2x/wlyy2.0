package com.yihu.jw.service;/**
 * Created by nature of king on 2018/8/28.
 */

import com.yihu.jw.dao.SpecialistHospitalServiceItemDao;
import com.yihu.jw.dao.SpecialistServiceItemDao;
import com.yihu.jw.entity.specialist.HospitalServiceItemDO;
import com.yihu.jw.entity.specialist.SpecialistServiceItemDO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzhinan
 * @create 2018-08-28 19:57
 * @desc 机构服务项目
 **/
@Service
@Transactional
public class SpecialistHospitalServiceItemService extends EnvelopRestEndpoint {

    @Autowired
    private SpecialistHospitalServiceItemDao specialistHospitalServiceItemDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SpecialistServiceItemDao specialistServiceItemDao;


    /**
     * 添加机构服务项目
     *
     * @param hospitalServiceItemDO
     * @return
     */
    public MixEnvelop<HospitalServiceItemDO,HospitalServiceItemDO> insert(HospitalServiceItemDO hospitalServiceItemDO){
        MixEnvelop<HospitalServiceItemDO,HospitalServiceItemDO> envelop = new MixEnvelop<>();
        hospitalServiceItemDO = specialistHospitalServiceItemDao.save(hospitalServiceItemDO);
        List<HospitalServiceItemDO> hospitalServiceItemDOS = new ArrayList<>();
        hospitalServiceItemDOS.add(hospitalServiceItemDO);
        envelop.setDetailModelList(hospitalServiceItemDOS);
        return envelop;
    }


    /**
     * 根据医院code查找数据
     *
     * @param hospitals
     * @return
     */
    public MixEnvelop<HospitalServiceItemDO,HospitalServiceItemDO> selectByHospital(List<String> hospitals){
        MixEnvelop<HospitalServiceItemDO,HospitalServiceItemDO> envelop = new MixEnvelop<>();
        StringBuffer buffer = new StringBuffer();
        buffer.append("hospital in (");
        for (int i =0 ;i<hospitals.size();i++){
            buffer.append("'"+hospitals.get(i)+"',");
        }
        buffer.deleteCharAt(buffer.length()-1);
        buffer.append(")");
        String sql = "select * from wlyy_hospital_service_item where "+buffer;
        List<HospitalServiceItemDO> hospitalServiceItemDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(HospitalServiceItemDO.class));
        for (HospitalServiceItemDO hospitalServiceItemDO:hospitalServiceItemDOS){
            SpecialistServiceItemDO specialistServiceItemDO = specialistServiceItemDao.findOne(hospitalServiceItemDO.getServiceItemId());
            hospitalServiceItemDO.setSpecialistServiceItemDO(specialistServiceItemDO);
        }
        envelop.setDetailModelList(hospitalServiceItemDOS);
        return envelop;
    }

    /**
     * 根据id获取服务项目
     *
     * @param hospitalServiceItems
     * @return
     */
    public MixEnvelop<HospitalServiceItemDO,HospitalServiceItemDO> selectById(List<String> hospitalServiceItems){
        MixEnvelop<HospitalServiceItemDO,HospitalServiceItemDO> envelop = new MixEnvelop<>();
        StringBuffer buffer = new StringBuffer();
        buffer.append("id in (");
        for (int i =0 ;i<hospitalServiceItems.size();i++){
            buffer.append("'"+hospitalServiceItems.get(i)+"',");
        }
        buffer.deleteCharAt(buffer.length()-1);
        buffer.append(")");
        String sql = "select * from wlyy_hospital_service_item where "+buffer;
        List<HospitalServiceItemDO> hospitalServiceItemDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(HospitalServiceItemDO.class));
        for (HospitalServiceItemDO hospitalServiceItemDO:hospitalServiceItemDOS){
            SpecialistServiceItemDO specialistServiceItemDO = specialistServiceItemDao.findOne(hospitalServiceItemDO.getServiceItemId());
            hospitalServiceItemDO.setSpecialistServiceItemDO(specialistServiceItemDO);
        }
        envelop.setDetailModelList(hospitalServiceItemDOS);
        return envelop;
    }

}
