package com.yihu.jw.base.dao.wx;

import com.yihu.jw.entity.base.wx.WxWechatSaasDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Administrator on 2018/10/8.
 */
public interface WxWechatSaasDao  extends PagingAndSortingRepository<WxWechatSaasDO, String>, JpaSpecificationExecutor<WxWechatSaasDO> {
    List<WxWechatSaasDO> findByWechatId(String wechatId);

    List<WxWechatSaasDO> findBySaasId(String saasId);
}
