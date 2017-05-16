项目结构
     common    公共的一些配置
        common-swagger   swagger工程
        涉及主要技术 swagger
     svr    业务微服务  
        svr-user 用户微服务
        svr-quota 统计微服务
     svr-configuration    配置服务 
        涉及主要技术 git(存储配置文件) 
     svr-discovery    发现服务 
        涉及主要技术 euruka ribbon 
     svr-dashboard    监控服务 
        涉及主要技术 hystrix,hystrix-dashboard
     common-lib-parent-pom    common公共工程的maven父pom
     jw-lib-parent-pom   jw业务公共工程的maven父pom 
     web-gateway 对外的接口网关
     .gitignore   项目提交忽略配置文件
     
项目运行
    先启动      svr-discovery(发现服务)
    然后在启动  svr-configuration(配置服务)
    之后启动微服务即可（微服务启动顺序一般没有要求）