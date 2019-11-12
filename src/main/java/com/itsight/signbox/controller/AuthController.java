package com.itsight.signbox.controller;

import com.itsight.signbox.constants.ViewConstant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AuthController {


    @GetMapping(value = "/login")
    public ModelAndView loginView( /*@RequestParam(value = "error", required = false) String error, */
                                  Model model) {
    /*    if (error != null) {
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
        } */
        return new ModelAndView(ViewConstant.MAIN_LOGIN);
    }

    public ModelAndView index(Model model) {
        return new ModelAndView(ViewConstant.MAIN_INICIO);
    }
}
