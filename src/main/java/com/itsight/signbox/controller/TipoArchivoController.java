package com.itsight.signbox.controller;

import com.itsight.signbox.constants.ViewConstant;
import com.itsight.signbox.domain.TipoArchivo;
import com.itsight.signbox.domain.dto.TipoArchivoDTO;
import com.itsight.signbox.domain.pojo.TipoArchivoPOJO;
import com.itsight.signbox.service.TipoArchivoProcedureInvoker;
import com.itsight.signbox.service.TipoArchivoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin/tipos-archivos")
public class TipoArchivoController {

    private TipoArchivoService tipoArchivoService;

    private TipoArchivoProcedureInvoker tipoArchivoProcedureInvoker;

    public TipoArchivoController(TipoArchivoService tipoArchivoService, TipoArchivoProcedureInvoker tipoArchivoProcedureInvoker){
        this.tipoArchivoService = tipoArchivoService;
        this.tipoArchivoProcedureInvoker = tipoArchivoProcedureInvoker;
    }

    @GetMapping(value = "/gestion")
    public ModelAndView principal(Model model) {
        return new ModelAndView(ViewConstant.MAIN_TIPO_ARCHIVOS);
    }

    @GetMapping("/{id}")
    public TipoArchivo listarTipoArchivo(@PathVariable Integer id){

        return tipoArchivoService.obtenerTipoArchivoPorId(id);
    }

    @GetMapping("")
    public ResponseEntity<List<TipoArchivoPOJO>> listarTodo(@RequestParam Integer offset, @RequestParam Integer limit){

        return new ResponseEntity<List<TipoArchivoPOJO>>(tipoArchivoProcedureInvoker.getTipoArchivos(limit,offset), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public TipoArchivo actualizar(@PathVariable Integer id, @ModelAttribute @Valid TipoArchivoDTO parametro){

        TipoArchivo qTipoArchivo = listarTipoArchivo(id);

        return tipoArchivoService.update(qTipoArchivo);
    }
}
