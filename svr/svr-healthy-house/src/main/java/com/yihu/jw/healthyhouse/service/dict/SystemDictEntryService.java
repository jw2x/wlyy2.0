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
     * 获取所有字典项。对于大字典，若不分页效率可能会很低。
     *
     * @param dictId 字典ID
     * @param page   分页，-1 表示查找全部
     * @param size   页大小, page 为 -1 时忽略此参数
     * @return
     */
    public Page<SystemDictEntry> getDictEntries(String dictId, int page, int size) {
        SystemDictEntryDao repo = (SystemDictEntryDao) getJpaRepository();

        return repo.findByDictId(dictId, page == -1 ? null : new PageRequest(page, size));
    }

    /**
     * 获取简易字典项列表.
     *
     * @param dictId
     * @param codes  字典项代码列表,为空返回所有字典项. 但对于大字典效率会很低.
     * @return
     */
    public List<SystemDictEntry> getDictEntries(String dictId, String[] codes) {
        SystemDictEntryDao repo = (SystemDictEntryDao) getJpaRepository();

        if (codes == null) {
            return repo.findByDictId(dictId);
        } else {
            return repo.findByDictIdAndCodes(dictId, codes);
        }
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

    public SystemDictEntry getDictEntryByValueAndDictId(String value, String dictId) {
        List<SystemDictEntry> systemDictEntryList = systemDictEntryDao.findByDictIdAndValue(dictId, value);
        if (null != systemDictEntryList && systemDictEntryList.size() > 0) {
            return systemDictEntryList.get(0);
        }
        return null;
    }

    /**
     * 按字典ID与字典项值查找字典项.
     *
     * @param dictId
     * @param value
     * @param page
     * @param size
     * @return
     */
    public Page<SystemDictEntry> findByDictIdAndValueLike(String dictId, String value, int page, int size) {
        SystemDictEntryDao repo = (SystemDictEntryDao) getJpaRepository();

        return repo.findByDictIdAndValueLike(dictId, value, new PageRequest(page, size));
    }

    /**
     * 按字典ID查找字典项.
     *
     * @param dictId
     * @param page
     * @param size
     * @return
     */
    public Page<SystemDictEntry> findByDictId(String dictId, int page, int size) {
        SystemDictEntryDao repo = (SystemDictEntryDao) getJpaRepository();

        return repo.findByDictId(dictId, new PageRequest(page, size));
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

    public void createDictEntry(SystemDictEntry systemDictEntry) {
        SystemDictEntryDao repo = (SystemDictEntryDao) getJpaRepository();

        repo.save(systemDictEntry);
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
