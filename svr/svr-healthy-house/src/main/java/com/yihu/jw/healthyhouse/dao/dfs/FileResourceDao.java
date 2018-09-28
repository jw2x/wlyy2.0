package com.yihu.jw.healthyhouse.dao.dfs;


import com.yihu.jw.healthyhouse.model.dfs.FileResource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 文件服务
 * @author zdm
 * @version 1.0
 * @created 2018.09.25
 */
public interface FileResourceDao extends JpaRepository<FileResource, String> {

    FileResource findById(String id);
}

