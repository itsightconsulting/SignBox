package com.itsight.signbox.controller;

import com.itsight.signbox.constants.ViewConstant;
import com.itsight.signbox.domain.pojo.LogsNotificacionesPOJO;
import com.itsight.signbox.domain.query.LogsNotificacionesQueryDTO;
import com.itsight.signbox.service.LogsNotificacionesProcedureInvoker;
import com.itsight.signbox.service.LogsNotificacionesService;
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
@RequestMapping("/reportes/logs-notificaciones")
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








}
