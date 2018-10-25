package com.yihu.jw.base.service.user;

import com.alibaba.fastjson.JSONObject;
import com.yihu.jw.base.dao.role.BaseRoleMenuDao;
import com.yihu.jw.base.dao.role.RoleDao;
import com.yihu.jw.base.dao.saas.SaasDao;
import com.yihu.jw.base.dao.user.UserDao;
import com.yihu.jw.entity.base.role.RoleDO;
import com.yihu.jw.entity.base.saas.SaasDO;
import com.yihu.jw.entity.base.user.UserDO;
import com.yihu.mysql.query.BaseJpaService;
import com.yihu.utils.security.MD5;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Service - 后台管理员
 * Created by progr1mmer on 2018/8/20.
 */
@Service
public class UserService extends BaseJpaService<UserDO, UserDao> {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private SaasDao saasDao;
    @Autowired
    private BaseRoleMenuDao baseRoleMenuDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserDO registerWithIdcard(UserDO userDO) {
        userDO.setSalt(randomString(5));
        userDO.setEnabled(true);
        userDO.setLocked(false);
        userDO.setLoginFailureCount(0);
        String password = userDO.getPassword();
        if (StringUtils.isEmpty(password)) {
            password = userDO.getIdcard().substring(0, 5);
        }
        userDO.setPassword(MD5.md5Hex(password + "{" + userDO.getSalt() + "}"));
        return userDao.save(userDO);
    }

    /**
     * 手机号作为账号，初始密码为手机号后6位
     * @param userDO
     * @return
     */
    public UserDO registerWithMobile(UserDO userDO) {
        userDO.setSalt(randomString(5));
        userDO.setEnabled(true);
        userDO.setLocked(false);
        userDO.setLoginFailureCount(0);
        String password = userDO.getPassword();
        if (StringUtils.isEmpty(password)) {
            password =  userDO.getMobile().substring(0, 5);
        }
        userDO.setPassword(MD5.md5Hex(password + "{" + userDO.getSalt() + "}"));
        return userDao.save(userDO);
    }


    /**
     * 根据用户手机号查找（手机号为登录账号）
     * @param mobile
     * @return
     */
    public UserDO findByMobile(String mobile) {
        return userDao.findByMobile(mobile);
    }

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    public UserDO findById(String id) {
        return userDao.findById(id);
    }

    /**
     * 判断手机号是否存在
     * @param mobile
     * @return
     */
    public Boolean existMobile(String mobile){
        if(StringUtils.isEmpty(mobile)) {
            return null;
        }
        return userDao.existsByMobile(mobile);
    }

    /**
     * 判断用户名是否存在
     * @param username
     * @return
     */
    public Boolean existUserName(String username){
        if(StringUtils.isEmpty(username)) {
            return null;
        }
        return userDao.existsByUsername(username);
    }

    /**
     * 用户管理，获取用户基本信息列表
     * @param name 模糊查询
     * @param saasId 精准匹配，为空查全部
     * @param roleId 精准匹配，为空查全部
     * @return
     */
    public List<Map<String,Object>> queryBaseUserInfoList(String name,String saasId,String roleId){
        List<Map<String,Object>> result = new ArrayList<>();
        return result;
    }

    public Map<String,Object> findUserBaseInfo(String id){

        Map<String,Object> userinfo = new HashedMap();

        UserDO user = userDao.findOne(id);
        RoleDO role = roleDao.findByCode(user.getRoleCode());

        userinfo.put("id",user.getId());
        userinfo.put("name",user.getName());
        userinfo.put("role",role.getName());
        userinfo.put("roleCode",role.getCode());
        userinfo.put("system",role.getSystem());

        if("admin".equals(role.getCode())){

        }else if("saasAdmin".equals(role.getCode())){

            if(org.apache.commons.lang3.StringUtils.isNotBlank(user.getId())){
                SaasDO saas = saasDao.findOne(user.getSaasId());
                Map<String,Object> ss = new HashedMap();
                ss.put("id",saas.getId());
                ss.put("name",saas.getName());
                userinfo.put("saas",ss);
            }


        }else if("hosAdmin".equals(role.getCode())){
            String sql = "SELECT " +
                    " g.code AS orgCode, " +
                    " g.province_code AS provinceCode, " +
                    " g.province_name AS privinceName, " +
                    " g.city_code AS cityCode, " +
                    " g.city_name AS cityName, " +
                    " g.town_code AS townCode, " +
                    " g.town_name AS townName, " +
                    " g.street_code AS streetCode, " +
                    " g.street_name AS streetName, " +
                    " g.name, " +
                    " address " +
                    " FROM " +
                    " base_org g " +
                    " JOIN base_org_user u ON g.`code` = u.org_code " +
                    " WHERE  " +
                    " u.user_id = '"+id+"'";
            List<Map<String,Object>> org = jdbcTemplate.queryForList(sql);
            userinfo.put("org",org.get(0));
        }

        return userinfo;
    }

//    public Map<String,Object> findUserMenu(String id){
//
//    }

}
