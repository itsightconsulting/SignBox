$(function () {	
	register_show();
    buscador();
	inputRbt();
	//pieChart();
});

function register_show() {
	$(".btn-add").click(function() {
		$(".view_register").show().removeClass('slideOutLeft animated').addClass('fadeInLeftBig' + ' animated').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
        	$(".wrapper").scrollTop(0);
        	$(".wrapper").addClass("overflow_hide");
        });
	});

	$(".btn_register_hide").click(function() { 
		$(".view_register").removeClass('fadeInLeftBig animated').addClass('slideOutLeft' + ' animated').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
        	$(".view_register").hide();
        	$(".wrapper").removeClass("overflow_hide");
        });
	})
}

function irRegistro() {
    //$('#view_list').removeClass().addClass('fadeOutRightBig' + ' animated').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
    //    $('#view_list').hide();
    //    $(".view_register").show().removeClass('slideOutLeft animated').addClass('fadeInLeftBig' + ' animated').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
    //        $(".wrapper").scrollTop(0);
    //        $(".wrapper").addClass("overflow_hide");
    //    });
    //});
    $(".view_register").show().removeClass('slideOutLeft animated').addClass('fadeInLeftBig' + ' animated').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
        $(".wrapper").scrollTop(0);
        $(".wrapper").addClass("overflow_hide");
    });
}

function irListado() {
    $(".view_register").removeClass('fadeInLeftBig animated').addClass('slideOutLeft' + ' animated').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
        $(".view_register").hide();
        $(".wrapper").removeClass("overflow_hide");
    });

    //$('#view_register').removeClass().addClass('fadeOutRightBig' + ' animated').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
    //    $('#view_register').hide();
    //    $("#view_list").show().removeClass().addClass('fadeInLeft' + ' animated').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
    //    });
    //});

}




function inputRbt() {
    $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
      checkboxClass: 'icheckbox_minimal-blue',
      radioClass: 'iradio_minimal-blue'
    });
}

function buscador() {
    $("#accordion .panel-heading").click(function(){
        $(".panel-body").fadeToggle(300);
    });
}

//function pieChart() {
//    var pieChartCanvas = $("#pieChart").get(0).getContext("2d");
//    var pieChart = new Chart(pieChartCanvas);
//    var PieData = [
//      {
//        value: 50,
//        color: "#00a65a",
//        highlight: "#00a65a",
//        label: "A. Finalizadas"
//      },
//      {
//        value: 30,
//        color: "#f56954",
//        highlight: "#f56954",
//        label: "A. Pendientes"
//      },
//      {
//        value: 20,
//        color: "#3c8dbc",
//        highlight: "#3c8dbc",
//        label: "A. Próximas"
//      }
//    ];
//    var pieOptions = {
//      segmentShowStroke: true,
//      segmentStrokeColor: "#fff",
//      segmentStrokeWidth: 2,
//      percentageInnerCutout: 50,
//      animationSteps: 100,
//      animationEasing: "easeOutBounce",
//      animateRotate: true,
//      animateScale: false,
//      responsive: true,
//      maintainAspectRatio: true,
//      legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++){%><li><span style=\"background-color:<%=segments[i].fillColor%>\"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>"
//    };
//    pieChart.Doughnut(PieData, pieOptions);
//}

//function areaChart() {
//	var areaChartCanvas = $("#areaChart").get(0).getContext("2d");
//    var areaChart = new Chart(areaChartCanvas);
//    var areaChartData = {
//      labels: ["January", "February", "March", "April", "May", "June", "July"],
//      datasets: [
//        {
//          label: "Electronics",
//          fillColor: "rgba(210, 214, 222, 1)",
//          strokeColor: "rgba(210, 214, 222, 1)",
//          pointColor: "rgba(210, 214, 222, 1)",
//          pointStrokeColor: "#c1c7d1",
//          pointHighlightFill: "#fff",
//          pointHighlightStroke: "rgba(220,220,220,1)",
//          data: [65, 59, 80, 81, 56, 55, 40]
//        },
//        {
//          label: "Digital Goods",
//          fillColor: "rgba(60,141,188,0.9)",
//          strokeColor: "rgba(60,141,188,0.8)",
//          pointColor: "#3b8bba",
//          pointStrokeColor: "rgba(60,141,188,1)",
//          pointHighlightFill: "#fff",
//          pointHighlightStroke: "rgba(60,141,188,1)",
//          data: [28, 48, 40, 19, 86, 27, 90]
//        }
//      ]
//    };

//    var areaChartOptions = {
//      showScale: true,
//      scaleShowGridLines: false,
//      scaleGridLineColor: "rgba(0,0,0,.05)",
//      scaleGridLineWidth: 1,
//      scaleShowHorizontalLines: true,
//      scaleShowVerticalLines: true,
//      bezierCurve: true,
//      bezierCurveTension: 0.3,
//      pointDot: false,
//      pointDotRadius: 4,
//      pointDotStrokeWidth: 1,
//      pointHitDetectionRadius: 20,
//      datasetStroke: true,
//      datasetStrokeWidth: 2,
//      datasetFill: true,
//      legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].lineColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>",
//      maintainAspectRatio: true,
//      responsive: true
//    };

//    //Create the line chart
//    areaChart.Line(areaChartData, areaChartOptions);
//}