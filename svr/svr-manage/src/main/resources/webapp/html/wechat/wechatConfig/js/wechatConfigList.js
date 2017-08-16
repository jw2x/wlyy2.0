var table;
var userCode = getUserCode();
var saasId = getSaasId();
$(function () {
    table = $("#list").DataTable({
            "aLengthMenu": [10,15,20],
            "searching": false,//禁用搜索
            "lengthChange": true,
            "paging": true,//开启表格分页
            "bProcessing": true,
            "bServerSide": true,
            "bAutoWidth": false,
            "sort": "position",
            "deferRender": true,//延迟渲染
            "bStateSave": false, //在第三页刷新页面，会自动到第一页
            "iDisplayLength": 10,//每页显示条数
            "iDisplayStart": 0, //当前页
            "dom": '<l<\'#topPlugin\'>f>rt<ip><"clear">',
            "ordering": false,//全局禁用排序
            "ajax": {
                url: server+'/wechat/wechatConfig/list',
                data: function (d) {
                    d.name = $("#name").val();
                    d.userCode = userCode;
                    d.saasId = saasId;
                },
                type: 'GET',
                dataSrc: "detailModelList"
            },
            "aoColumns": [
                {
                    "mData": "code",
                    "orderable": false, // 禁用排序
                    "sDefaultContent": "",
                    "sWidth": "2%"
                },
                {
                    "mData": 'name',
                    "sWidth": "10%",
                    "orderable": false // 禁用排序
                },
                {
                    "mData": 'appId',
                    "sWidth": "10%",
                    "orderable": false // 禁用排序
                },
                {
                    "mData": 'type',
                    "sWidth": "10%",
                    "orderable": false, // 禁用排序
                    "render": function (data, type, full, meta) {
                        if (data == 1) {
                            data = "服务号";
                        } else {
                            data = "订阅号";
                        }
                        return data;
                    }
                },
                {
                    "mData": 'status',
                    "sWidth": "10%",
                    "orderable": false, // 禁用排序
                    "render": function (data, type, full, meta) {
                        if (data == -1) {//类型 -1 已删除 0待审核 1审核通过 2 审核不通过
                            data = "已删除";
                        } else if (data == 0) {
                            data = "待审核";
                        } else if (data == 1) {
                            data = "审核通过";
                        } else if (data == 2) {
                            data = "审核不通过";
                        }
                        return data;
                    }
                },
                {
                    "mData": 'createTime',
                    "sWidth": "10%",
                    "orderable": false, // 禁用排序
                    "render": function (data, type, full, meta) {
                        //时间格式化
                        return moment(data).format("YYYY-MM-DD HH:mm:ss");
                    }
                },
                {
                    "mData": "code",
                    "orderable": false, // 禁用排序
                    "sDefaultContent": '',
                    "sWidth": "10%",
                    "render": function (data, type, full, meta) {
                        return data = '<button class="btn btn-primary  btn-sm" data-id=' + data + ' onclick="show(\''+data+'\')">查 看</button>';/*<button class="btn btn-danger btn-sm" style="margin-left: 7px;" data-id=' + data + '>删 除</button>*/

                    }
                }
            ],
            "columnDefs": [{
                "orderable": false, // 禁用排序
                "targets": [0], // 指定的列
                "data": "code",
                "render": function (data, type, full, meta) {
                    return '<input type="checkbox" value="' + data + '" name="code"/>';
                }
            }],
            "oLanguage": { // 国际化配置
                "sProcessing": "正在获取数据，请稍后...",
                "sLengthMenu": "显示 _MENU_ 条",
                "sZeroRecords": "没有找到数据",
                "sInfo": "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",
                "sInfoEmpty": "记录数为0",
                "sInfoFiltered": "(全部记录数 _MAX_ 条)",
                "sInfoPostFix": "",
                "sSearch": "搜索",
                "sUrl": "",
                "oPaginate": {
                    "sFirst": "第一页",
                    "sPrevious": "上一页",
                    "sNext": "下一页",
                    "sLast": "最后一页"
                }
            },
            initComplete: initComplete,
            drawCallback: function (settings) {
                $('input[name=checkAll]')[0].checked = false;//取消全选状态
            }
        }
    );

    /**
     * 表格加载渲染完毕后执行的方法
     * @param data
     */
    function initComplete(data){
        //删除用户按钮的HTMLDOM
        var topPlugin='<button   class="btn btn-danger btn-sm" id="deleteAll">批量删除</button> <button   class="btn btn-primary btn-sm addBtn" >新 增</button>       <button  class="btn btn-warning btn-sm" id="reset">重置搜索条件</button>' ;

        $("#topPlugin").append(topPlugin);//在表格上方topPlugin DIV中追加HTML
    }

    /**
     * 多选选中和取消选中,同时选中第一个单元格单选框,并联动全选单选框
     */
    $('#list tbody').on('click', 'tr', function(event) {
        var checkAll=$('input[name=checkAll]')[0];//关联全选单选框
        $($(this).children()[0]).children().each(function(){
            if(this.type=="checkbox" && (!$(event.target).is(":checkbox") && $(":checkbox",this).trigger("click"))){
                if(!this.checked){
                    this.checked = true;
                    addValue(this);
                    var selected=table.rows('.selected').data().length;//被选中的行数
                    //全选单选框的状态处理
                    var recordsDisplay=table.page.info().recordsDisplay;//搜索条件过滤后的总行数
                    var iDisplayStart=table.page.info().start;// 起始行数
                    if(selected === table.page.len()||selected === recordsDisplay||selected === (recordsDisplay - iDisplayStart)){
                        checkAll.checked = true;
                    }
                }else{
                    this.checked = false;
                    cancelValue(this);
                    checkAll.checked = false;
                }
            }
        });
        $(this).toggleClass('selected');//放在最后处理，以便给checkbox做检测
    });


    /**
     * 全选按钮被点击事件
     */
    $('input[name=checkAll]').click(function(){
        if(this.checked){
            $('#list tbody tr').each(function(){
                if(!$(this).hasClass('selected')){
                    $(this).click();
                }
            });
        }else{
            $('#list tbody tr').click();
        }
    });

    /**
     * 单选框被选中时将它的value放入隐藏域
     */
    function addValue(para) {
        var wechatCodes = $("input[name=wechatCodes]");
        if(wechatCodes.val() === ""){
            wechatCodes.val($(para).val());
        }else{
            wechatCodes.val(wechatCodes.val()+","+$(para).val());
        }
    }

    /**
     * 单选框取消选中时将它的value移除隐藏域
     */
    function cancelValue(para){
        //取消选中checkbox要做的操作
        var wechatCodes = $("input[name=checkAll]");
        var array = wechatCodes.val().split(",");
        wechatCodes.val("");
        for (var i = 0; i < array.length; i++) {
            if (array[i] === $(para).val()) {
                continue;
            }
            if (wechatCodes.val() === "") {
                wechatCodes.val(array[i]);
            } else {
                wechatCodes.val(wechatCodes.val() + "," + array[i]);
            }
        }
    }


    $(document).delegate('.addBtn','click',function() {
        contentVM.wechatConfig='';
        $('#myModal-add-info').modal('show');
       setTimeout(function(){
           $(':input','#myModal-add-info')
               .not(':button, :submit, :reset, :hidden')
               .val('')
               .removeAttr('checked');
        },200);


    });


    //批量删除
    $(document).delegate('#deleteAll','click',function() {
        var theArray=[];
        $("input[name=code]:checked").each(function() {
            theArray.push($(this).val());
        });
        if(theArray.length<1){
            alert("请至少选择一个");
        }else{
            var codes = theArray.join(",");
            del(codes);
        }

    });

    //清空查询条件
    $(document).delegate('#reset','click',function() {
        $("#name").val("");
    });

    //重新查询
    $(document).delegate('.search', 'click', function () {
        table.ajax.reload();
    });
});

var contentVM = new Vue({
    el: '#category_add',
    data: {
        wechatConfig: ''//记录详情信息
    },
    replace:false
});

//查看配置
function show(code){
    $("#myModal-add-info").removeData("modal");
    var data={};
    do_get(server+"/wechat/wechatConfig/"+code,data,function(data){
        contentVM.wechatConfig = data.obj;
        $('#myModal-add-info').modal('show');
        $("#myModalLabel").html("查看");
    },function(data){

    });
}

function del(codes){
    var url = server+"/wechat/wechatConfig/"+codes;
    do_delete(url,{},function(data){
        alert("删除成功");
        table.ajax.reload();
    })
}

$("#category_add").submit(function(){
    var  url = server+"/wechat/wechatConfig";
    var id = $("#id").val();
    var data = $("#category_add").serialize();
    do_post(url,data,function(data){
        if(data.errorMsg!=undefined){
            alert(data.errorMsg);
            return;
        }
        if(id==''){
            alert("保存成功");
        }else{
            alert("修改成功");
        }
        $('#myModal-add-info').modal('hide');
        table.ajax.reload();
        return;

    })
    return false;
})

