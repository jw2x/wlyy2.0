package com.yihu.jw.quota.dao.es;

import com.yihu.jw.quota.model.es.QuotaResult;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by chenweida on 2017/5/23.
 */
public interface QuotaResultDao extends ElasticsearchRepository<QuotaResult, String> {
    @Query("{\"index\":{\"user\" :{\"field\" : {\"id\" : \" ?0\"}}}}")
    QuotaResult findById(String id);
}
