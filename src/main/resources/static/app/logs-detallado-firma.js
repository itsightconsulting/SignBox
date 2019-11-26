var controlador = _ctx + 'portalAdmin/reportes/logs-detallados/';
var $table = $('#tblRegistros');
var TEXTO_SELECCIONE = "Seleccione";

$(function () {
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
    listarRegistros();
});

function actualizarMaxFechaFin() {
    if ($('#dtFechaFinFiltro').data("DateTimePicker") != null) {
        $('#dtFechaFinFiltro').data("DateTimePicker").destroy();
    }
    var fechaIoriginal = dateFromString($("#txtFechaInicioFiltro").val());
    var fechaI = dateFromString($("#txtFechaInicioFiltro").val());
    fechaI.setDate(fechaI.getDate() + 90);

    $("#dtFechaFinFiltro").datetimepicker({
        format: 'DD/MM/YYYY', locale: 'es',
        maxDate: fechaI,
        minDate: fechaIoriginal,
        useCurrent: false
    });
}
function actualizarMinFechaInicio() {
    if ($('#dtFechaInicioFiltro').data("DateTimePicker") != null) {
        $('#dtFechaInicioFiltro').data("DateTimePicker").destroy();
    }
    var fechaForiginal = dateFromString($("#txtFechaFinFiltro").val());
    var fechaF = dateFromString($("#txtFechaFinFiltro").val());
    fechaF.setDate(fechaF.getDate() - 90);

    $("#dtFechaInicioFiltro").datetimepicker({
        format: 'DD/MM/YYYY', locale: 'es',
        minDate: fechaF,
        maxDate: fechaForiginal,
        useCurrent: false
    });
}
function listarRegistros() {  $table.bootstrapTable('destroy');
    $table.bootstrapTable({
        url: controlador,
        method: 'get',
        pagination: true,
        sidePagination: 'server',
        pageSize: 5,
        queryParams: (res) => {

                res.fechaI = ($("#txtFechaInicioFiltro").val() === "") ? null : $("#txtFechaInicioFiltro").val(),
                res.fechaF =  ($("#txtFechaFinFiltro").val() === "") ? null : $("#txtFechaFinFiltro").val(),
                res.transaccion =  $("#txtFiltroTransaccion").val() == "" ? null : $("#txtFiltroTransaccion").val() ,
                res.tipoDocumento = $("#txtFiltroTipo").val() === "0" ? null :  $("#txtFiltroTipo").val(),
                res.documento = $("#txtFiltroDocumento").val().trim() === "" ? null :  $("#txtFiltroDocumento").val().trim()  ,
                res.numeroCuenta = $("#txtNumeroCuenta").val().trim() === "" ? null : $("#txtNumeroCuenta").val().trim()

            return res;
        },
        onLoadSuccess: function (data) {
        },
        onLoadError: function (xhr) {
            console.log(xhr);
        },
        responseHandler: function (res) {
            return {rows: res, total: res.length > 0 ? res[0].rows : 0};
        }
    });
}


function limpiarFiltros() {
    $("#txtFiltroTipo").val("0");
    $("#txtFiltroTransaccion").val("");
    $("#txtFiltroDocumento").val("");
    $("#txtNumeroCuenta").val("");
    listarRegistros();
    $(".panel-body").fadeOut();
}


function exportarReporte() {

         const fechaI = $("#txtFechaInicioFiltro").val();
        const fechaF =   $("#txtFechaFinFiltro").val();
        const transaccion =   $("#txtFiltroTransaccion").val();
        const tipoDocumento =  $("#txtFiltroTipo").val() === "0" ? "" :  $("#txtFiltroTipo").val();
        const documento =   $("#txtFiltroDocumento").val().trim() ;
        const numeroCuenta =  $("#txtNumeroCuenta").val().trim();


    window.location = controlador + `reporte-excel/?` +
        `order=asc&` +
        `offset=&` +
        `limit=&` +
        `transaccion=${transaccion}&` +
        `tipoDocumento=${tipoDocumento}&` +
        `documento=${documento}&` +
        `numeroCuenta=${numeroCuenta}&` +
        `fechaI=${fechaI}&` +
        `fechaF=${fechaF}`;

}