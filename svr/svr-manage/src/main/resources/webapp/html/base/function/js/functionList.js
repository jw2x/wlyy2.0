var userCode = getUserCode();
var saasId = getSaasId();
$(function () {
    var treeGridUrl = server+'/bases/function/list';
    easyloader.load('treegrid',function(){
        $('#list').treegrid({
            idField:'code',
            striped:true,
            treeField:'name',
            initialState:"collapsed",
            fitColumns:true,
            pagination: true,
            fit:true,
            pageSize: 10,
            pageList: [10,15,20],
            columns:[[
                {title:'功能名称',field:'name',width:'15%'},
                {title:'创建时间',field:'createTime',width:"15%",formatter:function(val,row,index){
                    var time = row.createTime;
                    if(time!=null && time!=undefined){
                        return  moment(row.createTime).format("YYYY-MM-DD HH:mm:ss");
                    }
                    return "";
                }},
                {title:'操作',field:'aaaaa',width:"20%",formatter:function(val,row,index){
                    return '<button type="button" class="btn btn-success" onclick="add(\''+row.code+'\')">添 加</button><button type="button" class="btn btn-success" style="margin-left: 15px;" onclick="show(\''+row.code+'\',\'' +row.parentCode+  '\')">查 看</button><button type="button" class="btn btn-success" style="margin-left: 15px;" onclick="del(\''+row.code+'\')">删 除</button>';
                }}
            ]],
            loader:function(param,success,error){
                var id = param.id;
                if(id!=undefined){
                    treeGridUrl =server+ '/bases/function/children/'+ id;
                }else{
                    treeGridUrl = server+'/bases/function/list';
                }
                param.name=$("#name").val();
                do_get(treeGridUrl,param,function(data){
                    var rows = data.rows;
                    if(rows==undefined){  //因为请求不同的url,返回不同的data
                        rows = data;
                    }
                    for(var i =0;i<rows.length;i++){
                        if(rows[i].children.length>0){
                            rows[i].state="closed";
                            rows[i].children=[];
                        }else{
                            rows[i].state="open";
                            rows[i].children=[];
                        }
                    }
                    success(data);

                });
            }
        });

    });
    easyloader.locale = "zh_CN";
});

//查看
function show(code,parentCode){
    $('body').on('hidden.bs.modal', '.modal', function () {
        $(this).removeData('bs.modal');
    });
    $("#myModal-add-info").attr("data-parentCode",parentCode);
    $("#myModal-add-info").attr("data-code",code);

    $("#myModal-add-info").modal({
        remote: "detail.html",
        show:true
    });
}

function del(codes){
    //首先判断是否有子节点,有子节点不可删
    do_get(server+'/bases/function/children/'+ codes+"?userCode="+userCode+"&saasId="+saasId,{},function(data){
        if(data.length>0){
            alert("该功能含有子功能,不能删除")
            return;
        }
        var url =server+ "/bases/function/"+codes;
        do_delete(url,{},function(data){
            alert("删除成功");
            $('#list').treegrid('reload');
        })
    })
}


//添加功能
function add(parentCode){
    $('body').on('hidden.bs.modal', '.modal', function () {
        $(this).removeData('bs.modal');
    });
    $("#myModal-add-info").attr("data-parentCode",parentCode);
    $("#myModal-add-info").attr("data-code","");
    $("#myModal-add-info").modal({
        remote: "detail.html",
        show:true
    });
    $('#myModal-add-info').modal('show');

}

function search(){
    $('#list').treegrid('reload');
}
function clearSearch(){
    $("#name").val("");
}
