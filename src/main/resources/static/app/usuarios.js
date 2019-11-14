var controlador =  _ctx + 'seguridad/usuarios/';
var $table = $('#tblRegistros');
var TEXTO_SELECCIONE = "Seleccione";
var TEXTO_TODOS = "Todos";
var validator;
var strDni;
var strEmail;
var validatorUpdate = true;
var validarUsuarioAD = false;

$(function () {
    $("#cboPerfil").append($("<option />").val("0").text(TEXTO_SELECCIONE));
    $("#cboFiltroPerfil").append($("<option />").val("0").text(TEXTO_SELECCIONE));
    $("#cboModoAcceso").append($("<option />").val("0").text(TEXTO_SELECCIONE));
    $("#btnGuardar").click(function () {  addUsuario(); });
    $("#btnBuscar").click(function () {         $table.bootstrapTable('refresh');
        $(".panel-body").fadeOut(); });
    $("#btnLimpiar").click(function () { limpiarFiltros(); })


    $(".btn-add").click(function () {

        var myForm = document.getElementById("form_registro");
        clearValidation(myForm);

        limpiarRegistro();
        $("#txtTitleForm").html("Nuevo Usuario");
        validatorUpdate = true;
        validarUsuarioAD = false;

        $("#cboModoAcceso").removeAttr("disabled");
        $("#cboPerfil").removeAttr("disabled");
        $("#txtUserAD").removeAttr("disabled");
        $("#txtUserName").removeAttr("disabled");
        $("#txtPass").removeAttr("disabled");

        $("#divActiveD").fadeOut();
        $("#divNormal").fadeOut();
        $("#divCuerpoRegistro").fadeOut();
    });

    $("#cboModoAcceso").change(function () {
        const select = parseInt($("#cboModoAcceso").val());
        switch (select) {
            case 0:
                $("#divCuerpoRegistro").fadeOut();
                $("#divActiveD").fadeOut();
                $("#divNormal").fadeOut();
                break;
            case 1:
                $("#divCuerpoRegistro").fadeIn();
                $("#divActiveD").fadeIn();
                $("#divNormal").fadeOut();
                break;
            case 2:
                $("#divCuerpoRegistro").fadeIn();
                $("#divNormal").fadeIn();
                $("#divActiveD").fadeOut();
                break;
        }
    });

  //  cargarData();
    validarRegistros();
    cargarPerfiles()
    cargarModosAcceso();
    listarRegistros();

    $("#txtUserName").keypress(function (key) {
        window.console.log(key.charCode)
        if (key.charCode == 32) //espacio
            return false;
    });

    $("#txtFiltroDni").keypress(function (e) {
        if (e.which == 13) {
            $("#btnBuscar").click();
        }
    });

    $("#txtEmail").keypress(function (event) {
        if (event.keyCode == 32) {
            event.preventDefault();
        }
    });

    $('#txtEmail').on('input', function (e) {
        this.value = this.value.trim();
    });



});


function limpiarFiltros() {

    $("#txtFiltroDni").val("");
    $("#cboFiltroPerfil").val("0");
    $("#estadobusqueda").val("0");
    listarRegistros();
    $(".panel-body").fadeOut();
    $("#txtTitleForm").html("Nuevo Usuario");
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

function cargarPerfiles() {
    $.ajax({
        type: 'GET',
        contentType: "application/json; charset=utf-8",
        url: controlador + 'perfiles',
        dataType: "json",
        success: function (dataObject, textStatus) {
            if (textStatus == "success") {

                const data = dataObject;
                $.each(data, function () {
                    $("#cboFiltroPerfil").append($("<option />").val(this.id).text(this.nombre));
                    $("#cboPerfil").append($("<option />").val(this.id).text(this.nombre));

                });
            }
        },
        error: function (xhr, ajaxOptions, thrownError) {
            var error = JSON.parse(xhr.responseText);
        },
        complete: function (data) {
        },
        async: false
    });
}


function cargarModosAcceso() {
    $.ajax({
        type: 'GET',
        contentType: "application/json; charset=utf-8",
        url: controlador + 'modos-acceso',
        dataType: "json",
        success: function (dataObject, textStatus) {
            if (textStatus == "success") {

                const data = dataObject;

                $.each(data, function () {
                    $("#cboModoAcceso").append($("<option />").val(this.id).text(this.descripcion));
                });

            }
        },
        error: function (xhr, ajaxOptions, thrownError) {
            var error = JSON.parse(xhr.responseText);
        },
        complete: function (data) {
        },
        async: false
    });
}


function cargarData() {
    $.ajax({
        type: 'GET',
        contentType: "application/json; charset=utf-8",
        url: controlador,
        dataType: "json",
        //data: JSON.stringify(params),
        success: function (dataObject, textStatus) {
            if (textStatus == "success") {

                var data = dataObject.d;
                // al registrar
                $.each(data.ListModoAcceso, function () {
                    $("#cboModoAcceso").append($("<option />").val(this.ModoAccesoId).text(this.Nombre));
                });

                $.each(data.ListPerfiles, function () {
                    $("#cboFiltroPerfil").append($("<option />").val(this.PerfilId).text(this.Descripcion));
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
function addUsuario() {

    if ($("#form_registro").valid()) {
        $("#btnGuardar").button("loading");
        var usuario = {};
        const usuarioId = ($("#hId").val() == "") ? null : parseInt($("#hId").val());
        usuario.nombres = $("#txtNombres").val().trim();
        usuario.materno = $("#txtMaterno").val().trim();
        usuario.paterno = $("#txtPaterno").val();
        usuario.perfilId = $("#cboPerfil").val();
        usuario.modoAccesoId = $("#cboModoAcceso").val();
        usuario.correoElectronico = $("#txtEmail").val();
        usuario.contrasena = $("#txtPass").val();
        usuario.nombreUsuario = parseInt($("#cboModoAcceso").val()) == 2 ? $("#txtUserName").val() : $("#txtUserAD").val(); //Modo por Formulario
        usuario.dni = $("#txtDni").val();

        debugger;

        $.ajax({
            type: usuarioId === null ? 'POST' : 'PUT',
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            url: usuarioId === null ? controlador : controlador + usuarioId,
            dataType: "json",
            data: usuario,
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
            }
            //async: false
        });

        }

}


function validateEmail() {

    let rpta = false;
    const usuarioId = ($("#hId").val() == "") ? null : parseInt($("#hId").val());


    const params = {
        email: $("#txtEmail").val()  ,
        userId : usuarioId
    };

    $.ajax({
        type: 'GET',
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        url: controlador + 'email/validacion',
        dataType: "json",
        data: params,
        success: function (dataObject, textStatus) {
            if (textStatus == "success") {

                var data = dataObject;

                if (data != null) {
                    rpta = data.isEmailValid;
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




function validateUsername(modoAccesoId) {

    let rpta = false;

    const params = {
        username: modoAccesoId === 1 ?  $("#txtUserAD").val()  : $("#txtUserName").val(),
        modoAccesoId : modoAccesoId
    };

    $.ajax({
        type: 'GET',
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        url: controlador + 'username/validacion',
        dataType: "json",
        data: params,
        success: function (dataObject, textStatus) {
            if (textStatus == "success") {

                var data = dataObject;

                if (data != null) {
                    rpta = data.isUsernameValid;
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

function validateUserDni() {
    var rpta = false;
    const usuarioId = ($("#hId").val() == "") ? null : parseInt($("#hId").val());

    $.ajax({
        type: 'GET',
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        url: controlador + 'dni/validacion',
        dataType: "json",
        data: {
            dni: $("#txtDni").val(),
            userId: usuarioId
        },
        success: function (dataObject, textStatus) {
            if (textStatus == "success") {
                rpta = dataObject.isDNIValid;
            }
        },

        async: false
    });
    return rpta;
}

function validarRegistros() {


    $.validator.addMethod('validExistUserName', function (val, element) {
        if (!validatorUpdate) return true;
        if (parseInt($("#cboModoAcceso").val()) === 1 /*Modo Active Directory */) {
            return true;
        } else {
            return validateUsername(2); //Modo formulario
        }
    });
    $.validator.addMethod('requiredUserName', function (val, element) {

        if (!validatorUpdate) return true;

        if (parseInt($("#cboModoAcceso").val()) === 1 /*Modo Active Directory */) {
            return true;
        } else {
            return $("#txtUserName").val() != "";
        }
    });


    $.validator.addMethod("validateUserDni", function (value, element) {

      return validateUserDni();
    });

    $.validator.addMethod("validateUserEmail", function (value, element) {

        return validateEmail();
    });



    $.validator.addMethod("validPassFormat", function (value, element) {

        if (parseInt($("#cboModoAcceso").val()) === 1 /*Modo Active Directory */) {
            return true;
        } else {
            var regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;
            return regex.test($("#txtPass").val());
        }
    });

    $.validator.addMethod('requiredPass', function (val, element) {
        if (!validatorUpdate) return true;

        if (parseInt($("#cboModoAcceso").val())  === 1 /*Modo Active Directory */) {
            return true;
        } else {
            return $("#txtPass").val() != "";
        }
    });

    //

    // VALIDACION CREDENCIALES ACTIVE DIRECTORY

    $.validator.addMethod('requiredtxtUserAD', function (val, element) {
        if (!validatorUpdate) return true;

        if (parseInt($("#cboModoAcceso").val()) === 2 /* modoNormal */) {
            return true;
        } else {
            return $("#txtUserAD").val() != "";
        }
    });

    $.validator.addMethod('validExisttxtUserAD', function (val, element) {
        if (!validatorUpdate) return true;

        if (parseInt($("#cboModoAcceso").val()) === 2 /* modoNormal */) {
            return true;
        } else {
            return validateUsername(1);
        }
    });



    validator = $("#form_registro").validate({

        ignore: ".ignore",
        errorClass: "my-error-class",
        validClass: "my-valid-class",
        onkeyup: function(element) { $(element).valid(); },
        onfocusout: false,
        rules: {
            txtNombres: {
                required: true,
                minlength: 1
            },
            txtMaterno: {
                required: true
            },
            txtPaterno: {
                required: true
            },
            cboPerfil: {
                min: 1
            },
            cboModoAcceso: {
                min: 1
            },
            txtEmail: {
                required: true,
                email: true,
                validateUserEmail : true
            },
            txtDni: {
                required: true,
                minlength: 8,
                maxlength: 8,
                number: true,
                validateUserDni: true
            },
            txtUserName: {
                requiredUserName: true,
                validExistUserName: true
            },
            txtPass: {
                requiredPass: true,
                validPassFormat: true
            },
            txtUserAD: {
                requiredtxtUserAD: true,
                validExisttxtUserAD: true
            }
        },
        messages: {
            txtNombres: {
                required: "Debes ingresar el Nombre del usuario",
                minlength: "Debe ingresar un mínimo (1 caracter)",
                validarEspacioBlanco: "Debe ingresar un mínimo (1 caracter)",
            },
            txtMaterno: {
                required: "Debes ingresar los Apellidos del usuario"
            },
            txtPaterno: {
                required: "Debes ingresar los Apellidos del usuario"
            },
            cboPerfil: {
                min: "Debe seleccionar un perfil para el usuario"
            },
            cboModoAcceso: {
                min: "Debe seleccionar un Modo de Acceso"
            },
            txtEmail: {
                required: "Debes ingresar un correo para el usuario",
                email: "Debe ingresar un correo válido",
                validateUserEmail : "El correo ingresado ya existe, por favor elija otro"
            },
            txtDni: {
                required: "Debes ingresar el DNI del usuario",
                minlength: "Debe ingresar un DNI válido",
                maxlength: "Debe ingresar un DNI válido",
                number: "Sólo se permiten números",
                validateUserDni: "Número de DNI ya registrado, por favor elija otro"
            },
            txtUserName: {
                requiredUserName: "Debe ingresar un nombre de usuario",
                validExistUserName: "El nombre de usuario no está disponible"
            },
            txtPass: {
                requiredPass: "Debe ingresar una contraseña",
                validPassFormat: "La contraseña debe ser de mínimo 8 caracteres y contener mínimo 1 mayúscula, 1 número, 1 minúscula"
            },
            txtUserAD: {
                requiredtxtUserAD: "Debe ingresar un usuario",
                validExisttxtUserAD: "El nombre de usuario no está disponible"
            }
        }
    });
}
function limpiarRegistro() {
    $("#form_registro").trigger("reset");

    //listarRegistros();
}
function listarRegistros() {
    $table.bootstrapTable('destroy');
    $table.bootstrapTable({
        url: controlador,
        method: 'get',
        pagination: true,
        sidePagination: 'server',
        queryParams : (res)=> {
            res.documentoId =  $("#txtFiltroDni").val() === ""  ? null : parseInt( $("#txtFiltroDni").val()) ;
            res.flagActivo =  ($("#estadobusqueda").val() === "") ? null : $("#estadobusqueda").val();
            res.perfilId =  ($("#cboFiltroPerfil").val() === "0") ? null : $("#cboFiltroPerfil").val();
            return res;
        },
        pageSize: 5,
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
function linkFormatter(value, row, index) {
    return String.Format('<a class="editable editable-click" href="javascript:irModificarRegistro(' + row.id + ')" title="Editar">{0}</a>', value);
}

function estado(value, row, index){
    if (row.flagActivo) {
        return "Activo";
    }
    return "Inactivo";

}

function irModificarRegistro(id) {
    validatorUpdate = false;
    validarUsuarioAD = false;
    $("#txtTitleForm").html("Editar Usuario");
    validator.resetForm();
    $("#divActiveD").fadeOut();
    $("#divNormal").fadeOut();
    $("#divCuerpoRegistro").fadeOut();

    var params = {
        userId: id,
    };
    $.ajax({
        type: 'GET',
        contentType: "application/json; charset=utf-8",
        url: controlador + id,
        dataType: "json",
        success: function (dataObject, textStatus) {
            if (textStatus == "success") {

                const registro = dataObject;
                var modoAcceso = registro.modoAccesoId;
                var perfil = registro.perfilId;

                //Active Directory
                if (modoAcceso === 1 ) {
                    $("#divActiveD").fadeIn();
                    $("#divCuerpoRegistro").fadeIn();

                    $("#txtUserAD").val(registro.nombreUsuario);
                }
                else {
                    $("#divNormal").fadeIn();
                    $("#divCuerpoRegistro").fadeIn();
                    $("#txtUserName").val(registro.nombreUsuario);
                }

                $("#hId").val(registro.usuarioId);
                $("#txtNombres").val(registro.nombres);
                $("#txtMaterno").val(registro.materno);
                $("#txtPaterno").val(registro.paterno);
                $("#txtEmail").val(registro.correoElectronico);
                $("#txtDni").val(registro.dni);
                $("#txtPass").val(registro.contrasena);

                $("#cboModoAcceso").val(registro.modoAccesoId);
                $("#cboPerfil").val(registro.perfilId);

                $("#cboModoAcceso").attr("disabled", "disabled");
                $("#cboPerfil").attr("disabled", "disabled");
                $("#txtUserAD").attr("disabled", "disabled");
                $("#txtUserName").attr("disabled", "disabled");
                ///$("#txtPass").attr("disabled", "disabled");
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
function opciones(value, row, index) {
    var estado = "";
    var eliminar = "";
    if (row.flagActivo) {
        estado = String.Format("<a href='javascript:cambiarEstado(" + row.id + ");' title='Desactivar registro'><i class='glyphicon glyphicon-ok'></i></a>");
    } else {
        estado = String.Format("<a href='javascript:cambiarEstado(" + row.id + ");' title='Activar registro'><i class='glyphicon glyphicon-ok' style='color:gray;opacity: 0.35'></i></a>");
    }
    eliminar = String.Format("<a href='javascript:eliminar(" + row.id + ");' title='Eliminar registro'><i class='glyphicon glyphicon-trash'></i></a>");
    return estado + eliminar;
}

function eliminar(id) {
    bootbox.setLocale("es");
    bootbox.confirm("¿Estás seguro que deseas eliminar el estado del registro seleccionado?", function (result) {
        if (result) {

            //$("#load_pace").show();
            $.ajax({
                type: 'PUT',
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                url: controlador + "eliminar/" + id,
                success: function (dataObject, textStatus) {

                    if (textStatus == "success") {
                        const data = dataObject;
                        if (data != "") {
                            bootbox.alert({
                                message: "Acción realizada con Éxito.",
                                size: 'small'
                            });
                        }
                    }
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    var error = JSON.parse(xhr.responseText);
                },
                complete: function (data) {
                    $table.bootstrapTable('refresh');
                }
            });
        }
    });
}

function cambiarEstado(id) {
    bootbox.setLocale("es");
    bootbox.confirm("¿Estás seguro que deseas cambiar el estado del registro seleccionado?", function (result) {
        if (result) {
            var params = {
                id: id,
            };
            //$("#load_pace").show();
            $.ajax({
                type: 'PUT',
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                url: controlador + 'estado/'+ id,
                dataType: 'json',
                success: function (dataObject, textStatus) {
                    if (textStatus === "success") {
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
