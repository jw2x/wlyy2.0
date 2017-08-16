package com.yihu.jw.manage.service.system;

import com.yihu.jw.manage.dao.system.MenuDao;
import com.yihu.jw.manage.model.system.ManageMenu;
import com.yihu.jw.manage.model.system.ManageUser;
import com.yihu.jw.manage.model.system.MenuItems;
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
public class MenuService {
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private ManageMenuUrlService menuUrlService;

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
        String sql = "SELECT * FROM (SELECT DISTINCT  m.* FROM manage_menu m,manage_role_menu rm WHERE m.code = rm.menu_code and m.status=1 AND rm.role_code IN (SELECT r.CODE FROM manage_role r, manage_user_role ur WHERE r.code = ur.role_code AND ur.user_code = ?   AND r.status = 1 ) ORDER BY m.sort asc ) A WHERE A.parent_code = '0'";
        List<ManageMenu> mr = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ManageMenu.class), usercode);
        return mr;
    }

    public List<ManageMenu> findChildMenus(String usercode, String code) {
        String sql = "SELECT * FROM (SELECT DISTINCT  m.* FROM manage_menu m,manage_role_menu rm WHERE m.code = rm.menu_code and m.status=1 AND rm.role_code IN (SELECT r.CODE FROM manage_role r, manage_user_role ur WHERE r.code = ur.role_code AND ur.user_code = ?   AND r.status = 1 ) ORDER BY m.sort asc) A WHERE A.parent_code = ?  ";
        List<ManageMenu> mr = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ManageMenu.class), usercode,code);
        return mr;
    }

    public Map<String, List> getMenuTree() throws ManageException {
        List<MenuItems> menuItemses = new ArrayList<>();
        Map<String, List> data = new HashMap<>();
        //查询所有模块
        List<ManageMenu> parentMenus = getParentMenus();
        if(parentMenus!=null){
            for(ManageMenu parentMenu:parentMenus){
                //查询所有菜单
                List<ManageMenu> childMenus = getChildMenus(parentMenu.getCode());
                MenuItems menuItem = new MenuItems();
                menuItem.setParentMenu(parentMenu);
                menuItem.setChildMenus(childMenus);
                menuItemses.add(menuItem);
            }
        }
        data.put("menus", menuItemses);
        return data;
    }

    public List<ManageMenu> list(Integer size, Integer page,Map<String,String> map) {
        // 排序
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        // 分页信息
        PageRequest pageRequest = new PageRequest(page, size, sort);
        // 设置查询条件
        Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
        String name = map.get("name");
        if (!StringUtils.isEmpty(name)) {
            filters.put("name", new SearchFilter("name", SearchFilter.Operator.LIKE, name));
        }
        //首先查找父菜单
        filters.put("type",new SearchFilter("type", SearchFilter.Operator.EQ, 1));

        // 未作废
        filters.put("status", new SearchFilter("status", SearchFilter.Operator.EQ, WlyyContant.status_normal));
        Specification<ManageMenu> spec = DynamicSpecifications.bySearchFilter(filters.values(), ManageMenu.class);
        Page<ManageMenu> manageMenus = menuDao.findAll(spec, pageRequest);
        List<ManageMenu> parentMenus = manageMenus.getContent();
        for(ManageMenu parentMenu: parentMenus){
            List<ManageMenu> childMenus = getChildMenus(parentMenu.getCode());//查找子菜单
            if(childMenus.size()>0){
                parentMenu.setState("closed");
            }else{
                parentMenu.setState("open");
            }
            for(ManageMenu childMenu:childMenus){
                List<ManageMenu> funcMenus = getChildMenus(childMenu.getCode());//查找功能
                if(funcMenus.size()>0){
                    childMenu.setState("closed");
                }else{
                    childMenu.setState("open");
                }
                childMenu.setChildren(funcMenus);
            }
            parentMenu.setChildren(childMenus);
        }
        return parentMenus;
    }

    public ManageMenu findByCode(String code) {
        ManageMenu menu = menuDao.findByCode(code);
        List<Map<String, Object>> req = menuUrlService.getUrlByMenuCode(menu.getCode());
        menu.setReq(req);
        return menuDao.findByCode(code);
    }

    public List<ManageMenu> getChildMenus(String parentCode) {
       return menuDao.getChildMenus(parentCode);
    }

    public List<ManageMenu> getParentMenus() {
        return menuDao.getParentMenus();
    }

    public void delete(String codes,String userCode) {
        ManageUser user = userService.findByCode(userCode);
        String userName = user.getName();
        for(String code:codes.split(",")){
            ManageMenu menu = menuDao.findByCode(code);
            if (menu == null) {
                continue;
            }
            if ("0".equals(menu.getParentCode())) {//如果为0,说明是为父菜单
                List<ManageMenu> childMenus = getChildMenus(menu.getCode());
                if (childMenus != null) {
                    for (ManageMenu childMenu : childMenus) {
                        childMenu.setStatus(-1);
                        childMenu.setUpdateUser(userCode);
                        childMenu.setUpdateUserName(userName);
                        menuDao.save(childMenu);
                    }
                }
            }
            menu.setStatus(-1);
            menu.setUpdateUser(userCode);
            menu.setUpdateUserName(userName);
            menuDao.save(menu);
        }
    }

    @Transactional
    public void saveOrUpdate(ManageMenu menu,String userCode) {
        ManageUser user = userService.findByCode(userCode);
        String userName = user.getName();
        if(null==menu.getId()){
            String code = UUID.randomUUID().toString().replaceAll("-","");
            menu.setCode(code);
            menu.setCreateUser(userCode);
            menu.setCreateUserName(userName);
        }
        menu.setUpdateUser(userCode);
        menu.setUpdateUserName(userName);
        menuDao.save(menu);//保存或更新之后,同步保存manage_menu_url
        List<String> urls = menu.getUrl();
        List<String> methods = menu.getMethod();
        String menuCode = menu.getCode();
        menuUrlService.saveOrUpdate(menuCode,urls,methods);
    }

    public List<ManageMenu> list(){
        List<ManageMenu> parentMenus = getParentMenus();//查找模块/父菜单
        for(ManageMenu parentMenu: parentMenus){
            List<ManageMenu> childMenus = getChildMenus(parentMenu.getCode());//查找子菜单
            for(ManageMenu childMenu:childMenus){
                List<ManageMenu> funcMenus = getChildMenus(childMenu.getCode());//查找功能
                childMenu.setChildren(funcMenus);
            }
            parentMenu.setChildren(childMenus);
        }
        return parentMenus;
    }

}


