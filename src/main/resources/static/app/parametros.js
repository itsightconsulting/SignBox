var $table = $('#tblRegistros');
var _ctx = $('meta[name="_ctx"]').attr('content');
var controlador = _ctx + 'admin/parametros/';

$(function () {
    $("#btnGuardar").click(function () {
        actualizar();
    });

    $("#btnCancelar").click(function () {
        limpiarRegistro();
    });

   listarRegistros();
})



function limpiarRegistro() {
    $("#hId").val(0);
    $("#param").val("");
    $("#valor").val("");
}

function irModificarRegistro(id) {
    limpiarRegistro();


    var params = {
        id: id,
    };
    //$("#load_pace").show();
    $.ajax({
        type: 'get',
        contentType: "application/json; charset=utf-8",
        url: controlador + 'obtener/',
        dataType: "json",
        data: params,
        success: function (dataObject, textStatus) {
            if (textStatus == "success") {


                const registro = dataObject;
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
            valor: {
                required: true,
                maxlength: 300
            }
        },
        messages: {
            valor: {
                required: "Debes de ingresar un valor",
                maxlength: $.validator.format("Debes ingresar como máximo {0} caracteres"),
            }
        }
    });
}

function listarRegistros() {

    $table.bootstrapTable('destroy');
    var dataRpta;

    $table.bootstrapTable({
        url:  'parametros/obtener/todo',
        method: 'get',
        queryParamsType: 'Else',
        pagination: false,
        //
        //  pagination: true,
      //
     //   pageSize: 20,

     /*   queryParams: function (p) {
            var pageNumber = p.pageNumber;
            var pageSize = p.pageSize;
            return {
                pageNumber: p.pageNumber,
                pageSize: p.pageSize
            };
        }*/
        responseHandler: function (res) {
            var data = res.d;

            console.log(res);
            return res;
         //    return { rows: data.ListConfiguracion, total: data.Total }
        }
    });
}

function actualizar() {

  //  if ($("#form_registro").valid()) {
        var $btn = $("#btnGuardar");
      //  $btn.button('loading');

        var params = {};
    const id = $("#hId").val();
    params.nombre = $("#txtParam").val();
    params.descripcion = $("#txtDescription").val();
    params.valor = $("#txtValue").val();

       $.ajax({
            type: 'PUT',
           contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            url: controlador + 'actualizar/'+id,
            dataType: "json",
            data:  params,
            success: function (dataObject, textStatus) {
                if (textStatus == "success") {
                //    MostrarAlerta("alertBox", "alert-success", "Se actualizó con <strong>éxito</strong> el valor del parámetro.")
                   irListado();
                    $('#form_registro').trigger("reset");

                }
            },
            error: function (xhr, ajaxOptions, thrownError) {
                var error = JSON.parse(xhr.responseText);
            },
            complete: function (data) {
              //  $btn.button('reset');
                listarRegistros();
                //$("#load_pace").hide();
            }
        });
 //   }



}
