package com.yihu.jw.config.security.roleService;

import com.yihu.base.security.rbas.RbasService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by chenweida on 2017/11/30.
 */
@Component("rbasService")
public class UserRbasService implements RbasService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    public Boolean hasPerssion(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPerssion = false;
        if (principal instanceof UserDetails) {
            //用户名字
            String userName = ((UserDetails) principal).getUsername();

            //根据用户名字去数据库查找权限
            Set<String> urls = new HashSet<>();

            for (String url : urls) {
                if (antPathMatcher.match(url, request.getRequestURI())) {
                    hasPerssion = true;
                    break;
                }
            }
        }

        return hasPerssion;
    }
}
