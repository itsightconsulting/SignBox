package com.itsight.signbox.controller;

import com.itsight.signbox.advice.NotFoundValidationException;
import com.itsight.signbox.constants.ViewConstant;
import com.itsight.signbox.domain.TipoArchivo;
import com.itsight.signbox.domain.TipoFirma;
import com.itsight.signbox.domain.dto.TipoArchivoDTO;
import com.itsight.signbox.domain.pojo.TipoArchivoPOJO;
import com.itsight.signbox.service.TipoArchivoProcedureInvoker;
import com.itsight.signbox.service.TipoArchivoService;
import com.itsight.signbox.service.TipoFirmaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/configuracion/tipos-archivos")
public class TipoArchivoController {

    private TipoArchivoService tipoArchivoService;
    private TipoArchivoProcedureInvoker tipoArchivoProcedureInvoker;
    private TipoFirmaService tipoFirmaService;

    public TipoArchivoController(TipoArchivoService tipoArchivoService,
                                 TipoArchivoProcedureInvoker tipoArchivoProcedureInvoker,
                                 TipoFirmaService tipoFirmaService)
    {
        this.tipoArchivoService = tipoArchivoService;
        this.tipoArchivoProcedureInvoker = tipoArchivoProcedureInvoker;
        this.tipoFirmaService = tipoFirmaService;
    }

    @GetMapping(value = "/gestion")
    public ModelAndView principal(Model model) {
        return new ModelAndView(ViewConstant.MAIN_TIPO_ARCHIVOS);
    }

    @GetMapping("/{id}")
    public TipoArchivo listarTipoArchivo(@PathVariable Integer id) throws NotFoundValidationException {

        return tipoArchivoService.findOne(id);
    }

    @GetMapping("listarTodo")
    public ResponseEntity<List<TipoArchivoPOJO>> listarTodo(
            @RequestParam Integer tipoId, @RequestParam String nombre,
            @RequestParam Boolean flagActivo,
            @RequestParam String tipoBusqueda,
            @RequestParam Integer offset, @RequestParam Integer limit){

            return new ResponseEntity<List<TipoArchivoPOJO>>(
                tipoArchivoProcedureInvoker.getTipoArchivos(
                        limit,offset, nombre, flagActivo, tipoId, tipoBusqueda), HttpStatus.OK);
    }

    @PutMapping("actualizar/{id}")
    public TipoArchivo actualizar(@PathVariable Integer id, @ModelAttribute @Valid TipoArchivoDTO parametro) throws NotFoundValidationException {

        TipoArchivo qTipoArchivo = tipoArchivoService.findOne(id);

        qTipoArchivo.setIdFormatoFirma(parametro.getIdFormatoFirma());
        qTipoArchivo.setDescripcion(parametro.getDescripcion());
        qTipoArchivo.setExtensiones(parametro.getExtensiones());
        qTipoArchivo.setModificadoPor("José Chacón");
        qTipoArchivo.setFechaModificacion(new Date());

        return tipoArchivoService.update(qTipoArchivo);
    }

    @PostMapping("CambiarEstado/{id}")
    public TipoArchivo CambiarEstado(@PathVariable Integer id) throws NotFoundValidationException {

        TipoArchivo qTipoArchivo = tipoArchivoService.findOne(id);

        qTipoArchivo.setFlagActivo(qTipoArchivo.getFlagActivo() == true ? false : true);
        qTipoArchivo.setModificadoPor("José Chacón");
        qTipoArchivo.setFechaModificacion(new Date());

        return tipoArchivoService.update(qTipoArchivo);
    }

    @GetMapping("CargarData")
    public List<TipoFirma> CargarData(){
        return tipoFirmaService.findAll();
    }

    @PostMapping("Agregar")
    public TipoArchivo Agregar(@ModelAttribute @Valid TipoArchivoDTO item){

        TipoArchivo qtipoTipoArchivo = new TipoArchivo();

        qtipoTipoArchivo.setCodigoArchivo(item.getCodigoArchivo());
        qtipoTipoArchivo.setExtensiones(item.getExtensiones());
        qtipoTipoArchivo.setDescripcion(item.getDescripcion());
        qtipoTipoArchivo.setIdFormatoFirma(item.getIdFormatoFirma());
        qtipoTipoArchivo.setFlagActivo(true);
        qtipoTipoArchivo.setFlagEliminado(false);

        qtipoTipoArchivo.setCreadoPor("José Chacón");
        qtipoTipoArchivo.setFechaCreacion(new Date());

        return tipoArchivoService.save(qtipoTipoArchivo);
    }

    @GetMapping("ValidarCodigo")
    public boolean ValidarCodigo(@RequestParam String codigo){

        return tipoArchivoService.validarCodigo(codigo) == false ? true : false;
    }

    @GetMapping("ObtenerPorId/{id}")
    public TipoArchivo ObtenerPorId(@PathVariable Integer id) throws NotFoundValidationException {

        return tipoArchivoService.findOne(id);
    }

}
