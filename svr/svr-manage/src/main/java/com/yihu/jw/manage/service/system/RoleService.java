package com.yihu.jw.manage.service.system;

import com.yihu.jw.manage.dao.system.RoleDao;
import com.yihu.jw.manage.dao.system.RoleMenuDao;
import com.yihu.jw.manage.model.system.ManageRole;
import com.yihu.jw.manage.model.system.ManageUser;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.exception.business.ManageException;
import com.yihu.jw.restmodel.wlyy.WlyyContant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import javax.transaction.Transactional;
import java.util.*;

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
    @Autowired
    private UserService userService;

    public List<ManageRole> findByUserCode(String code) {
        String sql = " select r.* from manage_role r,manage_user_role ur where r.`code`=ur.role_code and ur.user_code=? ";
        List<ManageRole> mr = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ManageRole.class), code);
        return mr;
    }

    public Page<ManageRole> list(String name, Integer page, Integer pageSize) {
        // 排序
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        // 分页信息
        PageRequest pageRequest = new PageRequest(page, pageSize, sort);
        // 设置查询条件
        Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
        // 用户名称
        if (!StringUtils.isEmpty(name)&&!("null".equals(name))) {
            filters.put("name", new SearchFilter("name", SearchFilter.Operator.LIKE, name));
        }
        // 未作废
        filters.put("status", new SearchFilter("status", SearchFilter.Operator.EQ, WlyyContant.status_normal));
        Specification<ManageRole> spec = DynamicSpecifications.bySearchFilter(filters.values(), ManageRole.class);

        return roleDao.findAll(spec, pageRequest);
    }

    @Transactional
    public void delete(String codes,String userCode) {
        ManageUser user = userService.findByCode(userCode);
        String userName = user.getName();
        for(String code:codes.split(",")){
            ManageRole role = roleDao.findByCode(code);
            role.setUpdateUser(userCode);
            role.setUpdateUserName(userName);
            role.setStatus(-1);
            roleDao.save(role);
        }
    }

    public Envelop saveOrUpdate(ManageRole role, String userCode) throws ManageException {
        ManageUser user = userService.findByCode(userCode);
        String userName = user.getName();
        role.setUpdateUserName(userName);
        role.setUpdateUser(userCode);
        if(role.getId()==null){//保存
            String code = UUID.randomUUID().toString().replaceAll("-","");
            role.setCode(code);
            role.setCreateUser(userCode);
            role.setCreateUserName(userName);
            roleDao.save(role);
            return Envelop.getSuccess("保存成功",role);
        }else{//修改
            ManageRole role1 = findByCode(role.getCode());
            //role.set
            roleDao.save(role);
            return Envelop.getSuccess("修改成功",role);
        }
    }

    public ManageRole findByCode(String code) {
        return roleDao.findByCode(code);
    }
}
