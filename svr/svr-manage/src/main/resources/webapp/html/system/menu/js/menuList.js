var userCode = getUserCode();
var saasId = getSaasId();
$(function () {
    easyloader.load('treegrid',function(){
        $('#list').treegrid({
            idField:'code',
            striped:true,
            method:"get",
            treeField:'name',
            initialState:"collapsed",
            pagination: true,
            pageSize: 10,
            pageList: [10,15,20],
            fitColumns:true,
            fit:true,
            columns:[[
                {title:'名称',field:'name',width:'15%'},
                {title:'类型',field:'type',width:"15%",formatter:function(val,row,index){
                    if(row.type==1){
                        return "父菜单";
                    }
                    if(row.type==2){
                        return "子菜单";
                    }
                    if(row.type==3){
                        return "功能";
                    }
                }},
                {title:'创建时间',field:'createTime',width:"15%",formatter:function(val,row,index){
                    var time = row.createTime;
                    if(time!=null&&time!=undefined){
                        return  moment(row.createTime).format("YYYY-MM-DD HH:mm:ss");
                    }
                    return "";
                }},
                {title:'操作',field:'aaaaa',width:"20%",formatter:function(val,row,index){
                    if(row.type==0){
                        return '<button type="button" class="btn btn-success" onclick="add('+row.type+","+"0"+')">添 加</button>';
                    }
                    if(row.type==1){
                        return ' <button type="button" class="btn btn-success" onclick="add('+row.type+",'"+row.code+'\')">添 加</button><button type="button" class="btn btn-success" style="margin-left: 15px;" onclick="show('+row.type+",\'"+row.code+'\',\'' +row.parentCode+  '\')">查 看</button><button type="button" class="btn btn-success" style="margin-left: 15px;" onclick="del(\''+row.code+'\')">删 除</button>';
                    }
                    if(row.type==2){
                        return ' <button type="button" class="btn btn-success" onclick="add('+row.type+",'"+row.code+'\')">添 加</button><button type="button" class="btn btn-success" style="margin-left: 15px;" onclick="show('+row.type+",\'"+row.code+'\',\'' +row.parentCode+  '\')">查 看</button><button type="button" class="btn btn-success" style="margin-left: 15px;" onclick="del(\''+row.code+'\')">删 除</button>';
                    }
                    if(row.type==3){
                        return ' <button type="button" class="btn btn-success" onclick="show('+row.type+",'"+row.code+'\',\'' +row.parentCode+  '\')">查 看</button><button type="button" class="btn btn-success" style="margin-left: 15px;" onclick="del(\''+row.code+'\')">删 除</button>';
                    }
                    return "";
                }}
            ]],
            loader:function(param,success,error){
                var treeGridUrl = server+'/manage/menu/list?userCode='+userCode+"&saasId="+saasId;
                param.name=$("#name").val();
                do_get(treeGridUrl,param,function(data){
                    success(data);
                });
            }
        });

    });
    easyloader.locale = "zh_CN";
});
//查看
function show(type,code,parentCode){
    $('body').on('hidden.bs.modal', '.modal', function () {
        $(this).removeData('bs.modal');
    });
    $("#myModal-add-info").attr("data-type",type);
    $("#myModal-add-info").attr("data-parentCode",parentCode);
    $("#myModal-add-info").attr("data-code",code);

    $("#myModal-add-info").modal({
        remote: "detail.html",
        show:true
    });
}

function del(codes){
    var url = server+"/manage/menu/"+codes;
    do_delete(url,{},function(data){
        alert("删除成功");
        $('#list').treegrid('reload');
    })
}


//添加功能
function add(type,parentCode){
    $('body').on('hidden.bs.modal', '.modal', function () {
        $(this).removeData('bs.modal');
    });
    type = type+1;
    $("#myModal-add-info").attr("data-type",type);
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