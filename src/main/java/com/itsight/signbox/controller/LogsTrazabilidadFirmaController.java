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
@RequestMapping("/portalAdmin/reportes/logs-trazabilidad-firma")
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


    @RequestMapping(path = "/reporte-excel", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public HttpServletResponse test(HttpServletRequest request,
                                    HttpServletResponse response, @ModelAttribute @Valid LogsTrazabilidadFirmaQueryDTO logsTrazabilidadFirmaQueryDTO ) throws IOException {

        try {

            List<LogsTrazabilidadFirmaPOJO> lstLogsPortal = logsTrazabilidadFirmaProcedureInvoker.getLogsTrazabilidadFirma(logsTrazabilidadFirmaQueryDTO);

            Workbook workbook = logsTrazabilidadFirmaService.generateExcel(lstLogsPortal);

            response.setContentType("application/vnd.ms-excel");
            ServletOutputStream outStream = response.getOutputStream();

            String documentoQuery = logsTrazabilidadFirmaQueryDTO.getDocumento();
            String transaccionQuery = logsTrazabilidadFirmaQueryDTO.getTransaccion();
            String nroCuentaQuery = logsTrazabilidadFirmaQueryDTO.getNumeroCuenta();


            String fn_documento = documentoQuery == "" ? "" : "_documento_" + documentoQuery;
            String fn_transaccion = transaccionQuery == "" ? "" : "_transaccion_" + transaccionQuery;
            String fn_nroCuenta = nroCuentaQuery == "" ? "" : "_nroCuenta_" + nroCuentaQuery;

            String fileName = "ReporteLogTrazabilidadFirma_" + fn_documento + fn_transaccion + fn_nroCuenta   + ".xlsx";

            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            workbook.write(outStream);
            outStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

}
