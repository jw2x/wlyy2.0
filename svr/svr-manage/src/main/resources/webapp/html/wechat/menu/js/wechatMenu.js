var userCode = getUserCode();
var saasId = getSaasId();
$(function () {
    var treeGridUrl;
    var isWechat;
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
                {title:'微信/菜单名',field:'name',width:"15%"},
                {title:'类型',field:'type',width:"15%",formatter:function(val,row,index){
                    if(row.appId!=undefined){
                        return "微信号";
                    }
                    if(row.supMenucode==0){
                        return "父菜单";
                    }
                    return "子菜单";
                }},
                {title:'创建时间',field:'createTime',width:"15%",formatter:function(val,row,index){
                    var time = row.createTime;
                    if(time!=null&&time!=undefined){
                        return  moment(row.createTime).format("YYYY-MM-DD HH:mm:ss");
                    }
                    return "";
                }},
                {title:'操作',field:'aaaaa',width:"20%",formatter:function(val,row,index){
                    var isMenu = 1;//1是菜单
                    if(row.appId!=undefined){
                        isMenu = 0;
                        return '<button class="btn btn-success" onclick="add('+isMenu+",'"+row.code+'\')">添 加</button>';
                    }
                    if(row.supMenucode==0){
                        return '<button class="btn btn-success" onclick="add('+isMenu+",'"+row.code+'\')">添 加</button><button type="button" class="btn btn-success" style="margin-left: 15px;" onclick="show('+isMenu+",\'"+row.code+'\')">查 看</button><button type="button" class="btn btn-success" style="margin-left: 15px;" onclick="del('+isMenu+",\'"+row.code+'\')">删 除</button>';
                    }else{
                        return '<button class="btn btn-success" onclick="show('+isMenu+",\'"+row.code+'\')">查 看</button><button type="button" class="btn btn-success" style="margin-left: 15px;" onclick="del('+isMenu+",\'"+row.code+'\')">删 除</button>';
                    }
                }}
            ]],
            onBeforeExpand:function(row, param){
                if(row.wechatCode!=undefined){
                    isWechat=false;
                }else{
                    isWechat=true;
                }
            },
            loader:function(param,success,error){
                var id = param.id;
                if(id!=undefined){
                    if(isWechat){
                        treeGridUrl =server+ '/wechat/parentMenu/'+ id;
                    }else{
                        treeGridUrl =server+ '/wechat/childMenu/list/'+ id;
                    }
                }else{
                    treeGridUrl = server+'/wechat/menu/list';
                    param.name=$("#name").val();
                }
                debugger
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
function add(isMenu,code){
    if(isMenu==1){//是菜单
        $("#myModal-add-info").attr("data-code","");//当前菜单code,用于显示详情
        $("#myModal-add-info").attr("data-parentCode",code);//微信code
        $("#myModal-add-info").modal({
            remote: "detail.html",
            show:true
        });
        return
    }else{//微信添加,则为添加父菜单
        $("#myModal-add-info").attr("data-code","");//当前菜单code,用于显示详情
        $("#myModal-add-info").attr("data-wechatCode",code);//微信code
        $("#myModal-add-info").attr("data-parentCode","0");//父菜单code
        $("#myModal-add-info").modal({
            remote: "detail.html",
            show:true
        });
    }
}

function show(isMenu,code){
    $("#myModal-add-info").attr("data-code",code);
    $("#myModal-add-info").modal({
        remote: "detail.html",
        show:true
    });
}

function del(isMenu,code){
    var url = server+"/wechat/menu/"+code;
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


