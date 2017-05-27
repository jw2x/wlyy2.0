package com.yihu.jw.restmodel.wx;

/**
 * Created by Administrator on 2017/5/20 0020.
 */
public class WxContants {
    //微信token模块常量
    public static class WxAccessToken{
        public static final String message_success_get="wxAccessToken get success";

        public static final String api_common="wxAccessToken";
        public static final String api_create="create";
        public static final String api_get="get";
    }

    //微信按钮模块常量
    public static class WxMenu{
        public static final String api_common="wxMenu";
        public static final String api_create="create";
        public static final String api_delete="delete";
        public static final String api_getByCode="getByCode";
        public static final String api_update="update";
        public static final String api_getWxMenus="getWxMenus";
        public static final String api_getWxMenuNoPage="getWxMenuNoPage";
        public static final String api_createMenu="createMenu";

        public static final String message_fail_wechatCode_is_null="wechatCode is null";
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

        public static final String message_success_update="wxMenu update success";
        public static final String message_success_delete="wxMenu delete success";
        public static final String message_success_find="wxMenu find success";
        public static final String message_success_create="wxMenu create success";
        public static final String message_success_find_functions="wxMenu find success";

    }
    //微信模板消息
    public static class WxTemplate {
        public static final String api_common="wxTemplate";
        public static final String api_create="create";
        public static final String api_update="update";
        public static final String api_delete="delete";
        public static final String api_getByCode="getByCode";
        public static final String api_getWxTemplates="getWxTemplates";
        public static final String api_getWxTemplatesNoPage="getWxTemplatesNoPage";
        public static final String api_sendTemplateMessage="sendTemplateMessage";

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
    }
    //微信配置表
    public static class Wechat {
        public static final String api_common="wechat";
        public static final String api_create="create";
        public static final String api_update="update";
        public static final String api_delete="delete";
        public static final String api_getWechatNoPage="getWechatNoPage";
        public static final String api_getWechats="getWechats";
        public static final String api_getByCode="getByCode";

        public static final String message_success_create="wechat create success";
        public static final String message_success_update="wechat update success";
        public static final String message_success_find="wechat find success";
        public static final String message_success_find_functions="wechat find success";
        public static final String message_success_delete="wechat delete success";

        public static final String message_fail_code_is_null="code is null";
        public static final String message_fail_code_no_exist="code no exist";
        public static final String message_fail_appSecret_is_null="appSecret is null";
        public static final String message_fail_appId_exist="wechat appId exist";
        public static final String message_fail_status_is_null="status is null";
        public static final String message_fail_type_is_null="type is null";
        public static final String message_fail_appId_is_null="appId is null";
        public static final String message_fail_name_is_null="name is null";
        public static final String message_fail_wxWechat_is_no_exist="wxWechat is no exist";//微信配置不存在
    }
    //微信图文消息
    public static class WxGraphicMessage {
        public static final String api_common="wxGraphicMessage";
        public static final String api_create="create";
        public static final String api_update="update";
        public static final String api_delete="delete";
        public static final String api_getWxGraphicMessageNoPage="getWxGraphicMessageNoPage";
        public static final String api_sendGraphicMessages="sendGraphicMessages";

        public static final String api_getWxGraphicMessages="getWxGraphicMessages";
        public static final String api_getByCode="getByCode";
        public static final String message_success_create="wxGraphicMessage create success";
        public static final String message_success_update="wxGraphicMessage update success";
        public static final String message_success_find="wxGraphicMessage find success";

        public static final String message_success_find_functions="wxGraphicMessage find success";
        public static final String message_success_delete="wxGraphicMessage delete success";
        public static final String message_fail_code_is_null="code is null";
        public static final String message_fail_code_no_exist="code no exist";
        public static final String message_fail_code_exist="code exist";
        public static final String message_fail_status_is_null="status is null";
        public static final String message_fail_title_is_null="title is null";
    }
}
