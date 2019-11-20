package com.itsight.signbox.controller;

import com.itsight.signbox.constants.ViewConstant;
import com.itsight.signbox.domain.pojo.LogsDetalladoFirmaPOJO;
import com.itsight.signbox.domain.query.LogsDetalladoFirmaQueryDTO;
import com.itsight.signbox.service.LogsDetalladoFirmaProcedureInvoker;
import com.itsight.signbox.service.LogsDetalladoFirmaService;
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
@RequestMapping("/reportes/logs-detallados")
public class LogsDetalladoFirmaController {

    private LogsDetalladoFirmaProcedureInvoker logsDetalladoFirmaProcedureInvoker;
    private LogsDetalladoFirmaService logsDetalladoFirmaService;

    public LogsDetalladoFirmaController(LogsDetalladoFirmaProcedureInvoker logsDetalladoFirmaProcedureInvoker,
                                        LogsDetalladoFirmaService logsDetalladoFirmaService){
        this.logsDetalladoFirmaProcedureInvoker = logsDetalladoFirmaProcedureInvoker;
        this.logsDetalladoFirmaService = logsDetalladoFirmaService;
    }

    @RequestMapping("/consulta")
    public ModelAndView principal(Model model){
        return new ModelAndView(ViewConstant.MAIN_LOGS_DETALLADO_FIRMA);
    }

    @GetMapping("/")
    public ResponseEntity<List<LogsDetalladoFirmaPOJO>> Obtener(@ModelAttribute @Valid LogsDetalladoFirmaQueryDTO logsDetalladoFirmaQueryDTO){

        return new ResponseEntity<List<LogsDetalladoFirmaPOJO>> (logsDetalladoFirmaProcedureInvoker.getLogsDetalladoFirma(logsDetalladoFirmaQueryDTO), HttpStatus.OK);
    }




    @RequestMapping(path = "/reporte-excel", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public HttpServletResponse test(HttpServletRequest request,
                                    HttpServletResponse response, @ModelAttribute @Valid LogsDetalladoFirmaQueryDTO logsDetalladoFirmaQueryDTO ) throws IOException {

        try {

            List<LogsDetalladoFirmaPOJO> lstLogsDetallado= logsDetalladoFirmaProcedureInvoker.getLogsDetalladoFirma(logsDetalladoFirmaQueryDTO);

            Workbook workbook = logsDetalladoFirmaService.generateExcel(lstLogsDetallado);

            response.setContentType("application/vnd.ms-excel");
            ServletOutputStream outStream = response.getOutputStream();

            String fechaInicioQuery = logsDetalladoFirmaQueryDTO.getFechaI();
            String fechaFinQuery = logsDetalladoFirmaQueryDTO.getFechaF();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyyMMdd");
            String fechaInicio = fechaInicioQuery == "" ? "" : LocalDate.parse(fechaInicioQuery, formatter).format(formatter2);
            String fechaFin = fechaFinQuery == "" ? "" : LocalDate.parse(fechaFinQuery, formatter).format(formatter2);

            String fn_fechaInicio = fechaInicio == "" ? "" : "_" + fechaInicio;
            String fn_fechaFin = fechaFin == "" ? "" : "_" + fechaFin;

            String fileName = "ReporteDetalladoLogFirma_" + fn_fechaInicio + fn_fechaFin + ".xlsx";

            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            workbook.write(outStream);
            outStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

}
