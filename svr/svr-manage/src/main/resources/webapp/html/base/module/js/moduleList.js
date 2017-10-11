var userCode = getUserCode();
var saasId = getSaasId();
$(function () {
    var treeGridUrl = server+'/bases/module/list';
    easyloader.load('treegrid',function(){
        $('#list').treegrid({
            idField:'code',
            striped:true,
            treeField:'name',
            initialState:"collapsed",
            fitColumns:true,
            fit:true,
            pagination: true,
            pageSize: 10,
            pageList: [10,15,20],
            columns:[[
                {title:'模块名称',field:'name',width:'15%'},
                {title:'创建时间',field:'createTime',width:"15%",formatter:function(val,row,index){
                    var time = row.createTime;
                    if(time!=null && time!=undefined){
                        return  moment(row.createTime).format("YYYY-MM-DD HH:mm:ss");
                    }
                    return "";
                }},
                {title:'操作',field:'aaaaa',width:"30%",formatter:function(val,row,index){
                    return '<button type="button" class="btn btn-success" onclick="add(\''+row.code+'\')">添 加</button>' +
                        '<button type="button" class="btn btn-success" style="margin-left: 15px;" onclick="show(\''+row.code+'\',\'' +row.parentCode+  '\')">查 看</button>' +
                        '<button type="button" class="btn btn-success" style="margin-left: 15px;" onclick="del(\''+row.code+'\')">删 除</button>' +
                        '<button type="button" class="btn btn-success" style="margin-left: 15px;" onclick="relateFun(\''+row.code+'\')">功能配置</button>';
                }}
            ]],
            loader:function(param,success,error){
                var id = param.id;
                if(id!=undefined){
                    treeGridUrl =server+ '/bases/module/children/'+ id;
                }else{
                    treeGridUrl = server+'/bases/module/list';
                }
                param.name=$("#name").val();
                do_get(treeGridUrl,param,function(data){
                    success(data);
                });
            }
        });

    });
    easyloader.locale = "zh_CN";

});

$('body').on('hidden.bs.modal', '.modal', function () {
    $(this).removeData('bs.modal');
});

//查看
function show(code,parentCode){

    $("#myModalDialog").css("width","500px");
    $("#myModal").attr("data-parentCode",parentCode);
    $("#myModal").attr("data-code",code);

    $("#myModal").modal({
        remote: "detail.html",
        show:true
    });
}

function del(codes){
    //首先判断是否有子节点,有子节点不可删
    do_get(server+'/bases/module/children/'+ codes,{},function(data){
        if(data.length>0){
            alert("该功能含有子模块,不能删除")
            return;
        }
        var url = server+"/bases/module/"+codes;
        do_delete(url,{},function(data){
            alert("删除成功");
            $('#list').treegrid('reload');
        })
    })
}

//添加功能
function add(parentCode){
    $("#myModalDialog").css("width","500px");
    $("#myModal").attr("data-parentCode",parentCode);
    $("#myModal").attr("data-code","");
    $("#myModal").modal({
        remote: "detail.html",
        show:true
    });
    $('#myModal').modal('show');

}

function search(){
    $('#list').treegrid('reload');
}
function clearSearch(){
    $("#name").val("");
}

function relateFun(code){
    $("#myModal").attr("data-code",code);
    $("#myModalDialog").css("width","250px");
    $("#myModal").modal({
        remote: "moduleFunc.html",
        show:true
    });
}
