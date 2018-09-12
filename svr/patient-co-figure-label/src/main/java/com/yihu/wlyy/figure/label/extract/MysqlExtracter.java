package com.yihu.wlyy.figure.label.extract;

import com.yihu.wlyy.figure.label.model.DataModel;
import com.yihu.wlyy.figure.label.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author lith on 2018.03.14 -- Created by chenweida on 2018/3/7.
 * mysql数据抽取器
 */
@Component
public class MysqlExtracter implements Extracter {

    private Logger logger = LoggerFactory.getLogger(MysqlExtracter.class);

    private ExecutorService threadPoolService = Executors.newCachedThreadPool();

    private int numPerPage = 100000; //一次性最多查询10万条

    private Map<String,JdbcTemplate> templateMap = new HashMap<>();

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate wlyydefaultJdbcTemplate;

    @Autowired
    @Qualifier("healtharchiveTemplate")
    private JdbcTemplate healtharchiveTemplate;

    @Autowired
    @Qualifier("wlyy85Template")
    private JdbcTemplate wlyy85Template;


    @PostConstruct
    public void initTemplateMap(){
        templateMap.put("wlyy",wlyydefaultJdbcTemplate);
        templateMap.put("healtharchive",healtharchiveTemplate);
        templateMap.put("wlyy85",wlyy85Template);
    }

    @Override
    public List<DataModel> extractData(String sql, String datasource) {
        return null;
    }

    public List<DataModel> extractData(String sql,String countSql,String datasource) {
        if(StringUtils.isEmpty(countSql)){
            return null;
        }
        JdbcTemplate jdbcTemplate = null;
        // 默认的
        if(StringUtils.isEmpty(datasource)){
            jdbcTemplate = templateMap.get("wlyy");
            return  getDataByThread(sql,countSql,jdbcTemplate);
        }
        String [] arr = datasource.split("-");

        if(null == arr || arr.length <= 1){
            logger.error("invalid datasource format,check in fl_job_config: "+ datasource);
            return null;
        }
        jdbcTemplate = templateMap.get(arr[1]);
        if(null == jdbcTemplate){
            logger.error("datasource not exist,check in fl_job_config: "+ datasource);
            return null;
        }
        return  getDataByThread(sql,countSql,jdbcTemplate);
    }

    /**
     * 根据数据量采用多线程分页查询数据
     * @param sql
     * @return
     */
    public List<DataModel> getDataByThread(String sql,String countSql,JdbcTemplate jdbcTemplate){
        List<DataModel> datas = new ArrayList<>();
        int size = 0;

        // 统计出有多少条数据
        size = jdbcTemplate.queryForObject(countSql, Integer.class);
        logger.info("data count:" + size);

        int number = size / numPerPage + 1;
        List<Callable<List<DataModel>>> threadList = new ArrayList<>();

        // 计时
        long start = System.currentTimeMillis();
        String logTitle = "therad get data";
        TimeUtil.start(logger,logTitle,start);

        // 循环，拼凑分页sql
        try {
            for (int i = 0; i < number; i++) {
                StringBuilder limitSql = new StringBuilder();
                if(sql.contains("limitNumber") && i == 0){
                    String replacedSql = sql.replace("limitNumber",String.valueOf(1));
                    limitSql.append(replacedSql);
                }else if(sql.contains("limitNumber") && number > 1 && i > 0){
                    String replacedSql = sql.replace("limitNumber",String.valueOf(i * numPerPage));
                    limitSql.append(replacedSql);
                }else{
                    limitSql.append(sql);
                }
                limitSql.append(" limit ")
                        .append(numPerPage);
                MutilThreadMysqlExtracter mutilThreadMysqlExtracter = new MutilThreadMysqlExtracter(jdbcTemplate, limitSql.toString());
                threadList.add(mutilThreadMysqlExtracter);
//                Future<List<DataModel>> future = threadPoolService.submit(mutilThreadMysqlExtracter);
//                datas.addAll(future.get());
            }
            List<Future<List<DataModel>>> futureList = threadPoolService.invokeAll(threadList);
            for(Future<List<DataModel>> future : futureList){
                datas.addAll(future.get());
            }
            TimeUtil.finish(logger,logTitle,start,System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MutilThreadConvert call failed！");
        }
        return datas;
    }

}
