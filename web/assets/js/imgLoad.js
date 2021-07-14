
function fileChange() {
    var file = document.getElementById("file");
    var form = new FormData();
    form.append("image", file.files[0]);
    var settings = {
        url: "https://api.imgbb.com/1/upload?key=784d2b960951253fd254d55db5ad59e0",
        method: "POST",
        timeout: 0,
        processData: false,
        mimeType: "multipart/form-data",
        contentType: false,
        data: form,
    };

    $.ajax(settings).done(function (response) {
        var jx = JSON.parse(response);
        var img = jx.data.url;
        document.getElementById("img").setAttribute("src", img);
    });
}

