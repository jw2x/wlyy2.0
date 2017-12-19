package com.yihu.ehr.iot.service.common;

import com.yihu.ehr.agModel.user.RoleOrgModel;
import com.yihu.ehr.agModel.user.UserDetailModel;
import com.yihu.ehr.constants.ServiceApi;
import com.yihu.ehr.constants.SessionAttributeKeys;
import com.yihu.ehr.model.geography.MGeographyDict;
import com.yihu.ehr.model.org.MOrganization;
import com.yihu.ehr.model.resource.MRsRolesResource;
import com.yihu.ehr.model.user.MRoles;
import com.yihu.ehr.iot.constant.AuthorityKey;
import com.yihu.ehr.iot.util.http.HttpHelper;
import com.yihu.ehr.iot.util.http.HttpResponse;
import com.yihu.ehr.iot.model.AccessToken;
import com.yihu.ehr.iot.model.ObjectResult;
import com.yihu.ehr.iot.model.Result;
import com.yihu.ehr.util.log.LogService;
import com.yihu.ehr.util.rest.Envelop;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Service - 登陆
 * @author Progr1mmer
 */
@Service
public class LoginService extends BaseService {

    //------------------------ 初始化内外网IP start ------------------------
    private final long a1 = getIpNum("10.0.0.0");
    private final long a2 = getIpNum("10.255.255.255");
    private final long b1 = getIpNum("172.16.0.0");
    private final long b2 = getIpNum("172.31.255.255");
    private final long c1 = getIpNum("192.168.0.0");
    private final long c2 = getIpNum("192.168.255.255");
    private final long d1 = getIpNum("10.44.0.0");
    private final long d2 = getIpNum("10.69.0.255");

    public void initUrlInfo(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if(ip != null) {
            if("0:0:0:0:0:0:0:1".equals(ip)) {
                request.getSession().setAttribute("isInnerIp", true);
            }else {
                if("127.0.0.1".equals(ip) || isInnerIP(ip)) {
                    request.getSession().setAttribute("isInnerIp", true);
                }else {
                    request.getSession().setAttribute("isInnerIp", false);
                }
            }
        }
    }

    public boolean isInnerIP(String ip){
        long n = getIpNum(ip);
        return (n >= a1 && n <= a2) || (n >= b1 && n <= b2) || (n >= c1 && n <= c2) || (n >= d1 && n <= d2);
    }

    public long getIpNum(String ipAddress) {
        String [] ip = ipAddress.split("\\.");
        long a = Integer.parseInt(ip[0]);
        long b = Integer.parseInt(ip[1]);
        long c = Integer.parseInt(ip[2]);
        long d = Integer.parseInt(ip[3]);
        return a * 256 * 256 * 256 + b * 256 * 256 + c * 256 + d;
    }

    //------------------------ 初始化内外网IP end ------------------------

    /**
     * 用户名密码登录
     * @param request
     * @param userName
     * @param password
     * @return
     */
    public Result login(HttpServletRequest request, String userName, String password) {
        try {
            ObjectResult result = new ObjectResult();
            Map<String, Object> params = new HashMap<>();
            params.put("userName", userName);
            params.put("password", password);
            params.put("clientId", clientId);
            HttpResponse response = HttpHelper.get(portalInnerUrl + "/oauth/login", params);
            if (response != null && response.getStatusCode() == 200) {
                ObjectResult re = toModel(response.getBody(), ObjectResult.class);
                if (re.isSuccessFlg()){
                    Map userMap = new HashMap<>();
                    userMap.put("user",re.getData());
                    result.setData(userMap);
                    String userId = ((LinkedHashMap) re.getData()).get("id").toString();
                    //获取token
                    Result tokenResponse = getAccessToken(userName, password, clientId);
                    if (tokenResponse.isSuccessFlg()) {
                        initUrlInfo(request);
                        String data = objectMapper.writeValueAsString(((ObjectResult) tokenResponse).getData());
                        AccessToken token = objectMapper.readValue(data,AccessToken.class);
                        request.getSession().setAttribute("isLogin", true);
                        request.getSession().setAttribute("token", token);
                        request.getSession().setAttribute("loginName", userName);
                        request.getSession().setAttribute("userId", userId);
                        result.setSuccessFlg(true);
                        result.setMessage("登录成功");
                        result.setCode(200);
                        return result;
                    }
                    else{
                        return tokenResponse;
                    }
                }
                else {
                    return re;
                }
            } else {
                return Result.error(response.getStatusCode(), response.getBody());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    /**
     * 自动登录
     * @param request
     * @param model
     * @param token
     * @return
     * @throws Exception
     */
    public Envelop autoLogin(HttpServletRequest request, Model model, @RequestParam String token) throws Exception {
        try {
            String clientId = request.getParameter("clientId").toString();
            Map<String, Object> params = new HashMap<>();
            params.put("clientId", clientId);
            params.put("accessToken", token);
            HttpResponse response = HttpHelper.post(oauth2InnerUrl + "/oauth/validToken", params);
            Map<String, Object> map = objectMapper.readValue(response.getBody(), Map.class);
            if ((Boolean) map.get("successFlg")) {
                AccessToken accessToken = objectMapper.readValue(objectMapper.writeValueAsString(map.get("data")), AccessToken.class);
                String loginName = accessToken.getUser();
                //验证通过。赋值session中的用户信息
                response = HttpHelper.get(profileInnerUrl + "/users/" + loginName, params);
                Envelop envelop = (Envelop) this.objectMapper.readValue(response.getBody(), Envelop.class);
                String ex = this.objectMapper.writeValueAsString(envelop.getObj());
                UserDetailModel userDetailModel = this.objectMapper.readValue(ex, UserDetailModel.class);
                //获取用户的角色，机构，视图 等权限
                getUserRolePermissions(userDetailModel, loginName, request);
                // 注：SessionAttributeKeys.CurrentUser 是用 @SessionAttributes 来最终赋值，换成用 session.setAttribute() 赋值后将会被覆盖。
                model.addAttribute(SessionAttributeKeys.CurrentUser, userDetailModel);
                HttpSession session = request.getSession();
                //增加超级管理员信息
                if(loginName.equals(permissionsInfo)) {
                    session.setAttribute(AuthorityKey.IsAccessAll, true);
                }else {
                    session.setAttribute(AuthorityKey.IsAccessAll, false);
                }
                session.setAttribute("isLogin", true);
                session.setAttribute("token", accessToken);
                session.setAttribute("loginName", loginName);
                session.setAttribute("userId", userDetailModel.getId());
                session.setAttribute("clientId", clientId);
                //获取用户角色信息
                List<Map<String, Object>> features = getUserFeatures(userDetailModel.getId());
                Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                if (features != null) {
                    for (Map<String, Object> temp : features) {
                        if (temp.get("url") != null && !StringUtils.isEmpty(temp.get("url").toString())){
                            grantedAuthorities.add(new SimpleGrantedAuthority(temp.get("url").toString()));
                        }
                    }
                }
                //生成认证token
                Authentication AuthenticationToken = new UsernamePasswordAuthenticationToken(loginName, "", grantedAuthorities);
                //将信息存放到SecurityContext
                SecurityContextHolder.getContext().setAuthentication(AuthenticationToken);
                return success(userDetailModel);
            } else {
                String msg = String.valueOf(map.get("message"));
                return failed(msg);
            }
        } catch (Exception e) {
            return failed(e.getMessage());
        }
    }

    /**
     * 通过用户名密码获取token
     * @param userName
     * @param password
     * @param clientId
     * @return
     */
    public Result getAccessToken(String userName, String password, String clientId) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("userName", userName);
            params.put("password", password);
            params.put("clientId", clientId);
            HttpResponse response = HttpHelper.post(oauth2InnerUrl + "oauth/accessToken", params);
            if (response != null && response.getStatusCode() == 200) {
                return toModel(response.getBody(),ObjectResult.class);
            }
            else {
                return Result.error(response.getStatusCode(),response.getBody());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    /**
     * 刷新token
     * @param refreshToken
     * @param clientId
     * @return
     */
    public Result refreshToken(String refreshToken, String clientId) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("refreshToken", refreshToken);
            params.put("clientId", clientId);
            HttpResponse response = HttpHelper.post(oauth2InnerUrl + "oauth/refreshToken", params);
            if (response!=null && response.getStatusCode() == 200) {
                return toModel(response.getBody(),ObjectResult.class);
            }
            else {
                return Result.error(response.getStatusCode(),response.getBody());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    /**
     * 校验token
     * @param clientId
     * @param accessToken
     * @return
     */
    public Result validToken(String clientId, String accessToken) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("clientId", clientId);
            params.put("accessToken", accessToken);
            HttpResponse response = HttpHelper.post(oauth2InnerUrl + "oauth/validToken", params);
            if (response != null && response.getStatusCode() == 200) {
                return toModel(response.getBody(),ObjectResult.class);
            }
            else {
                return Result.error(response.getStatusCode(),response.getBody());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }


    /**
     * 获取用户的角色，机构，视图 等权限
     * @param userDetailModel
     * @param request
     * @throws Exception
     */
    public void getUserRolePermissions(UserDetailModel userDetailModel, String loginCode, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        if(loginCode.equals(permissionsInfo)){
            session.setAttribute(AuthorityKey.UserRoles, null);
            session.setAttribute(AuthorityKey.UserResource, null);
            session.setAttribute(AuthorityKey.UserAreaSaas, null);
            session.setAttribute(AuthorityKey.UserOrgSaas, null);
        }else{
            //获取用户角色
            String roleStr = "";
            List<String> roleList = new ArrayList<>();
            roleStr =  gerUserRoles(userDetailModel.getId());
            if( !StringUtils.isEmpty(roleStr)){
                roleList =  Arrays.asList(roleStr.split(","));
                session.setAttribute(AuthorityKey.UserRoles, roleList);
                //获取角色机构
                List<RoleOrgModel> roleOrgModels = new ArrayList<>();
                gerRolesOrgs(roleList,roleOrgModels);
                if(roleOrgModels !=null && roleOrgModels.size() >0){
                    List<String> roleOrgCodes = new ArrayList<>();
                    for(RoleOrgModel roleOrgModel : roleOrgModels){
                        roleOrgCodes.add(roleOrgModel.getOrgCode());
                    }
                    getUserSaasOrgAndArea(roleOrgCodes, request);
                }else{
                    List<String> userOrgList = new ArrayList<>();
                    userOrgList.add("-NoneOrg");
                    session.setAttribute(AuthorityKey.UserOrgSaas, userOrgList);
                }
                //获取角色视图
                List<String> rolesResourceIdList =  new ArrayList<>();
                List<MRsRolesResource> rolesResourceList = new ArrayList<>();
                gerRolesResource(roleList, rolesResourceList);
                if(rolesResourceList !=null && rolesResourceList.size() >0){
                    for(MRsRolesResource rsRolesResource : rolesResourceList){
                        rolesResourceIdList.add(rsRolesResource.getResourceId());
                    }
                    session.setAttribute(AuthorityKey.UserResource, rolesResourceIdList);
                }else{
                    rolesResourceIdList.add("-NoneResource");
                    session.setAttribute(AuthorityKey.UserResource, rolesResourceIdList);
                }
            }else{
                roleList.add("-NoneRole");
                session.setAttribute(AuthorityKey.UserRoles, roleList);
            }
        }
    }

    /**
     * 获取用户角色
     * @param userId
     * @return
     */
    public String gerUserRoles(String userId){
        //获取用户所属角色
        String roleStr = "";
        try {
            String url = "/roles/role_user/userRolesIds";
            Map<String,Object> params = new HashMap<>();
            params.put("user_id",userId);
            HttpResponse response = HttpHelper.get(profileInnerUrl + url,params);
            Envelop envelop = objectMapper.readValue(response.getBody(),Envelop.class);
            if (envelop.isSuccessFlg() && null != envelop.getObj() && !"".equals(envelop.getObj())) {
                roleStr = envelop.getObj().toString();
            }
        } catch (Exception ex) {
            LogService.getLogger(LoginService.class).error(ex.getMessage());
        }
        return  roleStr;
    }

    /**
     * 获取角色机构
     * @param roleList 角色组列表
     * @return
     */
    public List<RoleOrgModel> gerRolesOrgs(List<String> roleList,List<RoleOrgModel> roleOrgs){
        for(String roleId : roleList){
            try {
                Map<String,Object> params = new HashMap<>();
                String roleUrl = "/roles/role/"+roleId;
                params.put("id",Long.valueOf(roleId));
                HttpResponse response = HttpHelper.get(profileInnerUrl + roleUrl,params);
                Envelop envelopRole = objectMapper.readValue(response.getBody(),Envelop.class);
                if(envelopRole.getObj() != null){
                    MRoles mRoles = objectMapper.convertValue(envelopRole.getObj(), MRoles.class);
                    if ( ! StringUtils.isEmpty( mRoles.getOrgCode() )){
                        RoleOrgModel roleOrgModel = new RoleOrgModel();
                        roleOrgModel.setOrgCode(mRoles.getOrgCode());
                        roleOrgModel.setRoleId(mRoles.getId());
                        roleOrgs.add(roleOrgModel);
                    }
                }
                String url = ServiceApi.Roles.RoleOrgsNoPage;
                params.clear();
                params.put("filters","roleId=" + roleId);
                response = HttpHelper.get(profileInnerUrl + url,params);
                Envelop envelop = objectMapper.readValue(response.getBody(),Envelop.class);
                if (envelop.isSuccessFlg() && null != envelop.getDetailModelList() &&  envelop.getDetailModelList().size()>0) {
                    List<RoleOrgModel> roleOrgModels = envelop.getDetailModelList();
                    if(roleOrgModels != null && roleOrgModels.size() > 0){
                        for(int i = 0; i < roleOrgModels.size() ;i++){
                            RoleOrgModel orgModel = objectMapper.convertValue(roleOrgModels.get(i), RoleOrgModel.class) ;
                            roleOrgs.add(orgModel);
                        }
                    }
                }
            } catch (Exception ex) {
                LogService.getLogger(LoginService.class).error(ex.getMessage());
            }
        }
        return  roleOrgs;
    }

    /**
     * 获取用的saas机构
     */
    public void getUserSaasOrgAndArea(List<String> roleOrgCodes, HttpServletRequest request) throws Exception {
        Envelop envelop = new Envelop();
        List<String> userOrgList = new ArrayList<>();
        for(String code : roleOrgCodes){
            userOrgList.add(code);
        }
        //使用orgCode获取saas化的机构或者区域。
        String urlUOrg = "/org/getUserOrgSaasByUserOrgCode/";
        Map<String, Object> uParams = new HashMap<>();
        uParams.put("orgCodeStr",org.apache.commons.lang.StringUtils.join(roleOrgCodes,',') );
        HttpResponse response = HttpHelper.get(profileInnerUrl + urlUOrg, uParams);
        envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        HttpSession session = request.getSession();
        session.setAttribute("userAreaSaas", envelop.getObj());
        session.setAttribute("userOrgSaas", envelop.getDetailModelList());
        userOrgList = envelop.getDetailModelList();
        List<String> districtList = (List<String>) envelop.getObj();
        String geographyUrl = "/geography_entries/";
        if(districtList != null && districtList.size() > 0){
            for(String code : districtList){
                uParams.clear();
                response = HttpHelper.get(profileInnerUrl + geographyUrl + code, uParams);
                envelop = objectMapper.readValue(response.getBody(),Envelop.class);
                MGeographyDict mGeographyDict = null;
                String objJsonData = objectMapper.writeValueAsString(envelop.getObj());
                mGeographyDict = objectMapper.readValue(objJsonData, MGeographyDict.class);
                if(mGeographyDict != null){
                    String province = "";
                    String city = "";
                    String district = "";
                    if(mGeographyDict.getLevel() == 1){
                        province =  mGeographyDict.getName();
                    }else if(mGeographyDict.getLevel() == 2){
                        city =  mGeographyDict.getName();
                    }else if(mGeographyDict.getLevel() == 3){
                        district =  mGeographyDict.getName();
                    }
                    String  orgGeographyStr = "/organizations/geography";
                    uParams.clear();
                    uParams.put("province",province);
                    uParams.put("city",city);
                    uParams.put("district",district);
                    response = HttpHelper.get(profileInnerUrl + orgGeographyStr , uParams);
                    envelop = objectMapper.readValue(response.getBody(),Envelop.class);
                    if(envelop !=null && envelop.getDetailModelList() != null ){
                        List<MOrganization> organizations = (List<MOrganization>)getEnvelopList(envelop.getDetailModelList(),new ArrayList<MOrganization>(),MOrganization.class);
                        if(organizations !=null ){
                            java.util.Iterator it = organizations.iterator();
                            while(it.hasNext()){
                                MOrganization mOrganization = (MOrganization)it.next();
                                userOrgList.add(mOrganization.getCode());
                            }
                        }
                    }
                }
            }
        }
        userOrgList.removeAll(Collections.singleton(null));
        userOrgList.removeAll(Collections.singleton(""));
        request.getSession().setAttribute(AuthorityKey.UserOrgSaas, userOrgList);
    }

    /**
     * 获取角色视图列表
     * @param roleList
     * @param rolesResourceList
     * @return
     */
    public List<MRsRolesResource> gerRolesResource(List<String> roleList,List<MRsRolesResource> rolesResourceList){
        for(String roleId : roleList){
            try {
                String url = ServiceApi.Resources.GetRolesGrantResources;
                Map<String,Object> params = new HashMap<>();
                params.put("rolesId",roleId);
                HttpResponse response = HttpHelper.get(profileInnerUrl + url,params);
                Envelop envelop = objectMapper.readValue(response.getBody(),Envelop.class);
                if (envelop.isSuccessFlg() && null != envelop.getDetailModelList() && envelop.getDetailModelList().size() > 0 ) {
                    List<MRsRolesResource> roleResourceModels = envelop.getDetailModelList();
                    if(roleResourceModels != null && roleResourceModels.size() > 0){
                        for(int i = 0; i < roleResourceModels.size() ;i++){
                            MRsRolesResource rolesResource = objectMapper.convertValue(roleResourceModels.get(i),MRsRolesResource.class) ;
                            rolesResourceList.add(rolesResource);
                        }
                    }
                }
            } catch (Exception ex) {
                LogService.getLogger(LoginService.class).error(ex.getMessage());
            }
        }
        return  rolesResourceList;
    }

    private List<Map<String, Object>> getUserFeatures(String userId) throws Exception {
        Map params = new HashMap<>();
        params.put("user_id", userId);
        HttpResponse response = HttpHelper.get(profileInnerUrl + "/roles/user/features", params);
        Envelop envelop =  objectMapper.readValue(response.getBody(), Envelop.class);
        if (envelop.isSuccessFlg()) {
            return envelop.getDetailModelList();
        } else {
            throw new Exception(envelop.getErrorMsg());
        }
    }
}
