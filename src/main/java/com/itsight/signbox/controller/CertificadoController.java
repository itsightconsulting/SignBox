package com.itsight.signbox.controller;

import com.itsight.signbox.advice.NotFoundValidationException;
import com.itsight.signbox.constants.ViewConstant;
import com.itsight.signbox.domain.Certificados;
import com.itsight.signbox.domain.Parametro;
import com.itsight.signbox.domain.TipoArchivo;
import com.itsight.signbox.domain.dto.TipoArchivoDTO;
import com.itsight.signbox.domain.pojo.CertificadosPOJO;
import com.itsight.signbox.domain.pojo.ParametroPOJO;
import com.itsight.signbox.domain.query.CertificadosQueryDTO;
import com.itsight.signbox.service.CertificadoProcedureInvoker;
import com.itsight.signbox.service.CertificadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/certificados")
public class CertificadoController {

    private CertificadoService certificadoService;

    private CertificadoProcedureInvoker certificadoProcedureInvoker;

    public CertificadoController(CertificadoService certificadoService, CertificadoProcedureInvoker certificadoProcedureInvoker) {
        this.certificadoService = certificadoService;
        this.certificadoProcedureInvoker = certificadoProcedureInvoker;
    }

    @RequestMapping("/gestion")
    public ModelAndView principal(Model model){
        return new ModelAndView(ViewConstant.MAIN_CERTIFICADOS);
    }

    @GetMapping("")
    public ResponseEntity<List<CertificadosPOJO>> listarTodo(@ModelAttribute @Valid CertificadosQueryDTO certificadosQueryDTO){

        return new ResponseEntity<List<CertificadosPOJO>>(certificadoProcedureInvoker.getCertificados(certificadosQueryDTO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Certificados> listarporId(@PathVariable(name = "id") Integer id) throws NotFoundValidationException {

        return new ResponseEntity<Certificados>(certificadoService.findOne(id),HttpStatus.OK) ;
    }


    @PostMapping("")
    public Certificados agregar(@ModelAttribute @Valid Certificados certificado){
        certificado.setFlagActivo(true);
        return certificadoService.save(certificado);
    }


    @PutMapping("/{id}")
    public @ResponseBody Certificados actualizar(@ModelAttribute @Valid Certificados certificados, @PathVariable(name = "id") Integer id) throws NotFoundValidationException {

        Certificados qCertificados = certificadoService.findOne(id);
        qCertificados.setCertificados(certificados);

        return certificadoService.update(qCertificados);
    }


    @PutMapping("/estado/{id}")
    public @ResponseBody Certificados actualizarEstado(@PathVariable(name = "id") Integer id) throws NotFoundValidationException {

        Certificados certificados = certificadoService.findOne(id);
        certificados.setFlagActivo(!certificados.isFlagActivo());

        return certificadoService.update(certificados);
    }


    @GetMapping("/alias/validacion")
    public @ResponseBody
    Map<String, Boolean> existeAlias(@RequestParam String alias) {
        return Collections.singletonMap("aliasExiste", certificadoService.existsByAlias(alias));
    }


}
