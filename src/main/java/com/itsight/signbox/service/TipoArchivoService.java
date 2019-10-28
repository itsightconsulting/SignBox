package com.itsight.signbox.service;

import com.itsight.signbox.domain.TipoArchivo;
import com.itsight.signbox.generic.BaseService;

public interface TipoArchivoService extends BaseService<TipoArchivo, Integer> {
    boolean validarCodigo(String codigo);
}
