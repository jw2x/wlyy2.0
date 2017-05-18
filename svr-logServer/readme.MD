svr-logServer 
    分布式跟踪系统
    ui地址 :localhost:9411
    
配置需要跟踪的系统
    添加maven支持即可
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-sleuth</artifactId>
            </dependency>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-sleuth-zipkin</artifactId>
            </dependency>
         