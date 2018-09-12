package com.yihu.wlyy.figure.label.extract;

import com.yihu.base.SolrHelper;
import com.yihu.base.hbase.HBaseHelper;
import com.yihu.wlyy.figure.label.model.DataModel;
import com.yihu.wlyy.figure.label.util.TimeUtil;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.QualifierFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.CollectionUtils;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author litaohong on 2018/5/5
 * @project patient-co-management
 * hbase数据抽取器
 */
@Component
public class HbaseExtracter implements Extracter{

    private Logger logger = LoggerFactory.getLogger(HbaseExtracter.class);

    @Autowired
    private HBaseHelper hBaseHelper;

    @Autowired
    private SolrHelper solrHelper;

    @Autowired
    private HbaseTemplate hbaseTemplate;

    private int numPerPage = 1000; //从solr中一次性取得1000条

    @Override
    public List<DataModel> extractData(String sql, String datasource) {
        return null;
    }

    public  List<Map<String,Object>>  extractData(String core, String q, String fq,String extractColumn) {
        List<Map<String,Object>> resultList = new ArrayList<>();
        String[] arr = core.split(";");
        String solrCore = arr[0];
        String table = arr[1];

        //组装提取的列
        String basicFl = "";
        String dFl = "";
        String[] columnArr = extractColumn.split(";");
        if(null != columnArr){
            for(int i = 0;i < columnArr.length; i++){
                if (columnArr[i].contains("basic=")) {
                    basicFl = columnArr[i].split("=")[1];
                } else if (columnArr[i].contains("d=")) {
                    dFl = columnArr[i].split("=")[1];
                }
            }
        }
        List<String> rowkeys = new ArrayList<>();
        rowkeys = getRowkeys(solrCore,q,fq);
        if(CollectionUtils.isEmpty(rowkeys)){
            return resultList;
        }
        long start = System.currentTimeMillis();
        String logTitle = "get data from hbase";
        TimeUtil.start(logger, logTitle, start);
        Result[] resultArr = hBaseHelper.getResultList(table,rowkeys,basicFl,dFl);
        for(int i = 0; i < resultArr.length; i++){
            List<Cell> ceList = resultArr[i].listCells();
            if(CollectionUtils.isEmpty(ceList)){
                continue;
            }
            Map<String, Object> map = new HashMap();
            map.put("rowkey",rowkeys.get(i));
            Iterator var5 = ceList.iterator();
            while(var5.hasNext()) {
                Cell cell = (Cell)var5.next();
                map.put(Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength()), Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
            }
            resultList.add(map);
        }
        TimeUtil.finish(logger, logTitle, start, System.currentTimeMillis());
        logger.info("hbase data counts:" + resultList.size());
        return resultList;
    }

    public List<String> getRowkeys(String core, String q, String fq) {
        List<String> rowkeyList = new ArrayList<>();
        long count = 0;
        try {
            count = solrHelper.count(core, q);
            String[] fqs = {fq};
            rowkeyList = getRowkeysFromSolr(core, q, fqs, "rowkey",0, count);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("get solr query error");
        }
        return rowkeyList;
    }

    /**
     * 从solr中获取rowkey
     * @param core
     * @param q
     * @param fq
     * @param start
     * @param rows
     * @return
     */
    public List<String> getRowkeysFromSolr(String core, String q, String[] fq, String fl,long start, long rows){
        List<String> rowkeyList = new ArrayList<>();
        SolrDocumentList solrDocumentList = null;
        String logTitle = "get rowkeys from solr";
        long startTime = System.currentTimeMillis();
        TimeUtil.start(logger, logTitle, startTime);
        try {
            solrDocumentList = solrHelper.queryfl(core,q,fq,null,fl,start,rows);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long finish = System.currentTimeMillis();
        TimeUtil.finish(logger, logTitle, startTime, finish);
        logger.info("rowkeys counts:" + solrDocumentList.size());
        solrDocumentList.forEach(
                document -> {
                    rowkeyList.add(document.get("rowkey").toString());
                }
        );
        return rowkeyList;
    }
}
