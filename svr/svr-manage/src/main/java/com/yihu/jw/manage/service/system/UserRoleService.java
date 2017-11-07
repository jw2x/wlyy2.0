package com.yihu.jw.manage.service.system;

import com.yihu.jw.manage.dao.system.UserRoleDao;
import com.yihu.jw.manage.model.system.ManageUser;
import com.yihu.jw.manage.model.system.ManageUserRole;
import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.base.mysql.query.URLQueryParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/26 0026.
 */
@Service
public class UserRoleService extends BaseJpaService<ManageUserRole, UserRoleDao> {

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private UserService userService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Page<ManageUserRole> list(String roleCode, Integer page, Integer pageSize) throws ParseException {
        String filters = "roleCode=" + roleCode + ";";
        if (page <= 0) {
            page = 1;
        }
        if (pageSize <= 0) {
            pageSize = 15;
        }
        PageRequest pageRequest = new PageRequest(page - 1, pageSize);
        URLQueryParser queryParser = createQueryParser("", filters, "");
        CriteriaQuery query = queryParser.makeCriteriaQuery();

        List<ManageUserRole> list = entityManager
                .createQuery(query)
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
        long total = 0;
        if (list.size() > 0) {
            queryParser = createQueryParser(filters);
             query = queryParser.makeCriteriaCountQuery();
            total = (long) entityManager.createQuery(query).getSingleResult();
            for (ManageUserRole roleUser : list) {
                ManageUser user = userService.findByCode(roleUser.getUserCode());
                roleUser.setUserName(user == null ? "" : user.getName());
            }
        }
        return new PageImpl<ManageUserRole>(list, pageRequest, total);
    }

    /**
     * 根据roleCode获取userCode的
     * @param roleCode
     * @return
     */
    public  List<String> getUserCodes(String roleCode) {
        List<ManageUserRole> userRoles = userRoleDao.findByRoleCode(roleCode);
        List<String> userCodes = new ArrayList<>();
        for (ManageUserRole userRole:userRoles){
            userCodes.add(userRole.getUserCode());
        }
        return userCodes;
    }

    /**
     * 更改角色权限
     * @param roleCode
     * @param userCode
     */
    @Transactional
    public void changeUserRole(String roleCode ,String userCode) {
        ManageUserRole userRole = userRoleDao.findByRoleAndUserCode(roleCode, userCode);
        if(userRole!=null){
            userRoleDao.deleteUserRole(roleCode,userCode);
            return;
        }
        userRole = new ManageUserRole();
        userRole.setRoleCode(roleCode);
        userRole.setUserCode(userCode);
        userRoleDao.save(userRole);
    }

    /**
     * 获取角色名称
     * @param manageUsers
     * @return
     */
    public List<ManageUser> findByManageUser(List<ManageUser> manageUsers) {
        if(manageUsers==null)
            return null;
        for (ManageUser manageUser:manageUsers){
            String sql = "select group_concat(mr.name) as name from manage_role mr where mr.code in (select role_code from manage_user_role mur where mur.user_code =?)";
            String name = jdbcTemplate.queryForObject(sql, String.class, manageUser.getCode());
            manageUser.setRoleName(name);
        }
        return manageUsers;
    }

    /**
     * 根据userCode获取saasid
     */
    public List<String> getSaasIdByUserCode(String userCode){
        String sql = "select distinct mr.saas_id from manage_user_role mur left join manage_role mr on mr.code = mur.role_code where mur.user_code=? ";
       return jdbcTemplate.queryForList(sql,String.class,userCode);
    }
}
