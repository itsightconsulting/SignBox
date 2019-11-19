var controlador = _ctx + 'reportes/logs-notificaciones/';
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


function listarRegistros() {

    $table.bootstrapTable('destroy');
    $table.bootstrapTable({
        url: controlador,
        method: 'get',
        pagination: true,
        sidePagination: 'server',
        queryParams: (res) => {
           res.fechaInicio =  $("#txtFechaInicioFiltro").val() === "" ? null : $("#txtFechaInicioFiltro").val() ,
           res.fechaFin =  $("#txtFechaFinFiltro").val() === "" ? null : $("#txtFechaFinFiltro").val() ,
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
    var transaccion = $("#txtFiltroTransaccion").val();
    var documento = $("#txtFiltroDocumento").val();
    var cuenta = $("#txtNumeroCuenta").val();

    window.location.href = siteRoot + "handlers/DownloadReports.ashx"
        + "?reporte=" + reporte
        + "&documento=" + documento
        + "&transaccion=" + transaccion
        + "&cuenta=" + cuenta;
}