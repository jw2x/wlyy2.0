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

    List<SystemDictEntry> findByDictId(String dictId);

    Page<SystemDictEntry> findByDictId(String dictId, Pageable pageable);

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

    /**
     * 批量获取字典项列表.
     *
     * @param dictId
     * @param codes
     * @return
     */
    @Query("select entry from SystemDictEntry entry where entry.dictId = :dictId and entry.code in (:codes) order by entry.sort asc")
    List<SystemDictEntry> findByDictIdAndCodes(@Param("dictId") String dictId, @Param("codes") String[] codes);

    @Query("select entry from SystemDictEntry entry where entry.dictId = :dictId and entry.value = :value")
    List<SystemDictEntry> findByDictIdAndValue(@Param("dictId") String dictId, @Param("value") String value);

    List<SystemDictEntry> findByDictIdAndCode(@Param("dictId") String dictId, @Param("code") String code);
}

