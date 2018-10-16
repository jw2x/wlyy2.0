package com.yihu.jw.base.endpoint.common.populationBatchImport;


import com.yihu.jw.util.excel.AExcelReader;
import jxl.Workbook;

import java.util.*;

/**
 * 基础人口信息列表-excel解析类
 *
 * @author zdm
 * @vsrsion 1.0
 * Created at 2018/10/15.
 */
public class PopulationMsgReader extends AExcelReader {

    @Override
    public void read(Workbook rwb) throws Exception {
        try {
            jxl.Sheet[] sheets = rwb.getSheets();
            int j = 0, rows;
            PopulationMsg populationMsg;
            getRepeat().put("saasName", new HashSet<>());
            getRepeat().put("year", new HashSet<>());
            getRepeat().put("populationNum", new HashSet<>());
            getRepeat().put("regisPopulationNum", new HashSet<>());
            getRepeat().put("dmNum", new HashSet<>());
            getRepeat().put("hbpNum", new HashSet<>());
            getRepeat().put("taskNum", new HashSet<>());
            for (jxl.Sheet sheet : sheets) {
                if ((rows = sheet.getRows()) == 0) {
                    continue;
                }
                for (int i = 1; i < rows; i++, j++) {
                    populationMsg = new PopulationMsg();
                    populationMsg.setSaasName(replaceBlank(getCellCont(sheet, i, 0)));
                    populationMsg.setYear(replaceBlank(getCellCont(sheet, i, 1)));
                    populationMsg.setPopulationNum(null == getCellCont(sheet, i, 2) ? 0 : Integer.valueOf(getCellCont(sheet, i, 2).trim()));
                    populationMsg.setRegisPopulationNum(null == getCellCont(sheet, i, 3) ? 0 : Integer.valueOf(getCellCont(sheet, i, 3).trim()));
                    populationMsg.setDmNum(null == getCellCont(sheet, i, 4) ? 0 : Integer.valueOf(getCellCont(sheet, i, 4).trim()));
                    populationMsg.setHbpNum(null == getCellCont(sheet, i, 5) ? 0 : Integer.valueOf(getCellCont(sheet, i, 5).trim()));
                    populationMsg.setTaskNum(null == getCellCont(sheet, i, 6) ? 0 : Integer.valueOf(getCellCont(sheet, i, 6).trim()));
                    int rs = populationMsg.validate(repeat);
                    if (rs == 0) {
                        errorLs.add(populationMsg);
                    } else if (rs == 1) {
                        correctLs.add(populationMsg);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("模板不正确，请下载新的模板，并按照示例正确填写后上传！");
        } finally {
            if (rwb != null) {
                rwb.close();
            }
        }
    }


}
