package com.yihu.jw.base.dao.oauth2;

import com.yihu.jw.entity.base.oauth2.OauthClientDetailsDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Dao - Oauth2
 * Created by progr1mmer on 2018/1/23.
 */
public interface OauthClientDetailsDao extends JpaRepository<OauthClientDetailsDO, String> {

}
