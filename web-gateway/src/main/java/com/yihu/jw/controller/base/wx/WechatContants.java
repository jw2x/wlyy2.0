package com.yihu.jw.controller.base.wx;

/**
 * Created by Administrator on 2017/5/20 0020.
 */
public class WechatContants {
    //微信token模块常量
    public static class AccessToken{
        public static final String message_success_get="wxAccessToken get success";

        public static final String api_common="accessToken";
        public static final String api_create="create";
        public static final String api_get="get";
    }

    //微信按钮模块常量
    public static class Menu{
        public static final String api_common="menu";
        public static final String api_create="create";
        public static final String api_delete="delete";
        public static final String api_getByCode="getByCode";
        public static final String api_update="update";
        public static final String api_getWxMenus="getWxMenus";
        public static final String api_getWxMenuNoPage="getWxMenuNoPage";
        public static final String api_createMenu="createMenu";

    }
    //微信模板消息
    public static class Template {
        public static final String api_common="template";
        public static final String api_create="create";
        public static final String api_update="update";
        public static final String api_delete="delete";
        public static final String api_getByCode="getByCode";
        public static final String api_getWxTemplates="getWxTemplates";
        public static final String api_getWxTemplatesNoPage="getWxTemplatesNoPage";
        public static final String api_sendTemplateMessage="sendTemplateMessage";
    }
    //微信配置表
    public static class Config {
        public static final String api_common="wechatConfig";
        public static final String api_create="create";
        public static final String api_update="update";
        public static final String api_delete="delete";
        public static final String api_getWechatNoPage="getWechatNoPage";
        public static final String api_getWechats="getWechats";
        public static final String api_getByCode="getByCode";
    }
    //微信图文消息
    public static class GraphicMessage {
        public static final String api_common="graphicMessage";
        public static final String api_create="create";
        public static final String api_update="update";
        public static final String api_delete="delete";
        public static final String api_getWxGraphicMessageNoPage="getWxGraphicMessageNoPage";
        public static final String api_sendGraphicMessages="sendGraphicMessages";
        public static final String api_getWxGraphicMessages="getWxGraphicMessages";
        public static final String api_getByCode="getByCode";

    }
}
