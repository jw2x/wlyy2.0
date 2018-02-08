package com.yihu.jw.dao;

import com.yihu.jw.entity.archives.PatientArchives;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Trick on 2018/2/7.
 */
public interface PatientArchivesDao extends PagingAndSortingRepository<PatientArchives, String>,
        JpaSpecificationExecutor<PatientArchives> {
}
