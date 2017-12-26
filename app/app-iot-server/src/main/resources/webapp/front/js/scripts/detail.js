/**
 * Created by JKZL-A on 2017/10/20.
 */
var indexRelyOn = ['jquery', 'layer', 'vue', 'showTab', 'jsHelper', 'apiServer','hplus'];
require(indexRelyOn, function ($, layer, vue, showTab, jsHelper, apiServer) {
    var id=getUrlVars("id");
    //检查是否登录
    jsHelper.checkLogin();
    jsHelper.setLayerCon();
    $(function(){
        apiServer.attendanceDetail({
            data:{
                id: id
            }
        }).then(function (res) {
            if (res.successFlg) {
                console.info(res);
                var obj = res.obj;
                if (obj.attendance){
                    $.each(obj.attendance,function(key,value){
                        if($('#'+key)){
                            $('#'+key).html(nullToSpace(value));
                        }
                    });
                    $('#callAddress1').html('<i class="glyphicon glyphicon-map-marker"></i>'+nullToSpace(obj.attendance.callAddress));
                    $('#deliverAddress').html('<i class="glyphicon glyphicon-map-marker"></i>'+nullToSpace(obj.attendance.deliverAddress));
                }
                if (obj.schedule){
                    $.each(obj.schedule,function(id,item){
                        if (item.dutyRole=="医生"){
                            $('#yisheng').html(nullToSpace(item.dutyName));
                        }else if (item.dutyRole=="护士"){
                            $('#hushi').html(nullToSpace(item.dutyName));
                        }else if (item.dutyRole=="司机"){
                            $('#siji').html(nullToSpace(item.dutyName));
                        }
                    });
                }
                if (obj.user){
                    $('#realName').html(nullToSpace(obj.user.realName));
                }
            }else {
                layer.msg(res.errorMsg);
            }
        });
    });

    function getUrlVars(name, flag) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        //modify by zhengwei
        if (flag) {
            if (r == null) {
                r = window.parent.location.search.substr(1).match(reg);
            }
        }
        if (r != null) return decodeURI(r[2]);
        return null;
    }

    function nullToSpace(param) {
        if (param == null || typeof param == 'undefined') {
            param = '';
        }
        return param;
    }
    //公共类
    window._apiServer = apiServer;
    window._SHOWTAB = showTab;
});
