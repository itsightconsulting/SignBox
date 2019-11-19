package com.itsight.signbox.controller;

import com.itsight.signbox.constants.ViewConstant;
import com.itsight.signbox.domain.pojo.LogsTrazabilidadFirmaPOJO;
import com.itsight.signbox.domain.query.LogsTrazabilidadFirmaQueryDTO;
import com.itsight.signbox.service.LogsTrazabilidadFirmaProcedureInvoker;
import com.itsight.signbox.service.LogsTrazabilidadFirmaService;
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
@RequestMapping("/reportes/logs-trazabilidad-firma")
public class LogsTrazabilidadFirmaController {

    private LogsTrazabilidadFirmaProcedureInvoker logsTrazabilidadFirmaProcedureInvoker;
    private LogsTrazabilidadFirmaService logsTrazabilidadFirmaService;

    public LogsTrazabilidadFirmaController(LogsTrazabilidadFirmaProcedureInvoker logsTrazabilidadFirmaProcedureInvoker,
                                           LogsTrazabilidadFirmaService logsTrazabilidadFirmaService){
        this.logsTrazabilidadFirmaProcedureInvoker = logsTrazabilidadFirmaProcedureInvoker;
        this.logsTrazabilidadFirmaService = logsTrazabilidadFirmaService;
    }

    @RequestMapping("/consulta")
    public ModelAndView principal(Model model){
        return new ModelAndView(ViewConstant.MAIN_LOGS_TRAZABILIDAD_FIRMA);
    }

    @GetMapping("/")
    public ResponseEntity<List<LogsTrazabilidadFirmaPOJO>> Obtener(@ModelAttribute @Valid LogsTrazabilidadFirmaQueryDTO logsTrazabilidadFirmaQueryDTO){

        return new ResponseEntity<List<LogsTrazabilidadFirmaPOJO>> (logsTrazabilidadFirmaProcedureInvoker.getLogsTrazabilidadFirma(logsTrazabilidadFirmaQueryDTO), HttpStatus.OK);
    }


    /*

    @RequestMapping(path = "/reporte-excel", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public HttpServletResponse test(HttpServletRequest request,
                                    HttpServletResponse response, @ModelAttribute @Valid LogsTrazabilidadFirmaQueryDTO logsTrazabilidadFirmaQueryDTO ) throws IOException {

        try {

            List<LogsTrazabilidadFirmaPOJO> lstLogsPortal = logsTrazabilidadFirmaProcedureInvoker.getLogs(logsTrazabilidadFirmaQueryDTO);

            Workbook workbook = logsTrazabilidadFirmaService.generateExcel(lstLogsPortal);

            response.setContentType("application/vnd.ms-excel");
            ServletOutputStream outStream = response.getOutputStream();

            String fechaInicioQuery = logsTrazabilidadFirmaQueryDTO.getFechaI();
            String fechaFinQuery = logsTrazabilidadFirmaQueryDTO.getFechaF();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyyMMdd");
            String fechaInicio = fechaInicioQuery == "" ? "" : LocalDate.parse(fechaInicioQuery, formatter).format(formatter2);
            String fechaFin = fechaFinQuery == "" ? "" : LocalDate.parse(fechaFinQuery, formatter).format(formatter2);

            String fn_fechaInicio = fechaInicio == "" ? "" : "_" + fechaInicio;
            String fn_fechaFin = fechaFin == "" ? "" : "_" + fechaFin;

            String fileName = "ReporteLogFirma_" + fn_fechaInicio + fn_fechaFin + ".xlsx";

            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            workbook.write(outStream);
            outStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }
*/
}
