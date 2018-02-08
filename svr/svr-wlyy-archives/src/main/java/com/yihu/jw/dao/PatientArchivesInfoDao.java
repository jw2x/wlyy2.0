package com.yihu.jw.dao;

import com.yihu.jw.entity.archives.PatientArchivesInfo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Trick on 2018/2/7.
 */
public interface PatientArchivesInfoDao extends PagingAndSortingRepository<PatientArchivesInfo, String>,
        JpaSpecificationExecutor<PatientArchivesInfo> {
    List<PatientArchivesInfo> findByCodeOrderByLevel1(String code);
    int deleteByArchivesCode(String archivesCode);
}
