package com.yihu.jw.dao;

import com.yihu.jw.entity.archives.PatientArchivesInfoDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Trick on 2018/2/7.
 */
public interface PatientArchivesInfoDao extends PagingAndSortingRepository<PatientArchivesInfoDO, String>,
        JpaSpecificationExecutor<PatientArchivesInfoDO> {
    List<PatientArchivesInfoDO> findByArchivesCodeOrderByLevel1(String archivesCode);
    int deleteByArchivesCode(String archivesCode);
}
