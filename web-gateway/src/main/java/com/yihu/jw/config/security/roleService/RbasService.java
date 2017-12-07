package com.yihu.jw.config.security.roleService;

import com.yihu.base.security.rbas.IRbasService;
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
@Service("rbasService")
public class RbasService implements IRbasService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();


    @Override
    public Boolean hasPerssion(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPerssion = false;
        if (principal instanceof UserDetails) {
            //获取用户名字
            String username = ((UserDetails) principal).getUsername();


            //获取用户全部权限
            Set<String> uris = new HashSet<>();
            for (String uri : uris) {
                if (antPathMatcher.match(uri, request.getRequestURI())) {
                    hasPerssion = true;
                    break;
                }
            }
        }
        return hasPerssion;
    }

    public Boolean hello() {
        return true;
    }
}
