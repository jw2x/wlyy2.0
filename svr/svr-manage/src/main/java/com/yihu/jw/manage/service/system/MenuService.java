package com.yihu.jw.manage.service.system;

import com.yihu.jw.manage.dao.system.MenuDao;
import com.yihu.jw.manage.model.system.ManageMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenweida on 2017/6/9.
 */
@Service
public class MenuService {
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ManageMenu> findByUserCode(String code) {
        String sql="SELECT DISTINCT " +
                "  m.`code`, " +
                "  m.* " +
                "FROM " +
                "  manage_menu m, " +
                "  manage_role_menu rm " +
                "WHERE " +
                "  m.`code` = rm.menu_code and m.`status`=1 " +
                "AND rm.role_code IN ( " +
                "  SELECT " +
                "    r. CODE " +
                "  FROM " +
                "    manage_role r, " +
                "    manage_user_role ur " +
                "  WHERE " +
                "    r.`code` = ur.role_code " +
                "  AND ur.user_code = ? " +
                "  AND r.`status` = 1 " +
                ")";
        List<ManageMenu> mr = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ManageMenu.class), code);
        return mr;
    }

    public List<ManageMenu> findParentMenus(String usercode) {
        String sql = "SELECT * FROM (SELECT DISTINCT  m.* FROM manage_menu m,manage_role_menu rm WHERE m.code = rm.menu_code and m.status=1 AND rm.role_code IN (SELECT r.CODE FROM manage_role r, manage_user_role ur WHERE r.code = ur.role_code AND ur.user_code = ?   AND r.status = 1 ) ORDER BY m.sort asc ) A WHERE A.parent_code = 0";
        List<ManageMenu> mr = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ManageMenu.class), usercode);
        return mr;
    }

    public List<ManageMenu> findChildMenus(String usercode, String code) {
        String sql = "SELECT * FROM (SELECT DISTINCT  m.* FROM manage_menu m,manage_role_menu rm WHERE m.code = rm.menu_code and m.status=1 AND rm.role_code IN (SELECT r.CODE FROM manage_role r, manage_user_role ur WHERE r.code = ur.role_code AND ur.user_code = ?   AND r.status = 1 ) ORDER BY m.sort asc) A WHERE A.parent_code = ?  ";
        List<ManageMenu> mr = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ManageMenu.class), usercode,code);
        return mr;
    }
}


