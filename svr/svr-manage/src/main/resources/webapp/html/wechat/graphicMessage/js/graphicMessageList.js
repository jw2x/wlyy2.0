$(document).ready(function () {
    //初始化用户列表
    $("#graphicMessageList").dataTable({
        serverSide: true, //开启服务器模式
        paging: true,//是否显示分页栏，默认是true
        searching: false, //禁用搜索和排序 默认false
        ordering: false,//禁止排序 默认false
        ajax: {
            url: '/graphicMessage/list',
            type: 'GET',
            dataSrc: "detailModelList"
        },
        columns: [
            {data: 'detailModelList.name'},
            {data: 'detailModelList.code'},
            {data: 'detailModelList.mobile'}
        ],
        "columnDefs": [  //隐藏第几列  下标从0开始
            {
                "targets": [0, 2],
                "visible": false,
                "searchable": false
            }
        ],
        "order": [[1, 'asc']]  //索引行
    });
});

function haha(){
    $.ajax({
        url:"/graphicMessage/getByCode?code=1",
        success:function(data){
            debugger
        }
    });
}


