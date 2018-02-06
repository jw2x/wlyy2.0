/**
 * Created by JKZL-A on 2017/10/20.
 */
var loginRelyOn = ['jquery', 'layer', 'vue', 'jsHelper', 'apiServer', 'promise', 'bootstrap', 'validate'];
require(loginRelyOn, function ($, layer, vue, jsHelper, apiServer, Promise) {
   jsHelper.setLayerCon();
    var load = null;
    var loginApp = new vue({
        el: '#loginApp',
        data: {
            userID: '',
            password: ''
        },
        methods: {
            setLoad: function () {
                load =layer.load(1, {
                    shade: [0.5,'#000']
                });
            },
            sendFun: function () {
                if (this.userID == '') {
                    this.tips('请输入账号', '#lUAn');
                    return ;
                }
                if (this.password == '') {
                    this.tips('请输入密码', '#lUPwd');
                    return ;
                }
                this.setLoad();
                apiServer.login({
                    data:{
                        "username": this.userID,
                        "password": this.password
                    }
                }).then(function (res) {
                    layer.close(load);
                    if (res.successFlg) {
                        debugger
                        sessionStorage.setItem("ZF_UID",res.obj.id);
                        sessionStorage.setItem("ZF_UN",res.obj.realName);
                        sessionStorage.setItem("ZF_U",res.obj);
                        location.href = apiServer.indexPage();
                    } else {
                        layer.msg(res.message);
                    }
                });
            },
            tips: function (msg, id) {
                layer.tips(msg, id,{
                    tips: [2, '#ff5722'],
                    time: 1000
                });
            }
        }
    });
});