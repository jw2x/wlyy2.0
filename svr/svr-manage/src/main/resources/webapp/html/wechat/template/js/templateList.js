var userCode = getUserCode();
var saasId = getSaasId();
$(function () {
    var treeGridUrl;
    easyloader.load('treegrid',function(){
        $('#list').treegrid({
            idField:'code',
            striped:true,
            method:"get",
            treeField:'name',
            initialState:"collapsed",
            pagination: true,
            fit:true,
            pageSize: 10,
            pageList: [10,15,20],
            fitColumns:true,
            columns:[[
                {title:'微信名/模板标题',field:'name',width:"15%"},
                {title:'类型',field:'type',width:"15%",formatter:function(val,row,index){
                    if(row.wechatCode==undefined){
                        return "微信号";
                    }
                    return "模板消息";
                }},
                {title:'创建时间',field:'createTime',width:"15%",formatter:function(val,row,index){
                    var time = row.createTime;
                    if(time!=null&&time!=undefined){
                        return  moment(row.createTime).format("YYYY-MM-DD HH:mm:ss");
                    }
                    return "";
                }},
                {title:'操作',field:'aaaaa',width:"20%",formatter:function(val,row,index){
                    if(row.wechatCode==undefined){
                        return "";
                    }
                    return '<button class="btn btn-success" onclick="show(\''+row.code+'\')">查 看</button><button type="button" class="btn btn-success" style="margin-left: 15px;" onclick="del(\''+row.code+'\')">删 除</button>';
                }}
            ]],
            loader:function(param,success,error){
                var id = param.id;
                if(id!=undefined){
                    treeGridUrl =server+ '/wechat/template/listNoPage?wechatCode='+ id;
                }else{
                    treeGridUrl = server+'/wechat/template/list';
                    param.name=$("#name").val();
                }
                do_get(treeGridUrl,param,function(data){
                    success(data);

                });
            }
        });

    });
    easyloader.locale = "zh_CN";
});

var contentVM = new Vue({
    el: '#category_add',
    data: {
        template: '',//记录详情信息
        wechatConfigs:''//记录saas列表
    },
    replace:false
});


//查看
function show(code){
    $("#myModal-add-info").removeData("modal");
    var data={};
    do_get(server+"/wechat/template/"+code,data,function(data){
        contentVM.template = data.obj;
        $('#myModal-add-info').modal('show');
        $("#myModalLabel").html("查看");
    },function(data){

    });
}

function del(codes){
    var url =server+ "/wechat/template/"+codes;
    do_delete(url,{},function(data){
        alert("删除成功");
        $('#list').treegrid('reload');
    })
}

function search(){
    $('#list').treegrid('reload');
}

function clearSearch(){
    $("#name").val("");
}
/*$("#category_add").submit(function(){
    var url =server+ "/wechat/template";
    var id = $("#id").val();
    var data = $("#category_add").serialize();
    do_post(url,data,function(data){
        if(data.errorMsg!=undefined){
            alert(data.errorMsg);
            return
        }
        if(id==''){
            alert("保存成功");
        }else{
            alert("修改成功");
        }
        $('#myModal-add-info').modal('hide');
        $('#list').treegrid('reload');
        return;
    })
    return false;
})*/
