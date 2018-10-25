package com.yihu.jw.healthyhouse.service.dict;

import com.yihu.jw.healthyhouse.constant.UserConstant;
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

import java.util.ArrayList;
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
        String pcode = UserConstant.DEFAULT_PARENTID;
        String sql = "SELECT code,value FROM system_dict_entries where dict_id=:dict_id AND pcode =:pcode";
        SQLQuery sqlQuery = currentSession().createSQLQuery(sql);
        sqlQuery.setParameter("dict_id", dict_id);
        sqlQuery.setParameter("pcode", pcode);
        return sqlQuery.list();
    }
    /**
     * 根据字典id获取字典项编码和值
     */
    public List getDictEntryCodeAndValueByDictIdAndPcode(String dict_id) {
        String pcode = UserConstant.DEFAULT_PARENTID;
        String sql = "SELECT code,value FROM system_dict_entries where dict_id=:dict_id  AND pcode !=:pcode ";
        SQLQuery sqlQuery = currentSession().createSQLQuery(sql);
        sqlQuery.setParameter("dict_id", dict_id);
        sqlQuery.setParameter("pcode", pcode);
        return sqlQuery.list();
    }

    public Integer getDictEntryCodeByName(String dictCode, String value){
        String sql = "SELECT t.code FROM system_dict_entries t LEFT JOIN system_dicts d ON t.dict_id = d.id where d.code=:dictCode and t.value=:dictEntryValue";
        SQLQuery sqlQuery = currentSession().createSQLQuery(sql);
        sqlQuery.setParameter("dictCode", dictCode);
        sqlQuery.setParameter("dictEntryValue", value);
        Object result = sqlQuery.uniqueResult();
        if (result !=null ){
            return Integer.parseInt(result.toString());
        }else {
            return null;
        }

    }

    public List<SystemDictEntry> getSystemDictEntryListByDictId(String dictId){
        return  systemDictEntryDao.findAllByDictId(dictId);
    }

    /**
     * 获取字典项,获取最小字典项。
     *
     * @param dictId
     * @param code
     * @return
     */
    public List<String> getMinDictEntryCodeByCode(String dictId, String code) {
        List<String> list = new ArrayList<>();
        list.add(code);
        SystemDictEntry systemDictEntry = systemDictEntryDao.findOne(new DictEntryKey(code, dictId));
        if (null != systemDictEntry && systemDictEntry.getPcode().equals(UserConstant.DEFAULT_PARENTID)) {
            list.remove(code);
            String sql = "SELECT code FROM system_dict_entries where dict_id=:dict_id AND pcode=:pcode";
            SQLQuery sqlQuery = currentSession().createSQLQuery(sql);
            sqlQuery.setParameter("dict_id", dictId);
            sqlQuery.setParameter("pcode", code);
            list = sqlQuery.list();
        }
        return list;
    }


}
