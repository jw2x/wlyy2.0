package com.yihu.jw.healthyhouse.service.dfs;

import com.yihu.jw.healthyhouse.dao.dfs.FileResourceDao;
import com.yihu.jw.healthyhouse.model.dfs.FileResource;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 文件服务.
 *
 * @author zdm
 * @version 1.0
 * @created 2018.09.25
 */
@Service
@Transactional
public class FileResourceService extends BaseJpaService<FileResource, FileResourceDao> {

    @Autowired
    private FileResourceDao fileResourceDao;

    public FileResource findById(String id) {
        return  fileResourceDao.findById(id);
    }

}
