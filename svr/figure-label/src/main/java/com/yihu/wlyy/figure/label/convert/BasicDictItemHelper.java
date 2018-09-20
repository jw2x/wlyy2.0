package com.yihu.wlyy.figure.label.convert;

import com.yihu.wlyy.figure.label.entity.FlLabelDict;
import io.swagger.annotations.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author litaohong on 2018/4/27
 * @project patient-co-management
 * 一些基础字典信息载入内存
 */
@Component
public class BasicDictItemHelper {

    public  Map<String,String> dictCategoryMap = new HashMap<>();
    public  Map<String,List<FlLabelDict>> labelDictMap = new HashMap<>();

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init(){
        loadCategoryMap();
        loadLabelDictMap();
    }

    /**
     * 加载标签分类表
     * @return
     */
    public Map<String,String> loadCategoryMap(){
        if(!CollectionUtils.isEmpty(dictCategoryMap)){
            return dictCategoryMap;
        }
        String sql = "select * from fl_label_dict_category";
        List<Map<String, Object>> dictCategoryList = jdbcTemplate.queryForList(sql);
        dictCategoryList.forEach(
                one -> {
                    dictCategoryMap.put(String.valueOf(one.get("id")),String.valueOf(one.get("dict_code")));
                }
        );
        return dictCategoryMap;
    }

    /**
     * 加载标签字典表
     * @return
     */
    public Map<String,List<FlLabelDict>> loadLabelDictMap(){
        if(!CollectionUtils.isEmpty(labelDictMap)){
            return labelDictMap;
        }
        String sql = "select * from fl_label_dict";
        List<FlLabelDict> labelDictList = jdbcTemplate.query(sql,new BeanPropertyRowMapper(FlLabelDict.class));
        labelDictList.forEach(
                one -> {
                    List list = new ArrayList();
                    if(labelDictMap.containsKey(one.getDictCode())){
                        labelDictMap.get(one.getDictCode()).add(one);
                        return;
                    }
                    list.add(one);
                    labelDictMap.put(one.getDictCode(), list);
                }
        );
        return labelDictMap;
    }

//    public Map<String,FlLabelDict> getOneDictMap(String dictCode){
//        Map<String,FlLabelDict> map = new HashMap<>();
//        if(this.labelDictMap.get(dictCode).size() > 1){
//            List<FlLabelDict> list = this.labelDictMap.get(dictCode);
//            list.forEach(
//                    one -> {
//                        map.put()
//                    }
//            );
//        }
//    }
}
