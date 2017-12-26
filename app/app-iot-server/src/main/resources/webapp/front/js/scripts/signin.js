/**
 * Created by JKZL-A on 2017/11/28.
 */
require([
    'jquery',
    'jsHelper',
    'apiServer'
], function ($, jsHelper, apiServer) {
    var signin = {
        $signinCon: $('#signinCon'),
        $tipsCon: $('.tips-con'),
        init:function () {
            var me = this;
            //判断是否自动登录
            var hash = window.location.hash;
            if(hash.indexOf("#access_token")>=0)
            {
                var tokenString =hash.substring(1,hash.indexOf("&")),
                    token = tokenString.substr(hash.indexOf("=")),
                    clientId = jsHelper.GetRequest()['clientId'];
                if (clientId) {
                    me.login(token, clientId);
                } else {
                    me.$tipsCon.html(me.gethtml('未知参数：clientId'));
                }
            } else {
                me.$tipsCon.html(me.gethtml('参数有误!'));
            }
        },
        login: function (token, clientId) {
            var me = this;
            apiServer.autoLogin({
                data: {
                    "token":token,
                    "clientId": clientId
                }
            }).then(function (data) {
                if(data.successFlg){
                    sessionStorage.setItem("ZF_UID",data.obj.id);
                    sessionStorage.setItem("ZF_UN",data.obj.realName);
                    sessionStorage.setItem("ZF_U",data.obj);
                    var iframe = document.createElement('iframe');
                    iframe.src = apiServer.indexPage();
                    me.$signinCon.html(iframe);
                }else{
                    me.$tipsCon.html(me.gethtml(data.errorMsg));
                }
            });
        },
        gethtml: function (msg) {
            return '<img src="../images/error.png"><p>' + msg + '</p>';
        }
    }
    signin.init();
});