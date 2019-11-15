

function validUniqueEmailOrUsernameOrNomPag(input, modoAcceso,pathURLDiff){
 //   input.setAttribute('disabled', 'disabled');
   // input.previousElementSibling.previousElementSibling.classList.add('hidden');
   // input.previousElementSibling.classList.remove('hidden');
  /*  const params = {
        username: modoAccesoId === 1 ?  $("#txtUserAD").val()  : $("#txtUserName").val(),
        modoAccesoId : modoAccesoId
    };
    */
    $.ajax({
        type: 'GET',
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        url: controlador + 'username/validacion',
        blockLoading: true,
        dataType: "json",
        data: {username: input.value , modoAccesoId: modoAcceso},
        noOne: false,
        success: function (res) {
            console.log(res);
            if(res.isUsernameValid === true){
                if(verifiedNames){
                    verifiedNames.push(input.value);
                }
               // input.previousElementSibling.previousElementSibling.classList.remove('hidden');
            }else{
                  $(input).rules('add', {dynUnique: input.value, messages:{dynUnique: 'El nombre de usuario ingresado ya se encuentra registrado'}});
                  $(input).valid();
            }
        },
        error: function (xhr, ajaxOptions, thrownError) {
            var error = JSON.parse(xhr.responseText);
       //     MostrarAlerta("alertBoxTabAreas", "alert-danger", String.Format("<strong>Error!</strong> {0}", error.Message));
        },
        complete: function (data) {
        }
    });
}
