package com.yihu.jw.quota.dao.esdao;

import com.yihu.jw.quota.model.QuotaResult;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by chenweida on 2017/5/23.
 */
public interface QuotaResultDao extends ElasticsearchRepository<QuotaResult, String> {
//    @Query(" selectfrom QuotaResult")
//    List<QuotaResult> find();
}
