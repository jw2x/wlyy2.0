//获取用户的菜单和角色
var contentVM;
do_get(
    "/index",
    {},
    function(data){
        if(data.successFlg==true){
            contentVM = new Vue({
                el: '#wrapper',
                data: {
                    role: '',
                    menus:'',
                    aaa:'aaa'
                }
            });
            contentVM.role = data.obj.role;
            contentVM.menus = data.obj.menus;
        }else{
            alert("获取角色,菜单失败")
        }
    }
)

