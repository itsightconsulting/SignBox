var $table = $('#tblRegistros');
var controlador = _ctx + 'admin/tipos-archivos/';
var validator;

var TEXTO_SELECCIONE = "Seleccione";

$(function () {

    $("#cboFiltroTipo").append($("<option />").val("0").text(TEXTO_SELECCIONE));
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

    cargarData();
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

function cargarData() {
    $.ajax({
        type: 'GET',
        contentType: "application/json; charset=utf-8",
        url: controlador + 'CargarData',
        dataType: "json",
        //data: JSON.stringify(params),
        success: function (dataObject, textStatus) {
            if (textStatus == "success") {

                var data = dataObject.d;
                // al registrar
                $.each(data.ListTipoFirma, function ()
                {
                    $("#cboTipo").append($("<option />").val(this.FormatoFirmaId).text(this.Descripcion));
                    $("#cboFiltroTipo").append($("<option />").val(this.FormatoFirmaId).text(this.Descripcion));
                });
            }
        },
        error: function (xhr, ajaxOptions, thrownError) {
            var error = JSON.parse(xhr.responseText);
        },
        complete: function (data) {
            //irRegistro();
        }
    });
}
function addSede() {
    //editarUser = false;
    if ($("#form_registro").valid()) {
        $("#btnGuardar").button("loading");
        var item = {};
        item.TipoArchivoId = ($("#hId").val() == "") ? 0 : parseInt($("#hId").val());
        item.CodigoArchivo = $("#txtNombre").val().trim();
        item.Extensiones = $("#txtExtensiones").val();
        item.Descripcion = $("#txtDescripcion").val();
        item.IdFormatoFirma = $("#cboTipo").val();


        $.ajax({
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            url: controlador + 'Agregar',
            dataType: "json",
            data: JSON.stringify({ item }),
            success: function (dataObject, textStatus) {

                $table.bootstrapTable('refresh');
                irListado();
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
    }
}

function listarRegistros() {

    var tipoId = $("#cboFiltroTipo").val();
    var nombre = $("#txtFiltroNombre").val();// == "" ? null : $("#txtFiltroNombre").val();
    var flagActivo = ($("#cboFiltroEstado").val() == "") ? false : $("#cboFiltroEstado").val();

    $table.bootstrapTable('destroy');
        $table.bootstrapTable({
            url:  controlador + 'listarTodo' + '?tipoId=' + tipoId + '&nombre=' + nombre + '&flagActivo=' + flagActivo,
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
            },
            txtExtensiones: {
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
            },
            txtExtensiones: {
                required: "Debes ingresar un valor para el campo"
            }
        }
    });
}
function limpiarFiltros() {

    $("#txtFiltroNombre").val("");
    $("#cboFiltroEstado").val("");
    $("#cboFiltroTipo").val("0");
    listarRegistros();
    $(".panel-body").fadeOut();
    $("#txtTitleForm").html("Nuevo Tipo");
}
function opciones(value, row, index) {
    var estado = "";

    if (row.FlagActivo) {
        estado = String.Format("<a href='javascript:cambiarEstado(" + row.TipoArchivoId + ");' title='Desactivar registro'><i class='glyphicon glyphicon-ok'></i></a>");
    } else {
        estado = String.Format("<a href='javascript:cambiarEstado(" + row.TipoArchivoId + ");' title='Activar registro'><i class='glyphicon glyphicon-ok' style='color:gray;opacity: 0.35'></i></a>");
    }

    return estado;
}
function linkFormatter(value, row, index) {
    return String.Format('<a class="editable editable-click" href="javascript:irModificarRegistro(' + row.TipoArchivoId + ')" title="Editar">{0}</a>', value);
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
    $("#txtTitleForm").html("Editar Tipo");

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
                $("#hId").val(registro.TipoArchivoId);
                $("#txtNombre").val(registro.CodigoArchivo);
                $("#cboTipo").val(registro.IdFormatoFirma);
                $("#txtDescripcion").val(registro.Descripcion);
                $("#txtExtensiones").val(registro.Extensiones);

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
    $("#txtExtensiones").val("");

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