package com.yihu.jw.healthyhouse.service.dict;

import com.yihu.jw.healthyhouse.dao.dict.SystemDictDao;
import com.yihu.jw.healthyhouse.dao.dict.SystemDictEntryDao;
import com.yihu.jw.healthyhouse.model.dict.SystemDict;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 系统字典管理器.
 *
 * @author zdm
 * @version 1.0
 * @created 2018.09.18
 */
@Service
@Transactional
public class SystemDictService extends BaseJpaService<SystemDict, SystemDictDao> {

    @Autowired
    private SystemDictDao dictRepo;
    @Autowired
    private SystemDictEntryDao entryRepo;

    public void updateDict(SystemDict dict) {
        dictRepo.save(dict);
    }

    public void deleteDict(String dictId) {
        entryRepo.deleteByDictId(dictId);
        dictRepo.delete(Long.parseLong(dictId));
    }

    public boolean isDictNameExists(String name) {
        SystemDict systemDict = dictRepo.findByName(name);

        return systemDict != null;
    }

    public SystemDict findByPhoneticCode(String code) {
        return dictRepo.findByPhoneticCode(code);
    }

    public SystemDict findById(String id) {
        return  dictRepo.findById(id);
    }

}
