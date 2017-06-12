$(document).ready(function () {
    $(".dataTables-example").dataTable();
    var a = $("#editable").dataTable();
    a.$("td").editable("../example_ajax.php", {
        "callback": function (d, c) {
            var b = a.fnGetPosition(this);
            a.fnUpdate(d, b[0], b[1])
        }, "submitdata": function (c, b) {
            return {"row_id": this.parentNode.getAttribute("id"), "column": a.fnGetPosition(this)[2]}
        }, "width": "90%", "height": "100%"
    })

    //获取用户列表

});
function fnClickAddRow() {
    $("#editable").dataTable().fnAddData(["Custom row", "New row", "New row", "New row", "New row"])
}
