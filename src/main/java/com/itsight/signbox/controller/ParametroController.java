package com.itsight.signbox.controller;

import com.itsight.signbox.constants.ViewConstant;
import com.itsight.signbox.domain.Parametro;
import com.itsight.signbox.domain.dto.ParametroDTO;
import com.itsight.signbox.domain.pojo.ParametroPOJO;
import com.itsight.signbox.service.ParametroProcedureInvoker;
import com.itsight.signbox.service.ParametroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/configuracion/parametros")
public class ParametroController {

    private ParametroService parametroService;

    private ParametroProcedureInvoker parametroProcedureInvoker;

    public ParametroController(ParametroService parametroService, ParametroProcedureInvoker parametroProcedureInvoker){
        this.parametroService = parametroService;
        this.parametroProcedureInvoker = parametroProcedureInvoker;
    }

    @GetMapping(value = "/gestion")
    public ModelAndView principal(Model model) {
        return new ModelAndView(ViewConstant.MAIN_PARAMETROS);
    }

    @GetMapping("/{id}")
    public Parametro listarParametro(@PathVariable Integer id){

        return parametroService.obtenerParametroPorId(id);
    }

    @GetMapping("")
    public ResponseEntity<List<ParametroPOJO>> listarTodo(@RequestParam Integer offset, @RequestParam Integer limit){

        return new ResponseEntity<List<ParametroPOJO>>(parametroProcedureInvoker.getParametros(limit,offset), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public Parametro actualizar(@PathVariable Integer id, @ModelAttribute @Valid ParametroDTO parametro){
        Parametro qParametro = listarParametro(id);
        qParametro.setModificadoPor("José Chacón");
        qParametro.setFechaModificacion(new Date());
        qParametro.setParametro(parametro);

        return parametroService.update(qParametro);
    }
}
