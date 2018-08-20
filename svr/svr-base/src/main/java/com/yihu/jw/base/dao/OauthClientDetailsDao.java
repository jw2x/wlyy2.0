package com.yihu.jw.base.dao;

import com.yihu.jw.entity.base.oauth2.OauthClientDetailsDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by progr1mmer on 2018/1/23.
 */
public interface OauthClientDetailsDao extends JpaRepository<OauthClientDetailsDO, String> {

}
