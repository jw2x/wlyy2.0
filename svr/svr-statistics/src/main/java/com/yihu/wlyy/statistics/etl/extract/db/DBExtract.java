package com.yihu.wlyy.statistics.etl.extract.db;

import com.yihu.wlyy.statistics.util.SpringUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2016.10.16.
 * 数据库抽取
 */
@Component
@Scope("prototype")
public class DBExtract<T> {
    Integer pageSize=100000;

    /**
     * 不分页抽取
     * @param clazz
     * @param sql
     * @return
     */
    public List<T> extract(Class<T> clazz,String sql,JdbcTemplate jdbcTemplate){
        List<T> returnList= jdbcTemplate.query(sql,new BeanPropertyRowMapper(clazz));
        return returnList;
    }
    /**
     * 分页抽取
     * @param clazz
     * @param sql
     * @param pageSize 每页显示多少 默认10000
     * @param isMultithreading 是否多线程抽取 默认否
     * @return
     */
    public List<T> extractByPage(Class<T> clazz,String sql,String countSql,int pageSize,Boolean isMultithreading,JdbcTemplate jdbcTemplate)throws  Exception{
        return SpringUtil.getBean(DBPageExtract.class).extractByPage(clazz,sql,countSql,pageSize,isMultithreading,jdbcTemplate);
    }
    /**
     * 分页抽取
     * @param clazz
     * @param sql
     * @param isMultithreading 是否多线程抽取 默认否
     * @return
     */
    public List<T> extractByPage(Class<T> clazz,String sql,String countSql,Boolean isMultithreading,JdbcTemplate jdbcTemplate)throws  Exception{
        if(StringUtils.isEmpty(countSql)){
            return extract(clazz,sql,jdbcTemplate);
        }
        return SpringUtil.getBean(DBPageExtract.class).extractByPage(clazz,sql,countSql,pageSize,isMultithreading,jdbcTemplate);
    }
    /**
     * 分页抽取
     * @param clazz
     * @param sql
     * @return
     */
    public List<Object> extractByPage(Class<T> clazz,String sql,JdbcTemplate jdbcTemplate)throws  Exception{
        Boolean isMultithreading=false;
        String countSql="";
        return SpringUtil.getBean(DBPageExtract.class).extractByPage(clazz,sql,countSql,pageSize,isMultithreading,jdbcTemplate);
    }
}
