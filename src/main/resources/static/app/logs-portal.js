var controlador = _ctx + 'reportes/logs-portal/';
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
           res.entidad = $("#txtFiltroNombre").val() === "" ? null : $("#txtFiltroNombre").val();
           res.fechaInicio = ($("#txtFechaInicioFiltro").val() === "") ? null : $("#txtFechaInicioFiltro").val()      , //dateFromString($("#txtFechaInicioFiltro").val()),
           res.fechaFin = ($("#txtFechaFinFiltro").val() === "") ? null : $("#txtFechaFinFiltro").val()   //dateFromString($("#txtFechaFinFiltro").val());
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
    $("#txtFiltroNombre").val("");
    listarRegistros();
    $(".panel-body").fadeOut();
}

function exportarReporte() {

    const entidadQuery =  $("#txtFiltroNombre").val();
    const fInicioQuery =  $("#txtFechaInicioFiltro").val();
    const fFinQuery =  $("#txtFechaFinFiltro").val();


    window.location = controlador + `reporte-excel/?` +
                        `order=asc&` +
                        `offset=&` +
                        `limit=&` +
                        `entidad=${entidadQuery}&` +
                        `fechaInicio=${fInicioQuery}&` +
                        `fechaFin=${fFinQuery}`;

}

function testReporte() {

            //$("#load_pace").show();
            $.ajax({
                type: 'GET',
                url: controlador + "reporte-excel/",
                success: function(data) {

                    alert("lo hice");
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    var error = JSON.parse(xhr.responseText);
                },
                complete: function (data) {
                  //  $table.bootstrapTable('refresh');
                }
            });

}


