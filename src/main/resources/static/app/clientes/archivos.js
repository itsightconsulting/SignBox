var controlador = _ctx + 'cliente/archivos/';
var $table = $('#tblArchivos');
var TEXTO_SELECCIONE = "Seleccione";

$(function () {

    listarRegistros();
    /*
    var fechaAhora = new Date();
    var mesAtras = new Date();
    mesAtras.setDate(mesAtras.getDate() - 30);

    $('#dtFechaInicioFiltro').datetimepicker({
        format: 'DD/MM/YYYY', locale: 'es',
        defaultDate: mesAtras,
        useCurrent: false
    }).on('dp.hide', function (e) {
        actualizarMaxFechaFin();
    });
    $('#dtFechaFinFiltro').datetimepicker({
        format: 'DD/MM/YYYY', locale: 'es',
        defaultDate: new Date(fechaAhora.getFullYear(), fechaAhora.getMonth(), fechaAhora.getDate()),
        useCurrent: false
    }).on('dp.hide', function (e) {
        actualizarMinFechaInicio();
    });
    $("#btnBuscar").click(function () {
        $table.bootstrapTable('refresh');
        $(".panel-body").fadeOut();
    });
    $("#btnLimpiar").click(function () {
        limpiarFiltros();
    });
    $("#btnExportar").click(function () {
        exportarReporte();
    });

    */

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
    window.location = controlador + `test/?` +
        `path=${path}&`+
        `filename=${filename}`;
}




