var $table = $('#tblRegistros');
var controlador = _ctx + 'admin/ambientes/';
var validator;
var validatorUser;
var editarUser = false;

var TEXTO_SELECCIONE = "Seleccione";

$(function () {
    $("#btnGuardar").click(function () {
        var id = ($("#hId").val() == "") ? 0 : parseInt($("#hId").val());

        if(id == 0){
            addSede();
        }
        else{
            update();
        }
    });
    $("#btnBuscar").click(function () {
        listarRegistros();
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
    if ($("#form_registro").valid()) {
        $("#btnGuardar").button("loading");
        var item = {};
        item.AmbientesId = ($("#hId").val() == "") ? 0 : parseInt($("#hId").val());
        item.Prefijo = $("#txtNombre").val().trim();
        item.Nombre = $("#txtDescripcion").val();


        $.ajax({
            type: 'POST',
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            url: controlador + 'Agregar',
            dataType: "json",
            data: item ,
            success: function (dataObject, textStatus) {

                $table.bootstrapTable('refresh');
                listarRegistros();
            },
            error: function (xhr, ajaxOptions, thrownError) {
                var error = JSON.parse(xhr.responseText);
                MostrarAlerta("alertBoxTabAreas", "alert-danger", String.Format("<strong>Error!</strong> {0}", error.Message));
            },
            complete: function (data) {
                $("#btnGuardar").button("reset");
                limpiarRegistro();

                $(".view_register").hide();
            },
            //async: false
        });
    }
}

function update() {

    if ($("#form_registro").valid()) {
        $("#btnGuardar").button("loading");
        var item = {};
        item.AmbientesId = ($("#hId").val() == "") ? 0 : parseInt($("#hId").val());
        item.Prefijo = $("#txtNombre").val().trim();
        item.Nombre = $("#txtDescripcion").val();

        $.ajax({
            type: 'PUT',
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            url: controlador + 'actualizar/' + item.AmbientesId,
            dataType: "json",
            data:  item,
            success: function (dataObject, textStatus) {

                $table.bootstrapTable('refresh');
                listarRegistros();
            },
            error: function (xhr, ajaxOptions, thrownError) {
                var error = JSON.parse(xhr.responseText);
                MostrarAlerta("alertBoxTabAreas", "alert-danger", String.Format("<strong>Error!</strong> {0}", error.message));
            },
            complete: function (data) {
                $("#btnGuardar").button("reset");
                limpiarRegistro();


                $(".view_register").hide();
            },
            //async: false
        });
    }
}

function listarRegistros() {

    var nombre = $("#txtFiltroNombre").val();
    var flagActivo = ($("#cboFiltroEstado").val() == "") ? true : $("#cboFiltroEstado").val();

    $table.bootstrapTable('destroy');
    $table.bootstrapTable({
        url:  controlador + 'listarTodo' + '?nombre=' + nombre + '&flagActivo=' + flagActivo,
        method: 'get',
        pagination: true,
        sidePagination: 'server',
        pageSize: 20,
        onLoadSuccess: function (data) {
        },
        onLoadError: function (xhr) {
            console.log(xhr);
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
            txtDescripcion: {
                required: true
            }
        },
        messages: {
            txtNombre: {
                required: "Debes ingresar un valor para el campo",
                validarCodigo: "El código ingresado ya existe"
            },
            txtDescripcion: {
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
        estado = String.Format("<a href='javascript:cambiarEstado(" + row.ambientesId + ");' title='Desactivar registro'><i class='glyphicon glyphicon-ok'></i></a>");
    } else {
        estado = String.Format("<a href='javascript:cambiarEstado(" + row.ambientesId + ");' title='Activar registro'><i class='glyphicon glyphicon-ok' style='color:gray;opacity: 0.35'></i></a>");
    }

    return estado;
}

function estado(value, row, index){
    if (row.flagActivo) {
        return "Activo";
    }
    return "Inactivo";

}

function linkFormatter(value, row, index) {
    return String.Format('<a class="editable editable-click" href="javascript:irModificarRegistro(' + row.ambientesId + ')" title="Editar">{0}</a>', value);
}
function cambiarEstado(id) {
    bootbox.setLocale("es");
    bootbox.confirm("¿Estás seguro que deseas cambiar el estado del registro seleccionado?", function (result) {
        if (result) {

            $("#load_pace").show();
            $.ajax({
                type: 'POST',
                contentType: "application/json; charset=utf-8",
                url: controlador + 'CambiarEstado/'+ id,
                dataType: "json",

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

    $.ajax({
        type: 'GET',
        contentType: "application/json; charset=utf-8",
        url: controlador + 'ObtenerPorId/' + id,
        dataType: "json",
        success: function (dataObject, textStatus) {
            if (textStatus == "success") {

                data = dataObject;

                var registro = data;
                $("#hId").val(registro.ambientesId);
                $("#txtNombre").val(registro.prefijo);
                $("#txtDescripcion").val(registro.nombre);

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

    var codigo = $("#txtNombre").val();

    $.ajax({
        type: 'GET',
        contentType: "application/json; charset=utf-8",
        url: controlador + 'ValidarCodigo?codigo='+ codigo,
        success: function (dataObject, textStatus) {
            if (textStatus == "success") {

                var data = dataObject;
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