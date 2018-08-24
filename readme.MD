项目结构
     common     公共依赖包
     svr     业务微服务  
        svr-base 基础微服务
        svr-quota 统计微服务
     server
         svr-configuration    配置服务 
            涉及主要技术 git(存储配置文件) 
         svr-discovery    发现服务 
            涉及主要技术 euruka ribbon 
         svr-dashboard    监控服务 
            涉及主要技术 hystrix,hystrix-dashboard
         svr-logServer 分布式追踪
            涉及主要技术 zipkin 
     common-lib-parent-pom    common公共工程的maven父pom
     jw-lib-parent-pom   jw业务公共工程的maven父pom 
     web-gateway 对外的接口网关
     manage-gateway 基卫后台管理系统的接口网关
     .gitignore   项目提交忽略配置文件
     
项目运行
    环境变量配置
        开发环境:  spring_profiles_active jwdev
        测试环境:  spring_profiles_active jwtest
        正式环境:  spring_profiles_active jwprod
    启动顺序
        先启动      svr-discovery(发现服务)
        然后在启动  svr-configuration(配置服务)
        之后启动微服务即可（微服务启动顺序一般没有要求）
项目文档资料
	SVN:https://192.168.1.103:888/svn/outwindow/基础开发部/doc/i健康2.0