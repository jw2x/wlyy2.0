package com.yihu.jw.quota.etl.extract.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by Administrator on 2016.10.16.
 * 分页抽取器
 */
@Component
@Scope("prototype")
public class DBPageExtract<T> {

    private  List<T> returnList=new ArrayList<T>();

    public synchronized void addData( List<T> dataList) {
         returnList.addAll(dataList);
    }


    public List<T> extractByPage(Class<T> clazz, String sql, String countSql, int pageSize, Boolean isMultithreading,JdbcTemplate jdbcTemplate) throws Exception{
        if(!StringUtils.isEmpty(countSql)){
            isMultithreading=true;
        }
        if(isMultithreading){
            if(StringUtils.isEmpty(countSql)){
                throw new Exception("countSql is null");
            }
            return MultiThreadExtract(clazz, sql,countSql, pageSize,jdbcTemplate);
        }else{
            return noMultiThreadExtract(clazz, sql, pageSize,jdbcTemplate);
        }
    }

    /**
     * 不用多线程抽取
     * @param clazz
     * @param sql
     * @param pageSize
     * @return
     */

    private List<T> noMultiThreadExtract(Class<T> clazz, String sql, int pageSize,JdbcTemplate jdbcTemplate) {
        List<T> returnList=new ArrayList<T>();
        int start=0;
        int page=1;
        while (true){
            String finalSql=sql+" limit "+start+","+pageSize; //拼凑分页的语句
            List<T> listTemp= jdbcTemplate.query(finalSql,new BeanPropertyRowMapper(clazz));
            returnList.addAll(listTemp);//添加到list里面
            //判断是都是最后页面
            if(listTemp.size()!=pageSize){
                listTemp.clear();
                break;
            }else{
                start =page*pageSize;
                page++;
                listTemp.clear();
            }
        }
        return returnList;
    }
    /**
     * 多线程抽取数据
     * @param clazz
     * @param sql
     * @param pageSize
     * @return
     */
    private   List<T> MultiThreadExtract(Class<T> clazz, String sql,String countSql, int pageSize,JdbcTemplate jdbcTemplate)  {
        try{
            //得到数据的总数
            Integer dataCount=getCount(countSql,jdbcTemplate);
            //根据count 计算出 总共要执行几次
            Integer forCount=getForCount(dataCount,pageSize);
            forCount++;
            //綫程返回值的值
            List<AsyncResult<Boolean>> asyncResultPool=new ArrayList<AsyncResult<Boolean>>();
            for(int page=0;page<forCount;page++){
                //启动多线程采集数据
                AsyncResult<Boolean> future= multiExtractData(sql,page*pageSize,pageSize,clazz,jdbcTemplate);
                asyncResultPool.add(future);
            }
            ///循环判断，等待获取结果信息
            while (true){
                //得到迭代器
                Iterator<AsyncResult<Boolean>> asyncResultIterator=asyncResultPool.iterator();
                //判断有没有下一个
                while (asyncResultIterator.hasNext()){
                    AsyncResult<Boolean> asyncResult= asyncResultIterator.next();
                    if(asyncResult.isDone()){
                        //如果做完了就移除迭代器
                        asyncResultIterator.remove();
                    }else{
                        Thread.sleep(500L);
                    }
                }
                //如果一直移除到没有下一个就跳出循环 说明数据已经采集完成
                if(!asyncResultIterator.hasNext()){
                    break;
                }
            }
            return returnList;
        }catch (Exception e){
            //如果多线程错误 就改用单线程
            return noMultiThreadExtract(clazz,sql,pageSize,jdbcTemplate);
        }
    }

    private Integer getForCount(Integer dataCount, int pageSize) {
        //根据所有的数据取余数
        int yushu=dataCount%pageSize;
        int page=dataCount/pageSize;
        if(yushu==0){
            //如果没有余数 返回总的页数
            return page;
        }else{
            //如果有余数 页数多加1
            page++;
            return page;
        }
    }

    private Integer getCount(String countSql,JdbcTemplate jdbcTemplate) {
        Integer countMap= jdbcTemplate.queryForObject(countSql,Integer.class);
        return countMap;
    }

    /**
     * 多线程采集数据
     * @param sql
     * @param start
     * @param pageSize
     * @param clazz
     * @return
     */
    @Async("dbExtractExecutor")
    private AsyncResult<Boolean> multiExtractData(String sql, int start, int pageSize, Class<T> clazz,JdbcTemplate jdbcTemplate) {
        String finalSql=sql+" limit "+start+","+pageSize; //拼凑分页的语句
        List<T> listTemp= jdbcTemplate.query(finalSql,new BeanPropertyRowMapper(clazz));
        addData(listTemp);
        return new AsyncResult<>(true);
    }


}
