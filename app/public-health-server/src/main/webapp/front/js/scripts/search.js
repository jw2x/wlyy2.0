$(document).ready(function(){
        laydate({
            elem: '#startDate',
            format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
            festival: true //显示节日
        });
        laydate({
            elem: '#endDate',
            format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
            festival: true //显示节日
        });
        var table = $('#dateTable').DataTable({
            "dom": '<"top">rt<"bottom"flip><"clear">',
            "autoWidth": true,                      // 自适应宽度
            "stateSave": true,                      // 刷新后保存页数
            "ordering" :false,
            "searching": false,                     // 本地搜索
            "info": false,                           // 控制是否显示表格左下角的信息
            "stripeClasses": ["odd", "even"],       // 为奇偶行加上样式，兼容不支持CSS伪类的场合
            "pagingType": "simple_numbers",         // 分页样式 simple,simple_numbers,full,full_numbers
            "language": {                           // 国际化
                "url":'../js/lib/plugins/dataTables/language.json'
            },
            "serverSide":true,                        //开启服务器模式
            "deferRender": true,                    // 当处理大数据时，延迟渲染数据，有效提高Datatables处理能力
            "sServerMethod" : "GET",
            ajax: {
                url: "http://localhost:9099/emergency/attendance/list",
                data : function(data) {
                    var filters="";
                    if ($("#startDate").val()!=""){
                        filters+="createDate>="+$("#startDate").val()+";";
                    }
                    if ($("#endDate").val()!=""){
                        filters+="createDate<="+$("#endDate").val()+";";
                    }
                    if ($("#carId").val()!=""){
                        filters+="carId?"+$("#carId").val()+";";
                    }
                    if ($("#callAddress").val()!=""){
                        filters+="callAddress?"+$("#callAddress").val()+";";
                    }
                    if ($("#deliverAddress").val()!=""){
                        filters+="deliverAddress?"+$("#deliverAddress").val()+";";
                    }
                    data.filters=filters;
                }
            },
            "columns": [                            // 自定义数据列
                {data: 'createDate'},
                {data: 'alarmTel'},
                {data: 'callAddress'},
                {data: 'carId'},
                {data: 'deliverAddress'},
                {data:function(obj){
                    return  '<a title="查看" class="btn-edit pr-10" ><i class="glyphicon glyphicon-eye-open"></i></a>' +
                        '<a title="查看轨迹" class="btn-view" ><i class="glyphicon glyphicon-map-marker"></i></a>';
                },sClass:'text-center'}

            ]
        }).on('click', '.btn-edit', function (e) {
            e.stopPropagation();
            var row = table.row($(this).parents('tr')).data();
            var index =layer.open({
                type: 2,
                area: ['800px', $(window).height()-100+'px'],
                fix: false, //不固定
                maxmin: false,
                shadeClose: false,
                shade:0.4,
                title: "急救信息详情",
                content: "detail.html?id="+row.id
            });
            layer.full(index);
        }).on('click', '.btn-view', function (e) {
            e.stopPropagation();
            var row = table.row($(this).parents('tr')).data();
            var index =layer.open({
                type: 2,
                area: ['800px', $(window).height()-100+'px'],
                fix: false, //不固定
                maxmin: false,
                shadeClose: false,
                shade:0.4,
                title: "急救车辆轨迹",
                content: "trace.html?id="+row.id
            });
            layer.full(index);
        });

        //查询
        $("#searchBtn").on('click',function(){
            table.ajax.reload();
        });
        $("input").on("keypress",function(event){
            if(event.keyCode == 13) {
                table.ajax.reload();
            }
        });
    });
