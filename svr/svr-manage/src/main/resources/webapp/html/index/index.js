//获取用户的菜单和角色
var contentVM;
do_get(
    "/index",
    {},
    function (data) {
        if (data.successFlg == true) {
            contentVM = new Vue({
                el: '#wrapper',
                data: {
                    role: '',
                    menus: '',
                    aaa: 'aaa'
                },
                updated: function () {
                    //渲染结束之后调用菜单初始化
                    $('#side-menu').metisMenu();
                }
            });
            contentVM.role = data.obj.role;
            contentVM.menus = data.obj.menus;

        } else {
            //判断是否登陆
            isLogin(data);
            alert("获取角色,菜单失败")
        }
    }
)
/**
 * 退出
 */
function loginout() {
    do_get(
        "/loginout",
        {},
        function (data) {
            if (data.successFlg == true) {
                //清空用户缓存
                cleanUserCode();
                alert("退出成功")
                window.location.href = '/login/login.html';
            }
        }
    )
}
