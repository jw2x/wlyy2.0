package com.yihu.base.security.rbas.provider;

import com.yihu.base.security.rbas.IRbasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by chenweida on 2017/12/5.
 * 判断用户是否有权限访问该路径
 */
public class DefaultRbasService implements IRbasService {

    private Logger logger = LoggerFactory.getLogger(DefaultRbasService.class);

    @Override
    public Boolean hasPerssion(HttpServletRequest request, Authentication authentication) {
        logger.info("默认的角色DefaultRbasService,默认是true");
        return true;
    }

}
