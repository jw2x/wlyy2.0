//package com.yihu.jw.config.security;
//
//import com.yihu.base.security.rbas.RbasServiceProvider;
//import com.yihu.jw.fegin.base.user.BaseRoleFeign;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AnonymousAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//import org.springframework.util.AntPathMatcher;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
///**
// * Created by chenweida on 2017/12/5.
// * 判断用户是否有权限访问该请求路径
// */
//@Service("rbasServiceProvider")
//public class RbasService implements RbasServiceProvider {
//
//
//    private AntPathMatcher antPathMatcher = new AntPathMatcher();
//
//
//    @Override
//    public Boolean hasPerssion(HttpServletRequest request, Authentication authentication) {
//        Object principal = authentication.getPrincipal();
//        boolean hasPerssion = false;
//        if(authentication instanceof AnonymousAuthenticationToken){
//            if("anonymousUser".equals(authentication.getPrincipal().toString())){
//                return hasPerssion;
//            }
//        }
//
//        //角色列表
//        List<String> list = (List)authentication.getAuthorities();
//
//
//        if (principal instanceof UserDetails) {
//            //获取用户名字
//            String username = ((UserDetails) principal).getUsername();
//            String[] saasPhone = username.split(",");
//            String phone = saasPhone[0];
//            String saas = saasPhone[1];
//
//
//            //从数据库获取用户全部权限
//            Set<String> uris = new HashSet<>();
//
//            for (String uri : uris) {
//                if (antPathMatcher.match(uri, request.getRequestURI())) {
//                    hasPerssion = true;
//                    break;
//                }
//            }
//            return hasPerssion;
//        }else{
//            return true;
//        }
//    }
//}
