package com.yihu.wlyy.figure.label.extract;

import com.yihu.wlyy.figure.label.model.DataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * @Author lith 2018/03/23
 * 多线程分页查询mysql数据，因为mysql数据量比较大的时候，会比较慢
 */
public class MutilThreadMysqlExtracter implements Callable {

    private Logger logger = LoggerFactory.getLogger(MysqlExtracter.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String sql;

    public MutilThreadMysqlExtracter(JdbcTemplate jdbcTemplate,String sql){
        this.jdbcTemplate = jdbcTemplate;
        this.sql = sql;
    }

    @Override
    public Object call() throws Exception {
        List<DataModel> datas = jdbcTemplate.query(sql,new BeanPropertyRowMapper(DataModel.class));
        logger.info("job get data counts:" + datas.size());
        return datas;
    }
}
