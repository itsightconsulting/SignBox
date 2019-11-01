package com.itsight.signbox.service;

import com.itsight.signbox.domain.Aplicaciones;
import com.itsight.signbox.generic.BaseService;

public interface AplicacionesService extends BaseService<Aplicaciones, Integer> {
    boolean validarCodigo(String codigo);
}
