**授权码模式:(一直开放API用)**
获取code
http://localhost:8060/oauth/authorize?response_type=code&client_id=cwd&redirect_uri=http://example.com&scope=all
获取token post请求
http://localhost:8060/oauth/token
header:  Basic {appid}:{appsecuri} 加密  例如 Basic Y3dkOmN3ZA==
  
{
     "grant_type":"authorization_code",  
     "client_id":"cwd",
     "code":"第一步请求获取的code",
     "redirect_uri":"http://example.com",
     "scope":"all"
}
返回值
{
    "access_token":"bd677e24-2de5-4862-a5e1-8f90a074db42",
    "token_type":"bearer",
    "refresh_token":"1427b997-ef94-4061-8940-c71da6549acd",
    "expires_in":43199,
    "scope":"all"
}

**密码模式（一般自己公司系统用）**
获取token post请求
http://localhost:8060/oauth/token
header:  Basic {appid}:{appsecuri} 加密  例如 Basic Y3dkOmN3ZA==
  
{
     "grant_type":"password",  
     "username":"jojo",
     "password":"123456",
     "scope":"all"
}

返回值
{
    "access_token":"630e2ccc-a5ce-4486-a855-ba755eb3d0d2",
    "token_type":"bearer",
    "refresh_token":"bbb36b54-61b2-4d86-aed3-91c5135174c3",
    "expires_in":43199,
    "scope":"all"
}

**刷新token **
获取token post请求
http://localhost:8060/oauth/token
header:  Basic {appid}:{appsecuri} 加密  例如 Basic Y3dkOmN3ZA==
  
{
     "grant_type":"refresh_token",
     "refresh_token":"all"
}

返回值
{
    "access_token":"630e2ccc-a5ce-4486-a855-ba755eb3d0d2",
    "token_type":"bearer",
    "refresh_token":"bbb36b54-61b2-4d86-aed3-91c5135174c3",
    "expires_in":43199,
    "scope":"all"
}



**自定义账号密码登陆**
POST
http://localhost:8060/authentication/form

header:  Basic {appid}:{appsecuri} 加密  例如 Basic Y3dkOmN3ZA==

body
{
    "username":"test",
    "password":"123456"
}
返回值
{
    "access_token":"630e2ccc-a5ce-4486-a855-ba755eb3d0d2",
    "token_type":"bearer",
    "refresh_token":"bbb36b54-61b2-4d86-aed3-91c5135174c3",
    "expires_in":43199,
    "scope":"all"
}


**自定义手机号短信验证码登陆**

获取短信
GET
http://localhost:8060/code/sms

body
{
    "mobile":"13612345678"
}


POST
http://localhost:8060/authentication/mobile

header:  Basic {appid}:{appsecuri} 加密  例如 Basic Y3dkOmN3ZA==

body
{
    "mobile":"test",
    "sms":"246053"
}

返回值
{
    "access_token":"630e2ccc-a5ce-4486-a855-ba755eb3d0d2",
    "token_type":"bearer",
    "refresh_token":"bbb36b54-61b2-4d86-aed3-91c5135174c3",
    "expires_in":43199,
    "scope":"all"
}

**访问方式**
http://localhost:8060/user
header 
{
"Authorization":"bearer 5fe6b2c3-f69c-4ddc-a36a-367cdf9479a3"      即 bearer accesstoken
}
