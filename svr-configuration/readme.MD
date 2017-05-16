配置服务：
    存储:git
    地址:http://192.168.1.220:10080/jiwei/jw.config.git
    配置服务的优先级(左到右 高到低)
        git（svr-base.yml）->git(application.yml)->本地(application.yml)->本地(bootstrap.yml)
        注:1.如果以上配置有相同默认采取优先级高的 
    刷新当个微服务的配置
        地址:端口/refresh 
        例 localhost:8080/reflesh
    让微服务添加配置服务代码如下
        spring:
          #从发现服务里面取配置服务的信息
          cloud:
            config:
              username: jw
              password: jkzl
              discovery:
                enabled: true ##使用发现服务
                service-id: svr-configurations ##配置服务的名字

        