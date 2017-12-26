/**
 * Created by JKZL-A on 2017/10/20.
 */
var indexRelyOn = ['jquery', 'layer', 'vue', 'showTab', 'jsHelper', 'apiServer','hplus'];
require(indexRelyOn, function ($, layer, vue, showTab, jsHelper, apiServer) {
    var id=getUrlVars("id");
    var map;
    //检查是否登录
    jsHelper.checkLogin();
    jsHelper.setLayerCon();
    $(function(){
        // 百度地图API功能
        initmap();
    });
    //初始化地图
    function initmap() {
        map = new BMap.Map("map");    // 创建Map实例
        map.centerAndZoom(new BMap.Point(117.950, 28.463), 14);
        map.addControl(new BMap.ScaleControl());
        map.enableScrollWheelZoom(true);
        map.setCurrentCity("上饶");
        map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
        getTrace();
    }
    //获取实时经纬度
    function getTrace() {
        apiServer.attendanceDetail({
            data:{
                id: id
            }
        }).then(function (res) {
            if (res.successFlg) {
                var obj = res.obj;
                var entity_name = obj.car.entityName;
                var start_time = Math.round(new Date(obj.attendance.createDate).getTime()/1000);
                var end_time;
                if (obj.attendance.completeTime){
                    end_time = Math.round(new Date(obj.attendance.completeTime).getTime()/1000);
                }else{
                    end_time = Math.round(new Date().getTime()/1000);
                }
                if (!entity_name){
                    layer.msg('车载设备信息不存在');
                    return;
                }
                $.ajax({
                    url:'http://yingyan.baidu.com/api/v3/track/gettrack?ak=1Gi8rVhlLxpYHeFoIw3AoZWdTcPzWass&' +
                        'service_id=153674&entity_name='+entity_name+'&start_time='+start_time+'&end_time='+end_time,
                    dataType:'jsonp',
                    processData: false,
                    type:'get',
                    success:function(data){
                        console.info(data);
                        if (data.status=='0'){
                            if (data.points&&data.points.length>0){
                                var points = [];
                                $.each(data.points,function (id,item) {
                                    var point = new BMap.Point(item.longitude, item.latitude);
                                    points.push(point);
                                });
                                var startPoint = points[0];
                                var endPoint = points[points.length-1];

                                // 起点
                                var startMarker = new BMap.Marker(startPoint);
                                var label = new BMap.Label("起",{offset:new BMap.Size(3,3)});
                                label.setStyle({
                                    color : "white",
                                    fontWeight:"bold",
                                    border:"none",
                                    backgroundColor:"none"
                                });
                                startMarker.setLabel(label);
                                map.addOverlay(startMarker);

                                // 终点
                                var endMarker = new BMap.Marker(endPoint);
                                var label = new BMap.Label("终",{offset:new BMap.Size(3,3)});
                                label.setStyle({
                                    color : "white",
                                    fontWeight:"bold",
                                    border:"none",
                                    backgroundColor:"none"
                                });
                                endMarker.setLabel(label);
                                //endMarker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
                                map.addOverlay(endMarker);

                                /* 添加轨迹接口begin */
                                var polyline = new BMap.Polyline(points, {
                                    strokeColor : 'blue',
                                    strokeWeight : 6,
                                    strokeOpacity : 0.5
                                });
                                map.addOverlay(polyline);
                                setZoom(points);
                            }else{
                                layer.msg('暂无轨迹信息');
                            }
                        }else{
                            layer.msg(data.message);
                        }
                    },
                    error:function(XMLHttpRequest, textStatus, errorThrown) {
                        layer.msg('获取车辆轨迹失败');
                    }
                });
            }else {
                layer.msg(res.errorMsg);
            }
        });
    }

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

    /**
     * 根据原始数据计算中心坐标和缩放级别，并为地图设置中心坐标和缩放级别。
     * @param points
     */
    function setZoom(points) {
        if (points.length > 0) {
            var maxLng = points[0].lng;
            var minLng = points[0].lng;
            var maxLat = points[0].lat;
            var minLat = points[0].lat;
            var res;
            for (var i = points.length - 1; i >= 0; i--) {
                res = points[i];
                if(res !== null && res !==undefined){
                    if (res.lng > maxLng)
                        maxLng = res.lng;
                    if (res.lng < minLng)
                        minLng = res.lng;
                    if (res.lat > maxLat)
                        maxLat = res.lat;
                    if (res.lat < minLat)
                        minLat = res.lat;
                }
            };
            var cenLng = (parseFloat(maxLng) + parseFloat(minLng)) / 2;
            var cenLat = (parseFloat(maxLat) + parseFloat(minLat)) / 2;
            var zoom = getZoom(maxLng, minLng, maxLat, minLat);
            map.centerAndZoom(new BMap.Point(cenLng, cenLat), zoom);
        } else {
            // 没有坐标，显示全中国
            map.centerAndZoom(new BMap.Point(103.388611, 35.563611), 5);
        }
    };

    /**
     * 根据经纬极值计算缩放级别。
     * @param maxLng
     * @param minLng
     * @param maxLat
     * @param minLat
     * @returns {Number}
     */
    function getZoom(maxLng, minLng, maxLat, minLat) {
        var zoom = [ "50", "100", "200", "500", "1000", "2000", "5000", "10000", "20000", "25000", "50000", "100000", "200000", "500000",
            "1000000", "2000000" ];// 级别18到3。
        var pointA = new BMap.Point(maxLng, maxLat); // 创建点坐标A
        var pointB = new BMap.Point(minLng, minLat); // 创建点坐标B
        var distance = map.getDistance(pointA, pointB).toFixed(1); // 获取两点距离,保留小数点后两位
        for (var i = 0, zoomLen = zoom.length; i < zoomLen; i++) {
            if (zoom[i] - distance > 0) {
                return 18 - i + 3;// 之所以会多3，是因为地图范围常常是比例尺距离的10倍以上。所以级别会增加3。
            }
        }
    };


    /**
     * 校验经纬度是否有效
     * @param lng
     * @param lat
     */
    function checkLngAndLat(lng, lat) {
        // 校验lng
        if (lng === "" || lng === undefined || lng === null) {
            return false;
        }

        // 校验lat
        if (lat === "" || lat === undefined || lat === null) {
            return false;
        }

        return true;
    };
    //公共类
    window._apiServer = apiServer;
    window._SHOWTAB = showTab;
});