//用来获取请求中的参数的工具类
UrlParm = function () { // url参数
    var data, index;
    (function init() {
        data = [];
        index = {};
        var u = window.location.search.substr(1);
        if (u != '') {
            var parms = decodeURIComponent(u).split('&');
            for (var i = 0, len = parms.length; i < len; i++) {
                if (parms[i] != '') {
                    var p = parms[i].split("=");
                    if (p.length == 1 || (p.length == 2 && p[1] == '')) {
                        data.push(['']);
                        index[p[0]] = data.length - 1;
                    } else if (typeof(p[0]) == 'undefined' || p[0] == '') {
                        data[0] = [p[1]];
                    } else if (typeof(index[p[0]]) == 'undefined') { // c=aaa
                        data.push([p[1]]);
                        index[p[0]] = data.length - 1;
                    } else {// c=aaa
                        data[index[p[0]]].push(p[1]);
                    }
                }
            }
        }
    })();
    return {
        // 获得参数,类似request.getParameter()
        parm: function (o) { // o: 参数名或者参数次序
            try {
                return (typeof(o) == 'number' ? data[o][0] : data[index[o]][0]);
            } catch (e) {
            }
        },
        //获得参数组, 类似request.getParameterValues()
        parmValues: function (o) { //  o: 参数名或者参数次序
            try {
                return (typeof(o) == 'number' ? data[o] : data[index[o]]);
            } catch (e) {
            }
        },
        //是否含有parmName参数
        hasParm: function (parmName) {
            return typeof(parmName) == 'string' ? typeof(index[parmName]) != 'undefined' : false;
        },
        // 获得参数Map ,类似request.getParameterMap()
        parmMap: function () {
            var map = {};
            try {
                for (var p in index) {
                    map[p] = data[index[p]];
                }
            } catch (e) {
            }
            return map;
        }
    }
}();

//get方法
function do_get(url, data, success) {
    //获取用户的code
    var usercode = getUserCode();
    data.userCode = usercode;
    $.ajax({
        type: 'GET',
        url: url,
        data: data,
        success: success,
        dataType: "json"
    });
}
//post方法
function do_post(url, data, success) {
    //获取用户的code
    var usercode = getUserCode();
    data.userCode = usercode;
    $.ajax({
        type: 'POST',
        url: url,
        data: data,
        success: success,
        dataType: "json"
    });
}
//put方法
function do_put(url, data, success) {

    //获取用户的code
    var usercode = getUserCode();
    data.userCode = usercode;
    $.ajax({
        type: 'PUT',
        url: url,
        data: data,
        success: success,
        dataType: "json"
    });
}
//delete方法
function do_delete(url, data, success) {

    //获取用户的code
    var usercode = getUserCode();
    data.userCode = usercode;
    $.ajax({
        type: 'DELETE',
        url: url,
        data: data,
        success: success,
        dataType: "json"
    });
}

function getUserCode() {
    var code = window.localStorage.getItem("userCode");
    if (!code) {
        alert("请重新登陆")
        window.location.href = '/login/login.html';
    }
    return code
}

function setUserCode(code) {
    window.localStorage.setItem("userCode", code);
}

/**
 * 判断是否有登陆
 * @param data
 */
function isLogin(data){
    if(data.errorCode==not_login){
         alert("请重新登陆")
         window.location.href = '/login/login.html';
    }
}