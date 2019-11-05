package com.itsight.signbox.service;

import com.itsight.signbox.domain.AmbienteAplicacion;
import com.itsight.signbox.domain.Ambientes;
import com.itsight.signbox.domain.AplicacionCertificado;
import com.itsight.signbox.domain.Certificados;
import com.itsight.signbox.generic.BaseService;

import java.util.List;

public interface AmbienteAplicacionService extends BaseService<AmbienteAplicacion, Integer> {
    List<Ambientes> obtenerAmbientesPorAplicacion(Integer idAplicacion);
    AmbienteAplicacion obtenerPorAmbiente(Integer idAmbiente);
    List<AmbienteAplicacion> obtenerAmbienteAplicacion(Integer idAplicacion, Integer idAmbiente);

}
