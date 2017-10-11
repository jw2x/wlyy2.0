var userCode = getUserCode();
var saasId = getSaasId();
$(function () {
    var treeGridUrl='';
    easyloader.load('treegrid',function(){
        $('#list').treegrid({
            idField:'code',
            striped:true,
            method:"get",
            treeField:'name',
            initialState:"collapsed",
            fitColumns:true,
            pagination: true,
            fit:true,
            pageSize: 10,
            pageList: [10,15,20],
            columns:[[
                {title:'版本名称',field:'name',width:"15%"},
                {title:'类型',field:'type',width:"15%",formatter:function(val,row,index){
                    if(row.children!=undefined){
                        return "后台版本";
                    }else{
                        return "url版本"
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
                    if(row.children!=undefined){
                        return '<button type="button" class="btn btn-success addBtn" onclick="add(\''+row.code+'\',\'1\')">添 加</button>' +
                            '<button type="button" class="btn btn-success" style="margin-left: 15px;" onclick="show(\''+row.code+'\',\'0\')">查 看</button>' +
                            '<button type="button" class="btn btn-success" style="margin-left: 15px;" onclick="del(\''+row.code+'\',\'0\')">删 除</button>'
                    }else{
                        return '<button type="button" class="btn btn-success" onclick="show(\''+row.code+'\',\'1\')">查 看</button><button type="button" class="btn btn-success" style="margin-left: 15px;" onclick="del(\''+row.code+'\',\'1\')">删 除</button>';
                    }
                }}
            ]],

            loader:function(param,success,error){
                debugger
                var id = param.id;
                if(id!=undefined){
                    treeGridUrl =server+ '/version/serverUrl/listNoPage?serverCode='+ id;
                }else{
                    treeGridUrl = server+'/version/serverVersion/list';
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
    $("#myModal-add-info").attr("data-code","");//当前菜单code,用于显示详情
    $("#myModal-add-info").attr("data-serverCode","");//父菜单code

    var reloadCode =  $("#myModal-add-info").attr("data-reloadCode");//serverCode,判定属于哪个后台版本
    if(reloadCode!=''){
        $("#myModal-add-info").attr("data-reloadCode","");
        if(reloadCode=='#'){
            $('#list').treegrid('reload');
            return
        }
        $('#list').treegrid('reload',reloadCode);
    }
    $(this).removeData('bs.modal');
});

function search(){
    $('#list').treegrid('reload');
}

function clearSearch(){
    $("#name").val("");
}


/**
 *
 * @param code
 * @param type       type 0,后台版本 ,   type 1, url版本
 */
function add(code,type){
    if(type==0){
        $("#myModal-add-info").modal({
            remote: "detail.html",
            show:true
        });
        return
    }else{
        $("#myModal-add-info").attr("data-code","");//当前菜单code,用于显示详情
        $("#myModal-add-info").attr("data-serverCode",code);//父菜单code
        $("#myModal-add-info").modal({
            remote: "urlVersion_detail.html",
            show:true
        });
    }
}

/**
 *
 * @param code
 * @param type       type 0,后台版本 ,   type 1, url版本
 */
function show(code,type){
    $("#myModal-add-info").attr("data-code",code);
    if(type==0){
        $("#myModal-add-info").modal({
            remote: "detail.html",
            show:true
        });
        return
    }else{
        $("#myModal-add-info").modal({
            remote: "urlVersion_detail.html",
            show:true
        });
    }
}
/**
 *
 * @param code
 * @param type       type 0,后台版本 ,   type 1, url版本
 */
function del(code,type){
    var url="";
    if(type==0){
        url = server+"/version/serverVersion/" + code;
    }else{
        url = server+"/version/serverUrl/"+ code;
    }
    do_delete(url,{},function(data){
        if (data.errorMsg != undefined) {
            alert(data.errorMsg);
            return;
        }
        alert("删除成功");
        $('#list').treegrid('reload');
    })
}

function userVersion(code){
    $("#myModal-add-info").attr("data-code",code);//serverCode
    $("#myModal-add-info").modal({
        remote: "userVersion.html",
        show:true
    });
}