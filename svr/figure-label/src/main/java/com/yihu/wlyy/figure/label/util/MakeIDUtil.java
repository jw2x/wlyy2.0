package com.yihu.wlyy.figure.label.util;

import com.yihu.wlyy.figure.label.entity.FlLabelDict;
import com.yihu.wlyy.figure.label.model.DataModel;
import com.yihu.wlyy.figure.label.model.SaveModel;
import org.springframework.util.StringUtils;

public class MakeIDUtil {

    /**
     * 生成从mysql获取，后存入es的id，确保数据不会重复
     * id由 idcard，dictCode,parentCode，labelName，labelCode MD5加密而成。
     * @param saveModel
     * @return
     */
    public static String makeSaveModelID(SaveModel saveModel) {
        StringBuilder idStr = new StringBuilder();

        idStr.append(saveModel.getDictCode())
                .append(saveModel.getIdcard()).append("-")
                .append(saveModel.getLabelType()).append("-")
                .append(saveModel.getLabelCode()).append("-")
                .append(saveModel.getLabelName());
       if(!StringUtils.isEmpty(saveModel.getLabelValue())){
           idStr.append("-").append(saveModel.getLabelValue());
       }
        return MD5Util.GetMD5Code(idStr.toString());
    }

    /**
     * 生成从es获取，后存入es的id，确保数据不会重复
     * id由 id，id1,parentCode，labelName，labelCode MD5加密而成。
     * @param dataModel
     * @param flLabelDict
     * @return
     */
    public static String makeEsSaveModelID(DataModel dataModel, FlLabelDict flLabelDict) {
        StringBuilder idStr = new StringBuilder();
        idStr.append(dataModel.getId() + "").append("-")
                .append(dataModel.getId1() + "").append("-")
                .append(flLabelDict.getParentCode()).append("-")
                .append(flLabelDict.getLabelCode()).append("-")
                .append(flLabelDict.getLabelName());
        return MD5Util.GetMD5Code(idStr.toString());
    }


    public static void main(String[] args) {
        DataModel dataModel = new DataModel();
        dataModel.setIdcard("350881199004241228");
        dataModel.setParentCode("2");
        dataModel.setLabelCode("1");
        dataModel.setLabelName("健康人群");

//        String MD5ID = makeSaveModelID(dataModel,"");
    }
}
