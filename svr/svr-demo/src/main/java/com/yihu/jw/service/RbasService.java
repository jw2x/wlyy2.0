package com.yihu.jw.service;

import com.yihu.base.security.rbas.IRbasService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chenweida on 2017/12/5.
 * 判断用户是否有权限访问该路径
 */
@Service("rbasService")
public class RbasService implements IRbasService {
    @Override
    public Boolean hasPerssion(HttpServletRequest request, Authentication authentication) {
        return true;
    }
}
