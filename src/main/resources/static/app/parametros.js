var $table = $('#tblRegistros');
var controlador = _ctx + 'configuracion/parametros/';

$(function () {
    $("#btnGuardar").click(function () {
        actualizar();
    });

    $("#btnCancelar").click(function () {
        limpiarRegistro();
    });

    validarRegistros();
   listarRegistros();
})

function limpiarRegistro() {
    $("#hId").val(0);
    $("#param").val("");
    $("#valor").val("");
}

function irModificarRegistro(id) {
    limpiarRegistro();

    //$("#load_pace").show();
    $.ajax({
        type: 'get',
        contentType: "application/json; charset=utf-8",
        url: controlador + id,
        success: function (dataObject, textStatus) {
            if (textStatus == "success") {

                var registro = dataObject;
                $("#hId").val(registro.id);
                $("#txtParam").val(registro.nombre);
                $("#txtDescription").val(registro.descripcion);
                $("#txtValue").val(registro.valor);
            }
        },
        error: function (xhr, ajaxOptions, thrownError) {
            var error = JSON.parse(xhr.responseText);
        },
        complete: function (data) {
            irRegistro();
            $("#load_pace").show();
        }
    });
}

function linkFormatter(value, row, index) {
 return '<a class="editable editable-click" href="javascript:irModificarRegistro(' + row.id + ')" title="Editar">'+row.nombre+'</a>';

}

function validarRegistros() {
    $("#form_registro").validate({
        ignore: ".ignore",
        errorClass: "my-error-class",
        validClass: "my-valid-class",
        rules: {
            value: {
                required: true,
                maxlength: 300
            },
            description: {
                required: true,
                maxlength: 300
            }
        },
        messages: {
            value: {
                required: "Debes de ingresar un valor",
                maxlength: $.validator.format("Debes ingresar como máximo {0} caracteres"),
            },
            description: {
                required: "Debes de ingresar un valor",
                maxlength: $.validator.format("Debes ingresar como máximo {0} caracteres"),
            }
        }
    });
}

function listarRegistros() {

    $table.bootstrapTable('destroy');
    $table.bootstrapTable({
        url:  controlador,
        method: 'get',
        pagination: true,
        sidePagination: 'server',
        pageSize: 10,
        onLoadSuccess: function (data) {
        },
        onLoadError: function (xhr) {
            exception(xhr);
        },
        responseHandler: function (res) {
             return { rows: res, total: res.length > 0 ?  res[0].rows : 0 };
        }
    });
}

function actualizar() {

  if ($("#form_registro").valid()) {
      var $btn = $("#btnGuardar");
        $btn.button('loading');

      var params = {};
      var id = $("#hId").val();
        params.nombre = $("#txtParam").val();
        params.descripcion = $("#txtDescription").val();
        params.valor = $("#txtValue").val();

        $.ajax({
            type: 'PUT',
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            url: controlador +  id,
            dataType: "json",
            data:  params,
            success: function (dataObject, textStatus) {
                if (textStatus == "success") {
                    //No operativo , tampoco se encuentra en la plataforma
                    //MostrarAlerta("alertBox", "alert-success", "Se actualizó con <strong>éxito</strong> el valor del parámetro.")
                    irListado();
                    $('#form_registro').trigger("reset");
                }
            },
            error: function (xhr, ajaxOptions, thrownError) {
                var error = JSON.parse(xhr.responseText);
            },
            complete: function (data) {
                $btn.button('reset');
                listarRegistros();
            }
        });
 }
}
