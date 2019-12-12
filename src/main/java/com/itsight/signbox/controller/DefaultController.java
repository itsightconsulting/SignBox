package com.itsight.signbox.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DefaultController {
    @RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {

        if (request.isUserInRole("ROLE_ADMINISTRATOR")) {
            return "redirect:/configuracion/parametros/gestion";
        }
        if (request.isUserInRole("ROLE_OPERATOR")) {
            return "redirect:/seguridad/credenciales/gestion";
        }
        else if (request.isUserInRole("ROLE_AUDITOR")) {
            return "redirect:/reportes/logs-portal/consulta";
        }
        return "redirect:/";
    }
}