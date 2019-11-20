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
        url: controlador,
        method: 'get',
        pagination: true,
        sidePagination: 'server',
        pageSize: 5,
        queryParams: (res) => {

                res.archivo = $("#txtFiltroUsuario").val().trim(),
                res.fechaI = ($("#txtFechaInicioFiltro").val() === "") ? null : $("#txtFechaInicioFiltro").val(),
                res.fechaF = ($("#txtFechaFinFiltro").val() === "") ? null : $("#txtFechaFinFiltro").val(),
                res.transaccion =  $("#txtFiltroTransaccion").val(),
                res.tipoDocumento = $("#txtFiltroTipo").val().trim(),
                res.documento = $("#txtFiltroDocumento").val().trim(),
                res.numeroCuenta = $("#txtNumeroCuenta").val().trim()
            return res;
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

    var archivoQuery =  $("#txtFiltroUsuario").val();
    var transaccionQuery = $("#txtFiltroTransaccion").val();
    var tipoQuery = $("#txtFiltroTipo").val();
    var documentoQuery = $("#txtFiltroDocumento").val();
    var fInicioQuery = $("#txtFechaInicioFiltro").val();
    var fFinQuery = $("#txtFechaFinFiltro").val();
    var cuentaQuery = $("#txtNumeroCuenta").val();


    window.location = controlador + `reporte-excel/?` +
        `order=asc&` +
        `offset=&` +
        `limit=&` +
        `archivo=${archivoQuery}&` +
        `transaccion=${transaccionQuery}&` +
        `numeroCuenta=${cuentaQuery}&` +
        `documento=${documentoQuery}&` +
        `tipoDocumento=${tipoQuery}&` +
        `fechaI=${fInicioQuery}&` +
        `fechaF=${fFinQuery}`;

}