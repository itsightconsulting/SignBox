package com.itsight.signbox.controller;

import com.itsight.signbox.constants.ViewConstant;
import com.itsight.signbox.repository.UsuarioRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AuthController {

    UsuarioRepository usuarioRepository;

    public AuthController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    @GetMapping(value = "/portalAdminLogin")
    public ModelAndView loginView(@RequestParam(value = "error", required = false) String error,
                                  Model model) {
        if (error != null) {
            if (error.equals("true")) {
                model.addAttribute("error", "error");
            }
            else if(error.equals("session-expired")){
                model.addAttribute("error", "expired");
            }

            else if(error.equals("disabled")){
                model.addAttribute("error", "disabled");
            }

            else if(error.equals("checkout")){
                model.addAttribute("error", "checkout");
            }

            else if(error.equals("loggedin")){
                model.addAttribute("error", "loggedin");
            }
        }
        return new ModelAndView(ViewConstant.MAIN_LOGIN);
    }

    @GetMapping(value = "/clienteLogin")
    public ModelAndView loginClientesView(@RequestParam(value = "error", required = false) String error,
                                  Model model) {


    //    SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        if (error != null) {
            if (error.equals("true")) {
                model.addAttribute("error", "error");
            }
            else if(error.equals("session-expired")){
                model.addAttribute("error", "expired");
            }

            else if(error.equals("disabled")){
                model.addAttribute("error", "disabled");
            }

            else if(error.equals("checkout")){
                model.addAttribute("error", "checkout");
            }

            else if(error.equals("loggedin")){
                model.addAttribute("error", "loggedin");
            }
        }
        return new ModelAndView(ViewConstant.MAIN_CLIENTES_LOGIN);
    }



    @GetMapping(value = "")
    public ModelAndView index(Model model)
    {


        return new ModelAndView(ViewConstant.MAIN_INICIO);
    }
}
