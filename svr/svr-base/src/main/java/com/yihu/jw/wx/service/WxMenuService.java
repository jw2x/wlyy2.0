package com.yihu.jw.wx.service;

import com.yihu.jw.mysql.query.BaseJpaService;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.restmodel.wx.WxContants;
import com.yihu.jw.util.HttpUtil;
import com.yihu.jw.wx.dao.WxMenuDao;
import com.yihu.jw.wx.model.WxAccessToken;
import com.yihu.jw.wx.model.WxMenu;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/19 0019.
 */
@Service
public class WxMenuService extends BaseJpaService<WxMenu, WxMenuDao> {

    @Autowired
    private WxMenuDao wxMenuDao;

    @Autowired
    private WxAccessTokenService wxAccessTokenService;

    /**
     * 将菜单保存至数据库(微信未创建)
     * @param wxMenu
     * @return
     */
    public WxMenu createWxMenu(WxMenu wxMenu) {
        if(canSaveOrUpata(wxMenu)){
            return wxMenuDao.save(wxMenu);
        }
        return null;
    }

    @Transient
    public WxMenu updateWxMenu(WxMenu wxMenu) {
        if(canSaveOrUpata(wxMenu)){
            return wxMenuDao.save(wxMenu);
        }
        return null;
    }

    @Transient
    public void deleteWxMenu(String code) {
        WxMenu wxMenu = wxMenuDao.findByCode(code);
        if (wxMenu == null) {
            throw new ApiException(WxContants.WxMenu.message_fail_code_no_exist, CommonContants.common_error_params_code);
        }
        String supMenucode = wxMenu.getSupMenucode();
        if(StringUtils.isEmpty(supMenucode)) {//如果是空,则为父菜单
            List<WxMenu> childMenus = findChildMenus(wxMenu.getWechatCode(), wxMenu.getSupMenucode());
            if(childMenus!=null){
                for(WxMenu wxmenu:childMenus){
                    wxmenu.setStatus(-1);
                    wxMenuDao.save(wxmenu);
                }
            }
        }
        wxMenu.setStatus(-1);
        wxMenuDao.save(wxMenu);
    }

    public WxMenu findByCode(String code) {
        return wxMenuDao.findByCode(code);
    }

    /**
     * 根据wechatCode查找所有菜单
     * @param wechatCode
     * @return
     */
    public List<WxMenu> findByWechatCode(String wechatCode){
        return wxMenuDao.findByWechatCode(wechatCode);
    }

    /**
     * 根据wechatCode在微信公众号创建菜单
     * @param wechatCode
     * @return JSONObject
     */
    public JSONObject createWechatMenu(String wechatCode) {
        //首先根据wechatCode获取菜单,然后封装成json字符串
        List<WxMenu> menus = wxMenuDao.findByWechatCode(wechatCode);
        String menuJsonString = getMenuToString(menus, wechatCode);
        WxAccessToken wxAccessTokenByCode = wxAccessTokenService.getWxAccessTokenByCode(wechatCode);
        String token = wxAccessTokenByCode.getAccessToken();
        // 请求微信接口创建菜单
        String url = " https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + token;
        String jsonStr = HttpUtil.sendPost(url, menuJsonString);
        JSONObject result = new JSONObject(jsonStr);
        return result;
    }

    private String getMenuToString(List<WxMenu> menus,String wechatCode){
        String menuJsonStr = "";
        List<WxMenu> parentMenus = new ArrayList<WxMenu>();//存储父菜单
        if(menus!=null){
            menuJsonStr = "{\"button\":[{";
            for(WxMenu wxMenu:menus){
                if(StringUtils.isEmpty(wxMenu.getSupMenucode())){//说明是父菜单
                    parentMenus.add(wxMenu);
                }
            }
        }
        int j = 0;
        for(WxMenu menu:parentMenus){//遍历父菜单
            if(j==0){
                j++;
            }else{
                menuJsonStr += ",{";
            }
            menuJsonStr = getString(menuJsonStr, menu);
            //查找是否有子菜单
            List<WxMenu> childMenus = findChildMenus(wechatCode, menu.getCode());
            if(childMenus!=null){
                int i =0;
                menuJsonStr += ",\"sub_button\":[{";
                for(WxMenu childMenu:childMenus){
                    if(i==0){
                        i++;
                    }else{
                        menuJsonStr += ",{";
                    }
                    menuJsonStr = getString(menuJsonStr, childMenu);
                    menuJsonStr += "}";
                }
                menuJsonStr += "]}";
            }
        }
        menuJsonStr += "]}";
        return menuJsonStr;
    }

    private String getString(String menuJsonStr, WxMenu menu) {
        menuJsonStr += "\"name\":\""+ menu.getName()+"\"";
        if(!StringUtils.isEmpty(menu.getType())){
            menuJsonStr += ",\"type\":\"" + menu.getType()+"\"";
        }
        if(!StringUtils.isEmpty(menu.getKey())){
            menuJsonStr += ",\"key\":\"" + menu.getKey()+"\"";
        }
        if(!StringUtils.isEmpty(menu.getUrl())){
            menuJsonStr += ",\"url\":\"" + menu.getUrl()+"\"";
        }
        if(!StringUtils.isEmpty(menu.getMediaId())){
            menuJsonStr += ",\"media_id\":\"" + menu.getMediaId()+"\"";
        }
        if(!StringUtils.isEmpty(menu.getAppid())){
            menuJsonStr += ",\"appid\":\"" + menu.getAppid()+"\"";
        }
        if(!StringUtils.isEmpty(menu.getPagepath())){
            menuJsonStr += ",\"pagepath\":\"" + menu.getPagepath()+"\"";
        }
        return menuJsonStr;
    }

    /**
     * 查找子菜单
     * @param wechatCode
     * @param sup_menucode
     * @return
     */
    public List<WxMenu> findChildMenus(String wechatCode,String sup_menucode ){
        return wxMenuDao.findChildMenus(wechatCode,sup_menucode);
    }

    private boolean canSaveOrUpata(WxMenu wxMenu){
        if (StringUtils.isEmpty(wxMenu.getCode())) {
            throw new ApiException(WxContants.WxMenu.message_fail_code_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wxMenu.getWechatCode())) {
            throw new ApiException(WxContants.WxMenu.message_fail_wechatCode_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wxMenu.getName())) {
            throw new ApiException(WxContants.WxMenu.message_fail_name_is_null, CommonContants.common_error_params_code);
        }
        if(StringUtils.isEmpty(wxMenu.getType())){
            throw new ApiException(WxContants.WxMenu.message_fail_type_is_null, CommonContants.common_error_params_code);
        }
        if("click".equals(wxMenu.getType().toUpperCase())){
            String key = wxMenu.getKey();
            if(StringUtils.isEmpty(key)){
                throw new ApiException(WxContants.WxMenu.message_fail_wxMenuKey_is_null, CommonContants.common_error_params_code);
            }
            if(key.length()>128){
                throw new ApiException(WxContants.WxMenu.message_fail_wxMenuKey_is_toLong, CommonContants.common_error_params_code);
            }
        }
        if(StringUtils.isEmpty(wxMenu.getSort())){
            throw new ApiException(WxContants.WxMenu.message_fail_sort_is_null, CommonContants.common_error_params_code);
        }
        WxMenu wxMenuTemp = null;
        if(StringUtils.isEmpty(wxMenu.getSupMenucode())){//如果是空,则为父菜单
            //判断sort是否重复
            wxMenuTemp = wxMenuDao.findByWechatCodeExcludeSortFromParent(wxMenu.getWechatCode(), wxMenu.getSort(),wxMenu.getCode());
        }else{//子菜单
            wxMenuTemp = wxMenuDao.findByWechatCodeExcludeSortFromChild(wxMenu.getWechatCode(), wxMenu.getSort(), wxMenu.getSupMenucode(), wxMenu.getCode());
        }
        if(null!=wxMenuTemp){
            throw new ApiException(WxContants.WxMenu.message_fail_sort_is_repeat, CommonContants.common_error_params_code);
        }
        return true;
    }
}
