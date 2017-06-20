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
    网关对外的版本控制根据访问路径中的第一个参数决定
    例如：http://localhost:8088/v2/patient/hello   那个就是访问v2的这个版本
    对应的代码实现，在方法上添加版本的注解 @ApiVersion() 已经对应的版本号 1
    
    @RestController
    @RequestMapping("/{version}/patient")
    public class PatientController {
    
        @GetMapping("/hello")
        @ApiVersion(1)
        @ResponseBody
        public String hello1(HttpServletRequest request){
            System.out.println("haha1..........");
    
            return "hello";
        }
    
        @GetMapping("/hello")
        @ApiVersion(2)
        @ResponseBody
        public String hello2(HttpServletRequest request){
            System.out.println("haha2.........");
            return "hello";
        }
    }
    
    如果我们传大于v2的版本那么系统会自动识别到最新的版本 v2
    例如http://localhost:8088/v5/patient/hello     那么其实还是调用v2版本