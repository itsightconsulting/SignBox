package com.itsight.signbox.controller;

import com.itsight.signbox.advice.NotFoundValidationException;
import com.itsight.signbox.constants.ViewConstant;
import com.itsight.signbox.domain.Ambientes;
import com.itsight.signbox.domain.dto.AmbientesDTO;
import com.itsight.signbox.domain.pojo.AmbientesPOJO;
import com.itsight.signbox.service.AmbientesProcedureInvoker;
import com.itsight.signbox.service.AmbientesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/portalAdmin/configuracion/ambientes")
public class AmbienteController {

    private AmbientesService ambientesService;
    private AmbientesProcedureInvoker ambientesProcedureInvoker;

    public AmbienteController(AmbientesService ambientesService,
                              AmbientesProcedureInvoker ambientesProcedureInvoker)
    {
        this.ambientesService = ambientesService;
        this.ambientesProcedureInvoker = ambientesProcedureInvoker;
    }

    @GetMapping(value = "/gestion")
    public ModelAndView principal(Model model) {
        return new ModelAndView(ViewConstant.MAIN_AMBIENTES);
    }

    @GetMapping("/{id}")
    public Ambientes ObtenerAmbientePorId(@PathVariable Integer id) throws NotFoundValidationException {

        return ambientesService.findOne(id);
    }

    @GetMapping("listarTodo")
    public ResponseEntity<List<AmbientesPOJO>> listarTodo(
            @RequestParam String nombre,
            @RequestParam Boolean flagActivo,
            @RequestParam String tipoBusqueda,
            @RequestParam Integer offset, @RequestParam Integer limit){

        return new ResponseEntity<List<AmbientesPOJO>>(
                ambientesProcedureInvoker.getAmbientes(
                        limit,offset, nombre, flagActivo, tipoBusqueda), HttpStatus.OK);
    }

    @PutMapping("actualizar/{id}")
    public Ambientes actualizar(@PathVariable Integer id, @ModelAttribute @Valid AmbientesDTO parametro) throws NotFoundValidationException {

        Ambientes qAmbientes = ambientesService.findOne(id);

        qAmbientes.setNombre(parametro.getNombre());
        qAmbientes.setModificadoPor("José Chacón");
        qAmbientes.setFechaModificacion(new Date());

        return ambientesService.update(qAmbientes);
    }

    @PostMapping("CambiarEstado/{id}")
    public Ambientes CambiarEstado(@PathVariable Integer id) throws NotFoundValidationException {

        Ambientes qAmbientes = ambientesService.findOne(id);

        qAmbientes.setFlagActivo(qAmbientes.getFlagActivo() == true ? false : true);
        qAmbientes.setModificadoPor("José Chacón");
        qAmbientes.setFechaModificacion(new Date());

        return ambientesService.update(qAmbientes);
    }

    @PostMapping("Agregar")
    public Ambientes Agregar(@ModelAttribute @Valid AmbientesDTO item){

        Ambientes qAmbientes = new Ambientes();

        qAmbientes.setNombre(item.getNombre());
        qAmbientes.setPrefijo(item.getPrefijo());
        qAmbientes.setFlagActivo(true);
        qAmbientes.setFlagEliminado(false);

        qAmbientes.setCreadoPor("José Chacón");
        qAmbientes.setFechaCreacion(new Date());

        return ambientesService.save(qAmbientes);
    }

    @GetMapping("ValidarCodigo")
    public boolean ValidarCodigo(@RequestParam String codigo){

        return ambientesService.validarCodigo(codigo) == false ? true : false;
    }

    @GetMapping("ObtenerPorId/{id}")
    public Ambientes ObtenerPorId(@PathVariable Integer id) throws NotFoundValidationException {

        return ambientesService.findOne(id);
    }

}
