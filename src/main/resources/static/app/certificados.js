var $table = $('#tblRegistros');
const controlador = _ctx + 'admin/certificados/';
var validator;
var validatorUser;
var editarUser = false;

$(function () {
    $("#btnGuardar").click(function () { addSede(); });
    $("#btnBuscar").click(function () {
        $table.bootstrapTable('refresh');
        $(".panel-body").fadeOut();
    });
    $("#btnLimpiar").click(function () { limpiarFiltros(); })
    $("#frm_busqueda").keypress(function (e) {
        if (e.which == 13) {
            $("#btnBuscar").click();
        }
    });
    $(".btn-add").click(function () {
        limpiarRegistro();
        var myForm = document.getElementById("form_registro");
        clearValidation(myForm);

        $("#hId").val("");
    });
    $('#txtFecha').datetimepicker({
        locale: 'es',
        format: 'DD/MM/YYYY'
    });
    listarRegistros();
    validarRegistros();
});

function clearValidation(formElement) {
    var validator = $(formElement).validate();

    $('[name]', formElement).each(function () {
        validator.successList.push(this);
        validator.showErrors();
    });
    validator.resetForm();
    validator.reset();
}

function addSede() {


    //editarUser = false;
 //   if ($("#form_registro").valid()) {
        $("#btnGuardar").button("loading");
        var fecha = moment($("#txtFecha").val(), "DD/MM/YYYY");

        var item = {};


        ($("#hId").val() == "") ?  null: item.certificadosId  = parseInt($("#hId").val());
        item.alias = $("#txtNombre").val().trim();
        item.identificadorHsm = $("#txtHSM").val();
        item.pinSeguridad = $("#txtPIN").val();
        item.responsable = $("#txtResponsable").val();
        item.fechaCaducidad = fecha.toDate();
        item.descripcion = $("#txtDescripcion").val();

        console.log(item);

     $.ajax({
         type: 'POST',
         contentType: "application/x-www-form-urlencoded; charset=UTF-8",
         url: controlador,
         dataType: "json",
         data:  item ,
         success: function (dataObject, textStatus) {

             console.log(dataObject, textStatus);
            // $table.bootstrapTable('refresh');
            // irListado();
         },
         error: function (xhr, ajaxOptions, thrownError) {
             var error = JSON.parse(xhr.responseText);
             MostrarAlerta("alertBoxTabAreas", "alert-danger", String.Format("<strong>Error!</strong> {0}", error.Message));
         },
         complete: function (data) {
             $("#btnGuardar").button("reset");
             limpiarRegistro();
         },
         //async: false
     });

  //  }
}
function listarRegistros() {

    $table.bootstrapTable('destroy');
    $table.bootstrapTable({
        url:  controlador,
        method: 'get',
        pagination: true,
        sidePagination: 'server',
        queryParams : (res)=> {


            res.alias = ($("#txtFiltroNombre").val() == "") ? null : parseInt($("#txtFiltroNombre").val()) ;
            res.flagActivo =   ($("#cboFiltroEstado").val() == "") ? null : $("#cboFiltroEstado").val();

            return res;

        },
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
function validarRegistros() {
    $.validator.addMethod('validarCodigo', function (val, element) {
        return validarCodigo();
    });
    validator = $("#form_registro").validate({
        ignore: ".ignore",
        errorClass: "my-error-class",
        validClass: "my-valid-class",
        rules: {
            txtNombre: {
                required: true,
                validarCodigo: true
            },
            txtHSM: {
                required: true
            },
            txtPIN: {
                required: true
            },
            txtFecha: {
                required: true
            },
            txtResponsable: {
                required: true
            }
        },
        messages: {
            txtNombre: {
                required: "Debes ingresar un valor para el campo",
                validarCodigo: "El código ingresado ya existe"
            },
            txtHSM: {
                required: "Debes ingresar un valor para el campo"
            },
            txtPIN: {
                required: "Debes ingresar un valor para el campo"
            },
            txtFecha: {
                required: "Debes ingresar un valor para el campo"
            },
            txtResponsable: {
                required: "Debes ingresar un valor para el campo"
            }
        }
    });
}
function limpiarFiltros() {

    $("#txtFiltroNombre").val("");
    $("#cboFiltroEstado").val("");
    listarRegistros();
    $(".panel-body").fadeOut();
    $("#txtTitleForm").html("Nuevo Ambiente");
}
function opciones(value, row, index) {
    var estado = "";

    if (row.flagActivo) {
        estado = String.Format("<a href='javascript:cambiarEstado(" + row.CertificadosId + ");' title='Desactivar registro'><i class='glyphicon glyphicon-ok'></i></a>");
    } else {
        estado = String.Format("<a href='javascript:cambiarEstado(" + row.CertificadosId + ");' title='Activar registro'><i class='glyphicon glyphicon-ok' style='color:gray;opacity: 0.35'></i></a>");
    }

    return estado;
}
function linkFormatter(value, row, index) {
    return String.Format('<a class="editable editable-click" href="javascript:irModificarRegistro(' + row.CertificadosId + ')" title="Editar">{0}</a>', value);
}


function estado(value, row, index){
    if (row.flagActivo) {
        return "Activo";
    }
    return "Inactivo";

}


function cambiarEstado(id) {
    bootbox.setLocale("es");
    bootbox.confirm("¿Estás seguro que deseas cambiar el estado del registro seleccionado?", function (result) {
        if (result) {
            var params = {
                id: id,
            };
            $("#load_pace").show();
            $.ajax({
                type: 'POST',
                contentType: "application/json; charset=utf-8",
                url: controlador + 'CambiarEstado',
                dataType: "json",
                data: JSON.stringify(params),
                success: function (dataObject, textStatus) {
                    if (textStatus == "success") {
                        MostrarAlerta("alertBox", "alert-success", "Se cambió con <strong>éxito</strong> el estado del proveedor.")
                    }
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    var error = JSON.parse(xhr.responseText);
                },
                complete: function (data) {
                    $table.bootstrapTable('refresh');
                    listarRegistros();
                    $("#load_pace").hide();
                }
            });
        }
    });
}
function irModificarRegistro(id) {
    //validatorUpdate = false;
    $("#form_registro").validate().resetForm();
    $("#txtTitleForm").html("Editar Ambiente");

    limpiarRegistro();
    var params = {
        id: id,
    };
    $.ajax({
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        url: controlador + 'ObtenerPorId',
        dataType: "json",
        data: JSON.stringify(params),
        success: function (dataObject, textStatus) {
            if (textStatus == "success") {

                data = dataObject.d;

                var registro = data;
                $("#hId").val(registro.CertificadosId);
                $("#txtNombre").val(registro.Alias);
                $("#txtHSM").val(registro.IdentificadorHSM);
                $("#txtPIN").val(registro.PinSeguridadSinCifrar);
                $("#txtResponsable").val(registro.Responsable);
                $("#txtDescripcion").val(registro.Descripcion);
                $("#txtFecha").val(registro.FechaCaducidadToString);

                $("#txtNombre").attr("disabled", "disabled");
            }
        },
        error: function (xhr, ajaxOptions, thrownError) {
            var error = JSON.parse(xhr.responseText);
        },
        complete: function (data) {
            irRegistro();
        }
    });
}
function limpiarRegistro() {
    $("#hId").val("");
    $("#txtNombre").val("");
    $("#txtHSM").val("");
    $("#txtPIN").val("");
    $("#txtResponsable").val("");
    $("#txtFecha").val("");
    $("#txtDescripcion").val("");

    $("#txtNombre").removeAttr("disabled");
}
function centerModals(parametro) {
    $("#" + parametro.id + "").each(function (i) {
        var $clone = $(this).clone().css('display', 'block').appendTo('body');
        var top = Math.round(($clone.height() - $clone.find('.modal-content').height()) / 2);
        top = top > 0 ? top : 0;
        $clone.remove();
        $(this).find('.modal-content').css("margin-top", top);
    });
}

function validarCodigo() {

    var rpta = false;

    var params = {
        codigo: $("#txtNombre").val()
    };

    $.ajax({
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        url: controlador + 'ValidarCodigo',
        dataType: "json",
        data: JSON.stringify(params),
        success: function (dataObject, textStatus) {
            if (textStatus == "success") {
                var data = dataObject.d;
                if (data != null) {
                    rpta = data;
                }
            }
        },
        error: function (xhr, ajaxOptions, thrownError) {
            var error = JSON.parse(xhr.responseText);
            MostrarAlerta("alertBoxTabAreas", "alert-danger", String.Format("<strong>Error!</strong> {0}", error.Message));
        },
        complete: function (data) {
        },
        async: false
    });

    return rpta;
}