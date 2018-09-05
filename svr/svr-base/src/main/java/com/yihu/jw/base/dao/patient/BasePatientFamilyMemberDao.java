package com.yihu.jw.base.dao.patient;

import com.yihu.jw.entity.base.patient.BasePatientFamilyMemberDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Trick on 2018/8/31.
 */
public interface BasePatientFamilyMemberDao extends PagingAndSortingRepository<BasePatientFamilyMemberDO, String>, JpaSpecificationExecutor<BasePatientFamilyMemberDO> {

    public BasePatientFamilyMemberDO findByPatientAndFamilyMember(String patient,String member);
}
