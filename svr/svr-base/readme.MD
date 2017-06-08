基础微服务
    每个模块有自己独立的文件夹，这样做的好处是以后如果哪个模块需要独立成微服务，代码直接剪切到新的微服务即可。
    每个模块下面有独立的controller,service,model和dao
    base:
        saas  saas配置
        module 模块配置管理
        function 功能配置管理
    wx:
        wechat   微信配置相关
        wxAccessToken  微信token管理
        wxGraphicMessage    微信图文管理
        wxMenu  微信菜单管理
        wxTemplate  微信模版
    