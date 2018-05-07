//package com.yihu.jw.business.wx.service;
//
//import com.yihu.jw.base.wx.WxAccessTokenDO;
//import com.yihu.jw.base.wx.WxMenuDO;
//import com.yihu.jw.base.wx.WxWechatDO;
//import com.yihu.jw.business.wx.dao.WxMenuDao;
//import com.yihu.jw.exception.ApiException;
//import com.yihu.jw.exception.code.ExceptionCode;
//import com.yihu.base.mysql.query.BaseJpaService;
//import com.yihu.jw.rm.base.WechatRequestMapping;
//import com.yihu.jw.util.HttpUtil;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//
//import javax.persistence.Transient;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.UUID;
//
///**
// * Created by Administrator on 2017/5/19 0019.
// */
//@Service
//public class WxMenuService extends BaseJpaService<WxMenuDO, WxMenuDao> {
//
//    private Logger logger= LoggerFactory.getLogger(WxMenuService.class);
//
//    @Autowired
//    private WxMenuDao wxMenuDao;
//
//    @Autowired
//    private WechatService wechatService;
//
//    @Autowired
//    private WxAccessTokenService wxAccessTokenService;
//
//    /**
//     * 将菜单保存至数据库
//     * @param wxMenu
//     * @return
//     */
//    public WxMenuDO createWxMenu(WxMenuDO wxMenu) {
//        String code = UUID.randomUUID().toString().replaceAll("-", "");
//        wxMenu.setId(code);
//        if(canSaveOrUpata(wxMenu)){
//            return wxMenuDao.save(wxMenu);
//        }
//        return null;
//    }
//
//    @Transient
//    public WxMenuDO updateWxMenu(WxMenuDO wxMenu) {
//        if (StringUtils.isEmpty(wxMenu.getId())) {
//            throw new ApiException(WechatRequestMapping.WxMenu.message_fail_id_is_null, ExceptionCode.common_error_params_code);
//        }
//        if(canSaveOrUpata(wxMenu)){
//            WxMenuDO wxMenu1 = findById(wxMenu.getId());
//            if(wxMenu1==null){
//                throw new ApiException(WechatRequestMapping.WxMenu.message_fail_WxMenu_is_no_exist, ExceptionCode.common_error_params_code);
//            }
//            return wxMenuDao.save(wxMenu);
//        }
//        return null;
//    }
//
//    @Transient
//    public void deleteWxMenu(String ids, String userId, String userName) {
//        if(!StringUtils.isEmpty(ids)) {
//            String[] codeArray = ids.split(",");
//            for (String code : codeArray) {
//                WxMenuDO wxMenu = wxMenuDao.findById(code);
//                if (wxMenu == null) {
//                    continue;
//                }
//                String supMenucode = wxMenu.getSupMenuid();
//                if (StringUtils.isEmpty(supMenucode)) {//如果是空,则为父菜单
//                    List<WxMenuDO> childMenus = findChildMenus(wxMenu.getWechatId(), wxMenu.getId());
//                    if (childMenus != null) {
//                        for (WxMenuDO wxmenu : childMenus) {
//                            wxmenu.setStatus(-1);
//                            wxmenu.setUpdateUser(userId);
//                            wxmenu.setUpdateUserName(userName);
//                            wxMenuDao.save(wxmenu);
//                        }
//                    }
//                }
//                wxMenu.setStatus(-1);
//                wxMenu.setUpdateUser(userId);
//                wxMenu.setUpdateUserName(userName);
//                wxMenuDao.save(wxMenu);
//            }
//        }
//    }
//
//    @Transient
//    public void delete(String codes,String userCode) {
//        if(!StringUtils.isEmpty(codes)) {
//            String[] codeArray = codes.split(",");
//            for (String code : codeArray) {
//                WxMenuDO wxMenu = wxMenuDao.findById(code);
//                if (wxMenu == null) {
//                    continue;
//                }
//                String supMenucode = wxMenu.getSupMenuid();
//                if (StringUtils.isEmpty(supMenucode)) {//如果是空,则为父菜单
//                    List<WxMenuDO> childMenus = findChildMenus(wxMenu.getWechatId(), wxMenu.getId());
//                    if (childMenus != null) {
//                        for (WxMenuDO wxmenu : childMenus) {
//                            wxmenu.setStatus(-1);
//                            wxmenu.setUpdateUser(userCode);
//                            wxMenuDao.save(wxmenu);
//                        }
//                    }
//                }
//                wxMenu.setStatus(-1);
//                wxMenu.setUpdateUser(userCode);
//                wxMenuDao.save(wxMenu);
//            }
//        }
//    }
//
//    public WxMenuDO findById(String id) {
//        return wxMenuDao.findById(id);
//    }
//    /**
//     * 根据wechatCode查找所有父菜单
//     * @param wechatCode
//     * @return
//     */
//    public List<WxMenuDO> findParentMenuByWechatCode(String wechatCode){
//        List<WxMenuDO> parentMenus = wxMenuDao.findParentMenuByWechatId(wechatCode);
//        return parentMenus;
//    }
//
//    /**
//     * 根据wechatCode在微信公众号创建菜单
//     * @param wechatId
//     * @return JSONObject
//     */
//    public JSONObject createWechatMenu(String wechatId) throws JSONException {
//        WxWechatDO wechat = wechatService.findById(wechatId);
//        if(wechat==null){
//            throw new ApiException(WechatRequestMapping.WxConfig.message_fail_wxWechat_is_no_exist, ExceptionCode.common_error_params_code);
//        }
//        //首先根据wechatCode获取菜单,然后封装成json字符串
//        List<WxMenuDO> menus = wxMenuDao.findByWechatId(wechatId);
//        if(menus==null){
//            throw new ApiException(WechatRequestMapping.WxMenu.message_fail_WxMenu_is_no_exist, ExceptionCode.common_error_params_code);
//        }
//        String menuJsonString = getMenuToString(menus, wechatId);
//        logger.info("-----------------微信菜单json字符串:"+ menuJsonString+"--------------------");
//        WxAccessTokenDO wxAccessTokenByCode = wxAccessTokenService.getWxAccessTokenById(wechatId);
//        String token = wxAccessTokenByCode.getAccessToken();
//        // 请求微信接口创建菜单
//        String url = " https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + token;
//        String jsonStr = HttpUtil.sendPost(url, menuJsonString);
//        logger.info("------------------创建微信菜单,微信返回结果:"+jsonStr+"---------------------");
//        JSONObject result = new JSONObject(jsonStr);
//        return result;
//    }
//
//    private String getMenuToString(List<WxMenuDO> menus,String wechatCode){
//        String menuJsonStr = "";
//        List<WxMenuDO> parentMenus = new ArrayList<WxMenuDO>();//存储父菜单
//        if(menus!=null){
//            menuJsonStr = "{\"button\":[{";
//            for(WxMenuDO wxMenu:menus){
//                if(StringUtils.isEmpty(wxMenu.getSupMenuid())){//说明是父菜单
//                    parentMenus.add(wxMenu);
//                }
//            }
//        }
//        int j = 0;
//        for(WxMenuDO menu:parentMenus){//遍历父菜单
//            if(j==0){
//                j++;
//            }else{
//                menuJsonStr += ",{";
//            }
//            menuJsonStr = getString(menuJsonStr, menu);
//            //查找是否有子菜单
//            List<WxMenuDO> childMenus = findChildMenus(wechatCode, menu.getId());
//            if(childMenus!=null){
//                int i =0;
//                menuJsonStr += ",\"sub_button\":[{";
//                for(WxMenuDO childMenu:childMenus){
//                    if(i==0){
//                        i++;
//                    }else{
//                        menuJsonStr += ",{";
//                    }
//                    menuJsonStr = getString(menuJsonStr, childMenu);
//                    menuJsonStr += "}";
//                }
//                menuJsonStr += "]}";
//            }
//        }
//        menuJsonStr += "]}";
//        return menuJsonStr;
//    }
//
//    private String getString(String menuJsonStr, WxMenuDO menu) {
//        menuJsonStr += "\"name\":\""+ menu.getName()+"\"";
//        if(!StringUtils.isEmpty(menu.getType())){
//            menuJsonStr += ",\"type\":\"" + menu.getType()+"\"";
//        }
//        if(!StringUtils.isEmpty(menu.getMenuKey())){
//            menuJsonStr += ",\"key\":\"" + menu.getMenuKey()+"\"";
//        }
//        if(!StringUtils.isEmpty(menu.getUrl())){
//            menuJsonStr += ",\"url\":\"" + menu.getUrl()+"\"";
//        }
//        if(!StringUtils.isEmpty(menu.getMediaId())){
//            menuJsonStr += ",\"media_id\":\"" + menu.getMediaId()+"\"";
//        }
//        if(!StringUtils.isEmpty(menu.getAppid())){
//            menuJsonStr += ",\"appid\":\"" + menu.getAppid()+"\"";
//        }
//        if(!StringUtils.isEmpty(menu.getPagepath())){
//            menuJsonStr += ",\"pagepath\":\"" + menu.getPagepath()+"\"";
//        }
//        return menuJsonStr;
//    }
//
//    /**
//     * 查找子菜单
//     * @param wechatCode
//     * @param sup_menucode
//     * @return
//     */
//    public List<WxMenuDO> findChildMenus(String wechatCode,String sup_menucode ){
//        return wxMenuDao.findChildMenus(wechatCode,sup_menucode);
//    }
//
//    public List<WxMenuDO> findChildMenus(String parentCode ){
//        return wxMenuDao.findChildMenus(parentCode);
//    }
//
//    private boolean canSaveOrUpata(WxMenuDO wxMenu){
//        String wechatCode = wxMenu.getWechatId();
//        String supMenucode = wxMenu.getSupMenuid();
//        if(StringUtils.isEmpty(wechatCode)){
//            WxMenuDO parentMenuCode = findById(supMenucode);
//            wechatCode = parentMenuCode.getWechatId();
//            wxMenu.setWechatId(wechatCode);
//        }
//        String name = wxMenu.getName();
//        if (StringUtils.isEmpty(name)) {
//            throw new ApiException(WechatRequestMapping.WxMenu.message_fail_name_is_null, ExceptionCode.common_error_params_code);
//        }
//        if (StringUtils.isEmpty(wxMenu.getStatus())) {
//            throw new ApiException(WechatRequestMapping.WxMenu.message_fail_status_is_null, ExceptionCode.common_error_params_code);
//        }
//        if(StringUtils.isEmpty(wxMenu.getSort())){
//            throw new ApiException(WechatRequestMapping.WxMenu.message_fail_sort_is_null, ExceptionCode.common_error_params_code);
//        }
//        //根据wechatCode查找是否存在微信配置
//        WxWechatDO wxWechat = wechatService.findById(wechatCode);
//        if(wxWechat==null){
//            throw new ApiException(WechatRequestMapping.WxConfig.message_fail_wxWechat_is_no_exist, ExceptionCode.common_error_params_code);
//        }
//        if (!"0".equals(supMenucode)) {//不为0,说明是子菜单,判断父菜单是否存在
//            //说明是子菜单
//            //判断父菜单是否存在
//            WxMenuDO parentMenuCode = findById(supMenucode);
//            if(parentMenuCode==null){
//                throw new ApiException(WechatRequestMapping.WxMenu.message_fail_supMenuId_is_no_exist, ExceptionCode.common_error_params_code);
//            }
//
//            //查询已经存在的子菜单条数
//            List<WxMenuDO> childMenus = findChildMenus(wechatCode, supMenucode);
//            if(childMenus.size()==5){
//                throw new ApiException(WechatRequestMapping.WxMenu.message_fail_childMenu_is_to_much, ExceptionCode.common_error_params_code);
//            }
//            if(name.getBytes().length>60){
//                throw new ApiException(WechatRequestMapping.WxMenu.message_fail_name_is_to_long, ExceptionCode.common_error_params_code);
//            }
//            if(StringUtils.isEmpty(wxMenu.getType())){
//                throw new ApiException(WechatRequestMapping.WxMenu.message_fail_type_is_null, ExceptionCode.common_error_params_code);
//            }
//        }else{
//            //查找父菜单
//            List<WxMenuDO> parentMenus = findParentMenuByWechatCode(wechatCode);
//            if(parentMenus!=null){
//                if(parentMenus.size()==3){
//                    throw new ApiException(WechatRequestMapping.WxMenu.message_fail_parentMenu_is_to_much, ExceptionCode.common_error_params_code);
//                }
//            }
//
//            //说明是父菜单
//            if(name.getBytes().length>16){
//                throw new ApiException(WechatRequestMapping.WxMenu.message_fail_name_is_to_long, ExceptionCode.common_error_params_code);
//            }
//
//        }
//        String type1=wxMenu.getType();
//        if(type1==null){
//            type1="";
//        }
//        String type = type1.toUpperCase();
//        if("click".equals(type)){
//            String key = wxMenu.getMenuKey();
//            if(StringUtils.isEmpty(key)){
//                throw new ApiException(WechatRequestMapping.WxMenu.message_fail_key_is_null, ExceptionCode.common_error_params_code);
//            }
//            if(key.getBytes().length>128){
//                throw new ApiException(WechatRequestMapping.WxMenu.message_fail_key_is_toLong, ExceptionCode.common_error_params_code);
//            }
//        }
//        String url = wxMenu.getUrl();
//        if("view".equals(type)){
//            if(StringUtils.isEmpty(url)){
//                throw new ApiException(WechatRequestMapping.WxMenu.message_fail_url_is_null, ExceptionCode.common_error_params_code);
//            }
//            if(url.getBytes().length>128){
//                throw new ApiException(WechatRequestMapping.WxMenu.message_fail_url_is_toLong, ExceptionCode.common_error_params_code);
//            }
//        }
//        if("miniprogram".equals(type)){
//            if(StringUtils.isEmpty(url)){
//                throw new ApiException(WechatRequestMapping.WxMenu.message_fail_url_is_null, ExceptionCode.common_error_params_code);
//            }
//            if(url.getBytes().length>128){
//                throw new ApiException(WechatRequestMapping.WxMenu.message_fail_url_is_toLong, ExceptionCode.common_error_params_code);
//            }
//            if(StringUtils.isEmpty(wxMenu.getAppid())){
//                throw new ApiException(WechatRequestMapping.WxMenu.message_fail_appid_is_null, ExceptionCode.common_error_params_code);
//            }
//            if(StringUtils.isEmpty(wxMenu.getPagepath())){
//                throw new ApiException(WechatRequestMapping.WxMenu.message_fail_pagepath_is_null, ExceptionCode.common_error_params_code);
//            }
//        }
//        if("media_id".equals(type)||"view_limited".equals(type)){
//            if(StringUtils.isEmpty(wxMenu.getMediaId())){
//                throw new ApiException(WechatRequestMapping.WxMenu.message_fail_mediaId_is_null, ExceptionCode.common_error_params_code);
//            }
//        }
//        WxMenuDO wxMenuTemp = null;
//        if(StringUtils.isEmpty(wxMenu.getSupMenuid())){//如果是空,则为父菜单
//            //判断sort是否重复
//            wxMenuTemp = wxMenuDao.findByWechatIdExcludeSortFromParent(wxMenu.getWechatId(), wxMenu.getSort(),wxMenu.getId());
//        }else{//子菜单
//            wxMenuTemp = wxMenuDao.findByWechatIdExcludeSortFromChild(wxMenu.getWechatId(), wxMenu.getSort(), wxMenu.getSupMenuid(), wxMenu.getId());
//        }
//        if(null!=wxMenuTemp){
//            throw new ApiException(WechatRequestMapping.WxMenu.message_fail_sort_is_repeat, ExceptionCode.common_error_params_code);
//        }
//        wxMenu.setUpdateTime(new Date());
//        return true;
//    }
//}
