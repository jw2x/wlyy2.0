package com.yihu.jw.manage.interceptors;

import com.yihu.jw.manage.adapter.CacheAdapter;
import com.yihu.jw.manage.adapter.cache.model.LoginCacheModel;
import com.yihu.jw.manage.adapter.cache.model.RoleCacheModel;
import com.yihu.jw.manage.model.system.ManageUser;
import com.yihu.jw.manage.service.system.MenuRoleService;
import com.yihu.jw.manage.service.system.UserRoleService;
import com.yihu.jw.manage.service.system.UserService;
import com.yihu.jw.restmodel.common.Envelop;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chenweida on 2017/6/9.
 */
@Component
public class UserInterceptor implements HandlerInterceptor {
    private static Integer NOT_LOGIN=-1000;
    private static Integer NO_PRIVILEGE=-2000;
    List<String> unFilters=new ArrayList<>();
    @Autowired
    private UserService userService;
    @Autowired
    private CacheAdapter cacheAdapter;
    @Autowired
    private MenuRoleService menuRoleService;
    @Autowired
    private UserRoleService userRoleService;
    @PostConstruct
    public void addUnFilterURI(){
        //添加不需要过滤的路径
        unFilters.add("/login");
        unFilters.add("/error");
        unFilters.add("/index");
        unFilters.add("/loginout");
        unFilters.add("/manage/menuRole/reloadPrivilege");//刷新权限缓存
        unFilters.add("");
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        boolean flag = false;
        try {
            //判断路径是否要过滤
            String serverPath = request.getServletPath();
            if (unFilters.contains(serverPath)){
                return true;
            }
            String userCode= request.getParameter("userCode");
            if(org.springframework.util.StringUtils.isEmpty(userCode)){
                // 未登录
                response.getOutputStream().write(JSONObject.fromObject(Envelop.getError("请登录后再操作！",NOT_LOGIN)).toString().getBytes());
                return false;
            }

            //判断usercode是否存在
            ManageUser manageUser= userService.findByCode(userCode);
            if(manageUser==null){
                // 未登录
                response.getOutputStream().write(JSONObject.fromObject(Envelop.getError("请登录后再操作！",NOT_LOGIN)).toString().getBytes());
                return false;
            }

            //从缓存中获取
            Object data = cacheAdapter.getData(CacheAdapter.LOGIN, userCode);
            if(data==null){  //缓存丢失---->>重新放入缓存中
                String saasId= request.getParameter("saasId");
                //根据userCode查找用户拥有的权限,放入缓存中
                List<Map<String, Object>> maps = menuRoleService.findByUserCode(userCode);
                cacheAdapter.setData(CacheAdapter.ROLE,userCode,maps);

                //登陆用户放入缓存
                LoginCacheModel loginCacheModel = new LoginCacheModel();
                loginCacheModel.setCode(userCode);
                if(StringUtils.isNotBlank(saasId)){
                    loginCacheModel.setSaasId(saasId);
                }else{
                    //查找saas
                    List<String> saases = userRoleService.getSaasIdByUserCode(userCode);
                    //saas有多个时,取出第一个放入缓存
                    if(saases!=null&&saases.size()>0){
                        loginCacheModel.setSaasId(saases.get(0));
                    }
                }
                cacheAdapter.setData(CacheAdapter.LOGIN,userCode,loginCacheModel);
            }

            String method = request.getMethod();

            List<RoleCacheModel> privilege = (List<RoleCacheModel>) cacheAdapter.getData(CacheAdapter.ROLE, userCode);
            for(RoleCacheModel roleCacheModel:privilege){
                String url = roleCacheModel.getUrl();
                if(url==null)
                    continue;
                String pri_method = roleCacheModel.getMethod();
                if(serverPath.contains(url) && method.equalsIgnoreCase(pri_method)){//url以及请求方式相同才有权限
                    return true;
                }
            }
            response.getOutputStream().write(JSONObject.fromObject(Envelop.getError("该用户没有权限！", NO_PRIVILEGE)).toString().getBytes());
            return flag;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
