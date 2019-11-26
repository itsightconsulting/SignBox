package com.itsight.signbox.controller;

import com.itsight.signbox.constants.ViewConstant;
import com.itsight.signbox.domain.pojo.LogsNotificacionesPOJO;
import com.itsight.signbox.domain.pojo.LogsPortalPOJO;
import com.itsight.signbox.domain.query.LogsNotificacionesQueryDTO;
import com.itsight.signbox.domain.query.LogsPortalQueryDTO;
import com.itsight.signbox.service.LogsNotificacionesProcedureInvoker;
import com.itsight.signbox.service.LogsNotificacionesService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/portalAdmin/reportes/logs-notificaciones")
public class LogsNotificacionesController {


    private LogsNotificacionesProcedureInvoker logsNotificacionesProcedureInvoker;
    private LogsNotificacionesService logsNotificacionesService;

    public LogsNotificacionesController(LogsNotificacionesProcedureInvoker logsNotificacionesProcedureInvoker,
                                           LogsNotificacionesService logsNotificacionesService){
        this.logsNotificacionesProcedureInvoker = logsNotificacionesProcedureInvoker;
        this.logsNotificacionesService = logsNotificacionesService;
    }

    @RequestMapping("/consulta")
    public ModelAndView principal(Model model){
        return new ModelAndView(ViewConstant.MAIN_LOGS_NOTIFICACIONES);
    }

    @GetMapping("/")
    public ResponseEntity<List<LogsNotificacionesPOJO>> Obtener(@ModelAttribute @Valid LogsNotificacionesQueryDTO logsNotificacionesQueryDTO){

        return new ResponseEntity<List<LogsNotificacionesPOJO>> (logsNotificacionesProcedureInvoker.getLogsNotificaciones(logsNotificacionesQueryDTO), HttpStatus.OK);
    }


    @RequestMapping(path = "/reporte-excel", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public HttpServletResponse test(HttpServletRequest request,
                                    HttpServletResponse response, @ModelAttribute @Valid LogsNotificacionesQueryDTO logsNotificacionesQueryDTO ) throws IOException {

        try {

            List<LogsNotificacionesPOJO> lstLogsPortal = logsNotificacionesProcedureInvoker.getLogsNotificaciones(logsNotificacionesQueryDTO);

            Workbook workbook = logsNotificacionesService.generateExcel(lstLogsPortal);

            response.setContentType("application/vnd.ms-excel");
            ServletOutputStream outStream = response.getOutputStream();

            String fechaInicioQuery = logsNotificacionesQueryDTO.getFechaInicio();
            String fechaFinQuery = logsNotificacionesQueryDTO.getFechaFin();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyyMMdd");
            String fechaInicio = fechaInicioQuery == "" ? "" : LocalDate.parse(fechaInicioQuery, formatter).format(formatter2);
            String fechaFin = fechaFinQuery == "" ? "" : LocalDate.parse(fechaFinQuery, formatter).format(formatter2);

            String fn_fechaInicio = fechaInicio == "" ? "" : "_" + fechaInicio;
            String fn_fechaFin = fechaFin == "" ? "" : "_" + fechaFin;

            String fileName = "ReporteLogNotificaciones" + fn_fechaInicio + fn_fechaFin + ".xlsx";

            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            workbook.write(outStream);
            outStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return response;

    }








}
