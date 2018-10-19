package com.yihu.jw.base.dao.patient;

import com.yihu.jw.entity.base.patient.BasePatientDO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Trick on 2018/8/31.
 */
public interface BasePatientDao extends PagingAndSortingRepository<BasePatientDO, String>, JpaSpecificationExecutor<BasePatientDO> {

    BasePatientDO findByIdAndDel(String id,String del);


    @Query("select id as id,idcard as idcard,name as name,case sex when 1 then '男' when 2 then '女' else '未知' end as sex,phone as phone,concat(liveProvinceName,liveCityName,liveTownName,committeeName) as committeeName,concat(provinceName,cityName,townName,streetName) as address from BasePatientDO where idcard like ?1")
    List<Map<String,Object>> findByIdcard(String idcard, Pageable pageable);

    @Query("select id as id,idcard as idcard,name as name,case sex when 1 then '男' when 2 then '女' else '未知' end as sex,phone as phone,concat(liveProvinceName,liveCityName,liveTownName,committeeName) as committeeName,concat(provinceName,cityName,townName,streetName) as address from BasePatientDO where name like ?1")
    List<Map<String,Object>> findByName(String name, Pageable pageable);

    @Query("select id as id,idcard as idcard,name as name,case sex when 1 then '男' when 2 then '女' else '未知' end as sex,phone as phone,concat(liveProvinceName,liveCityName,liveTownName,committeeName) as committeeName,concat(provinceName,cityName,townName,streetName) as address from BasePatientDO")
    List<Map<String,Object>> findBaseInfo(Pageable pageable);

}
