package com.yihu.jw.dao.rehabilitation;

import com.yihu.jw.entity.specialist.rehabilitation.RehabilitationOperateRecordsDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by 刘文彬 on 2018/8/29.
 */
public interface RehabilitationOperateRecordsDao extends PagingAndSortingRepository<RehabilitationOperateRecordsDO, Long>,JpaSpecificationExecutor<RehabilitationOperateRecordsDO> {

    List<RehabilitationOperateRecordsDO> findByRehabilitationDetailId(String rehabilitationDetailId);

    @Modifying
    @Query("update RehabilitationOperateRecordsDO a set a.status=?1 where a.rehabilitationDetailId =?2 ")
    int updateStatus(Integer status,String rehabilitationDetailId);

    @Modifying
    @Query("update RehabilitationOperateRecordsDO a set a.node=?1,a.relationRecordImg=?2,a.status=1 where a.rehabilitationDetailId =?3 ")
    int updateNodeAndRelationRecordImg(String node,String rehabilitationRecordImg,String rehabilitataioDetailId);
}
