package com.yihu.iot.service.common;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yeshijie on 2018/1/16.
 */
@Component
public class MyJdbcTemplate{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * jdbc查询返回json
     * @param sql
     * @param args
     * @return
     * @throws DataAccessException
     */
    public List<JSONObject> queryJson(String sql, Object[] args) throws DataAccessException {
        return jdbcTemplate.query(sql, args, new ResultSetExtractor<List<JSONObject>>() {
            @Override
            public List<JSONObject> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                ResultSetMetaData rsd = resultSet.getMetaData();
                int clength = rsd.getColumnCount();
                List<JSONObject> li = new ArrayList<JSONObject>();
                String columnName;
                try {
                    while (resultSet.next()) {
                        JSONObject jo = new JSONObject();

                        for (int i = 0; i < clength; i++) {
                            columnName = rsd.getColumnLabel(i + 1);
                            jo.put(columnName, resultSet.getObject(i + 1));
                        }
                        li.add(jo);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return li;
            }
        });
    }
}
