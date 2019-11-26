package com.itsight.signbox.controller;

import com.itsight.signbox.constants.ViewConstant;
import com.itsight.signbox.service.CertificadoProcedureInvoker;
import com.itsight.signbox.service.CertificadoService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private CertificadoService certificadoService;

    private CertificadoProcedureInvoker certificadoProcedureInvoker;

    public ClienteController(CertificadoService certificadoService, CertificadoProcedureInvoker certificadoProcedureInvoker) {
        this.certificadoService = certificadoService;
        this.certificadoProcedureInvoker = certificadoProcedureInvoker;
    }

    @RequestMapping("/index")
    public ModelAndView principal(Model model){
        return new ModelAndView(ViewConstant.MAIN_CLIENTES_INICIO);
    }

}
