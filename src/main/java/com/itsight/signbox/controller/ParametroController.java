package com.itsight.signbox.controller;

import com.itsight.signbox.constants.ViewConstant;
import com.itsight.signbox.domain.Parametro;
import com.itsight.signbox.domain.dto.ParametroDTO;
import com.itsight.signbox.generic.BaseService;
import com.itsight.signbox.service.ParametroService;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin/parametros")
public class ParametroController {

    private ParametroService parametroService;

    public ParametroController(ParametroService parametroService){
        this.parametroService = parametroService;
    }

    @GetMapping(value = "")
    public ModelAndView principal(Model model) {
        return new ModelAndView(ViewConstant.MAIN_PARAMETROS);
    }

    @GetMapping("/obtener/")
    public Parametro listarParametro(@RequestParam Integer id){

        return parametroService.obtenerParametroPorId(id);
    }

    @GetMapping("/obtener/todo")
    public ResponseEntity<List<Parametro>> listarTodo(){

        return new ResponseEntity<>(parametroService.obtenerParametros(), HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public Parametro actualizar(@PathVariable Integer id, @ModelAttribute @Valid ParametroDTO parametro){

        Parametro qParametro = listarParametro(id);
        qParametro.setModificadoPor("José Chacón");
        qParametro.setFechaModificacion(new Date());
        qParametro.setParametro(parametro);

        return parametroService.update(qParametro);
    }

}
