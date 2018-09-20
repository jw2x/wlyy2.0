package com.yihu.wlyy.figure.label.storage;

import com.yihu.base.hbase.HBaseAdmin;
import com.yihu.base.hbase.HBaseHelper;
import com.yihu.base.hbase.TableBundle;
import com.yihu.wlyy.figure.label.model.SaveModel;
import com.yihu.wlyy.figure.label.util.BeanUtil;
import com.yihu.wlyy.figure.label.constant.ConstantUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @author lith 2018.03.23
 */
public class Store2Hbase implements Storager {

    private Logger logger = LoggerFactory.getLogger(Store2Hbase.class);

    @Autowired
    private HBaseHelper hBaseHelper;

    @Autowired
    private HBaseAdmin hBaseAdmin;

    @Override
    public void save(List<SaveModel> modelList) {
        if(CollectionUtils.isEmpty(modelList)){
            return;
        }
        String[] fieldName = BeanUtil.getFiledName(modelList.get(0));
        try {
            if(!hBaseAdmin.isTableExists(ConstantUtil.figure_label_hbase_table)){
                hBaseAdmin.createTable(ConstantUtil.figure_label_hbase_table,fieldName);
            }
        }catch (Exception e){
            logger.error("invalid hbase table:" + ConstantUtil.figure_label_hbase_table);
        }
        TableBundle tableBundle = new TableBundle();
        for (SaveModel saveModel : modelList) {
            tableBundle.addFamily(saveModel.getId(), ConstantUtil.figure_label_familyA);
            tableBundle.addColumns(saveModel.getId(), ConstantUtil.figure_label_familyA,fieldName);
            Map<String,String> map = BeanUtil.beanToMap(saveModel);
            tableBundle.addValues(saveModel.getId(), ConstantUtil.figure_label_familyA,map);
        }
        hBaseHelper.save(ConstantUtil.figure_label_hbase_table,tableBundle);
        logger.info("save to hbase count:" + modelList.size());
    }

}