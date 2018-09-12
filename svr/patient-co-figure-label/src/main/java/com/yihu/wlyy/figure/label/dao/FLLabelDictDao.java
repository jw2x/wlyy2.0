package com.yihu.wlyy.figure.label.dao;

import com.yihu.figure_label.entity.FlLabelDict;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author lith on 2018.03.14
 * 标签字典
 */
public interface FLLabelDictDao extends PagingAndSortingRepository<FlLabelDict, String>, JpaSpecificationExecutor<FlLabelDict> {

    /**
     * 根据父类标签查询字典信息
     * @param parentCode
     * @return
     */
    @Query(" FROM FlLabelDict a WHERE a.parentCode=?1")
    FlLabelDict findByParentCode(String parentCode);

    /**
     * 根据父类code和标签code查询标签字典信息
     * @param parentCode
     * @param labelCode
     * @return
     */
    @Query(" FROM FlLabelDict a WHERE a.parentCode=?1 and labelCode = ?2")
    FlLabelDict findByParentCodeAndLabelCode(String parentCode, String labelCode);

}
