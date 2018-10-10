package com.yihu.jw.healthyhouse.util;


import com.yihu.jw.healthyhouse.util.facility.msg.FacilityMsg;
import com.yihu.jw.healthyhouse.util.poi.AExcelReader;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *  设施列表-excel解析类
 *
 * @author HZY
 * @vsrsion 1.0
 * Created at 2018/9/25.
 */
public class ImportDemoReader extends AExcelReader {

    @Override
    public void read(Workbook rwb) throws Exception {
        try {
            Iterator<Sheet> sheets = rwb.sheetIterator();
            int j = 0, rows;
            Map<String,String> facilityMsg;
            while (sheets.hasNext()){
                Sheet sheet = sheets.next();
                if ((rows = sheet.getLastRowNum()) == 0) {
                    continue;
                }

                for (int i = 1; i <= rows; i++) {
                    facilityMsg = new HashMap<>();
                    //区
                    if(null != getCellCont(sheet, i, 1)){
                        //去除空格、回车、换行、制表符
                        facilityMsg.put("county",replaceBlank(getCellCont(sheet, i, 1)));
                    }else{
                        facilityMsg.put("county",getCellCont(sheet, i, 1));
                    }

                    //设施类型名称
                    if(null != getCellCont(sheet, i, 2)){
                        //去除空格、回车、换行、制表符
                        facilityMsg.put("street",replaceBlank(getCellCont(sheet, i, 2)));
                    }else{
                        facilityMsg.put("street",getCellCont(sheet, i, 2));
                    }

                    facilityMsg.put("service",getCellCont(sheet, i, 3));//服务
                    facilityMsg.put("address",getCellCont(sheet, i, 4));//详细地址

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
