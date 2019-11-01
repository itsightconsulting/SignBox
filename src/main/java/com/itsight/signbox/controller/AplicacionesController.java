package com.itsight.signbox.controller;

import com.itsight.signbox.advice.NotFoundValidationException;
import com.itsight.signbox.constants.ViewConstant;
import com.itsight.signbox.domain.Aplicaciones;
import com.itsight.signbox.domain.dto.AplicacionesDTO;
import com.itsight.signbox.domain.pojo.AmbientesPOJO;
import com.itsight.signbox.domain.pojo.AplicacionesPOJO;
import com.itsight.signbox.service.AmbientesProcedureInvoker;
import com.itsight.signbox.service.AplicacionesProcedureInvoker;
import com.itsight.signbox.service.AplicacionesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin/aplicaciones")
public class AplicacionesController {

    private AplicacionesService aplicacionesService;
    private AplicacionesProcedureInvoker aplicacionesProcedureInvoker;
    private AmbientesProcedureInvoker ambientesProcedureInvoker;

    public AplicacionesController(AplicacionesService aplicacionesService,
                                  AplicacionesProcedureInvoker aplicacionesProcedureInvoker,
                                  AmbientesProcedureInvoker ambientesProcedureInvoker) {
        this.aplicacionesService = aplicacionesService;
        this.aplicacionesProcedureInvoker = aplicacionesProcedureInvoker;
        this.ambientesProcedureInvoker = ambientesProcedureInvoker;
    }

    @GetMapping(value = "/gestion")
    public ModelAndView principal(Model model) {
        return new ModelAndView(ViewConstant.MAIN_APLICACIONES);
    }

    @GetMapping("/{id}")
    public Aplicaciones ObtenerAmbientePorId(@PathVariable Integer id) throws NotFoundValidationException {

        return aplicacionesService.findOne(id);
    }

    @GetMapping("listarTodo")
    public ResponseEntity<List<AplicacionesPOJO>> listarTodo(
            @RequestParam String nombre,
            @RequestParam Boolean flagActivo,
            @RequestParam String tipoBusqueda,
            @RequestParam Integer offset, @RequestParam Integer limit){

        return new ResponseEntity<List<AplicacionesPOJO>>(
                aplicacionesProcedureInvoker.getAplicaciones(
                        limit,offset, nombre, flagActivo, tipoBusqueda), HttpStatus.OK);
    }

    @GetMapping("ListarAmbientes")
    public ResponseEntity<List<AmbientesPOJO>> ListarAmbientes(){

        return new ResponseEntity<List<AmbientesPOJO>>(
                ambientesProcedureInvoker.getAmbientes(
                        100,1, "", true, "Todo"), HttpStatus.OK);
    }

    @GetMapping("ListarCertificados")
    public ResponseEntity<List<AmbientesPOJO>> ListarCertificados(){

        return new ResponseEntity<List<AmbientesPOJO>>(
                ambientesProcedureInvoker.getAmbientes(
                        100,1, "", true, "Todo"), HttpStatus.OK);
    }

    @PutMapping("actualizar/{id}")
    public Aplicaciones actualizar(@PathVariable Integer id, @ModelAttribute @Valid AplicacionesDTO parametro) throws NotFoundValidationException {

        Aplicaciones qAplicaciones = aplicacionesService.findOne(id);

        qAplicaciones.setNombre(parametro.getNombre());
        qAplicaciones.setDescripcion(parametro.getDescripcion());
        qAplicaciones.setUsuarioLider(parametro.getUsuarioLider());
        qAplicaciones.setModificadoPor("José Chacón");
        qAplicaciones.setFechaModificacion(new Date());

        return aplicacionesService.update(qAplicaciones);
    }

    @PostMapping("CambiarEstado/{id}")
    public Aplicaciones CambiarEstado(@PathVariable Integer id) throws NotFoundValidationException {

        Aplicaciones qAplicaciones = aplicacionesService.findOne(id);

        qAplicaciones.setFlagActivo(qAplicaciones.getFlagActivo() == true ? false : true);
        qAplicaciones.setModificadoPor("José Chacón");
        qAplicaciones.setFechaModificacion(new Date());

        return aplicacionesService.update(qAplicaciones);
    }

    @PostMapping("Agregar")
    public Aplicaciones Agregar(@ModelAttribute @Valid AplicacionesDTO item){

        Aplicaciones qAplicaciones = new Aplicaciones();

        qAplicaciones.setCodigo(item.getCodigo());
        qAplicaciones.setNombre(item.getNombre());
        qAplicaciones.setDescripcion(item.getDescripcion());
        qAplicaciones.setUsuarioLider(item.getUsuarioLider());
        qAplicaciones.setFlagActivo(true);
        qAplicaciones.setFlagEliminado(false);

        qAplicaciones.setCreadoPor("José Chacón");
        qAplicaciones.setFechaCreacion(new Date());

        return aplicacionesService.save(qAplicaciones);
    }

    @GetMapping("ValidarCodigo")
    public boolean ValidarCodigo(@RequestParam String codigo){

        return aplicacionesService.validarCodigo(codigo) == false ? true : false;
    }

    @GetMapping("ObtenerPorId/{id}")
    public Aplicaciones ObtenerPorId(@PathVariable Integer id) throws NotFoundValidationException {

        return aplicacionesService.findOne(id);
    }
    
}
