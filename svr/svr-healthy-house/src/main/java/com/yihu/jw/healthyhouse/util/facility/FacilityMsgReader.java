package com.yihu.jw.healthyhouse.util.facility;


import com.yihu.jw.healthyhouse.util.facility.msg.FacilityMsg;
import com.yihu.jw.healthyhouse.util.poi.AExcelReader;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.*;
/**
 *  设施列表-excel解析类
 *
 * @author HZY
 * @vsrsion 1.0
 * Created at 2018/9/25.
 */
public class FacilityMsgReader extends AExcelReader {

    @Override
    public void read(Workbook rwb) throws Exception {
        try {
            Iterator<Sheet> sheets = rwb.sheetIterator();
            int j = 0, rows;
            FacilityMsg facilityMsg;
            while (sheets.hasNext()){
                Sheet sheet = sheets.next();
                if ((rows = sheet.getLastRowNum()) == 0) {
                    continue;
                }

                for (int i = 1; i <= rows; i++) {
                    facilityMsg = new FacilityMsg();
                    //设施编码
//                    if(null != getCellCont(sheet, i, 2)){
//                        //去除空格、回车、换行、制表符
//                        facilityMsg.setCode(replaceBlank(getCellCont(sheet, i, 1)));
//                    }else{
//                        facilityMsg.setCode(getCellCont(sheet, i, 1));
//                    }

                    //设施类型名称
                    if(null != getCellCont(sheet, i, 0)){
                        //去除空格、回车、换行、制表符
                        facilityMsg.setCategory(replaceBlank(getCellCont(sheet, i, 0)));
                    }else{
                        facilityMsg.setCategory(getCellCont(sheet, i, 0));
                    }

                    facilityMsg.setUserName(getCellCont(sheet, i, 1));
                    facilityMsg.setUserTelePhone(getCellCont(sheet, i, 2));
                    //省
                    if(null != getCellCont(sheet, i, 3)){
                        //去除空格、回车、换行、制表符
                        facilityMsg.setProvince(replaceBlank(getCellCont(sheet, i, 3)));
                    }else{
                        facilityMsg.setProvince(getCellCont(sheet, i, 3));
                    }

                    //市
                    if(null != getCellCont(sheet, i, 4)){
                        //去除空格、回车、换行、制表符
                        facilityMsg.setCity(replaceBlank(getCellCont(sheet, i, 4)));
                    }else{
                        facilityMsg.setCity(getCellCont(sheet, i, 4));
                    }

                    //区
                    if(null != getCellCont(sheet, i, 5)){
                        //去除空格、回车、换行、制表符
                        facilityMsg.setCounty(replaceBlank(getCellCont(sheet, i, 5)));
                    }else{
                        facilityMsg.setCounty(getCellCont(sheet, i, 5));
                    }

                    facilityMsg.setStreet(getCellCont(sheet, i, 6));
                    facilityMsg.setStatus(getCellCont(sheet, i, 7));
                    facilityMsg.setOrgName(getCellCont(sheet, i, 8));
                    facilityMsg.setServiceDate(getCellCont(sheet, i, 9));
                    facilityMsg.setServiceStartTime(getCellCont(sheet, i, 10));
                    facilityMsg.setServiceEndTime(getCellCont(sheet, i, 11));
                    facilityMsg.setLongitude(getCellCont(sheet, i, 12));
                    facilityMsg.setLatitude(getCellCont(sheet, i, 13));

                    facilityMsg.setExcelSeq(i);
                    correctLs.add(facilityMsg);
                }

                j++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (rwb != null) {
                rwb.close();
            }
        }
    }


}
