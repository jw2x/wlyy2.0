package com.yihu.jw.commnon.base.wx;

/**
 * Created by Administrator on 2017/5/20 0020.
 */
public class WechatContants {
    public static final String api_common="wechat";


    //微信token模块常量
    public static class AccessToken{
        public static final String api_common="accessToken";
        public static final String api_create="create";
        public static final String api_get="get";
    }

    //微信按钮模块常量
    public static class Menu{
        public static final String api_create="wxMenu";
        public static final String api_update="wxMenu";
        public static final String api_delete="wxMenu/{codes}/{userCode}";
        public static final String api_getWxMenuNoPage="wxMenu/listNoPage";
        public static final String api_getWxMenus="wxMenu/list";
        public static final String api_getByCode="wxMenu/{code}";
        public static final String api_getParentMenu="parentMenu/{wechatCode}";
        public static final String api_createMenu="createMenu";
        public static final String api_getChildMenus="childMenu/list/{parentCode}";
    }

    //微信模板消息
    public static class Template {
        public static final String api_create="template";
        public static final String api_update="template";
        public static final String api_delete="template/{codes}";
        public static final String api_getWxTemplatesNoPage="template/listNoPage";
        public static final String api_getWxTemplates="template/list";
        public static final String api_getByCode="template/{code}";
        public static final String api_sendTemplateMessage="sendTemplateMessage";
    }

    //微信配置表
    public static class Config {
        public static final String api_create="wechatConfig";
        public static final String api_update="wechatConfig";
        public static final String api_delete="wechatConfig/{codes}";
        public static final String api_getWechatNoPage="wechatConfig/listNoPage";
        public static final String api_getWechats="wechatConfig/list";
        public static final String api_getByCode="wechatConfig/{code}";
    }

    //微信图文消息
    public static class GraphicMessage {
        public static final String api_create="graphicMessage";
        public static final String api_update="graphicMessage";
        public static final String api_delete="graphicMessage/{codes}";
        public static final String api_getWxGraphicMessageNoPage="graphicMessage/listNoPage";
        public static final String api_getWxGraphicMessages="graphicMessage/list";
        public static final String api_getByCode="graphicMessage/{code}";
        public static final String api_sendGraphicMessages="sendGraphicMessages";
    }
}
