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
import com.yihu.jw.util.DataUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
                hospitalServiceItemDO.setCreateTime(new Date());
                hospitalServiceItemDO.setUpdateTime(new Date());
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
        }else{
            buffer.append("");
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
        if (StringUtils.isNoneBlank(serviceItemName)||serviceItemName != null){
            sqlUtil=" AND service_item_id IN ( SELECT id FROM wlyy_service_item WHERE 1=1 and title LIKE '%"+serviceItemName+"%')";
        }
        List<HospitalServiceItemDO> hospitalServiceItemDOS1 = new ArrayList<>();
        if (StringUtils.isNoneBlank(hospital)&&hospital.equals(docHospital)){
            String sql1 = "select * from wlyy_hospital_service_item where 1=1 AND status = 1 AND imediate=1 AND hospital = '"+docHospital+"'"+sqlUtil;
            List<HospitalServiceItemDO> hospitalServiceItemDOList = jdbcTemplate.query(sql1,new BeanPropertyRowMapper(HospitalServiceItemDO.class));
            for (HospitalServiceItemDO hospitalServiceItemDO:hospitalServiceItemDOList){
                hospitalServiceItemDO.setFlag(3);
                hospitalServiceItemDOS1.add(hospitalServiceItemDO);
            }
        }else if (hospital == null || hospital == ""){
            String sql1 = "select * from wlyy_hospital_service_item where 1=1 AND status = 1 AND imediate=1 AND hospital = '"+docHospital+"'"+sqlUtil;
            List<HospitalServiceItemDO> hospitalServiceItemDOList = jdbcTemplate.query(sql1,new BeanPropertyRowMapper(HospitalServiceItemDO.class));
            for (HospitalServiceItemDO hospitalServiceItemDO:hospitalServiceItemDOList){
                hospitalServiceItemDO.setFlag(2);
                hospitalServiceItemDOS1.add(hospitalServiceItemDO);
            }
        }else{
            String sql = "select * from wlyy_hospital_service_item where 1=1 AND status = 1 AND imediate=1 AND hospital = '"+hospital+"' "+sqlUtil;
            List<HospitalServiceItemDO> hospitalServiceItemDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(HospitalServiceItemDO.class));
            String sql1 = "select * from wlyy_hospital_service_item where 1=1 AND status = 1 AND imediate=1 AND hospital = '"+docHospital+"' "+sqlUtil;
            List<HospitalServiceItemDO> hospitalServiceItemDOList = jdbcTemplate.query(sql1,new BeanPropertyRowMapper(HospitalServiceItemDO.class));
            if (hospitalServiceItemDOList != null && hospitalServiceItemDOList.size()!=0){
                for (HospitalServiceItemDO hospitalServiceItemDO:hospitalServiceItemDOList){
                    boolean flag = false;
                    for (HospitalServiceItemDO hospitalServiceItemDO1 :hospitalServiceItemDOS){
                        boolean isTrue = false;
                        if (hospitalServiceItemDOS1 != null && hospitalServiceItemDOS1.size() != 0){
                            for (HospitalServiceItemDO hospitalServiceItemDO2:hospitalServiceItemDOS1){
                                if (hospitalServiceItemDO1.getServiceItemId().equals(hospitalServiceItemDO2.getServiceItemId())){
                                    if (hospitalServiceItemDO.getServiceItemId().equals(hospitalServiceItemDO1.getServiceItemId())){
                                        hospitalServiceItemDO2.setFlag(3);
                                    }
                                    isTrue = true;

                                }
                            }
                        }
                        if (hospitalServiceItemDO.getServiceItemId().equals(hospitalServiceItemDO1.getServiceItemId())){
                            if (isTrue==false){
                                hospitalServiceItemDO1.setFlag(3);
                                hospitalServiceItemDOS1.add(hospitalServiceItemDO1);
                                flag = true;
                            }
                        }else{
                            if (isTrue == false){
                                hospitalServiceItemDO1.setFlag(1);
                                hospitalServiceItemDOS1.add(hospitalServiceItemDO1);
                            }
                        }
                    }
                    if (flag==false){
                        boolean isTrue1 = false;
                        if (hospitalServiceItemDOS1 != null && hospitalServiceItemDOS1.size() != 0){
                            for (HospitalServiceItemDO hospitalServiceItemDO2:hospitalServiceItemDOS1){
                                if (hospitalServiceItemDO.getServiceItemId().equals(hospitalServiceItemDO2.getServiceItemId())){
                                    isTrue1 = true;
                                }
                            }
                        }
                        if (isTrue1 == false){
                            hospitalServiceItemDO.setFlag(2);
                            hospitalServiceItemDOS1.add(hospitalServiceItemDO);
                        }
                    }
                }
            }else {
                String sql3 = "select * from wlyy_hospital_service_item where 1=1 AND status = 1 AND imediate=1 AND hospital = '"+hospital+"'"+sqlUtil;
                List<HospitalServiceItemDO> hospitalServiceItemDOList1 = jdbcTemplate.query(sql3,new BeanPropertyRowMapper(HospitalServiceItemDO.class));
                for (HospitalServiceItemDO hospitalServiceItemDO:hospitalServiceItemDOList1){
                    hospitalServiceItemDO.setFlag(1);
                    hospitalServiceItemDOS1.add(hospitalServiceItemDO);
                }
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
                    if (!itemType.contains(specialistServiceItemDO.getItemType())){
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
        List<String> serviceItems = new ArrayList<>();
        if (type != null && type.size() != 0){
                for (int z =0 ;z<type.size();z++){
                    List<HospitalServiceItemDO> hospitalServiceItemDOS2 = new ArrayList<>();
                    JSONObject object1 =new JSONObject();
                    for (int j = 0;j<hospitalServiceItemDOS1.size();j++){
                        SpecialistServiceItemDO specialistServiceItemDO = hospitalServiceItemDOS1.get(j).getSpecialistServiceItemDO();
                        if (itemType.get(i).equals(specialistServiceItemDO.getItemType())){
                            System.out.println(itemType.get(i));
                            System.out.println(specialistServiceItemDO.getDiseaseItem());
                            if (type.get(z).equals(specialistServiceItemDO.getDiseaseItem())) {
                                hospitalServiceItemDOS2.add(hospitalServiceItemDOS1.get(j));
                            }
                        }
                    }
                    if (serviceItems!=null && serviceItems.size()!=0){
                        if (!serviceItems.contains(type.get(z))){
                            serviceItems.add(type.get(z));
                            object1.put("itemName",type.get(z));
                            object1.put("hospitalServiceItems",hospitalServiceItemDOS2);
                            itemArray.add(object1);
                        }else {
                            object1.put("itemName",type.get(z));
                            object1.put("hospitalServiceItems",hospitalServiceItemDOS2);
                            itemArray.add(object1);
                        }
                    }else {
                        serviceItems.add(type.get(z));
                        object1.put("itemName",type.get(z));
                        object1.put("hospitalServiceItems",hospitalServiceItemDOS2);
                        itemArray.add(object1);
                    }
                }
        }
        if (itemArray !=null && itemArray.size()!=0){
            JSONArray jsonArray = new JSONArray();
            for (int j = 0;j<itemArray.size();j++){
                JSONObject object1 = itemArray.getJSONObject(j);
                JSONObject jsonObject1 = new JSONObject();
                JSONArray array1 = object1.getJSONArray("hospitalServiceItems");
                JSONArray array2 = new JSONArray();
                for (int k=0;k<array1.size();k++){
                    JSONObject jsonObject = array1.getJSONObject(k);
                    jsonObject.replace("expense", DataUtils.integerTransferDouble(jsonObject.getInteger("expense")));
                    array2.add(jsonObject);
                }
                jsonObject1.put("itemName",object1.getString("itemName"));
                jsonObject1.put("hospitalServiceItems",array2);
                jsonArray.add(jsonObject1);
            }
            object.put("item",jsonArray);
        }else {
            object.put("item",itemArray);
        }
        object.put("itemType",itemType.get(i));
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
    public MixEnvelop<JSONArray,JSONArray> selectByCondition(String serviceItemName,List<String> hospitals){
        MixEnvelop<JSONArray,JSONArray> envelop = new MixEnvelop<>();
        /*if (StringUtils.isNoneBlank(serviceItemName)||serviceItemName != null){
            String sql = "select * from wlyy_hospital_service_item where service_item_name = '"+serviceItemName+"' and status=1";
            List<HospitalServiceItemDO> hospitalServiceItemDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(HospitalServiceItemDO.class));
            for (HospitalServiceItemDO hospitalServiceItemDO:hospitalServiceItemDOS){
                SpecialistServiceItemDO specialistServiceItemDO = specialistServiceItemDao.findOne(hospitalServiceItemDO.getServiceItemId());
                hospitalServiceItemDO.setSpecialistServiceItemDO(specialistServiceItemDO);
            }
            JSONArray array = new JSONArray();
            for (int i =0 ;i<hospitalServiceItemDOS.size();i++){
                SpecialistServiceItemDO specialistServiceItemDO = hospitalServiceItemDOS.get(i).getSpecialistServiceItemDO();
                JSONObject object = new JSONObject();
                object.put("itemName",specialistServiceItemDO.getItemType());
                object.put("hospitalServiceItem",hospitalServiceItemDOS.get(i));
                array.add(object);
            }
            List<JSONArray> list = new ArrayList<>();
            list.add(array);
            envelop.setDetailModelList(list);
        }else {*/
        StringBuffer buffer = new StringBuffer();
        if(hospitals != null && hospitals.size()!=0){
            buffer.append(" and hospital in (");
            for (int i =0 ;i<hospitals.size();i++){
                buffer.append("'"+hospitals.get(i)+"',");
            }
            buffer.deleteCharAt(buffer.length()-1);
            buffer.append(")");
        }
        String sql = "select * from wlyy_hospital_service_item where 1=1 AND status = 1"+buffer;
        List<HospitalServiceItemDO> hospitalServiceItemDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(HospitalServiceItemDO.class));
        for (HospitalServiceItemDO hospitalServiceItemDO:hospitalServiceItemDOS){
            SpecialistServiceItemDO specialistServiceItemDO = specialistServiceItemDao.findOne(hospitalServiceItemDO.getServiceItemId());
            hospitalServiceItemDO.setSpecialistServiceItemDO(specialistServiceItemDO);
        }
        JSONArray array = new JSONArray();
        for (int i =0 ;i<hospitalServiceItemDOS.size();i++){
            SpecialistServiceItemDO specialistServiceItemDO = hospitalServiceItemDOS.get(i).getSpecialistServiceItemDO();
            JSONObject object = new JSONObject();
            object.put("itemName",specialistServiceItemDO.getItemType());
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(hospitalServiceItemDOS.get(i));
            jsonObject.replace("expense", DataUtils.integerTransferDouble(hospitalServiceItemDOS.get(i).getExpense()));
            JSONObject object1 = jsonObject.getJSONObject("specialistServiceItemDO");
            object1.replace("threeHospitals", DataUtils.integerTransferDouble(hospitalServiceItemDOS.get(i).getSpecialistServiceItemDO().getThreeHospitals()));
            object1.replace("twoHospitals",DataUtils.integerTransferDouble(hospitalServiceItemDOS.get(i).getSpecialistServiceItemDO().getTwoHospitals()));
            object1.replace("oneHospitals",DataUtils.integerTransferDouble(hospitalServiceItemDOS.get(i).getSpecialistServiceItemDO().getOneHospitals()));
            object.put("hospitalServiceItem",jsonObject);
            array.add(object);
        }
        List<JSONArray> list = new ArrayList<>();
        list.add(array);
        envelop.setDetailModelList(list);
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
    public MixEnvelop<Boolean,Boolean> delete(String hospital, String serviceItemId,String id){
        MixEnvelop<Boolean,Boolean> envelop = new MixEnvelop<>();
        if (StringUtils.isNoneBlank(hospital)){
            String sql = "update wlyy_hospital_service_item set status = 0 where hospital = '"+hospital+"'";
            jdbcTemplate.update(sql);
        }else if (StringUtils.isNoneBlank(serviceItemId)){
            String sql = "update wlyy_hospital_service_item set status = 0 where service_item_id = '"+serviceItemId+"'";
            jdbcTemplate.update(sql);
        }else if (StringUtils.isNoneBlank(id)){
            String sql = "update wlyy_hospital_service_item set status = 0 where id = '"+id+"'";
            jdbcTemplate.update(sql);
        }
        return envelop;
    }


    /**
     * 机构服务项目数据添加
     *
     * @param hospitalServiceItemDOS
     * @return
     */
    public MixEnvelop<Boolean,Boolean> importData(List<HospitalServiceItemDO> hospitalServiceItemDOS) {
        MixEnvelop<Boolean,Boolean> envelop = new MixEnvelop<>();
        if (hospitalServiceItemDOS != null && hospitalServiceItemDOS.size()!=0){
            for (HospitalServiceItemDO hospitalServiceItemDO:hospitalServiceItemDOS){
                List<SpecialistServiceItemDO> specialistServiceItemDOS = specialistServiceItemDao.findByTitleAndContent(hospitalServiceItemDO.getTitle(),hospitalServiceItemDO.getContent());
                if (specialistServiceItemDOS != null && specialistServiceItemDOS.size()!=0){
                    List<HospitalServiceItemDO> hospitalServiceItemDOList = specialistHospitalServiceItemDao.findByHospitalAndServiceItemId(hospitalServiceItemDO.getHospital(),specialistServiceItemDOS.get(0).getId());
                    if (hospitalServiceItemDOList != null && hospitalServiceItemDOList.size() !=0){
                        continue;
                    }else {
                        hospitalServiceItemDO.setStatus(1);
                        hospitalServiceItemDO.setImediate(0);
                        hospitalServiceItemDO.setSaasId("dev");
                        hospitalServiceItemDO.setId(UUID.randomUUID().toString());
                        hospitalServiceItemDO.setServiceItemId(specialistServiceItemDOS.get(0).getId());
                        hospitalServiceItemDO.setServiceItemName(specialistServiceItemDOS.get(0).getDiseaseItem());
                        hospitalServiceItemDO.setCreateTime(new Date());
                        hospitalServiceItemDO.setUpdateTime(new Date());
                        specialistHospitalServiceItemDao.save(hospitalServiceItemDO);
                    }
                }
            }
        }
        envelop.setObj(true);
        return envelop;
    }

}
