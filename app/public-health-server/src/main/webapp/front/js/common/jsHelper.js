/**
 * Created by JKZL-A on 2017/10/24.
 */
define(['jquery', 'layer'], function ($, layer) {
    function showLogOutPop () {
        layer.open({
            type: 1,
            title: false,
            closeBtn: false,
            area: '300px;',
            shade: 0.8,
            id: 'LAY_layuipro',
            resize: false,
            btn: ['重新登录'],
            btnAlign: 'c',
            moveType: 1,
            content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">您还未登录,请重新登录</div>',
            success: function (layero) {
                var btn = layero.find('.layui-layer-btn');
                btn.find('.layui-layer-btn0').attr({
                    href: '#'
                }).on('click', function () {
                    window.location.replace('./login.html');
                });
            }
        });
    }
    
    var jsHelper = {
        setLayerCon: function () {//初始化layer路径
            layer.config({
                path: '../js/lib/plugins/layer/' //layer.js所在的目录，可以是绝对目录，也可以是相对目录
            });
        },
        checkLogin: function () {//检查是否登录
            var ZF_UN = null;
            try {
                ZF_UN = sessionStorage.getItem("ZF_UN");
            } catch (e){
                showLogOutPop();
            }
            if (!ZF_UN) {
                showLogOutPop();
            }
        },
        GetRequest: function () {//获取参数
            var url = location.search; //获取url中"?"符后的字串
            var theRequest = new Object();
            if (url.indexOf("?") != -1) {
                var str = url.substr(1);
                strs = str.split("&");
                for(var i = 0; i < strs.length; i ++) {
                    theRequest[strs[i].split("=")[0]]=(strs[i].split("=")[1]);
                }
            }
            return theRequest;
        }
    };
    return jsHelper;
});