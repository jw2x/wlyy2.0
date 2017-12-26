<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/commonInclude.jsp" %>

<script>
    var clientId = '${clientId}';
    var signin = {
        init:function () {
            //判断是否自动登录
            var hash = window.location.hash;
            if(hash.indexOf("#access_token")>=0)
            {
                //获取accrss_token
                var tokenString =hash.substring(1,hash.indexOf("&"));
                var token = tokenString.substr(hash.indexOf("="));
                debugger;
                //自动登录
                $.ajax({
                    url: "${contextRoot}/login/autoLogin",
                    type: 'POST',
                    dataType: 'json',
                    data:{
                        "token":token,
                        "clientId": clientId
                    },
                    success: function (data) {
                        debugger;
                        if(data.successFlg){
                            sessionStorage.setItem("ZF_UID",data.obj.id);
                            sessionStorage.setItem("ZF_UN",data.obj.realName);
                            sessionStorage.setItem("ZF_U",data.obj);
                            location.href = '${contextRoot}/index';
                        }else{
                            location.href = '${contextRoot}/login';
                        }
                    },
                    error: function (data) {
                        location.href = '${contextRoot}/login';
                    }
                });
                return;
            }
        }
    }

    $(function() {
        signin.init();
    });

</script>