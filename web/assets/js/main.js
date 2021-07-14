function Confirm() {
    //var result = Confirm("Do you want to delete");
    var result = confirm("Are you sure you want to do this ?");
    if (!result) {
        event.preventDefault();
    }
}
function Edit(index, total) {
    for (var i = 0; i < total + 1; i++) {
        if (index === i) {
            var idEdit = document.getElementById("idEdit" + i).value;
            var rankEdit = document.getElementById("rankEdit" + i).value;
            document.getElementById("rank").setAttribute("value", rankEdit);
            document.getElementById("userid").setAttribute("value", idEdit);
        }
    }
}
var total = document.getElementById("total").value;
$(function () {
    for (var i = 0; i < total + 1; i++) {
        $("#click" + i).click(function (e) {
            e.preventDefault();
        });
    }
});