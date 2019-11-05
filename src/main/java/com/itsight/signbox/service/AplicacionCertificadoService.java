package com.itsight.signbox.service;

import com.itsight.signbox.domain.AplicacionCertificado;
import com.itsight.signbox.domain.Certificados;
import com.itsight.signbox.generic.BaseService;

import java.util.List;

public interface AplicacionCertificadoService  extends BaseService<AplicacionCertificado, Integer> {


    List<Certificados> obtenerCertificadosPorAplicacion(Integer idAplicacion);
    List<AplicacionCertificado> obtenerAplicacionCertificado(Integer idAplicacion, Integer idCertificado);
}
