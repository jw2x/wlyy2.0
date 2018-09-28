package com.yihu.jw.base.service.role;

import com.yihu.jw.base.contant.MenuContant;
import com.yihu.jw.base.dao.role.MenuDao;
import com.yihu.jw.entity.base.role.MenuDO;
import com.yihu.mysql.query.BaseJpaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 菜单
 * @author yeshijie on 2018/9/26.
 */
@Service
public class MenuService extends BaseJpaService<MenuDO, MenuDao> {



    @Autowired
    private MenuDao menuDao;

    /**
     * 设置生效和失效
     * @param id
     * @param status
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(String id,Integer status){
        menuDao.updateStatus(id,status);
    }

    /**
     * 新增菜单
     * @param menuDO
     * @return
     */
    public MenuDO addMenu(MenuDO menuDO){
        if(StringUtils.isBlank(menuDO.getParentId())){
            menuDO.setParentId(MenuContant.DEFAULT_PARENTID);
        }
        int sort = menuDao.countMenuByParentId(menuDO.getParentId())+1;
        menuDO.setSort(sort);
        menuDao.save(menuDO);
        return menuDO;
    }

    /**
     * 上移
     * @param menuId
     */
    @Transactional(rollbackFor = Exception.class)
    public void moveUp(String menuId){
        MenuDO menuDO = menuDao.findOne(menuId);
        int sort = menuDO.getSort();
        if(sort > 1){
            sort = sort-1;
            menuDao.addSort(menuDO.getParentId(),sort);
            menuDO.setSort(sort);
            menuDao.save(menuDO);
        }
    }

    /**
     * 下移
     * @param menuId
     */
    @Transactional(rollbackFor = Exception.class)
    public void moveDown(String menuId){
        MenuDO menuDO = menuDao.findOne(menuId);
        int sort = menuDO.getSort();
        int count = menuDao.countMenuByParentId(menuDO.getParentId());
        if(sort < count){
            sort = sort+1;
            menuDao.subSort(menuDO.getParentId(),sort);
            menuDO.setSort(sort);
            menuDao.save(menuDO);
        }
    }

    /**
     * 名称是否存在
     * @param name
     * @return
     */
    public int isExistName(String name){
        return menuDao.isExistName(name);
    }
}
