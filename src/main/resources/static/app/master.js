//var controller = siteRoot + 'configuracion/default.aspx/';

$(function () {
   // SetDataInfo();
});

/*
function SetDataInfo() {

    $.ajax({
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        url: siteRoot + 'Default.aspx/GetUserInfo',
        dataType: "json",
        success: function (dataObject, textStatus) {
            if (textStatus == "success") {
                var data = dataObject.d;
                if (data != null) {
                    $("#lblNavUsername").html(data.FullName);
                    $("#lblUsername").html(data.FullName);
                }
            }
        },
        error: function (xhr, ajaxOptions, thrownError) {
            var error = JSON.parse(xhr.responseText);
        },
        complete: function (data) {

        }
    });

}
function CloseSession() {

    $.ajax({
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        url: + 'Default.aspx/CloseSession',
        dataType: "json",
        success: function (dataObject, textStatus) {
            if (textStatus == "success") {

                window.location.href = siteRoot + 'Login.aspx';
            }
        },
        error: function (xhr, ajaxOptions, thrownError) {
            var error = JSON.parse(xhr.responseText);
        },
        complete: function (data) {

        }
    });

}*/