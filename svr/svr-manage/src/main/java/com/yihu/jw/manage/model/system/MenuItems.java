package com.yihu.jw.manage.model.system;

import java.util.List;

/**
 * Created by Administrator on 2017/6/10 0010.
 *
 *  该model用于vue页面展示菜单
 */
public class MenuItems {
    private ManageMenu parentMenu;//父菜单

    private List<ManageMenu> childMenus;//子菜单


    public ManageMenu getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(ManageMenu parentMenu) {
        this.parentMenu = parentMenu;
    }


    public void setChildMenus(List<ManageMenu> childMenus) {
        this.childMenus = childMenus;
    }

    public List<ManageMenu> getChildMenus() {
        return childMenus;
    }
}
