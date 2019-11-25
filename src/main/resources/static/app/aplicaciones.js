var $table = $('#tblRegistros');
var $tableAmbientes = $('#tblAmbiente');
var $tableCertificados = $('#tblCertificados');
var controlador = _ctx + 'portalAdmin/configuracion/aplicaciones/';
var validator;
var validatorUser;

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

    $("#btnAgregarAmbiente").click(function () {
        agregarAmbiente();
    });

    $("#btnAgregarCertificados").click(function () {
        agregarCertificado();
    });

    listarRegistros();
    validarRegistros();

    cargarAmbientes();
    cargarCertificados();
});

function cargarAmbientes() {
    var combo = $("#cboAmbiente");

    $.ajax({
        type: 'get',
        contentType: "application/json; charset=utf-8",
        url: controlador + 'ListarAmbientes',
        dataType: "json",
        success: function (dataObject, textStatus) {
            if (textStatus == "success") {
                data = dataObject;

                combo.html('');
                $.each(data, function () {
                    combo.append($("<option />").val(this.ambientesId).text(this.nombre));
                });
            }
        },
        async: false
    });
}

function cargarCertificados() {
    var combo = $("#cboCertificado");

    $.ajax({
        type: 'get',
        contentType: "application/json; charset=utf-8",
        url: controlador + 'ListarCertificados',
        dataType: "json",
        success: function (dataObject, textStatus) {
            if (textStatus == "success") {
                data = dataObject;

                combo.html('');
                $.each(data, function () {
                    combo.append($("<option />").val(this.id).text(this.identificadorHsm));
                });
            }
        },
        async: false
    });
}

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
        item.AplicacionesId = ($("#hId").val() == "") ? 0 : parseInt($("#hId").val());
        item.Codigo = $("#txtNombre").val().trim();
        item.Nombre = $("#txtNombreApp").val();
        item.Descripcion = $("#txtDescripcion").val();
        item.UsuarioLider = $("#txtUsuario").val();

        $.ajax({
            type: 'POST',
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            url: controlador + 'Agregar',
            dataType: "json",
            data:  item ,
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

function update() {

    if ($("#form_registro").valid()) {
        $("#btnGuardar").button("loading");
        var item = {};
        item.AplicacionesId = ($("#hId").val() == "") ? 0 : parseInt($("#hId").val());
        item.Codigo = $("#txtNombre").val().trim();
        item.Nombre = $("#txtNombreApp").val();
        item.Descripcion = $("#txtDescripcion").val();
        item.UsuarioLider = $("#txtUsuario").val();

        $.ajax({
            type: 'PUT',
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            url: controlador + 'actualizar/' + item.AplicacionesId,
            dataType: "json",
            data:  item,
            success: function (dataObject, textStatus) {

                $table.bootstrapTable('refresh');
                irListado();
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

    var nombre= $("#txtFiltroNombre").val();
    var flagActivo= ($("#cboFiltroEstado").val() == "") ? "" : $("#cboFiltroEstado").val();

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
            txtNombreApp: {
                required: true
            }
        },
        messages: {
            txtNombre: {
                required: "Debes ingresar un valor para el campo",
                validarCodigo: "El código ingresado ya existe"
            },
            txtNombreApp: {
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
    var ambientes = "";
    var certificados = "";

    if (row.flagActivo) {
        estado = String.Format("<a href='javascript:cambiarEstado(" + row.aplicacionesId + ");' title='Desactivar registro'><i class='glyphicon glyphicon-ok'></i></a>");
    } else {
        estado = String.Format("<a href='javascript:cambiarEstado(" + row.aplicacionesId + ");' title='Activar registro'><i class='glyphicon glyphicon-ok' style='color:gray;opacity: 0.35'></i></a>");
    }

    ambientes = String.Format("<a href='javascript:verAmbientes(" + row.aplicacionesId + ");' title='Vincular ambientes'><i class='glyphicon glyphicon-hdd'></i></a>");
    certificados = String.Format("<a href='javascript:verCertificados(" + row.aplicacionesId + ");' title='Vincular certificados'><i class='glyphicon glyphicon-modal-window'></i></a>");

    return estado + "&nbsp;" + ambientes + "&nbsp;" + certificados;
}

function estado(value, row, index){
    if (row.flagActivo) {
        return "Activo";
    }
    return "Inactivo";

}

function linkFormatter(value, row, index) {
    return String.Format('<a class="editable editable-click" href="javascript:irModificarRegistro(' + row.aplicacionesId + ')" title="Editar">{0}</a>', value);
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
    var params = {
        id: id,
    };
    $.ajax({
        type: 'GET',
        contentType: "application/json; charset=utf-8",
        url: controlador + 'ObtenerPorId/' + id,
        dataType: "json",
        success: function (dataObject, textStatus) {
            if (textStatus == "success") {

                data = dataObject;

                var registro = data;
                $("#hId").val(registro.aplicacionesId);
                $("#txtNombre").val(registro.codigo);
                $("#txtNombreApp").val(registro.nombre);
                $("#txtDescripcion").val(registro.descripcion);
                $("#txtUsuario").val(registro.usuarioLider);

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
    $("#txtUsuario").val("");
    $("#txtNombreApp").val("");

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
        dataType: "json",
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

function opcionesCertificado(value, row, index) {
    return String.Format("<a href='javascript:eliminarCertificado(" + row.certificadosId + ");' title='Eliminar registro'><i class='glyphicon glyphicon-trash'></i></a>");
}

function opcionesAmbiente(value, row, index) {
    return String.Format("<a href='javascript:eliminarAmbiente(" + row.ambientesId + ");' title='Eliminar registro'><i class='glyphicon glyphicon-trash'></i></a>");
}


function listarAmbientes() {
    var id = $("#hIdApp").val();

    $tableAmbientes.bootstrapTable('destroy');
    $tableAmbientes.bootstrapTable({
        url: controlador + "ObtenerAmbientes?id="+id,
        method: 'GET',
        pagination: true,
        contentType: "application/json; charset=utf-8",
        sidePagination: 'server',
        queryParamsType: 'else',
        pageSize: 20,
        responseHandler: function (res) {
            var data = res;

            return { rows: data, total: data.length }
        }


    });
}

function listarCertificados() {
    var id = $("#hIdApp").val();

    $tableCertificados.bootstrapTable('destroy');
    $tableCertificados.bootstrapTable({
        url: controlador + "ObtenerCertificados?id="+id,
        method: 'GET',
        pagination: true,
        contentType: "application/json; charset=utf-8",
        sidePagination: 'server',
        queryParamsType: 'else',
        pageSize: 20,
        responseHandler: function (res) {
            var data = res;
            return { rows: data, total: data.length }
        }
    });
}

function eliminarCertificado(id) {

    var aplicacion = $("#hIdApp").val();

    bootbox.setLocale("es");
    bootbox.confirm("¿Estás seguro que deseas eliminar el registro seleccionado?", function (result) {
        if (result) {

            $.ajax({
                type: 'POST',
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                url: controlador + 'EliminarCertificado/'+aplicacion+'/'+id,
                dataType: "json",
                success: function (dataObject, textStatus) {
                    if (textStatus == "success") {
                        if (dataObject){
                            bootbox.alert("Registro eliminado con éxito");
                        }
                        else{
                            bootbox.alert("No se pudo eliminar el registro");
                        }
                    }
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    var error = JSON.parse(xhr.responseText);
                },
                complete: function (data) {
                    if (data.responseText == "true"){
                        $tableCertificados.bootstrapTable('refresh');
                        listarCertificados();
                    }
                }
            });
        }
    });
}

function eliminarAmbiente(id) {

    var aplicacion = $("#hIdApp").val();

    bootbox.setLocale("es");
    bootbox.confirm("¿Estás seguro que deseas eliminar el registro seleccionado?", function (result) {
        if (result) {

            $.ajax({
                type: 'POST',
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                url: controlador + 'EliminarAmbiente/'+aplicacion+'/'+id,
                dataType: "json",
                success: function (dataObject, textStatus) {
                    if (textStatus == "success") {
                        if (dataObject){
                            bootbox.alert("Registro eliminado con éxito");
                        }
                        else{
                            bootbox.alert("No se pudo eliminar el registro");
                        }

                    }
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    var error = JSON.parse(xhr.responseText);
                },
                complete: function (data) {
                    if (data.responseText == "true"){
                        $tableAmbientes.bootstrapTable('refresh');
                        listarAmbientes();
                    }

                }
            });
        }
    });
}

function verAmbientes(aplicacion) {
    $("#hIdApp").val(aplicacion);
    listarAmbientes();
    bootbox
        .dialog({
            title: 'Ambientes',
            message: $('#modalAmbientes'),
            size: 'medium',
            show: false // We will show it manually later
        })
        .on('shown.bs.modal', function () {
            $('#modalAmbientes').show();
        })
        .on('hide.bs.modal', function (e) {
            $('#modalAmbientes').hide().appendTo('body');
        })
        .modal('show');
}

function verCertificados(aplicacion) {
    $("#hIdApp").val(aplicacion);
    listarCertificados();
    bootbox
        .dialog({
            title: 'Certificados',
            message: $('#modalCertificados'),
            size: 'medium',
            show: false // We will show it manually later
        })
        .on('shown.bs.modal', function () {
            $('#modalCertificados').show();
        })
        .on('hide.bs.modal', function (e) {
            $('#modalCertificados').hide().appendTo('body');
        })
        .modal('show');
}

function agregarAmbiente() {
    var ambiente = $("#cboAmbiente").val();
    var aplicacion = $("#hIdApp").val();

    bootbox.setLocale("es");
    bootbox.confirm("¿Estás seguro que deseas vincular el ambiente a la aplicación?", function (result) {
        if (result) {

            $.ajax({
                type: 'POST',
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                url: controlador + 'AgregarAmbiente/'+aplicacion+'/'+ambiente,
                dataType: "json",
                success: function (dataObject, textStatus) {
                    if (textStatus == "success") {
                        if (dataObject){
                            bootbox.alert("Registro vinculado con éxito");
                        }
                        else{
                            bootbox.alert("Este ambiente ya está vinculado");
                        }

                    }
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    var error = JSON.parse(xhr.responseText);
                },
                complete: function (data) {

                    if(data.responseText != "false"){
                        $tableAmbientes.bootstrapTable('refresh');
                        listarAmbientes();
                    }
                }
            });
        }
    });
}

function agregarCertificado() {
    var certificado = $("#cboCertificado").val();
    var aplicacion = $("#hIdApp").val();

    bootbox.setLocale("es");
    bootbox.confirm("¿Estás seguro que deseas vincular el certificado a la aplicación?", function (result) {
        if (result) {

            $.ajax({
                type: 'POST',
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                url: controlador + 'AgregarCertificado/'+aplicacion+'/'+certificado,
                dataType: "json",
                success: function (dataObject, textStatus) {
                    if (textStatus == "success") {
                        if (dataObject){
                            bootbox.alert("Registro vinculado con éxito");
                        }
                        else{
                            bootbox.alert("Este certificado ya está vinculado");
                        }
                    }
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    var error = JSON.parse(xhr.responseText);
                },
                complete: function (data) {
                    if(data.responseText != "false"){
                        $tableCertificados.bootstrapTable('refresh');
                        listarCertificados();
                    }
                }
            });
        }
    });
}
