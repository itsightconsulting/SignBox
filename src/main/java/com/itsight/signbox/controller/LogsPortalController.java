package com.itsight.signbox.controller;

import com.itsight.signbox.constants.ViewConstant;
import com.itsight.signbox.domain.LogsPortal;
import com.itsight.signbox.domain.pojo.LogsPortalPOJO;
import com.itsight.signbox.domain.pojo.UsuariosPOJO;
import com.itsight.signbox.domain.query.LogsPortalQueryDTO;
import com.itsight.signbox.domain.query.UsuariosQueryDTO;
import com.itsight.signbox.service.LogsPortalService;
import com.itsight.signbox.service.LogsPortalProcedureInvoker;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.activation.MimetypesFileTypeMap;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/portalAdmin/reportes/logs-portal")
public class LogsPortalController {

    private LogsPortalService logsPortalService;

    private LogsPortalProcedureInvoker logsPortalProcedureInvoker;

    public LogsPortalController(LogsPortalService logsPortalService, LogsPortalProcedureInvoker logsPortalProcedureInvoker) {
        this.logsPortalService = logsPortalService;
        this.logsPortalProcedureInvoker = logsPortalProcedureInvoker;
    }

    @RequestMapping("/consulta")
    public ModelAndView principal(Model model){
        return new ModelAndView(ViewConstant.MAIN_LOGS_ADMIN);
    }

    @GetMapping("")
    public ResponseEntity<List<LogsPortalPOJO>> listarTodo(@ModelAttribute @Valid LogsPortalQueryDTO LogsPortalQueryDTO){


        return new ResponseEntity<List<LogsPortalPOJO>>(logsPortalProcedureInvoker.getLogsAdmin(LogsPortalQueryDTO), HttpStatus.OK);
    }


    @RequestMapping(path = "/reporte-excel", method = RequestMethod.GET)
    public HttpServletResponse  exportarReporte( HttpServletRequest request,
                                      HttpServletResponse response, @ModelAttribute @Valid LogsPortalQueryDTO logsPortalQueryDTO ) throws  IOException {

        try {

            List<LogsPortalPOJO> lstLogsPortal = logsPortalProcedureInvoker.getLogsAdmin(logsPortalQueryDTO);

            Workbook workbook = logsPortalService.generateExcel(lstLogsPortal);

            response.setContentType("application/vnd.ms-excel");
            ServletOutputStream outStream = response.getOutputStream();

            String fechaInicioQuery = logsPortalQueryDTO.getFechaInicio();
            String fechaFinQuery = logsPortalQueryDTO.getFechaFin();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyyMMdd");
            String fechaInicio = fechaInicioQuery == "" ? "" : LocalDate.parse(fechaInicioQuery, formatter).format(formatter2);
            String fechaFin = fechaFinQuery == "" ? "" : LocalDate.parse(fechaFinQuery, formatter).format(formatter2);

            String fn_fechaInicio = fechaInicio == "" ? "" : "_" + fechaInicio;
            String fn_fechaFin = fechaFin == "" ? "" : "_" + fechaFin;

            String fileName = "ReporteAuditoria" + fn_fechaInicio + fn_fechaFin + ".xlsx";

            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            workbook.write(outStream);
            outStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return response;

    }

}
