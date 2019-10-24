package com.itsight.signbox.controller;

import com.itsight.signbox.constants.ViewConstant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class InicioController {

    @GetMapping(value = "")
    public ModelAndView principal(Model model) {
        return new ModelAndView(ViewConstant.MAIN_INICIO);
    }

}
