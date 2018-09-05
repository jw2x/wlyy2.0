package com.yihu.jw.service;/**
 * Created by nature of king on 2018/8/28.
 */

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yihu.jw.dao.SpecialistHospitalServiceItemDao;
import com.yihu.jw.dao.SpecialistServiceItemDao;
import com.yihu.jw.entity.specialist.HospitalServiceItemDO;
import com.yihu.jw.entity.specialist.SpecialistServiceItemDO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import org.apache.commons.lang3.StringUtils;
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
     * @param hospitalServiceItemDOS
     * @return
     */
    public MixEnvelop<HospitalServiceItemDO,HospitalServiceItemDO> insert(List<HospitalServiceItemDO> hospitalServiceItemDOS){
        MixEnvelop<HospitalServiceItemDO,HospitalServiceItemDO> envelop = new MixEnvelop<>();
        if (hospitalServiceItemDOS !=null && hospitalServiceItemDOS.size()!=0){
            for (HospitalServiceItemDO hospitalServiceItemDO:hospitalServiceItemDOS){
                specialistHospitalServiceItemDao.save(hospitalServiceItemDO);
            }
        }
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
        if (hospitals!=null && hospitals.size()!=0){
            buffer.append(" and hospital in (");
            for (int i =0 ;i<hospitals.size();i++){
                buffer.append("'"+hospitals.get(i)+"',");
            }
            buffer.deleteCharAt(buffer.length()-1);
            buffer.append(")");
        }
        String sql = "select * from wlyy_hospital_service_item where 1=1 "+buffer;
        List<HospitalServiceItemDO> hospitalServiceItemDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(HospitalServiceItemDO.class));
        for (HospitalServiceItemDO hospitalServiceItemDO:hospitalServiceItemDOS){
            SpecialistServiceItemDO specialistServiceItemDO = specialistServiceItemDao.findOne(hospitalServiceItemDO.getServiceItemId());
            hospitalServiceItemDO.setSpecialistServiceItemDO(specialistServiceItemDO);
        }
        envelop.setDetailModelList(hospitalServiceItemDOS);
        return envelop;
    }


    /**
     *
     * @param hospital
     * @param docHospital
     * @param serviceItemName
     * @return
     */
    public MixEnvelop<JSONArray,JSONArray> selectByHospital1(String hospital,String docHospital,String serviceItemName){
        MixEnvelop<JSONArray,JSONArray> envelop = new MixEnvelop<>();
        String sqlUtil = "";
        if (StringUtils.isNoneBlank(serviceItemName)){
            sqlUtil="and service_item_name = '"+serviceItemName+"'";
        }
        String sql = "select * from wlyy_hospital_service_item where 1=1 AND hospital = '"+hospital+"' "+sqlUtil;
        List<HospitalServiceItemDO> hospitalServiceItemDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(HospitalServiceItemDO.class));
        String sql1 = "select * from wlyy_hospital_service_item where 1=1 AND hospital = '"+docHospital+"' "+sqlUtil;
        List<HospitalServiceItemDO> hospitalServiceItemDOList = jdbcTemplate.query(sql1,new BeanPropertyRowMapper(HospitalServiceItemDO.class));
        List<HospitalServiceItemDO> hospitalServiceItemDOS1 = new ArrayList<>();
        for (HospitalServiceItemDO hospitalServiceItemDO:hospitalServiceItemDOS){
            boolean flag = false;
            for (HospitalServiceItemDO hospitalServiceItemDO1 :hospitalServiceItemDOList){
                if (hospitalServiceItemDO.getHospital().equals(hospitalServiceItemDO1.getHospital())){
                    hospitalServiceItemDO1.setFlag(3);
                    hospitalServiceItemDOS1.add(hospitalServiceItemDO1);
                    flag = true;
                    hospitalServiceItemDOList.remove(hospitalServiceItemDO1);
                    break;
                }else{
                    hospitalServiceItemDO1.setFlag(2);
                    hospitalServiceItemDOS1.add(hospitalServiceItemDO1);
                    flag = false;
                }
            }
            if (flag==false){
                hospitalServiceItemDO.setFlag(1);
                hospitalServiceItemDOS1.add(hospitalServiceItemDO);
            }
        }
        for (HospitalServiceItemDO hospitalServiceItemDO:hospitalServiceItemDOS1){
            SpecialistServiceItemDO specialistServiceItemDO = specialistServiceItemDao.findOne(hospitalServiceItemDO.getServiceItemId());
            hospitalServiceItemDO.setSpecialistServiceItemDO(specialistServiceItemDO);
        }
        JSONArray array = new JSONArray();
        List<Integer> itemType = new ArrayList<>();
        for (HospitalServiceItemDO hospitalServiceItemDO:hospitalServiceItemDOS1){
            SpecialistServiceItemDO specialistServiceItemDO = hospitalServiceItemDO.getSpecialistServiceItemDO();
            if (itemType != null && itemType.size() != 0){
                for (int i=0;i<itemType.size();i++){
                    if (!itemType.get(i).equals(specialistServiceItemDO.getItemType())){
                        itemType.add(specialistServiceItemDO.getItemType());
                    }
                }
            }else {
                itemType.add(specialistServiceItemDO.getItemType());
            }
        }
        for (int i =0;i<itemType.size();i++){
            JSONObject object = new JSONObject();
            List<String> type = new ArrayList<>();
            for (int j = 0;j<hospitalServiceItemDOS1.size();j++){
                SpecialistServiceItemDO specialistServiceItemDO = hospitalServiceItemDOS1.get(j).getSpecialistServiceItemDO();
                if (itemType.get(i).equals(specialistServiceItemDO.getItemType())){
                    type.add(specialistServiceItemDO.getDiseaseItem());
                }
            }
            JSONArray itemArray = new JSONArray();
            if (type != null && type.size() != 0){
                for (int z =0 ;z<type.size();z++){
                    List<HospitalServiceItemDO> hospitalServiceItemDOS2 = new ArrayList<>();
                    JSONObject object1 =new JSONObject();
                    object1.put("itemName",type.get(z));
                    for (int j = 0;j<hospitalServiceItemDOS1.size();j++){
                        SpecialistServiceItemDO specialistServiceItemDO = hospitalServiceItemDOS1.get(j).getSpecialistServiceItemDO();
                        if (type.get(z).equals(specialistServiceItemDO.getDiseaseItem())){
                            hospitalServiceItemDOS2.add(hospitalServiceItemDOS1.get(j));
                        }
                    }
                    object1.put("hospitalServiceItems",hospitalServiceItemDOS2);
                    itemArray.add(object1);
                }

            }
            object.put("itemType",itemType.get(i));
            object.put("item",itemArray);
            array.add(object);
        }
        List<JSONArray> list = new ArrayList<>();
        list.add(array);
        envelop.setDetailModelList(list);
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
        if(hospitalServiceItems != null && hospitalServiceItems.size()!=0){
            buffer.append(" and id in (");
            for (int i =0 ;i<hospitalServiceItems.size();i++){
                buffer.append("'"+hospitalServiceItems.get(i)+"',");
            }
            buffer.deleteCharAt(buffer.length()-1);
            buffer.append(")");
        }
        String sql = "select * from wlyy_hospital_service_item where 1=1 "+buffer;
        List<HospitalServiceItemDO> hospitalServiceItemDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(HospitalServiceItemDO.class));
        for (HospitalServiceItemDO hospitalServiceItemDO:hospitalServiceItemDOS){
            SpecialistServiceItemDO specialistServiceItemDO = specialistServiceItemDao.findOne(hospitalServiceItemDO.getServiceItemId());
            hospitalServiceItemDO.setSpecialistServiceItemDO(specialistServiceItemDO);
        }
        envelop.setDetailModelList(hospitalServiceItemDOS);
        return envelop;
    }

    /**
     * 按条件查询机构服务项目
     *
     * @param serviceItemName 服务项目名称
     *
     * @param hospitals 医院code集合
     * @return
     */
    public MixEnvelop<HospitalServiceItemDO,HospitalServiceItemDO> selectByCondition(String serviceItemName,List<String> hospitals){
        MixEnvelop<HospitalServiceItemDO,HospitalServiceItemDO> envelop = new MixEnvelop<>();
        if (StringUtils.isNoneBlank(serviceItemName)){
            String sql = "select * from wlyy_hospital_service_item where service_item_name = '"+serviceItemName+"' and status=1";
            List<HospitalServiceItemDO> hospitalServiceItemDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(HospitalServiceItemDO.class));
            for (HospitalServiceItemDO hospitalServiceItemDO:hospitalServiceItemDOS){
                SpecialistServiceItemDO specialistServiceItemDO = specialistServiceItemDao.findOne(hospitalServiceItemDO.getServiceItemId());
                hospitalServiceItemDO.setSpecialistServiceItemDO(specialistServiceItemDO);
            }
            envelop.setDetailModelList(hospitalServiceItemDOS);
        }else {
            StringBuffer buffer = new StringBuffer();
            if(hospitals != null && hospitals.size()!=0){
                buffer.append(" and hospital in (");
                for (int i =0 ;i<hospitals.size();i++){
                    buffer.append("'"+hospitals.get(i)+"',");
                }
                buffer.deleteCharAt(buffer.length()-1);
                buffer.append(")");
            }
            String sql = "select * from wlyy_hospital_service_item where 1=1 "+buffer;
            List<HospitalServiceItemDO> hospitalServiceItemDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(HospitalServiceItemDO.class));
            for (HospitalServiceItemDO hospitalServiceItemDO:hospitalServiceItemDOS){
                SpecialistServiceItemDO specialistServiceItemDO = specialistServiceItemDao.findOne(hospitalServiceItemDO.getServiceItemId());
                hospitalServiceItemDO.setSpecialistServiceItemDO(specialistServiceItemDO);
            }
            envelop.setDetailModelList(hospitalServiceItemDOS);
        }
        return envelop;
    }

    /**
     * 删除机构服务项目
     *
     * @param hospital 医院code
     *
     * @param serviceItemId 服务项目id
     * @return
     */
    public MixEnvelop<Boolean,Boolean> delete(String hospital, String serviceItemId){
        MixEnvelop<Boolean,Boolean> envelop = new MixEnvelop<>();
        if (StringUtils.isNoneBlank(hospital)){
            String sql = "update wlyy_hospital_service_item set status = 0 where hospital = '"+hospital+"'";
            jdbcTemplate.update(sql);
        }else if (StringUtils.isNoneBlank(serviceItemId)){
            String sql = "update wlyy_hospital_service_item set status = 0 where service_item_id = '"+serviceItemId+"'";
            jdbcTemplate.update(sql);
        }

        return envelop;
    }


}
