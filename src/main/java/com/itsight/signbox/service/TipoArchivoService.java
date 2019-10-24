package com.itsight.signbox.service;

import com.itsight.signbox.domain.TipoArchivo;
import com.itsight.signbox.generic.BaseService;

import java.util.List;

public interface TipoArchivoService extends BaseService<TipoArchivo, Integer> {

    public List<TipoArchivo> obtenerTipoArchivos();

    public TipoArchivo obtenerTipoArchivoPorId(Integer id);

}
