package com.yihu.jw.healthyhouse.service.dict;

import com.yihu.jw.healthyhouse.dao.dict.SystemDictEntryDao;
import com.yihu.jw.healthyhouse.model.dict.DictEntryKey;
import com.yihu.jw.healthyhouse.model.dict.SystemDictEntry;
import com.yihu.mysql.query.BaseJpaService;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 字典项服务。
 *
 * @author zdm
 * @version 1.0
 * @created 2018.09.18
 */
@Service
@Transactional
public class SystemDictEntryService extends BaseJpaService<SystemDictEntry, SystemDictEntryDao> {

    @Autowired
    private SystemDictEntryDao systemDictEntryDao;

    /**
     * 下一字典项排序号。
     *
     * @param dictId
     * @return
     */
    public int getNextSN(String dictId) {
        SystemDictEntryDao repo = (SystemDictEntryDao) getJpaRepository();
        Integer nextSort = repo.getNextEntrySN(dictId);
        return null == nextSort ? 1 : nextSort + 1;
    }

    /**
     * 获取字典项。
     *
     * @param dictId
     * @param code
     * @return
     */
    public SystemDictEntry getDictEntry(String dictId, String code) {
        SystemDictEntryDao repo = (SystemDictEntryDao) getJpaRepository();

        return repo.findOne(new DictEntryKey(code, dictId));
    }

    /**
     * 按字典ID与字典项值查找字典项.
     *
     * @param dictId
     * @param value
     * @return
     */
    public List<SystemDictEntry> findByDictIdAndValueLike(String dictId, String value) {
        SystemDictEntryDao repo = (SystemDictEntryDao) getJpaRepository();
        return repo.findByDictIdAndValueLike(dictId, value);
    }

    public boolean isDictContainEntry(String dictId, String code) {
        SystemDictEntryDao repo = (SystemDictEntryDao) getJpaRepository();

        SystemDictEntry systemDictEntry = repo.findOne(new DictEntryKey(code, dictId));

        return systemDictEntry != null;
    }

    public SystemDictEntry saveDictEntry(SystemDictEntry systemDictEntry) {
        SystemDictEntryDao repo = (SystemDictEntryDao) getJpaRepository();

        return repo.save(systemDictEntry);
    }

    public void deleteDictEntry(String dictId, String code) {
        SystemDictEntryDao repo = (SystemDictEntryDao) getJpaRepository();
        repo.delete(new DictEntryKey(code, dictId));
    }

    /**
     * 根据字典id获取字典项编码和值
     */
    public List getDictEntryCodeAndValueByDictId(String dict_id) {
        String sql = "SELECT code,value FROM system_dict_entries where dict_id=:dict_id";
        SQLQuery sqlQuery = currentSession().createSQLQuery(sql);
        sqlQuery.setParameter("dict_id", dict_id);
        return sqlQuery.list();
    }
}
