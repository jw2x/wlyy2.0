package com.yihu.wlyy.figure.label.dao;

import com.yihu.wlyy.figure.label.entity.FlJobConfig;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
/**
 * @author lith on 2018.03.14
 * 定时任务配置
 */
public interface FlJobConfigDao extends PagingAndSortingRepository<FlJobConfig, Long>, JpaSpecificationExecutor<FlJobConfig> {

    /**
     * 根据id和状态查询job配置
     * @param id
     * @param status
     * @return
     */
    @Query(" FROM FlJobConfig a WHERE a.id=?1 and a.status=?2 and a.del='1'")
    FlJobConfig findByIdAndStatus(Long id, String status);

    /**
     * 查询所有的job配置
     * @param status
     * @return
     */
    @Query(" FROM FlJobConfig a WHERE a.status=?1 and a.del='1'")
    List<FlJobConfig> findByAll(String status);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Query(" FROM FlJobConfig a WHERE a.id=?1 and a.del='1'")
    FlJobConfig findById(Long id);

    /**
     * 更新job的状态（状态为启动或停止）
     * @param id
     * @param status
     * @return
     */
    @Modifying
    @Query(" update FlJobConfig a set a.status=?2 where a.id=?1 ")
    int updateStatus(Long id, String status);

    /**
     * 删除job（0 正常 1 删除）
     * @param id
     * @return
     */
    @Modifying
    @Query(" update FlJobConfig a set a.del = 1 where a.id=?1 ")
    int deleteJob(Long id);

    /**
     * 更新增量时间
     * @param id
     * @param value
     * @return
     */
    @Modifying
    @Query(" update FlJobConfig a set a.sqlFieldValue=?2 where a.id=?1 ")
    int updateSqlFildeValue(Long id, String value);
}
