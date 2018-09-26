package com.yihu.jw.base.service.role;

import com.yihu.jw.base.dao.role.MenuDao;
import com.yihu.jw.entity.base.role.MenuDO;
import com.yihu.mysql.query.BaseJpaService;
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
    @Transactional
    public void updateStatus(String id,Integer status){
        menuDao.updateStatus(id,status);
    }
}
