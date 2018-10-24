package com.yihu.jw.base.service.doctor.excelImport;

import com.yihu.jw.base.dao.dict.DictDoctorDutyDao;
import com.yihu.jw.base.dao.dict.DictHospitalDeptDao;
import com.yihu.jw.base.dao.dict.DictJobTitleDao;
import com.yihu.jw.base.dao.org.BaseOrgDao;
import com.yihu.jw.base.endpoint.common.excel.AExcelReader;
import com.yihu.jw.entity.base.dict.DictJobTitleDO;
import com.yihu.jw.entity.base.doctor.BaseDoctorRoleDO;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * 医生信息列表-excel解析类
 * Created by 刘文彬 on 2018/10/24.
 */
public class BaseDoctorExcelDOReader extends AExcelReader {
    @Autowired
    private BaseOrgDao baseOrgDao;
    @Autowired
    private DictHospitalDeptDao deptDao;
    @Autowired
    private DictDoctorDutyDao dutyDao;
    @Autowired
    private DictJobTitleDao jobTitleDao;

    @Override
    public void read(Workbook rwb) throws Exception {
        try{
            Iterator<Sheet> sheets = rwb.sheetIterator();
            int j = 0, rows;
            BaseDoctorExcelDO baseDoctorExcelDO;
            getRepeat().put("name", new HashSet<>());
            getRepeat().put("del", new HashSet<>());
            getRepeat().put("sex", new HashSet<>());
            getRepeat().put("idcard", new HashSet<>());
            getRepeat().put("mobile", new HashSet<>());
            getRepeat().put("hospitalInfo", new HashSet<>());
            getRepeat().put("jobTitleName", new HashSet<>());
            getRepeat().put("roleInfo", new HashSet<>());
            getRepeat().put("isFamous", new HashSet<>());
            getRepeat().put("expertise", new HashSet<>());
            getRepeat().put("brief", new HashSet<>());
            while (sheets.hasNext()){
                Sheet sheet = sheets.next();
                if ((rows = sheet.getLastRowNum()) == 0) {
                    continue;
                }
                for (int i = 1; i <= rows; i++) {
                    baseDoctorExcelDO = new BaseDoctorExcelDO();
                    baseDoctorExcelDO.setName(replaceBlank(getCellCont(sheet, i, 0)));
                    baseDoctorExcelDO.setDel(replaceBlank(getCellCont(sheet, i, 1)));
                    baseDoctorExcelDO.setSex(null == getCellCont(sheet, i, 2)?null:Integer.valueOf(replaceBlank(getCellCont(sheet, i, 2)).trim()));
                    baseDoctorExcelDO.setIdcard(replaceBlank(getCellCont(sheet, i, 3)));
                    baseDoctorExcelDO.setMobile(replaceBlank(getCellCont(sheet, i, 4)));
                    baseDoctorExcelDO.setHospitalInfo(replaceBlank(getCellCont(sheet, i, 5)));
                    baseDoctorExcelDO.setJobTitleName(replaceBlank(getCellCont(sheet, i, 6)));
                    baseDoctorExcelDO.setRoleInfo(replaceBlank(getCellCont(sheet, i, 7)));
                    baseDoctorExcelDO.setIsFamous(null == getCellCont(sheet, i, 8)?null:Integer.valueOf(replaceBlank(getCellCont(sheet, i, 7)).trim()));
                    baseDoctorExcelDO.setExpertise(replaceBlank(getCellCont(sheet, i, 9)));
                    baseDoctorExcelDO.setBrief(replaceBlank(getCellCont(sheet, i, 10)));
                    baseDoctorExcelDO.setExcelSeq(i);
                    int rs = baseDoctorExcelDO.validate(repeat);
                    if (rs == 0||validate(baseDoctorExcelDO)== 0) {
                        errorLs.add(baseDoctorExcelDO);
                    } else if (rs == 1) {
                        correctLs.add(baseDoctorExcelDO);
                    }

                }
                j++;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }finally {
            if (rwb != null) {
                rwb.close();
            }
        }
    }
    public int validate(BaseDoctorExcelDO baseDoctorExcelDO) {
        int rs = 1;
        if(StringUtils.isNotEmpty(baseDoctorExcelDO.getHospitalInfo())){
            String[] hospitals = baseDoctorExcelDO.getHospitalInfo().split(";");
            for(String hospital:hospitals){
                String[] element = hospital.split("/");
                String[] org = element[0].split(",");//机构
                String[] dept = element[1].split(",");//部门
                String[] duty = element[1].split(",");//职务
                String orgCode = org[0];
                String deptCode = dept[0];
                String dutyCode = duty[0];
                if(!baseOrgDao.existsByCode(orgCode)){
                    return 0;
                }
                if(!deptDao.existsByCodeAndOrgCode(deptCode,orgCode)){
                    return 0;
                }
                if(!dutyDao.existsByCode(dutyCode)){
                    return 0;
                }
            }
        }
        if(StringUtils.isNotEmpty(baseDoctorExcelDO.getRoleInfo())){
            String[] roles = baseDoctorExcelDO.getRoleInfo().split(";");
            for(String role:roles){
                String[] element = role.split(",");
                String roleCode = element[0];
                if(jobTitleDao.existsByCode(roleCode)){
                    return 0;
                }
            }
        }
        return rs;
    }
}

