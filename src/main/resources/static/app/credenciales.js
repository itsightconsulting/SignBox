const $table = $('#tblRegistros');
const controlador = _ctx + 'seguridad/credenciales/';
let validator;

$(function () {
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
    $("#btnEnviar").click(function () {
        enviarCredenciales();
    });
    $("#btnGuardar").click(function () { actualizar(); });

    listarRegistros();
    validarRegistros();
});

function actualizar() {
    if ($("#form_registro").valid()) {
        $("#btnGuardar").button("loading");
        const params = {
            id: $("#hIdPersona").val(),
            telefono: $("#txtTelefono").val(),
            correo: $("#txtCorreo").val()
        };
        $.ajax({
            type: 'PUT',
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            url: controlador + 'actualizar/'+  params.id,
            dataType: "json",
            data: params,
            success: function (dataObject, textStatus) {

                $table.bootstrapTable('refresh');
                irListado();
            },
            error: function (xhr, ajaxOptions, thrownError) {
                const error = JSON.parse(xhr.responseText);
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

    const nombre = $("#txtFiltroNombre").val();
    const tipo = $("#cboFiltroTipo").val();

    $table.bootstrapTable('destroy');
    $table.bootstrapTable({
        url: controlador + 'listarTodo' + '?dni=' + nombre + '&tipo=' + tipo,
        method: 'GET',
        pagination: true,
        sidePagination: 'server',
        pageSize: 20,
        responseHandler: function (res) {
            const data = res;
            return { rows: data, total: data.length }
        }
    });
}
function limpiarFiltros() {

    $("#txtFiltroNombre").val("");
    $("#cboFiltroTipo").val("");
    listarRegistros();
    $(".panel-body").fadeOut();
}

function linkFormatter(value, row, index) {
    return String.Format('<a class="editable editable-click" href="javascript:irModificarRegistro(' + row.personaId + ')" title="Editar">{0}</a>', value);
}

function opciones(value, row, index) {
    const credenciales = String.Format("<a href='javascript:modalCredenciales(" + row.personaId + ");' title='Enviar credenciales de acceso'><i class='glyphicon glyphicon-envelope'></i></a>");
    const reseteo = String.Format("<a href='javascript:resetearPass(" + row.personaId + ");' title='Generar una contraseña temporal'><i class='glyphicon glyphicon-refresh'></i></a>");

    return reseteo + "&nbsp;" + credenciales + "&nbsp;";
}

function modalCredenciales(id) {
    $("#hIdPersona").val(id);
    bootbox
        .dialog({
            title: 'Enviar credenciales',
            message: $('#modalEnvio'),
            size: 'medium',
            show: false // We will show it manually later
        })
        .on('shown.bs.modal', function () {
            $('#modalEnvio').show();
        })
        .on('hide.bs.modal', function (e) {
            $('#modalEnvio').hide().appendTo('body');
        })
        .modal('show');
}

function irModificarRegistro(id) {
    $("#form_registro").validate().resetForm();

    limpiarRegistro();

    $.ajax({
        type: 'GET',
        contentType: "application/json; charset=utf-8",
        url: controlador + 'ObtenerPorId/' + id,
        dataType: "json",
        success: function (dataObject, textStatus) {
            if (textStatus == "success") {

                data = dataObject;

                const registro = data;
                $("#hIdPersona").val(registro.personaId);
                $("#txtCorreo").val(registro.correo);
                $("#txtTelefono").val(registro.telefono);
            }
        },
        error: function (xhr, ajaxOptions, thrownError) {
            const error = JSON.parse(xhr.responseText);
        },
        complete: function (data) {
            irRegistro();
        }
    });
}

function limpiarRegistro() {
    $("#hIdPersona").val("");
    $("#txtCorreo").val("");
    $("#txtTelefono").val("");
}

function resetearPass(id) {
    bootbox.setLocale("es");
    bootbox.confirm("¿Estás seguro que deseas generar una contraseña temporal de acceso al usuario?, ten presente que esta contraseña no se enviará de manera automática", function (result) {
        if (result) {
            $.ajax({
                type: 'PUT',
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                url: controlador + 'reseteo-credenciales/' + id,
                dataType: "json",
                success: function (dataObject, textStatus) {

                    if (textStatus == "success") {
                        const result = dataObject.password;
                        bootbox.alert(String.Format("Se completó con éxito la operación, la contraseña generada es la siguiente: <strong>{0}</strong>", result));
                    }
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    bootbox.alert(xhr.responseText);
                },
                complete: function (data) {

                }
            });
        }
    });
}

function enviarCredenciales() {
    bootbox.setLocale("es");
    bootbox.confirm("¿Estás seguro que deseas re-enviar las credenciales de acceso al usuario?, ten presente que solo se enviarán si la cuenta tiene el correo y teléfono configurados", function (result) {
        if (result) {
            const params = {
                id: $("#hIdPersona").val(),
                tipo: $("#cboTipo").val()
            };
            $.ajax({
                type: 'GET',
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                url: controlador + 'enviar-credenciales/',
                dataType: "json",
                data: params,
                success: function (dataObject, textStatus) {
                    bootbox.alert("Se completó con éxito la operación")
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    bootbox.alert(xhr.responseText);
                },
                complete: function (data) {
                }
            });
        }
    });
}

function centerModals(parametro) {
    $("#" + parametro.id + "").each(function (i) {
        const $clone = $(this).clone().css('display', 'block').appendTo('body');
        let  top = Math.round(($clone.height() - $clone.find('.modal-content').height()) / 2);
        top = top > 0 ? top : 0;
        $clone.remove();
        $(this).find('.modal-content').css("margin-top", top);
    });
}

function validarRegistros() {
    validator = $("#form_registro").validate({
        ignore: ".ignore",
        errorClass: "my-error-class",
        validClass: "my-valid-class",
        rules: {
            txtCorreo: {
                required: true,
                email: true
            },
            txtTelefono: {
                required: true
            }
        },
        messages: {
            txtCorreo: {
                required: "Debes ingresar un valor para el campo",
                email: "Debes ingresar un correo electrónico válido"
            },
            txtTelefono: {
                required: "Debes ingresar un valor para el campo"
            }
        }
    });
}

function IsEnteroNumber(b) {
    let bool = false;
    if (parseInt(b.key) >= 0) {
        bool = Number.isInteger(parseInt(b.key));
    }

    return bool;
}