此项目是一个对外的网关项目
    网关调用微服务利用fegin（spring-cloud-starter-feign）
    
查看服务健康

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
    
    localhost:8088/health