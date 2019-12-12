var controlador = _ctx + 'reportes/logs-trazabilidad-firma/';
var $table = $('#tblRegistros');
var TEXTO_SELECCIONE = "Seleccione";

$(function () {
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
    listarRegistros();
});

function listarRegistros() {

    $table.bootstrapTable('destroy');
    $table.bootstrapTable({
        url: controlador,
        method: 'get',
        pagination: true,
        sidePagination: 'server',
        queryParams: (res) => {
            res.transaccion =  $("#txtFiltroTransaccion").val() === "" ? null : $("#txtFiltroTransaccion").val() ,
            res.documento = $("#txtFiltroDocumento").val().trim() === "" ? null : $("#txtFiltroDocumento").val().trim(),
            res.numeroCuenta = $("#txtNumeroCuenta").val().trim() === "" ? null : $("#txtNumeroCuenta").val().trim()
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
function limpiarFiltros() {
    $("#txtFiltroTransaccion").val("");
    $("#txtFiltroDocumento").val("");
    $("#txtNumeroCuenta").val("");
    listarRegistros();
    $(".panel-body").fadeOut();
}


function exportarReporte() {

    const transaccion =   $("#txtFiltroTransaccion").val() ;
    const documento =  $("#txtFiltroDocumento").val();
    const numeroCuenta =   $("#txtNumeroCuenta").val();


    window.location = controlador + `reporte-excel/?` +
        `order=asc&` +
        `offset=&` +
        `limit=&` +
        `numeroCuenta=${numeroCuenta}&` +
        `documento=${documento}&` +
        `transaccion=${transaccion}`;

}