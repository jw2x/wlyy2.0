package com.yihu.jw.base.dao.patient;

import com.yihu.jw.entity.base.patient.BasePatientDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Trick on 2018/8/31.
 */
public interface BasePatientDao extends PagingAndSortingRepository<BasePatientDO, String>, JpaSpecificationExecutor<BasePatientDO> {

    BasePatientDO findByIdAndDel(String id,String del);

}
