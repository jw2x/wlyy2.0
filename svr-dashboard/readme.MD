监控服务
    地址: ip:port/hystriz
    例如 http://localhost:9010/hystrix
    可以启动网关 然后输入网关的监控地址
    例如 http://localhost:8088/hystrix.stream
    
添加需要监控的客户端
    添加maven支持即可
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>