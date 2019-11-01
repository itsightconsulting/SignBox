package com.itsight.signbox.controller;

import com.itsight.signbox.advice.NotFoundValidationException;
import com.itsight.signbox.constants.ViewConstant;
import com.itsight.signbox.domain.AmbienteAplicacion;
import com.itsight.signbox.domain.Ambientes;
import com.itsight.signbox.domain.Aplicaciones;
import com.itsight.signbox.domain.dto.AplicacionesDTO;
import com.itsight.signbox.domain.pojo.AmbientesPOJO;
import com.itsight.signbox.domain.pojo.AplicacionesPOJO;
import com.itsight.signbox.domain.pojo.CertificadosPOJO;
import com.itsight.signbox.domain.query.CertificadosQueryDTO;
import com.itsight.signbox.service.*;
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
    private CertificadoProcedureInvoker certificadoProcedureInvoker;
    private AmbienteAplicacionService ambienteAplicacionService;

    public AplicacionesController(AplicacionesService aplicacionesService,
                                  AplicacionesProcedureInvoker aplicacionesProcedureInvoker,
                                  AmbientesProcedureInvoker ambientesProcedureInvoker,
                                  CertificadoProcedureInvoker certificadoProcedureInvoker,
                                  AmbienteAplicacionService ambienteAplicacionService) {
        this.aplicacionesService = aplicacionesService;
        this.aplicacionesProcedureInvoker = aplicacionesProcedureInvoker;
        this.ambientesProcedureInvoker = ambientesProcedureInvoker;
        this.certificadoProcedureInvoker = certificadoProcedureInvoker;
        this.ambienteAplicacionService = ambienteAplicacionService;
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

    @PutMapping("actualizar/{id}")
    public Aplicaciones actualizar(@PathVariable Integer id, @ModelAttribute @Valid AplicacionesDTO parametro) throws NotFoundValidationException {

        Aplicaciones qAplicaciones = aplicacionesService.findOne(id);

        qAplicaciones.setNombre(parametro.getNombre());
        qAplicaciones.setDescripcion(parametro.getDescripcion());
        qAplicaciones.setUsuarioLider(parametro.getUsuarioLider());
        //qAplicaciones.setModificadoPor("José Chacón");
        //qAplicaciones.setFechaModificacion(new Date());

        return aplicacionesService.update(qAplicaciones);
    }

    @PostMapping("CambiarEstado/{id}")
    public Aplicaciones CambiarEstado(@PathVariable Integer id) throws NotFoundValidationException {

        Aplicaciones qAplicaciones = aplicacionesService.findOne(id);

        qAplicaciones.setFlagActivo(qAplicaciones.getFlagActivo() == true ? false : true);
        //qAplicaciones.setModificadoPor("José Chacón");
        //qAplicaciones.setFechaModificacion(new Date());

        return aplicacionesService.update(qAplicaciones);
    }

    @PostMapping("Agregar")
    public Aplicaciones Agregar(@ModelAttribute @Valid AplicacionesDTO item){

        Aplicaciones qAplicaciones = new Aplicaciones();

        //qAplicaciones.setAplicacionesId(0);
        qAplicaciones.setCodigo(item.getCodigo());
        qAplicaciones.setNombre(item.getNombre());
        qAplicaciones.setDescripcion(item.getDescripcion());
        qAplicaciones.setUsuarioLider(item.getUsuarioLider());
        qAplicaciones.setFlagActivo(true);

        //qAplicaciones.setFlagEliminado(false);

        //qAplicaciones. setCreadoPor("José Chacón");
        //qAplicaciones.setFechaCreacion(new Date());

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


    @GetMapping("ListarAmbientes")
    public ResponseEntity<List<AmbientesPOJO>> ListarAmbientes(){

        return new ResponseEntity<List<AmbientesPOJO>>(
                ambientesProcedureInvoker.getAmbientes(
                        100,1, "", true, ""), HttpStatus.OK);
    }

    @GetMapping("ObtenerAmbientes")
    public ResponseEntity<List<Ambientes>> ObtenerAmbientes(@RequestParam Integer id){

        return new ResponseEntity<List<Ambientes>>(
                ambienteAplicacionService.obtenerAmbientesPorAplicacion(id), HttpStatus.OK);
    }

    @PostMapping("AgregarAmbiente/{idApp}/{idAmb}")
    public boolean AgregarAmbiente(@PathVariable Integer idApp, @PathVariable Integer idAmb) {

        List<AmbienteAplicacion> qAplicaciones = ambienteAplicacionService.obtenerAmbienteAplicacion(idApp, idAmb);
        Boolean resultado = false;
        if(qAplicaciones.size() == 0 ){
            AmbienteAplicacion qAplicacion = new AmbienteAplicacion();
            qAplicacion.setIdAplicacion(idApp);
            qAplicacion.setIdAmbiente(idAmb);

            AmbienteAplicacion insertado = ambienteAplicacionService.save(qAplicacion);

            if (insertado.getAmbienteAplicacionid() != 0){
                resultado = true;
            }

        }

        return resultado;
    }

    //
    @PostMapping("EliminarAmbiente/{idApp}/{idAmb}")
    public boolean EliminarAmbiente(@PathVariable Integer idApp, @PathVariable Integer idAmb) {

        List<AmbienteAplicacion> qAplicaciones = ambienteAplicacionService.obtenerAmbienteAplicacion(idApp, idAmb);
        Boolean resultado = false;

        if(qAplicaciones.size() > 0 ){

            ambienteAplicacionService.delete(qAplicaciones.get(0).getAmbienteAplicacionid());
            resultado = true;
        }

        return resultado;
    }

    @GetMapping("ListarCertificados")
    public ResponseEntity<List<CertificadosPOJO>> ListarCertificados(@ModelAttribute @Valid CertificadosQueryDTO certificadosQueryDTO){
        certificadosQueryDTO.setLimit(100);
        certificadosQueryDTO.setOffset(1);
        certificadosQueryDTO.setAlias("");
        certificadosQueryDTO.setFlagActivo(true);

        return new ResponseEntity<List<CertificadosPOJO>>(certificadoProcedureInvoker.getCertificados(certificadosQueryDTO), HttpStatus.OK);
    }


}
