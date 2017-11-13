此项目是一个对外的网关项目
    网关调用微服务利用fegin（spring-cloud-starter-feign）
    
查看服务健康需要添加以下支持

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
    
    localhost:8088/health
    
刷新配置
在有需要刷新配置的地方的类加上注解

    @RefreshScope
    访问以下路径刷新配置
    curl -X POST http://localhost:8088/refresh
    
    
版本控制
    nginx根据cookie中的是否是正式版本跳转服务器
    
    cookie
    {
        "prod":0, #  0跳转到灰度服务器  1跳转到正式的服务器
    }