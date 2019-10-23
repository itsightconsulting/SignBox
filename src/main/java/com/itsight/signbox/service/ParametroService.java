package com.itsight.signbox.service;

import com.itsight.signbox.domain.Parametro;
import com.itsight.signbox.generic.BaseService;

import java.util.List;

public interface ParametroService extends BaseService<Parametro, Integer> {

    public List<Parametro> obtenerParametros();

    public Parametro obtenerParametroPorId(Integer id);

    public Parametro update(Parametro parametro);



}
