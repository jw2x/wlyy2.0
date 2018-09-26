package com.yihu.jw.healthyhouse.service.facility;

import com.yihu.jw.exception.business.ManageException;
import com.yihu.jw.healthyhouse.dao.facility.FacilityDao;
import com.yihu.jw.healthyhouse.model.facility.Facility;
import com.yihu.jw.healthyhouse.service.area.BaseCityService;
import com.yihu.jw.healthyhouse.service.area.BaseStreetService;
import com.yihu.jw.healthyhouse.service.area.BaseTownService;
import com.yihu.jw.healthyhouse.util.facility.msg.FacilityMsg;
import com.yihu.jw.healthyhouse.util.poi.ExcelUtils;
import com.yihu.mysql.query.BaseJpaService;
import jxl.write.Colour;
import jxl.write.WritableCellFormat;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

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
    private BaseStreetService baseStreetService;


    public Facility findById(String id) {
        return  facilityDao.findById(id);
    }

    //excel中添加固定内容
    private void addStaticCell(Sheet sheet){
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
        ExcelUtils.addCellData(sheet,2,0,"设施类型名称",style);
        ExcelUtils.addCellData(sheet,3,0,"设施详细地址",style);
        ExcelUtils.addCellData(sheet,4,0,"联系人",style);
        ExcelUtils.addCellData(sheet,5,0,"联系方式",style);
        ExcelUtils.addCellData(sheet,6,0,"所在省",style);
        ExcelUtils.addCellData(sheet,7,0,"所在市",style);
        ExcelUtils.addCellData(sheet,8,0,"所在区",style);
        ExcelUtils.addCellData(sheet,9,0,"所在街道",style);
        ExcelUtils.addCellData(sheet,10,0,"运营状态",style);

    }

    /**
     *  导出设施列表excel
     * @param response  响应体
     * @param facilityList  用户列表
     * @throws ManageException
     */
    public void exportFacilityExcel(HttpServletResponse response, List<Facility> facilityList) throws ManageException {
        try {
            String fileName = "健康小屋-设施列表";
            //设置下载
            response.setContentType("octets/stream");
            response.setHeader("Content-Disposition", "attachment; filename="
                    + new String( fileName.getBytes("gb2312"), "ISO8859-1" )+".xlsx");
            OutputStream os = response.getOutputStream();
            //获取导出数据集
            JSONObject order = new JSONObject();
            order.put("id","asc");

            //写excel
            Workbook workbook = new XSSFWorkbook();
            int k=0;
            Facility metaData = null;
            int row=0;
            //创建Excel工作表 指定名称和位置
            String streetName = "健康小屋-设施列表";
            Sheet sheet = workbook.createSheet(streetName);
            addStaticCell(sheet);
            //添加数据元信息
            WritableCellFormat wc = new WritableCellFormat();
            wc.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, Colour.SKY_BLUE);//边框
            for(int j=0;k<facilityList.size(); j++,k++){
                metaData = facilityList.get(k);
                row=j+1;
                ExcelUtils.addCellData(sheet,0,row,j+1+"");//序号
                ExcelUtils.addCellData(sheet,1,row, metaData.getCode());//设施编码
                ExcelUtils.addCellData(sheet,2,row, metaData.getCategory().toString());//类型名称
                ExcelUtils.addCellData(sheet,3,row, metaData.getAddress());//信息地址
                ExcelUtils.addCellData(sheet,4,row, metaData.getUserName());//联系人
                ExcelUtils.addCellData(sheet,5,row, metaData.getUserTelephone());//联系电话
                ExcelUtils.addCellData(sheet,6,row, metaData.getProvinceId());//省
                ExcelUtils.addCellData(sheet,7,row, metaData.getCityName());//市
                ExcelUtils.addCellData(sheet,8,row, metaData.getCountyName());//区县
                ExcelUtils.addCellData(sheet,9,row, metaData.getStreet());//街道
                ExcelUtils.addCellData(sheet,10,row, metaData.getStatus());//运营状态

            }

            //写入工作表
            workbook.write(os);
            workbook.close();
            os.flush();
            os.close();
        } catch (Exception e) {
            throw new ManageException("导出设施列表异常",e);
        }
    }


    /**
     * 批量存储的集合
     * @param facilities    设施列表
     */
    public void batchInsertFacility(List<FacilityMsg> facilities){
        //批量存储的集合
        List<Facility> data = new ArrayList<>();
        Facility facility = null;
        //批量存储
        for(FacilityMsg facilityMsg : facilities) {
            facility = new Facility();
            Double longitude = StringUtils.isEmpty(facilityMsg.getLongitude()) ? null :Double.parseDouble(facilityMsg.getLongitude());
            Double latitude = StringUtils.isEmpty(facilityMsg.getLongitude()) ? null :Double.parseDouble(facilityMsg.getLongitude());
            String cityCode = baseCityService.getCodeByname(facilityMsg.getCity());
            String townCode = baseTownService.getCodeByname(facilityMsg.getCounty());

            facility.setCode(genFacilityCode());
            facility.setCategory(Integer.parseInt(facilityMsg.getCategory()));
            facility.setUserName(facilityMsg.getUserName());
            facility.setUserTelephone(facilityMsg.getUserTelePhone());
            facility.setProvinceId(facilityMsg.getProvince());
            facility.setCityCode(cityCode);
            facility.setCityName(facilityMsg.getCity());
            facility.setCountyCode(townCode);
            facility.setCountyName(facilityMsg.getCounty());
            facility.setStreet(facilityMsg.getStreet());
            facility.setStatus(facilityMsg.getStatus());
            facility.setOrgName(facilityMsg.getOrgName());
            facility.setServiceDay(facilityMsg.getServiceDate());
            facility.setServiceStartTime(facilityMsg.getServiceStartTime());
            facility.setServiceEndTime(facilityMsg.getServiceEndTime());
            facility.setLongitude(longitude);
            facility.setLatitude(latitude);

            data.add(facility);
            if (data.size()>100){
                facilityDao.save(data);
                data.clear();
            }
        }
        if(!data.isEmpty()) {
            facilityDao.save(data);
        }
    }

    /**
     * 设施编码生成
     * @return
     */
    public String genFacilityCode(){
        return "CSHF" + randomString(5);
    }


}
