package com.yihu.wlyy.figure.label.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.base.es.config.ElastricSearchHelper;
import com.yihu.wlyy.figure.label.constant.ConstantUtil;
import com.yihu.wlyy.figure.label.util.TimeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author litaohong on 2018/5/3
 * @project patient-co-management
 */
@RestController
@RequestMapping(value = "/data", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(description = "ES数据操作控制器")
public class ESDataOperationController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(ESDataOperationController.class);

    @Autowired
    private ElastricSearchHelper elastricSearchHelper;

    @Autowired
    ObjectMapper objectMapper;

    @ApiOperation(value = "根据sql删除es数据")
    @RequestMapping(value = "deteleByQuery", method = RequestMethod.POST)
    public String deleteBySql( @ApiParam(name = "sql", value = "sql", required = true)@RequestParam(value = "sql", required = true) String sql) throws JobExecutionException {
       long count = deleteData(sql);
       if(count ==-1){
           return error(-1,"invalid sql");
       }
       return success("删除成功，共计"+ count +"条");
    }


    public long  deleteData(String sql){
        long start = System.currentTimeMillis();
        String logTitle = "delete es data";
        TimeUtil.start(logger,logTitle,start);
        List<Map<String, Object>>  list = new ArrayList<>();
        if((!sql.contains("select") && !sql.contains("SELECT")) || (!sql.contains("from") && !sql.contains("FROM"))){
            return -1;
        }
        if(!sql.contains("limit")){
            sql = sql + " limit 10000";
        }
        list.addAll(elastricSearchHelper.excuceSQL(sql));
        long count = list.size();
        while (list.size() > 0){
            delete(list);
            list = elastricSearchHelper.excuceSQL(sql);
            count+= list.size();
        }
        TimeUtil.finish(logger,logTitle,start,System.currentTimeMillis());
        return count;
    }

    public void delete(List<Map<String, Object>> list){
        elastricSearchHelper.delete(ConstantUtil.figure_label_es_index,ConstantUtil.figure_label_es_type,list);
    }
}
