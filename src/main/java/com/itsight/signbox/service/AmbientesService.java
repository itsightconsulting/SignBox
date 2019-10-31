package com.itsight.signbox.service;

import com.itsight.signbox.domain.Ambientes;
import com.itsight.signbox.generic.BaseService;

public interface AmbientesService extends BaseService<Ambientes, Integer> {
    boolean validarCodigo(String codigo);
}
