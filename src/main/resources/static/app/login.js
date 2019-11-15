var controlador = siteRoot + 'login.aspx/';
$(function () {
  //  $("#btnLogin").click(function () { LoginUser(); });
    $("#txtPass").keypress(function (e) {
        if (e.which == 13) {
            LoginUser();
        }
    });
    $("#txtUsuario").focus();
    $("#txtUsuario").keypress(function (e) {
        if (e.which == 13) {
            $("#txtPass").focus();

        }
    });

    if(error){
       mostrarMensajeErrorLogin(error);
    }
  //  validarRegistros();
});


function mostrarMensajeErrorLogin(error){

    let mensajeError;
    switch(error) {
        case "error" :
            mensajeError = "Las credenciales son incorrectas. Vuelva a intentarlo";
            break;
        case "disabled" :  mensajeError = "Su cuenta se encuentra desactivada";
            break;
        case "expired" :  mensajeError = "Su sesión ha expirado, debe iniciar sesión nuevamente";
            break;
        case "checkout" :  mensajeError = "Algo salió mal, por favor vuelva a ingresar sus credenciales";
            break;
        case "loggedin" :  mensajeError = "Su cuenta ha llegado al máximo de sesiones simúltaneas activas. Debe cerrar sesión\n" +
            "                    en alguno de sus dispositivos para poder ingresar";
            break;
        case "username-credential" :  mensajeError = "Las credenciales de acceso no están registradas en la plataforma. Vuelva a intentarlo";
            break;
        default : null;
                    break;

    }
    bootbox.alert({
        message:mensajeError
    });

    }




function LoginUser() {
   /* if ($("#session_star").valid()) {
        var $btn = $("#btnLogin");
        $btn.button('loading');

        var params = {
            user: $("#txtUsuario").val(),
            pass: $("#txtPass").val()
        };

        $.ajax({
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            url: controlador + 'LoginUser',
            dataType: "json",
            data: JSON.stringify(params),
            success: function (dataObject, textStatus) {
                if (textStatus == "success") {
                    var data = dataObject.d;
                    if (data.ExitoAccion) {
                        window.document.location.href = data.Url;
                    } else {
                        bootbox.alert({
                            message: data.Mensaje
                        });
                        $("#txtUsuario").focus();
                        $btn.button('reset');
                    }
                }
            },
            error: function (xhr, ajaxOptions, thrownError) {
                var error = JSON.parse(xhr.responseText);
            },
            complete: function (data) {
                $btn.button('reset');
            }
        });
    } else {
        $('.input-group').find('label.my-error-class').each(function () {
            $(this).closest('.form-group').append($(this));
        });
    } */
}

function validarRegistros() {
    $("#session_star").validate({
        ignore: ".ignore",
        errorClass: "my-error-class",
        validClass: "my-valid-class",
        rules: {
            txtUsuario: {
                required: true,
                email: false
            },
            txtPass: {
                required: true
            }
        },
        messages: {
            txtUsuario: {
                required: "Debes ingresar un identificador para el usuario"
            },
            txtPass: {
                required: "Debes de ingresar una contraseña"
            }
        }
    });
}