var controlador = _ctx + 'cliente/archivos/';
var $table = $('#tblArchivos');
var TEXTO_SELECCIONE = "Seleccione";

$(function () {
    listarRegistros();
})

function listarRegistros() {

    $table.bootstrapTable('destroy');
    $table.bootstrapTable({
        url: controlador,
        method: 'get',
        pagination: true,
        sidePagination: 'server',
        queryParams: (res) => {
           return res;
        },
        pageSize: 5,
        onLoadSuccess: function (data) {
        },
        onLoadError: function (xhr) {
            exception(xhr);
        },
        responseHandler: function (res) {
            return {rows: res, total: res.length > 0 ? res[0].rows : 0};
        }
    });


}

function opciones(value, row, index) {

    const rutaArchivo = row.rutaArchivoFinal + row.nombreArchivo;
    const descarga = String.Format("<a href='javascript:descargarArchivo(\"" + row.rutaArchivoFinal  +"\", \"" + row.nombreArchivo  +"\");' title='Descargar archivo'><i class='glyphicon glyphicon-download'></i></a>");
    return descarga + "&nbsp;";
}

function descargarArchivo(path, filename){

    $.ajax({
        type: 'GET',
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        url: controlador + 'validar-descarga/',
        dataType: "json",
        data: {path : path , filename : filename},
        success: function (dataObject, textStatus) {

            debugger
            if(dataObject === -4){
                window.location = controlador + `descarga/?` +
                    `path=${path}&`+
                    `filename=${filename}`;
            }else{
                let mensajeError;
                switch(dataObject) {
                    case -9 :
                        mensajeError = "Ha sucedido un error al momento de descargar el archivo";
                        break;
                    case -12 :  mensajeError = "No se encuentra el archivo en nuestra base de datos";
                        break;
                    default : null;
                        break;
                }
                bootbox.alert({
                    message:mensajeError
                });
            }
        },
        error: function (xhr, ajaxOptions, thrownError) {

        },
        complete: function (data) {
        }
    });

}




