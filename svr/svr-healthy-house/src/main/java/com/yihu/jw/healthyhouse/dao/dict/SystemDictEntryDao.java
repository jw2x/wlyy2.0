package com.yihu.jw.healthyhouse.dao.dict;

import com.yihu.jw.healthyhouse.model.dict.DictEntryKey;
import com.yihu.jw.healthyhouse.model.dict.SystemDictEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * 字典项DAO。
 *
 * @author zdm
 * @version 1.0
 * @created 2018.09.18
 */
public interface SystemDictEntryDao extends JpaRepository<SystemDictEntry, DictEntryKey> {

    @Query("select entry from SystemDictEntry entry where entry.dictId = :dictId and entry.value =:value")
    List<SystemDictEntry> findByDictIdAndValueLike(@Param("dictId")String dictId, @Param("value") String value);

    @Modifying
    void deleteByDictId(String dictId);

    /**
     * 获取字典项下一排序号.
     *
     * @param dictId
     * @return
     */
    @Query("select max(entry.sort) from SystemDictEntry entry where entry.dictId = :dictId")
    Integer getNextEntrySN(@Param("dictId") String dictId);

}

