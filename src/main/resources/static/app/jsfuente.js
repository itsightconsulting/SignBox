let token = $("meta[name='_csrf']").attr("content");
let csrf_frk_header = $("meta[name='_csrf_header']").attr("content");
var _ctx = $('meta[name="_ctx"]').attr('content');
let $defaulTextButton = "";
let $gbInterval = 0;
var _URL = window.URL || window.webkitURL;




(function setUserFullNameToPage(){
    const user = document.querySelector('.user-full-name');
    if(user){
        user.innerHTML = getFullNameFromUser();
    }
})();


function getFullNameFromUser(){
    return b64DecodeUnicode(getCookie("GLL_NOMBRE_COMPLETO")).trim().replace(" xxx", "");
}

function b64DecodeUnicode(str) {
    // Going backwards: from bytestream, to percent-encoding, to original string.
    try {
        return decodeURIComponent(atob(str).split('').map(function(c) {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        }).join(''));
    }catch (e) {
        console.log('b64DecodeUnico..cth');
        return atob(str);
    }
}


function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

/* por implementar



function exception(xhr, errorName) {
    console.info(xhr);
    const sCode = xhr['status'];

    if(sCode === 0 && xhr.readyState === 0){
        $.smallBox({
            content: "<i class='fa fa-fw fa-exclamation-triangle'></i><i>Ups, parece que se ha perdido su conexión a internet. Compruébela y vuelva a intentarlo."+ "</i>",
            color: "#8a6d3b",
            iconSmall: "fa fa-warning fa-2x fadeInRight animated",
            timeout: 10000,
        });
        return;
    }

    if(sCode === 405){
        if(xhr.responseText && xhr.responseText.startsWith("<!DOCTYPE html>")){
            setTimeout(()=>{
                $.SmartMessageBox({
                    title: "<i class='fa fa-exclamation-triangle fa-fw' style='color:yellow;'></i> <b>RUNFIT</b>",
                    content: "<div class='font-md'><i> Su sesión ha expirado, usted será redireccionado a la página de login...</div></i>",
                    buttons: '[OK]'
                }, function (ButtonPressed) {
                    if(ButtonPressed == 'OK'){
                        window.location.href = _ctx+'login';
                    }
                });
            }, 1000);
            return;
        }
        $.smallBox({content: "Request method 'G|PT|PS|D' not supported or denied permissions",
                       color: "alert",
                       timeout: 10000});
        return;
    }

    if(typeof xhr == 'number' && xhr < 0){
        exception2(xhr, errorName);
    }else{
        try {

            if(sCode != 401 && sCode != 403 && sCode != 400 && sCode !== 409){
                const msg = xhr['responseJSON']['error'] ? xhr['responseJSON']['error'] : xhr['responseJSON']['message'] ? xhr['responseJSON']['message'] : 'Indefinido';
                $.smallBox({
                    content: "<i> Comuníquese con el administrador <br/> Code error: " + xhr['status'] + " <br/> Detail: " + msg + "</i>",
                    color: "#8a6d3b",
                    iconSmall: "fa fa-warning fa-2x fadeInRight animated",
                    timeout: 10000,
                });
            }else{
                if(xhr['status'] == 401 && (xhr.responseJSON && xhr.responseJSON.message === "Ajax Request Denied (Session Expired)")){
                    setTimeout(()=>{
                        $.SmartMessageBox({
                            title: "<i class='fa fa-exclamation-triangle fa-fw' style='color:yellow;'></i> <b>RUNFIT</b>",
                            content: "<div class='font-md'><i> Su sesión ha expirado, usted será redireccionado a la página de login...</div></i>",
                            buttons: '[OK]'
                        }, function (ButtonPressed) {
                            if(ButtonPressed == 'OK'){
                                window.location.href = _ctx+'login';
                            }
                        })
                    }, 1000);
                }
                else if(xhr['status'] == 403){
                    $.smallBox({
                        content: "<i> No se encuentra autorizado para <br/> ejecutar esta acción...</i>",
                        color: "#8a6d3b",
                        iconSmall: "fa fa-info fa-3x fadeInRight animated",
                        timeout: 5000,
                    });
                } else if(xhr['status'] == 400){
                    if(typeof xhr.responseJSON == 'object' && typeof xhr.responseJSON.message !== 'undefined' && xhr.responseJSON.message === 'Validation has failed'){
                        const errors = [];
                        xhr.responseJSON.subErrors.forEach(v=>{
                            const field = capitalizeFirstLetter(v.object);
                            const ele = document.getElementById(field);
                            ele != undefined ? ele.classList.toggle('state-error') : "";
                            if(v.message.includes("estar entre")){
                                v.message += " caracteres";
                            }
                            errors.push(`${field}: ${capitalizeFirstLetter(v.message)}`);

                        });
                        smallBoxAlertValidation2(errors);
                    }else{
                        if(typeof xhr.responseJSON == 'object' && typeof xhr.responseJSON.message !== 'undefined'){
                            $.smallBox({
                                content: "<i> "+xhr.responseJSON.message+"</i>",
                                color: "#8a6d3b",
                                iconSmall: "fa fa-info fa-3x fadeInRight animated",
                                timeout: 5000,
                            });
                        }else{
                            $.smallBox({
                                content: "<i> Usted ha realizado una petición incorrecta...</i>",
                                color: "#8a6d3b",
                                iconSmall: "fa fa-info fa-3x fadeInRight animated",
                                timeout: 5000,
                            });
                        }


                    }
                } else if(xhr["status"] === 409){
                    $.smallBox({
                        content: "<i> "+xhr.responseJSON.message+"...</i>",
                        color: "#8a6d3b",
                        iconSmall: "fa fa-info fa-3x fadeInRight animated",
                        timeout: 5000,
                    });
                }
            }

        } catch(ex) {
            console.error(ex);
            $.smallBox({
                content: "<i> Comuníquese con el administrador <br/> Code error: " + xhr['status'] + " <br/> Detail: " + "Error" + "</i>",
                color: "#8a6d3b",
                iconSmall: "fa fa-warning fa-2x fadeInRight animated",
                timeout: 5000,
            });
        }
    }
}


 */