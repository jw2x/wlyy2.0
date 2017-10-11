package com.yihu.jw.manage.service.system;

import com.yihu.jw.manage.adapter.CacheAdapter;
import com.yihu.jw.manage.adapter.cache.MapCache;
import com.yihu.jw.manage.dao.system.RoleMenuDao;
import com.yihu.jw.manage.model.system.ManageRoleMenu;
import com.yihu.jw.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/6/27 0027.
 */
@Service
public class MenuRoleService extends BaseJpaService<ManageRoleMenu,RoleMenuDao> {

    @Autowired
    private RoleMenuDao roleMenuDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private CacheAdapter cacheAdapter;
    @Autowired
    private MapCache mapCache;

    @Transactional
    public void changeMenuRole(String roleCode, String menuCodes) {
        //查找目前有的权限
        List<String> oldMenuCodes = getMenuRoles(roleCode);

        //新权限构建成list
        List<String> newMenuCodes = new ArrayList<>();
        for(String menuCode:menuCodes.split(",")){
            newMenuCodes.add(menuCode);
        }

        List<String> _oldMenuCodes = new ArrayList<String>(oldMenuCodes);//构建oldMenuCodes的副本
        _oldMenuCodes.removeAll(newMenuCodes);// 去除相同元素--->>需要删除的权限
        List<String> _newMenuCodes = new ArrayList<String>(newMenuCodes);//构建newMenuCodes的副本
        _newMenuCodes.removeAll(oldMenuCodes);// 去除相同元素---->>需要添加 的权限

        //先删除权限在添加...
        for(String delMenuCode:_oldMenuCodes){
            roleMenuDao.delete(delMenuCode, roleCode);
        }
        for(String addMenuCode:_newMenuCodes){
            ManageRoleMenu menuRole = new ManageRoleMenu();
            menuRole.setRoleCode(roleCode);
            menuRole.setMenuCode(addMenuCode);
            roleMenuDao.save(menuRole);
        }
    }

    public List<String> getMenuRoles(String roleCode){
        List<ManageRoleMenu> menuRoles = roleMenuDao.findByRoleCode(roleCode);
        List<String> list = new ArrayList<>();
        for(ManageRoleMenu menuRole:menuRoles){
            list.add(menuRole.getMenuCode());
        }
        return list;
    }

    /**
     * 通过userCode查找功能权限
     * @param userCode
     * @return
     */
    public  List<Map<String, Object>> findByUserCode(String userCode) {
        //只对功能权限进行查询
        String sql = "select DISTINCT mmu.url,mmu.method from manage_menu mm LEFT JOIN manage_role_menu mrm on mrm.menu_code = mm.code LEFT JOIN manage_user_role mur on mur.role_code = mrm.role_code LEFT JOIN manage_menu_url mmu on mmu.menu_code = mm.code where mur.user_code=? and mmu.url is not null";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, userCode);
        return maps;
    }

    public void reloadPrivilege(){
        Set<String> keys = cacheAdapter.keys(CacheAdapter.ROLE, ".*");//keys即为code的
        for (String key:keys){
            List<Map<String, Object>> maps = findByUserCode(key);
            cacheAdapter.setData(CacheAdapter.ROLE,key,maps);
        }
    }
}
