var controlador = _ctx +  'reportes/logs/';
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
        url: controlador + "Obtener",
        method: 'POST',
        pagination: true,
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        sidePagination: 'server',
        queryParamsType: 'else',
        pageSize: 20,
        queryParams: function (p) {
            var pageNumber = p.pageNumber;
            var pageSize = p.pageSize;
            return {
                archivo: $("#txtFiltroUsuario").val().trim(),
                fechaI: '10/10/2019'  ,// ($("#txtFechaInicioFiltro").val() === "") ? null : $("#txtFechaInicioFiltro").val(),
                fechaF: '11/11/2019', // ($("#txtFechaFinFiltro").val() === "") ? null : $("#txtFechaFinFiltro").val(),
                transaccion:  $("#txtFiltroTransaccion").val(),
                tipoDocumento: $("#txtFiltroTipo").val().trim(),
                documento: $("#txtFiltroDocumento").val().trim(),
                numeroCuenta: $("#txtNumeroCuenta").val().trim(),
                offset: pageNumber,
                limit: pageSize
            }
        },
        responseHandler: function (res) {
            return { rows: res, total: res.length > 0 ?  res[0].rows : 0 };
        }
    });
}
function limpiarFiltros() {
    $("#txtFiltroUsuario").val("");
    $("#txtFiltroTipo").val("0");
    $("#txtFiltroTransaccion").val("");
    $("#txtFiltroDocumento").val("");
    $("#txtNumeroCuenta").val("");
    listarRegistros();
    $(".panel-body").fadeOut();
}

function exportarReporte() {
    var usuario = $("#txtFiltroUsuario").val();
    var transaccion = $("#txtFiltroTransaccion").val();
    var tipo = $("#txtFiltroTipo").val();
    var documento = $("#txtFiltroDocumento").val();
    var fechaI = $("#txtFechaInicioFiltro").val();
    var fechaF = $("#txtFechaFinFiltro").val();
    var cuenta = $("#txtNumeroCuenta").val();

    window.location.href = controlador + "reporte-excel/?"
        + "?reporte=" + reporte
        + "&archivo=" + usuario
        + "&desde=" + fechaI
        + "&hasta=" + fechaF
        + "&tipo=" + tipo
        + "&documento=" + documento
        + "&transaccion=" + transaccion
        + "&cuenta=" + cuenta;
}