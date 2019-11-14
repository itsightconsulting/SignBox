package com.itsight.signbox.service;

import com.itsight.signbox.domain.Aplicaciones;
import com.itsight.signbox.domain.Persona;
import com.itsight.signbox.generic.BaseService;

import java.util.List;

public interface AplicacionesService extends BaseService<Aplicaciones, Integer> {

    boolean validarCodigo(String codigo);

}
