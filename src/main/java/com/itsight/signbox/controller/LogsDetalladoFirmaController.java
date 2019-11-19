package com.itsight.signbox.controller;

import com.itsight.signbox.constants.ViewConstant;
import com.itsight.signbox.domain.pojo.LogsDetalladoFirmaPOJO;
import com.itsight.signbox.domain.query.LogsDetalladoFirmaQueryDTO;
import com.itsight.signbox.service.LogsDetalladoFirmaProcedureInvoker;
import com.itsight.signbox.service.LogsDetalladoFirmaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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


    /*

    @RequestMapping(path = "/reporte-excel", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public HttpServletResponse test(HttpServletRequest request,
                                    HttpServletResponse response, @ModelAttribute @Valid LogsDetalladoFirmaQueryDTO logsDetalladoFirmaQueryDTO ) throws IOException {

        try {

            List<LogsDetalladoFirmaPOJO> lstLogsPortal = logsDetalladoFirmaProcedureInvoker.getLogs(logsDetalladoFirmaQueryDTO);

            Workbook workbook = logsDetalladoFirmaService.generateExcel(lstLogsPortal);

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
