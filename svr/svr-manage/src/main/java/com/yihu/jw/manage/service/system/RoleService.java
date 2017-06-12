package com.yihu.jw.manage.service.system;

import com.yihu.jw.manage.dao.system.RoleDao;
import com.yihu.jw.manage.dao.system.RoleMenuDao;
import com.yihu.jw.manage.model.system.ManageRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenweida on 2017/6/9.
 */
@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RoleMenuDao roleMenuDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ManageRole> findByUserCode(String code) {
        String sql = " select r.* from manage_role r,manage_user_role ur where r.`code`=ur.role_code and ur.user_code=? ";
        List<ManageRole> mr = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ManageRole.class), code);
        return mr;
    }
}
