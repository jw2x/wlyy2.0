/**
 * Created by JKZL-A on 2017/10/24.
 */
define(['jquery', 'promise', 'layer', 'jsHelper'], function ($, Promise, layer, jsHelper) {
    jsHelper.setLayerCon();
    var baseUrl = '/iot/';
    function httpGet(url,options) {
        //发送ajax请求
        return new Promise(function(resolve, reject) {
            $.ajax(url,
                $.extend({},{
                    type: 'GET',
                    dataType: 'JSON',
                    beforeSend: function(request) {
                    },
                    error: function(res) {
                        reject(res)
                    },
                    success: function(res) {
                        if(res.status && res.status == -200) {
                            layer.msg("登录失效，请重新登录！")
                            setTimeout(function() {
                                window.location.replace('./login.html')
                            },2000)
                            return ;
                        } else {
                            resolve(res)
                        }
                    }
                },options));
        })
    }

    function httpPost(url,options) {
        //发送ajax请求
        return new Promise(function(resolve, reject) {
            $.ajax( url,
                $.extend({},{
                    type: 'POST',
                    dataType: 'JSON',
                    beforeSend: function(request) {
                    },
                    error: function(res) {
                        reject(res)
                    },
                    success: function(res) {
                        if(res.status && res.status == -200) {
                            layer.msg("登录失效，请重新登录！")
                            setTimeout(function() {
                                window.location.replace('./login.html')
                            },2000)
                            return ;
                        } else {
                            resolve(res)
                        }

                    }
                },options));
        })
    }


    var APIService = {
        indexPage: function () {//首页
            return baseUrl + 'attendance/index'
        },
        autoLogin: function (opt) {//单点登录
            return httpPost(baseUrl + 'login', opt)
        },
        login: function(opt) {//登录
            return httpPost(baseUrl + 'login11', opt)
        },
        out: function (opt) {//退出
            sessionStorage.clear();
            return httpGet(baseUrl + 'login/exit', opt)
        },
        ambulanceSearch:function (opt) {//救护车列表
            return httpGet(baseUrl + 'ambulance/search', opt)
        },
        attendanceList:function (opt) {//保存出勤记录
            return httpGet(baseUrl + 'attendance/list', opt)
        },
        attendanceSave:function (opt) {//保存出勤记录
            return httpPost(baseUrl + 'attendance/save', opt)
        },
        attendanceUpdate:function (opt) {//更改出勤状态
            return httpPost(baseUrl + 'attendance/update', opt)
        },
        attendanceDetail:function (opt) {//出勤任务详情
            return httpGet(baseUrl + 'attendance/detail', opt)
        }
    }

    return APIService;
});