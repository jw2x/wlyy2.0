package com.yihu.jw.util.excel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class ExcelUtil implements Serializable {
    public int excelSeq;
    public Map<String, String> errorMsg = new HashMap<>();

    public int getExcelSeq() {
        return excelSeq;
    }

    public void setExcelSeq(int excelSeq) {
        this.excelSeq = excelSeq;
    }

    public void addErrorMsg(String field, String msg){
        errorMsg.put(field, msg);
    };

    public String findErrorMsg(String field){
        return errorMsg.get(field);
    };

    public void clearErrorMsg(){
        errorMsg.clear();
    };

    public void removeErrorMsg(String field){
        errorMsg.remove(field);
    };

    @Override
    public boolean equals(Object obj) {
        return getExcelSeq() == ((ExcelUtil) obj).getExcelSeq();
    }

    public String getAllErrorMsg(int seq){
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("第"+seq+"行：");
        for(String str:errorMsg.keySet()){
            stringBuffer.append(errorMsg.get(str)+";");
        }
        return stringBuffer.toString();
    };
}
