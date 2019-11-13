package com.itsight.signbox.controller;

import com.itsight.signbox.advice.NotFoundValidationException;
import com.itsight.signbox.constants.ViewConstant;
import com.itsight.signbox.domain.Estampa;
import com.itsight.signbox.domain.dto.EstampaDTO;
import com.itsight.signbox.domain.pojo.EstampaPOJO;
import com.itsight.signbox.service.EstampaProcedureInvoker;
import com.itsight.signbox.service.EstampaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/configuracion/estampas")
public class EstampaController {

    private EstampaService estampaService;
    private EstampaProcedureInvoker estampaProcedureInvoker;

    public EstampaController(EstampaService estampaService,
                              EstampaProcedureInvoker estampaProcedureInvoker)
    {
        this.estampaService = estampaService;
        this.estampaProcedureInvoker = estampaProcedureInvoker;
    }

    @GetMapping(value = "/gestion")
    public ModelAndView principal(Model model) {
        return new ModelAndView(ViewConstant.MAIN_ESTAMPA);
    }

    @GetMapping("/{id}")
    public Estampa ObtenerAmbientePorId(@PathVariable Integer id) throws NotFoundValidationException {

        return estampaService.findOne(id);
    }

    @GetMapping("listarTodo")
    public ResponseEntity<List<EstampaPOJO>> listarTodo(
            @RequestParam String nombre,
            @RequestParam Boolean flagActivo,
            @RequestParam String tipoBusqueda,
            @RequestParam Integer offset, @RequestParam Integer limit){

        return new ResponseEntity<List<EstampaPOJO>>(
                estampaProcedureInvoker.getEstampa(
                        limit,offset, nombre, flagActivo, tipoBusqueda), HttpStatus.OK);
    }

    @PutMapping("actualizar/{id}")
    public Estampa actualizar(@PathVariable Integer id, @ModelAttribute @Valid EstampaDTO parametro) throws NotFoundValidationException {

        Estampa qEstampa = estampaService.findOne(id);

        qEstampa.setNombre(parametro.getNombre());
        qEstampa.setPagina(parametro.getPagina());
        qEstampa.setDescripcion(parametro.getDescripcion());
        //qEstampa.setModificadoPor("José Chacón");
        //qEstampa.setFechaModificacion(new Date());

        return estampaService.update(qEstampa);
    }

    @PostMapping("CambiarEstado/{id}")
    public Estampa CambiarEstado(@PathVariable Integer id) throws NotFoundValidationException {

        Estampa qEstampa = estampaService.findOne(id);

        qEstampa.setFlagActivo(qEstampa.getFlagActivo() == true ? false : true);
        //qEstampa.setModificadoPor("José Chacón");
        //qEstampa.setFechaModificacion(new Date());

        return estampaService.update(qEstampa);
    }

    @PostMapping("Agregar")
    public Estampa Agregar(@ModelAttribute @Valid EstampaDTO item){

        Estampa qEstampa = new Estampa();

        qEstampa.setAlias(item.getAlias());
        qEstampa.setNombre(item.getNombre());
        qEstampa.setDescripcion(item.getDescripcion());
        qEstampa.setPagina(item.getPagina());
        qEstampa.setFlagActivo(true);
        //qEstampa.setFlagEliminado(false);

        //qEstampa.setCreadoPor("José Chacón");
        //qEstampa.setFechaCreacion(new Date());

        return estampaService.save(qEstampa);
    }

    @GetMapping("ValidarCodigo")
    public boolean ValidarCodigo(@RequestParam String codigo){

        return estampaService.validarCodigo(codigo) == false ? true : false;
    }

    @GetMapping("ObtenerPorId/{id}")
    public Estampa ObtenerPorId(@PathVariable Integer id) throws NotFoundValidationException {

        return estampaService.findOne(id);
    }
}
