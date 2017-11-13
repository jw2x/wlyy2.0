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
    
    upstream apache001 {
         server 192.168.1.1:8080 weight=1 max_fails=1 fail_timeout=30s;
    }
    
    upstream apache002 {
          server 192.168.1.2:8080 weight=1 max_fails=1 fail_timeout=30s;
    }
    
    upstream root {
        server 192.168.1.0:8080 weight=1 max_fails=1 fail_timeout=30s;
    }
    
    server {
        listen 8080;
        server_name beidoutest.baidu.com;
        
        #match cookie $group是一个变量，{}里面是映射规则
        set $group "root";
        if ($http_cookie ~* "prod=0"){
            set $group apache001;
        }
        if ($http_cookie ~* "prod=1"){
            set $group apache002;
        }
        
        log_format main '$remote_addr - $remote_user [$time_local] "$request" '
        '$status $body_bytes_sent "$http_referer" "group=$group"'
        '"$http_user_agent" $gzip_ratio $request_time "$http_x_forwarded_for"';
        
        access_log logs/access_log main;
        error_log logs/error_log;
        
        location / {
            proxy_pass http://$group;
            proxy_set_header X-Forwarded-For $remote_addr;
        }
    
    }
