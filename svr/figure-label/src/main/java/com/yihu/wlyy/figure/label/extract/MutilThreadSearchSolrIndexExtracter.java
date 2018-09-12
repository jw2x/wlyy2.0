package com.yihu.wlyy.figure.label.extract;

import com.yihu.base.SolrHelper;
import com.yihu.wlyy.figure.label.model.DataModel;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @Author lith 2018/03/23
 * 多线程分页查询mysql数据，因为mysql数据量比较大的时候，会比较慢
 */
public class MutilThreadSearchSolrIndexExtracter implements Callable {

    private Logger logger = LoggerFactory.getLogger(MysqlExtracter.class);

    private SolrHelper solrHelper;

    private String core;
    private String q;
    private String[] fq;
    private long start;
    private long rows;

    public MutilThreadSearchSolrIndexExtracter(SolrHelper solrHelper,String core,String q,String fq,long start,long rows){
        this.solrHelper = solrHelper;
        this.core = core;
        this.q = q;
        this.start = start;
        this.rows = rows;
    }

    @Override
    public Object call() throws Exception {
        List<String> rowkeyList = new ArrayList<>();
        SolrDocumentList solrDocumentList = solrHelper.queryfl(core,q,fq,null,"rowkey",start,rows);
        logger.info("job get data counts:" + solrDocumentList.size());
        solrDocumentList.forEach(
                document -> {
                    rowkeyList.add(document.get("rowkey").toString());
                }
        );
        return rowkeyList;
    }
}
