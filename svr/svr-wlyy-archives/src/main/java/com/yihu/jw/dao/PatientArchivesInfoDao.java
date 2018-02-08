package com.yihu.jw.dao;

import com.yihu.jw.entity.archives.PatientArchivesInfo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Trick on 2018/2/7.
 */
public interface PatientArchivesInfoDao extends PagingAndSortingRepository<PatientArchivesInfo, String>,
        JpaSpecificationExecutor<PatientArchivesInfo> {
}
