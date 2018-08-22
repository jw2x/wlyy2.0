package com.yihu.iot.util.excel.reader;

import com.yihu.iot.util.excel.AExcelReader;
import com.yihu.jw.restmodel.iot.device.IotDeviceImportVO;
import jxl.Sheet;
import jxl.Workbook;

/**
 * @author yeshijie on 2018/1/23.
 */
public class IotDeviceImportVOReader extends AExcelReader {

    @Override
    public void read(Workbook rwb) throws Exception {
        try {
            Sheet sheet = rwb.getSheet(0) ;
            for (int i = 1; i < sheet.getRows(); i++) {
                IotDeviceImportVO device = new IotDeviceImportVO();
                device.setSn(getCellCont(sheet, i, 0));
                device.setHospital(getCellCont(sheet, i, 1));
                device.setSim(getCellCont(sheet, i, 2).trim());

                correctLs.add(device);
            }
        } catch (Exception e) {
            throw new RuntimeException("模板不正确，请下载新的模板，并按照示例正确填写后上传！");
        } finally {
            if (rwb != null){
                rwb.close();
            }
        }
    }
}
