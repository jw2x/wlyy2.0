package com.yihu.jw.rm.wx;

/**
 * Created by Administrator on 2017/5/20 0020.
 */
public class WechatRequestMapping {

    public static final String api_common="/wechat";


    //微信token模块常量
    public static class WxAccessToken{
        public static final String api_create="accessToken";
        public static final String api_get="accessToken";


        public static final String message_success_get="wxAccessToken get success";

    }

    //微信按钮模块常量
    public static class WxMenu{
        public static final String api_create="/menu";
        public static final String api_update="/menu";
        public static final String api_delete="/menu/{codes}/{userCode}";
        public static final String api_getWxMenuNoPage="/menu/listNoPage";
        public static final String api_getWxMenus="/menu/list";
        public static final String api_getByCode="/menu/{code}";
        public static final String api_getParentMenu="/parentMenu/{wechatCode}";
        public static final String api_createMenu="/createMenu";
        public static final String api_getChildMenus="/childMenu/list/{parentCode}";

        public static final String message_fail_code_is_null="code is null";
        public static final String message_fail_name_is_null="name is null";
        public static final String message_fail_type_is_null="type is null";
        public static final String message_fail_key_is_null="key is null";
        public static final String message_fail_key_is_toLong="key is to long";
        public static final String message_fail_url_is_null="url is null";
        public static final String message_fail_appid_is_null="appid is null";
        public static final String message_fail_pagepath_is_null="pagepath is null";
        public static final String message_fail_url_is_toLong="url is to long";
        public static final String message_fail_sort_is_null="sort is null";
        public static final String message_fail_sort_is_repeat="sort is repeat";
        public static final String message_fail_code_no_exist="code no exist";
        public static final String message_fail_supMenuCode_is_no_exist="supMenuCode is no exist";
        public static final String message_fail_mediaId_is_null="mediaId is null";
        public static final String message_fail_childMenu_is_to_much="childMenu is to much";//子菜单数目过多
        public static final String message_fail_parentMenu_is_to_much="parentMenu is to much";//子菜单数目过多
        public static final String message_fail_name_is_to_long="name is to long";
        public static final String message_fail_status_is_null="status is null";
        public static final String message_fail_WxMenu_is_no_exist="menu is no exist";//微信菜单未配置

        public static final String message_success_update="wxMenu update success";
        public static final String message_success_delete="wxMenu delete success";
        public static final String message_success_find="wxMenu find success";
        public static final String message_success_create="wxMenu create success";
        public static final String message_success_find_functions="wxMenu find success";

    }
    //微信模板消息
    public static class WxTemplate {
        public static final String api_create="/template";
        public static final String api_update="/template";
        public static final String api_delete="/template/{codes}";
        public static final String api_getWxTemplatesNoPage="/template/listNoPage";
        public static final String api_getWxTemplates="/template/list";
        public static final String api_getByCode="/template/{code}";
        public static final String api_sendTemplateMessage="/sendTemplateMessage";

        public static final String message_success_create="wxTemplate create success";
        public static final String message_success_update="wxTemplate update success";
        public static final String message_success_find="wxTemplate find success";
        public static final String message_success_find_functions="wxTemplate find success";
        public static final String message_success_delete="wxTemplate delete success";

        public static final String message_fail_code_is_null="code is null";
        public static final String message_fail_code_no_exist="code no exist";
        public static final String message_fail_wechatCode_is_null="wechatCode is null";
        public static final String message_fail_templateid_is_null="templateid is null";
        public static final String message_fail_content_is_null="content is null";
        public static final String message_fail_content_format_is_not_right="content format is not right";
        public static final String message_fail_template_is_no_exist="template is no exist";
    }
    //微信配置表
    public static class WxConfig {

        public static final String api_create="/wechatConfig";
        public static final String api_update="/wechatConfig";
        public static final String api_delete="/wechatConfig/{codes}";
        public static final String api_getWechatNoPage="/wechatConfig/listNoPage";
        public static final String api_getWechats="/wechatConfig/list";
        public static final String api_getById="/wechatConfig/{id}";

        public static final String message_success_create="wechat create success";
        public static final String message_success_update="wechat update success";
        public static final String message_success_find="wechat find success";
        public static final String message_success_find_functions="wechat find success";
        public static final String message_success_delete="wechat delete success";

        public static final String message_fail_code_is_null="code is null";
        public static final String message_fail_code_no_exist="code not exist";
        public static final String message_fail_appSecret_is_null="appSecret is null";
        public static final String message_fail_appId_exist="wechat appId exist";
        public static final String message_fail_status_is_null="status is null";
        public static final String message_fail_type_is_null="type is null";
        public static final String message_fail_appId_is_null="appId is null";
        public static final String message_fail_name_is_null="name is null";
        public static final String message_fail_wxWechat_is_no_exist="wxWechat is not exist";//微信配置不存在
        public static final String message_fail_id_is_null="id is null";
        public static final String message_fail_saasId_is_null="saasId is null";
    }
    //微信图文消息
    public static class WxGraphicMessage {
        public static final String api_create="/graphicMessage";
        public static final String api_update="/graphicMessage";
        public static final String api_delete="/graphicMessage/{codes}";
        public static final String api_getWxGraphicMessageNoPage="/graphicMessage/listNoPage";
        public static final String api_getWxGraphicMessages="/graphicMessage/list";
        public static final String api_getByCode="/graphicMessage/{code}";
        public static final String api_sendGraphicMessages="/sendGraphicMessages";

        public static final String message_success_create="wxGraphicMessage create success";
        public static final String message_success_update="wxGraphicMessage update success";
        public static final String message_success_find="wxGraphicMessage find success";

        public static final String message_success_find_functions="wxGraphicMessage find success";
        public static final String message_success_delete="wxGraphicMessage delete success";
        public static final String message_fail_code_is_null="code is null";
        public static final String message_fail_code_no_exist="code not exist";
        public static final String message_fail_code_exist="code exist";
        public static final String message_fail_status_is_null="status is null";
        public static final String message_fail_title_is_null="title is null";
        public static final String message_fail_wxGraphicMessage_is_no_exist="wxGraphicMessage is not exist";
    }
}
