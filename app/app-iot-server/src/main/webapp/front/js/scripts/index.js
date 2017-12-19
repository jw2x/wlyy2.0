/**
 * Created by JKZL-A on 2017/10/20.
 */
var indexRelyOn = ['jquery', 'layer', 'vue', 'showTab', 'jsHelper', 'apiServer','hplus'];
require(indexRelyOn, function ($, layer, vue, showTab, jsHelper, apiServer) {
    var map;
    var carList;//车辆列表
    var sortList = [];//车辆排序列表
    var searchList;//搜索地址列表
    var point;//事发地点
    var range=500;//距离范围
    var i=0;//记录获取地址次数
    //检查是否登录
    jsHelper.checkLogin();
    jsHelper.setLayerCon();
    $(function(){
        // 百度地图API功能
        initmap();
        //设置车辆列表的最大高度
        $("#car-list ul").css("max-height",$(window).height()-218);
        //展开、隐藏车辆列表
        $(".dislpay-arrow").on("click",'a',function(){
            $(".left").toggle();
            if($(this).hasClass("zhankai")){
                $(this).removeClass("zhankai");
            }else{
                $(this).addClass("zhankai");
            }
        });
        var options = {
            onSearchComplete: function(results){
                // 判断状态是否正确
                if (local.getStatus() == BMAP_STATUS_SUCCESS){
                    searchList = results;
                    var s = [];
                    for (var i = 0; i < results.getCurrentNumPois(); i ++){
                        s.push('<li class="ui3-suggest-item"><a><i class="default">'+results.getPoi(i).title+'</i><em>'+results.getPoi(i).address+'</em></a></li>');
                    }
                    $("#addr-list ul").html(s.join(""));
                    $("#addr-list").show();
                    $("#car-back").hide();
                    $("#car-list").hide();
                }
            }
        };
        var local = new BMap.LocalSearch(map, options);
        //搜索事故地点
        $("#search-button").click(function(){
            if (!$.trim($("#sole-input").val())){
                layer.msg("请输入事发地点");
            }
            local.search($.trim($("#sole-input").val()));
        });
        //按回车键搜索
        $("#sole-input").on("keypress",function(event){
            if(event.keyCode == 13) {
                if (!$.trim($("#sole-input").val())){
                    layer.msg("请输入事发地点");
                }
                local.search($.trim($("#sole-input").val()));
            }
        });
        //搜索框输入事件
        $('#sole-input').bind('input', function () {//给文本框绑定input事件
            if ($('#sole-input').val().trim() != "") {
                $(".input-clear").show();
            }else{
                $(".input-clear").hide();
            }
        });
        //清空搜索框
        $(".input-clear").on('click',function () {
            formReset();
            $("#sole-input").val("");
            $("#addr-list").hide();
            $("#car-back").hide();
            $("#car-list").hide();
            $(".input-clear").hide();
            point = null;
            var allOverlay = map.getOverlays();
            for (var i = 0; i < allOverlay.length -1; i++){
                if(allOverlay[i].getTitle() == "place"){
                    map.removeOverlay(allOverlay[i]);
                    return false;
                }
            }
        });
        //点击搜索地点
        $("#addr-list").on("click","li",function () {
            formReset();
            var p = searchList.getPoi($(this).index());
            point = p.point;
            sortCar();
            $(".left").hide();
            $("#car-back span").html("返回“"+$('#sole-input').val()+"”的搜索结果");
            $(".weizhi-bg .item").html(p.title+"（"+p.address+"）");
            $("#addr-list").hide();
            $("#car-back").show();
            $("#car-list").show();
            setTimeout(function () {
                map.centerAndZoom(point, 14);
                var myIcon = new BMap.Icon("../images/biaoji_yixuan_icon.png", new BMap.Size(20,32));
                var marker = new BMap.Marker(p.point,{icon:myIcon});// 创建标注
                marker.setTitle("place");
                var infoWindow = new BMap.InfoWindow($(".weizhi-bg .item").html());
                marker.addEventListener("click", function(){
                    this.openInfoWindow(infoWindow);
                });
                map.addOverlay(marker);// 将标注添加到地图中
            }, 500);
        });
        //返回
        $("#car-back").on("click",function () {
            $("#addr-list").show();
            $("#car-back").hide();
            $("#car-list").hide();
            point = null;
            var allOverlay = map.getOverlays();
            for (var i = 0; i < allOverlay.length -1; i++){
                if(allOverlay[i].getTitle() == "place"){
                    map.removeOverlay(allOverlay[i]);
                    return false;
                }
            }
        });
        $("#car-list").on("click",".btn-primary",function () {
            var car = sortList[$(this).parents("li").index()].car;
            $("#carId").val(car.id);
            $("#dispatchHospital").val(car.orgName);
            $("#deliverAddress").val(car.initAddress);
            $("#callAddress").val($(".weizhi-bg .item").html());
            $("#myModal").modal("show");
        });
        //保存出勤任务
        $("#save").on("click",function () {
           var param = new Object();
           param.carId =$("#carId").val();
           param.dispatchHospital =$("#dispatchHospital").val();
           param.callAddress =$("#callAddress").val();
           param.alarmTel =$("#alarmTel").val().trim();
           param.chiefComplaint =$("#chiefComplaint").val().trim();
           param.patientNum =$("#patientNum").val().trim();
           param.patientGender =$("#patientGender").val().trim();
           param.disease =$("#disease").val().trim();
           param.deliverAddress =$("#deliverAddress").val().trim();
           param.remark =$("#remark").val().trim();
           param.longitude = point.lng;
           param.latitude = point.lat;
           if (!$("#alarmTel").val().trim()){
               layer.msg("请输入接警电话");
               return;
           }
           if (!$("#chiefComplaint").val().trim()){
               layer.msg("请输入主诉");
               return;
           }
           if (!$("#patientNum").val().trim()){
               layer.msg("请输入患者人数");
               return;
           }
            apiServer.attendanceSave({
                data:{
                    attendance:JSON.stringify(param)
                }
            }).then(function (res) {
                if (res.successFlg) {
                    //point=null;
                    ambulanceSearch();
                    $("#myModal").modal("hide");
                    layer.msg("保存成功");
                }else{
                    layer.msg(res.errorMsg);
                }
            });
        });
        //定位车辆
        $("#car").on("click",".dingwei",function () {
            map.centerAndZoom(new BMap.Point($(this).parents("li").attr("lng"), $(this).parents("li").attr("lat")), 14);
            var allOverlay = map.getOverlays();
            for (var i = 0; i < allOverlay.length -1; i++){
                if(allOverlay[i].getTitle() == $(this).parents("li").attr("data-id")){
                    allOverlay[i].dispatchEvent("click");
                }
            }
        });
        //定位车辆
        $("#car-list").on("click",".dingwei",function () {
            map.centerAndZoom(new BMap.Point($(this).parents("li").attr("lng"), $(this).parents("li").attr("lat")), 14);
            var allOverlay = map.getOverlays();
            for (var i = 0; i < allOverlay.length -1; i++){
                if(allOverlay[i].getTitle() == $(this).parents("li").attr("data-id")){
                    allOverlay[i].dispatchEvent("click");
                }
            }
        });

        //定位事发地点
        $(".weizhi-bg ").on("click",".dingwei",function (e) {
            e.stopPropagation();
            map.centerAndZoom(point, 14);
            var allOverlay = map.getOverlays();
            for (var i = 0; i < allOverlay.length -1; i++){
                if(allOverlay[i].getTitle() == "place"){
                    allOverlay[i].dispatchEvent("click");
                }
            }
            $(".left").hide();
        });

        $(".query-attendance").on("click",function () {
            layer.open({
                type: 2,
                area: ['1000px', $(window).height()-100+'px'],
                fix: false, //不固定
                maxmin: false,
                shadeClose: false,
                shade:0.4,
                title: "急救查询",
                content: "search.html"
            });
        })
    });
    //初始化地图
    function initmap() {
        map = new BMap.Map("map");    // 创建Map实例
        map.centerAndZoom(new BMap.Point(117.950, 28.463), 14);
        map.addControl(new BMap.ScaleControl());
        map.enableScrollWheelZoom(true);
        map.setCurrentCity("上饶");
        map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
        ambulanceSearch();
        //定时刷新5秒一次
        setInterval(function () {
            ambulanceSearch();
        },5000);
    }
    //获取车辆列表
    function ambulanceSearch() {
        apiServer.ambulanceSearch({
            data:{
                page: 1,
                size: 100,
                sorts: "+status"
            }
        }).then(function (res) {
            if (res.successFlg) {
                var html = [];
                var person = [];
                carList = res.detailModelList;
                if(res.detailModelList&&res.detailModelList.length>0){
                    $.each(res.detailModelList,function (id,item) {
                        html.push('<li class="car" data-id="'+item.car.id+'" entity-name="'+item.car.entityName+'">');
                        html.push('     <div class="overflow">');
                        html.push('           <img class="car-img" src="../images/car.png">');
                        html.push('           <div class="overflow">');
                        html.push('                 <p class="color1 f-fs16 mb-7">'+item.car.id+'</p>');
                        html.push('                 <p class="mb-7">');
                        html.push('                       <span class="item-left">随车手机</span>');
                        html.push('                       <span>'+item.car.phone+'</span>');
                        html.push('                  </p>');
                        html.push('                  <p class="mb-7">');
                        html.push('                        <span class="item-left">归属地点</span>');
                        html.push('                        <span>'+item.car.initAddress+'</span>');
                        html.push('                  </p>');
                        html.push('                  <p class="mb-7">');
                        html.push('                         <span class="item-left">状态</span>');
                        if (item.car.status=="wait"){
                            html.push('                         <span class="main-color">待命</span>');
                        }else if (item.car.status=="onWay"||item.car.status=="arrival"){
                            html.push('                         <span class="color3">出勤</span>');
                        }else if (item.car.status=="back"){
                            html.push('                         <span class="color2">返回</span>');
                        }else {
                            html.push('                         <span>异常</span>');
                        }
                        html.push('                  </p>');
                        html.push('            </div>');
                        html.push('      </div>');
                        html.push('      <div class="items">');
                        html.push('           <span><i class="weizhi"></i>所在位置：</span>');
                        html.push('           <span class="item position"></span>');
                        html.push('           <span class="dingwei main-color" style="cursor: pointer;">定位</span>');
                        html.push('      </div>');
                        html.push(' </li>');
                        //值班人员列表
                        if (item.car.dutyList&&item.car.dutyList.length>0){
                            $.each(item.car.dutyList,function (id1,item1) {
                                person.push('<li class="car">');
                                person.push('<div class="overflow">');
                                person.push('<img class="car-img" src="../images/moren_touxiang_img.png">');
                                person.push('<div class="overflow">');
                                person.push('<p class="color1 f-fs16 mb-7">'+item1.dutyName+'（'+item1.dutyRole+'）</p>');
                                person.push('<p class="mb-7">');
                                person.push('<span class="item-left">联系方式</span>');
                                person.push('<span>'+item1.dutyPhone+'</span>');
                                person.push('</p>');
                                person.push('<p class="mb-7">');
                                person.push('<span class="item-left">值班车辆</span>');
                                person.push('<span>'+item.car.id+'</span>');
                                person.push('</p>');
                                person.push('<p class="mb-7">');
                                person.push('<span class="item-left">状态</span>');
                                if (item.car.status=="wait"){
                                    person.push('                         <span class="main-color">待命</span>');
                                }else if (item.car.status=="onWay"||item.car.status=="arrival"){
                                    person.push('                         <span class="color3">出勤</span>');
                                }else if (item.car.status=="back"){
                                    person.push('                         <span class="color2">返回</span>');
                                }else {
                                    person.push('                         <span>异常</span>');
                                }
                                person.push('</p>');
                                person.push('</div>');
                                person.push('</div>');
                                person.push('</li>');
                            });
                        }
                    });
                    $("#car").html(html.join(""));
                    $("#person").html(person.join(""));
                    getPosition();
                }
            } else {
                layer.msg(res.errorMsg);
            }
        });
    }

    //获取实时经纬度
    function getPosition() {
        i = 0;
        map.clearOverlays();
        $.ajax({
            url:'http://yingyan.baidu.com/api/v3/entity/search?ak=1Gi8rVhlLxpYHeFoIw3AoZWdTcPzWass&service_id=153674',
            dataType:'jsonp',
            processData: false,
            type:'get',
            success:function(data){
                if (data.status=='0'){
                    if (data.entities&&data.entities.length>0){
                        $.each(data.entities,function (id,item) {
                            var html = [];
                            var baiduPoint = new BMap.Point(item.latest_location.longitude, item.latest_location.latitude);
                            if (checkPhone(item.entity_name)){//判断是车辆还是随车手机
                                var myIcon = new BMap.Icon("../images/biaoji_icon.png", new BMap.Size(20,32));
                                var car = getByPhone(item.entity_name);
                                if(!car){
                                    return;
                                }
                                var marker = new BMap.Marker(baiduPoint,{icon:myIcon});  // 创建标注
                                marker.setTop(true);
                                map.addOverlay(marker);              // 将标注添加到地图中
                                var infoWindow = new BMap.InfoWindow("随车手机<br>所属车辆："+car.id);
                                marker.addEventListener("click", function(){
                                    this.openInfoWindow(infoWindow);
                                });
                            }else{
                                var car = getByEntityName(item.entity_name);
                                var img="../images/che_daiming_img.png";
                                html.push('<div class="car">');
                                html.push('<div class="overflow">');
                                html.push('<img class="car-img" src="../images/car.png" style="height: 70px;">');
                                html.push('<div class="overflow">');
                                if (car.status=="wait"){
                                    html.push('<p class="color1 f-fs16 mb-7 items"><span class="item">'+car.id+'</span><span class="main-color f-fs14">待命</span></p>');
                                    img="../images/che_daiming_img.png";
                                }else if (car.status=="onWay"||car.status=="arrival"){
                                    html.push('<p class="color1 f-fs16 mb-7 items"><span class="item">'+car.id+'</span><span class="color3 f-fs14">出勤</span></p>');
                                    img="../images/che_chuqing_img.png";
                                }else if (car.status=="back"){
                                    html.push('<p class="color1 f-fs16 mb-7 items"><span class="item">'+car.id+'</span><span class="color2 f-fs14">返回</span></p>');
                                    img="../images/che_fanhui_img.png";
                                }else {
                                    html.push('<p class="color1 f-fs16 mb-7 items"><span class="item">'+car.id+'</span><span class="f-fs14">异常</span></p>');
                                }
                                html.push('<p class="mb-7">');
                                html.push('<span class="color4">随车手机：</span>');
                                html.push('<span>'+car.phone+'</span>');
                                html.push('</p>');
                                html.push('<p class="mb-7">');
                                html.push('<span class="color4">归属地点：</span>');
                                html.push('<span>'+car.initAddress+'</span>');
                                html.push('</p>');
                                html.push('</div>');
                                html.push('</div>');
                                if (car.dutyList&&car.dutyList.length>0) {
                                    $.each(car.dutyList, function (id1, item1) {
                                        if ((id1+1)%2==0){
                                            html.push('<div style="padding: 6px 0;">');
                                        }else{
                                            html.push('<div class="bg-color1" style="padding: 6px 0;">');
                                        }
                                        html.push('<img class="car-img" src="../images/moren_touxiang_img.png" style="height: 42px;padding-left: 30px;">');
                                        html.push('<div class="overflow">');
                                        html.push('<p>');
                                        html.push('<span class="color4">'+item1.dutyRole+'：</span>');
                                        html.push('<span>'+item1.dutyName+'</span>');
                                        html.push('</p>');
                                        html.push('<p>');
                                        html.push('<span class="color4">联系方式：</span>');
                                        html.push('<span>'+item1.dutyPhone+'</span>');
                                        html.push('</p>');
                                        html.push('</div>');
                                        html.push('</div>');
                                    });
                                }
                                html.push('</div>');
                                html.push('</div>');
                                var myIcon = new BMap.Icon(img, new BMap.Size(80,40));
                                var marker = new BMap.Marker(baiduPoint,{icon:myIcon});  // 创建标注
                                marker.setTitle(car.id);
                                map.addOverlay(marker);              // 将标注添加到地图中
                                var infoWindow = new BMap.InfoWindow(html.join(""),{width:320});
                                marker.addEventListener("click", function(){
                                    this.openInfoWindow(infoWindow);
                                });
                                if (car.status=="onWay"&&car.attendance){
                                    if (parseInt(map.getDistance(baiduPoint,new BMap.Point(car.attendance.longitude,car.attendance.latitude))) <= range){
                                        updateStatus(car.id,"1");
                                    }
                                }else if (car.status=="arrival"&&car.attendance){
                                    if (parseInt(map.getDistance(baiduPoint,new BMap.Point(car.attendance.longitude,car.attendance.latitude))) > range){
                                        updateStatus(car.id,"2");
                                    }
                                }else if (car.status == "back"){
                                    if (parseInt(map.getDistance(baiduPoint,new BMap.Point(car.initLongitude,car.initLatitude))) <= range){
                                        updateStatus(car.id,"3");
                                    }
                                }
                                getAddress(item.latest_location.longitude,item.latest_location.latitude,item.entity_name);
                            }
                        });
                    }
                    if(point){
                        map.centerAndZoom(point, 14);
                        var myIcon = new BMap.Icon("../images/biaoji_yixuan_icon.png", new BMap.Size(20,32));
                        var marker = new BMap.Marker(point,{icon:myIcon});// 创建标注
                        marker.setTitle("place");
                        var infoWindow = new BMap.InfoWindow($(".weizhi-bg .item").html());
                        marker.addEventListener("click", function(){
                            this.openInfoWindow(infoWindow);
                        });
                        map.addOverlay(marker);// 将标注添加到地图中
                    }
                }else{
                    layer.msg(data.message);
                }
            },
            error:function(XMLHttpRequest, textStatus, errorThrown) {
                layer.msg('获取实时位置失败');
            }
        });
    }

    //通过经纬度获取实时位置
    function getAddress(lng,lat,entityName){
        var url = "http://api.map.baidu.com/geocoder/v2/?ak=1Gi8rVhlLxpYHeFoIw3AoZWdTcPzWass" +
            "&callback=renderReverse" +
            "&location=" + lat + "," + lng +
            "&output=json" +
            "&pois=1";
        $.ajax({
            type: "GET",
            dataType: "jsonp",
            url: url,
            success: function (json) {
                if (json == null || typeof (json) == "undefined") {
                    return;
                }
                if (json.status != "0") {
                    return;
                }
                var district = json.result.addressComponent.district;
                $("#car li[entity-name="+entityName+"]").find(".position").html(district+json.result.pois[0].name);
                $("#car li[entity-name="+entityName+"]").attr("lng",lng);
                $("#car li[entity-name="+entityName+"]").attr("lat",lat);
                if(carList&&carList.length>0){
                    $.each(carList,function (id,item) {
                        if(item.car.entityName==entityName){
                            item.car.position=district+json.result.pois[0].name;
                            item.car.lng = lng;
                            item.car.lat = lat;
                            i++;
                        }
                    });
                }
                if (i == carList.length){//实际地址获取完成之后获取车辆排序
                    sortCar();
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                layer.msg("地址位置获取失败");
            }
        });
    }

    //车辆排序列表
    function sortCar(){
        getDistance();
        if(sortList&&sortList.length>0){
            var html = [];
            $.each(sortList,function (id,item) {
                html.push('<li class="car" data-id="'+item.car.id+'" entity-name="'+item.car.entityName+'" lng="'+item.car.lng+'" lat="'+item.car.lat+'">');
                html.push('     <div class="overflow">');
                html.push('           <img class="car-img" src="../images/car.png">');
                html.push('           <div class="overflow">');
                html.push('                 <p class="color1 f-fs16 mb-7 items"><span class="item">'+item.car.id+'</span>' +
                    '<span class="color4">距离：</span><span class="main-color">'+changeDistance(item.car.distance)+'</span></p>');
                html.push('                 <p class="mb-7">');
                html.push('                       <span class="item-left">随车手机</span>');
                html.push('                       <span>'+item.car.phone+'</span>');
                html.push('                  </p>');
                html.push('                  <p class="mb-7">');
                html.push('                        <span class="item-left">归属片区</span>');
                html.push('                        <span>'+item.car.district+'</span>');
                html.push('                  </p>');
                html.push('                  <p class="mb-7">');
                html.push('                         <span class="item-left">状态</span>');
                if (item.car.status=="wait"){
                    html.push('                         <span class="main-color">待命</span>');
                }else if (item.car.status=="onWay"||item.car.status=="arrival"){
                    html.push('                         <span class="color3">出勤</span>');
                }else if (item.car.status=="back"){
                    html.push('                         <span class="color2">返回</span>');
                }else {
                    html.push('                         <span>异常</span>');
                }
                html.push('                  </p>');
                if (item.car.status=="wait"){
                    html.push('<button type="button" class="btn btn-primary mb-10" style="padding: 3px 9px;">派遣救援车</button>');
                }
                html.push('            </div>');
                html.push('      </div>');

                html.push('      <div class="items">');
                html.push('           <span><i class="weizhi"></i>所在位置：</span>');
                html.push('           <span class="item position">'+item.car.position+'</span>');
                html.push('           <span class="dingwei main-color" style="cursor: pointer;">定位</span>');
                html.push('      </div>');
                html.push(' </li>');
            });
            $("#car-list ul").html(html.join(""));
        }
    }
    //更改状态
    function updateStatus(carId,status){
        apiServer.attendanceUpdate({
            data:{
                carId: carId,
                status: status
            }
        }).then(function (res) {
            if (res.successFlg) {
                if (status == "1"){
                    layer.msg(carId+"已经到达事发地点");
                }else if (status == "2"){
                    layer.msg(carId+"已经离开事发地点");
                }else if (status == "3"){
                    layer.msg(carId+"已经返回归属地点");
                }
            }else {
                layer.msg(res.errorMsg);
            }
        });
    }
    //计算距离
    function getDistance(){
        sortList = [];
        if(carList&&carList.length>0){
            $.each(carList,function (id,item) {
                var pointA = new BMap.Point(item.car.lng,item.car.lat);
                var distance = parseInt(map.getDistance(pointA,point));
                item.car.distance = distance;
                sortList.push(item);
                // if(distance<1000){
                //     item.car.distance = distance+"m";
                // }else{
                //     item.car.distance = toDecimal(distance/1000)+"km";
                // }
            });
        }
        sortList.sort(function(a,b){
            var s1=0
            var s2=0;
            if (a.car.status=="wait"){
                s1 = 0;
            }else if (a.car.status=="onWay"||a.car.status=="arrival"){
                s1 = 1;
            }else if (a.car.status=="back"){
                s1 = 2;
            }else {
                s1 = 3;
            }

            if (b.car.status=="wait"){
                s2 = 0;
            }else if (b.car.status=="onWay"||b.car.status=="arrival"){
                s2 = 1;
            }else if (b.car.status=="back"){
                s2 = 2;
            }else {
                s2 = 3;
            }
            if (s1 == s2){
                return a.car.distance-b.car.distance;
            }else{
                return s1-s2;
            }
        });
    }

    function formReset(){
        $("#carId").val("");
        $("#dispatchHospital").val("");
        $("#callAddress").val("");
        $("#alarmTel").val("");
        $("#chiefComplaint").val("");
        $("#patientNum").val("");
        $("#patientGender").val("");
        $("#disease").val("");
        $("#deliverAddress").val("");
        $("#remark").val("");
    }
    //根据entity_name获取车辆
    function getByEntityName(entityName){
        var car = null;
        if (carList&&carList.length>0){
            $.each(carList,function (id,item) {
                if (item.car.entityName == entityName){
                    car = item.car;
                }
            });
        }
        return car;
    }

    //根据随车手机获取车辆
    function getByPhone(phone){
        var car = null;
        if (carList&&carList.length>0){
            $.each(carList,function (id,item) {
                if (item.car.phone == phone){
                    car = item.car;
                }
            });
        }
        return car;
    }
    function checkPhone (phone){
        var reg= /(^1[3|4|5|7|8][0-9]{9}$)/;
        return reg.test(phone);
    }

    function toDecimal(x) {
        var f = parseFloat(x);
        if (isNaN(f)) {
            return;
        }
        f = Math.round(x*10)/10;
        return f;
    }
    function changeDistance(distance){
        var i= parseInt(distance);
        if(i<1000){
            return i+"m";
        }else{
            return toDecimal(i/1000)+"km";
        }
    }
    //公共类
    window._apiServer = apiServer;
    window._SHOWTAB = showTab;
});