package com.yihu.jw.healthyhouse.service.facility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.jw.exception.business.ManageException;
import com.yihu.jw.healthyhouse.dao.facility.FacilityDao;
import com.yihu.jw.healthyhouse.dao.facility.FacilityServerRelationDao;
import com.yihu.jw.healthyhouse.model.facility.Facility;
import com.yihu.jw.healthyhouse.model.facility.FacilityServer;
import com.yihu.jw.healthyhouse.model.facility.FacilityServerRelation;
import com.yihu.jw.healthyhouse.service.area.BaseCityService;
import com.yihu.jw.healthyhouse.service.area.BaseTownService;
import com.yihu.jw.healthyhouse.service.dict.SystemDictEntryService;
import com.yihu.jw.healthyhouse.util.facility.msg.FacilityMsg;
import com.yihu.jw.healthyhouse.util.poi.ExcelUtils;
import com.yihu.jw.util.http.HTTPResponse;
import com.yihu.jw.util.http.HttpClientKit;
import com.yihu.mysql.query.BaseJpaService;
import jxl.write.Colour;
import jxl.write.WritableCellFormat;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.*;

/**
 * 设施管理器.
 *
 * @author zdm
 * @version 1.0
 * @created 2018.09.19
 */
@Service
@Transactional
public class FacilityService extends BaseJpaService<Facility, FacilityDao> {

    @Autowired
    private FacilityDao facilityDao;
    @Autowired
    private BaseCityService baseCityService;
    @Autowired
    private BaseTownService baseTownService;
    @Autowired
    private SystemDictEntryService systemDictEntryService;
    @Autowired
    private FacilityServerService facilityServerService;
    @Autowired
    private FacilityServerRelationDao facilityServerRelationDao;



    public Facility findById(String id) {
        return facilityDao.findById(id);
    }

    public boolean isHasFacility(double longitude, double latitude) {
        boolean flag = false;
        List<Facility> facilitys = facilityDao.findByLongitudeAndLatitude(longitude, latitude);
        if (facilitys != null && facilitys.size() > 0) {
            flag = true;
        }
        return flag;
    }

    //excel中添加固定内容
    private void addStaticCell(Sheet sheet) {
        //设置样式
        Workbook workbook = sheet.getWorkbook();
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());//設置背景色
        Font font = workbook.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);
        ExcelUtils.addCellData(sheet,0,0,"序号",style);
        ExcelUtils.addCellData(sheet,1,0,"设施编码",style);
        ExcelUtils.addCellData(sheet,2,0,"设施名称",style);
        ExcelUtils.addCellData(sheet,3,0,"设施类型名称",style);
        ExcelUtils.addCellData(sheet,4,0,"设施详细地址",style);
        ExcelUtils.addCellData(sheet,5,0,"联系人",style);
        ExcelUtils.addCellData(sheet,6,0,"联系方式",style);
        ExcelUtils.addCellData(sheet,7,0,"所在省",style);
        ExcelUtils.addCellData(sheet,8,0,"所在市",style);
        ExcelUtils.addCellData(sheet,9,0,"所在区",style);
        ExcelUtils.addCellData(sheet,10,0,"所在街道",style);
        ExcelUtils.addCellData(sheet,11,0,"运营状态",style);

    }

    /**
     * 导出设施列表excel
     *
     * @param response     响应体
     * @param facilityList 用户列表
     * @throws ManageException
     */
    public void exportFacilityExcel(HttpServletResponse response, List<Facility> facilityList) throws ManageException {
        try {
            String fileName = "健康小屋-设施列表";
            //设置下载
            response.setCharacterEncoding("utf-8");
            response.setContentType("octets/stream");
            response.setHeader("Content-Disposition", "attachment; filename="
                    + new String(fileName.getBytes("gb2312"), "ISO8859-1") + ".xlsx");
            OutputStream os = response.getOutputStream();
            //写excel
            Workbook workbook = new XSSFWorkbook();
            int k = 0;
            Facility metaData = null;
            int row = 0;
            //创建Excel工作表 指定名称和位置
            String streetName = "健康小屋-设施列表";
            Sheet sheet = workbook.createSheet(streetName);
            addStaticCell(sheet);
            //添加数据元信息
            WritableCellFormat wc = new WritableCellFormat();
            wc.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, Colour.SKY_BLUE);//边框
            for (int j = 0; k < facilityList.size(); j++, k++) {
                metaData = facilityList.get(k);
                row = j + 1;
                ExcelUtils.addCellData(sheet, 0, row, j + 1 + "");//序号
                ExcelUtils.addCellData(sheet, 1, row, metaData.getCode());//设施编码
                ExcelUtils.addCellData(sheet, 2, row, metaData.getName());//设施名称
                ExcelUtils.addCellData(sheet, 3, row, metaData.getCategoryValue());//类型名称
                ExcelUtils.addCellData(sheet, 4, row, metaData.getAddress());//信息地址
                ExcelUtils.addCellData(sheet, 5, row, metaData.getUserName());//联系人
                ExcelUtils.addCellData(sheet, 6, row, metaData.getUserTelephone());//联系电话
                ExcelUtils.addCellData(sheet, 7, row, metaData.getProvinceId());//省
                ExcelUtils.addCellData(sheet, 8, row, metaData.getCityName());//市
                ExcelUtils.addCellData(sheet, 9, row, metaData.getCountyName());//区县
                ExcelUtils.addCellData(sheet, 10, row, metaData.getStreet());//街道
                ExcelUtils.addCellData(sheet, 11, row, metaData.getStatusName());//运营状态

            }

            //写入工作表
            workbook.write(os);
            workbook.close();
            os.flush();
            os.close();
        } catch (Exception e) {
            throw new ManageException("导出设施列表异常", e);
        }
    }


    /**
     * 批量导入设施的集合
     *
     * @param facilities 设施列表
     */
    public Map<String, Object> batchInsertFacility(List<FacilityMsg> facilities) throws ManageException {
        Map<String, Object> result = new HashMap<>();
        //批量存储的集合
        int correctCount = 0;
        List<Facility> corrects = new ArrayList<>();
        List<Facility> errors = new ArrayList<>();

        Facility facility = null;
        //批量存储
        for (FacilityMsg facilityMsg : facilities) {
            facility = new Facility();
            Double longitude = StringUtils.isEmpty(facilityMsg.getLongitude()) ? null : Double.parseDouble(facilityMsg.getLongitude());
            Double latitude = StringUtils.isEmpty(facilityMsg.getLongitude()) ? null : Double.parseDouble(facilityMsg.getLongitude());
            String cityCode = baseCityService.getCodeByname(facilityMsg.getCity());
            String townCode = baseTownService.getCodeByname(facilityMsg.getCounty());
            Integer categoryCode = systemDictEntryService.getDictEntryCodeByName("FacilityType", facilityMsg.getCategory());
            facility.setCode(genFacilityCode());
            facility.setName(facilityMsg.getName());
            facility.setCategory(categoryCode);
            facility.setCategoryValue(facilityMsg.getCategory());
            facility.setUserName(facilityMsg.getUserName());
            facility.setUserTelephone(facilityMsg.getUserTelePhone());
            facility.setProvinceId(facilityMsg.getProvince());
            facility.setCityCode(cityCode);
            facility.setCityName(facilityMsg.getCity());
            facility.setCountyCode(townCode);
            facility.setCountyName(facilityMsg.getCounty());
            facility.setStreet(facilityMsg.getStreet());
            facility.setAddress(facilityMsg.getCity() + facilityMsg.getCounty() + facilityMsg.getStreet());
            facility.setStatus(facilityMsg.getStatus());
            facility.setOrgName(facilityMsg.getOrgName());
            facility.setServiceDay(facilityMsg.getServiceDate());
            facility.setServiceStartTime(facilityMsg.getServiceStartTime());
            facility.setServiceEndTime(facilityMsg.getServiceEndTime());
            facility.setLongitude(longitude);
            facility.setLatitude(latitude);

            if (isHasFacility(longitude, latitude)) {
                errors.add(facility);
            } else {
                corrects.add(facility);
            }

            if (corrects.size() > 100) {
                facilityDao.save(corrects);
                correctCount += corrects.size();
                corrects.clear();
            }
        }
        if (!corrects.isEmpty()) {
            facilityDao.save(corrects);
            correctCount += corrects.size();
        }

        result.put("correctCount", correctCount);
        result.put("errors", errors);
        return result;
    }

    /**
     * 设施编码生成
     *
     * @return
     */
    public String genFacilityCode() {
        String code = "CSHF" + randomString(5);
        Facility facility = facilityDao.findByCode(code);
        while (facility != null) {
            code = "CSHF" + randomString(5);
            facility = facilityDao.findByCode(code);
        }
        return code;
    }

    /**
     * 根据服务类型获取设施
     */
    public List<String> getFacilityCodeByServerType(String type) {
        Session s = currentSession();
        String hql = "(SELECT  DISTINCT fsr.facilitie_code FROM  facility_server_relation fsr,facility_server fs WHERE fsr.service_code=fs.code and fs.type=:type)";
        Query query = s.createSQLQuery(hql);
        query.setParameter("type", type);
        return query.list();
    }

    public List<String> getFacilityCodeByServerCode(String[] codes) {
        String sql = "(SELECT  DISTINCT fsr.facilitie_code FROM  facility_server_relation fsr WHERE fsr.service_code IN(:codes))";
        SQLQuery sqlQuery = currentSession().createSQLQuery(sql);
        sqlQuery.setParameterList("codes", codes);
        return sqlQuery.list();
    }

    public List<Facility> getFacilityByFacilityCode(List<String> facilityCodes) {
        return facilityDao.findByCode(facilityCodes);
    }

    public boolean checkFacilityByFacilityId(String id, String fild, String value) {
        SQLQuery sqlQuery;
        String sql = "";
        if (fild.equals("code")) {
            //验证编码
            sql = "select count(1) from facility f where  f.status = 0 and f.code =:code and f.id !=:id";
            sqlQuery = currentSession().createSQLQuery(sql);
            sqlQuery.setParameter("id", id);
            sqlQuery.setParameter("code", value);
            BigInteger count = (BigInteger) sqlQuery.uniqueResult();
            return count.compareTo(new BigInteger("0")) > 0;
        } else {
            //验证名称
            sql = "select count(1) from facility f where  f.status = 0 and f.name =:name and f.id !=:id";
            sqlQuery = currentSession().createSQLQuery(sql);
            sqlQuery.setParameter("id", id);
            sqlQuery.setParameter("name", value);
            BigInteger count = (BigInteger) sqlQuery.uniqueResult();
            return count.compareTo(new BigInteger("0")) > 0;
        }
    }

    public Facility findByCode(String code) {
        return facilityDao.findByCode(code);
    }



    /**
     *  //TODO 临时方法
     * 批量导入设施的集合
     *
     * @param facilities 设施列表
     */
    public Map<String, Object> batchInsertDemo(List<Map<String,String> > facilities) throws ManageException, IOException {
        Map<String, Object> result = new HashMap<>();
        //批量存储的集合
        int correctCount = 0;
        List<Facility> corrects = new ArrayList<>();
        List<Facility> errors = new ArrayList<>();

        Facility facility = null;
        //批量存储
        for (Map<String,String> facilityMsg : facilities) {
            facility = new Facility();
            facility.setCode(genFacilityCode());
            facility.setName(facilityMsg.get("street")+"_健康小屋");
            facility.setCategory(1);
            facility.setCategoryValue("健康小屋");
            facility.setProvinceId("350000");
            facility.setCityCode("350200");
            facility.setCityName("厦门市");
            facility.setCountyCode(baseTownService.getCodeByname(facilityMsg.get("county")));
            facility.setCountyName(facilityMsg.get("county"));
            facility.setStreet(facilityMsg.get("street"));
            if (!facilityMsg.get("address").contains("厦门市")) {
                facility.setAddress("厦门市" + facilityMsg.get("address"));
            }else {
                facility.setAddress(facilityMsg.get("address"));
            }
            facility.setStatus("0");
            facility.setDeleteFlag("0");
            getLatAndlon(facility);//获取经纬度

            //已添加过的经纬度不再次导入
            if (isHasFacility(facility.getLongitude(), facility.getLatitude())) {
                errors.add(facility);
            } else {
                //获取设施服务
                String servies = facilityMsg.get("service");
                String[] serviceArr= servies.split("、");
                List<FacilityServer> facilityServers = facilityServerService.findByNameIn(Arrays.asList(serviceArr));
                if (facilities!=null ){
                    List<FacilityServerRelation> relations = new ArrayList<>();
                    for (FacilityServer facilityServer : facilityServers) {
                        //遍历添加设施项目
                        FacilityServerRelation relation = new FacilityServerRelation();
                        relation.setFacilitieCode(facility.getCode());
                        relation.setFacilitieName(facility.getName());
                        relation.setServiceCode(facilityServer.getCode());
                        relation.setServiceName(facilityServer.getName());
                        relation.setServiceName(facilityServer.getName());
                        relations.add(relation);
                    }
                    facilityServerRelationDao.save(relations);
                }
                corrects.add(facility);
            }

            if (corrects.size() > 100) {
                facilityDao.save(corrects);
                correctCount += corrects.size();
                corrects.clear();
            }
        }
        if (!corrects.isEmpty()) {
            facilityDao.save(corrects);
            correctCount += corrects.size();
        }

        result.put("correctCount", correctCount);
        result.put("errors", errors);
        return result;
    }

    /**
     *  //TODO  临时方法
     * 获取经纬度
     * @param facility
     * @throws IOException
     */
    public void getLatAndlon(Facility facility) throws IOException {

        String url = "http://api.map.baidu.com/geocoder/v2/?address="+ facility.getAddress() + "&output=json&ak=465443b4e84fb6823359e5921915e8dc";
        HTTPResponse httpResponse = HttpClientKit.get(url);
        if (httpResponse.getStatusCode() == 200) {
            String result = httpResponse.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String,Object> map = objectMapper.readValue(result,Map.class);
            Map result1 = (Map) map.get("result");
            Map<String,Double> location = (Map<String, Double>) result1.get("location");
            facility.setLatitude(location.get("lat"));
            facility.setLongitude(location.get("lng"));
//            System.out.println(result);
        }

    }

    /**
     * 根据服务类型获取设施
     */
    public List<String> getFacilityCodeByServerTypeList(String[] type) {
        Session s = currentSession();
        String hql = "SELECT  DISTINCT fsr.facilitie_code FROM  facility_server_relation fsr,facility_server fs WHERE fsr.service_code=fs.code and fs.type IN(:type)";
        Query query = s.createSQLQuery(hql);
        query.setParameter("type", type);
        return query.list();
    }

}
