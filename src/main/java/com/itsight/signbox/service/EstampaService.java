package com.itsight.signbox.service;

import com.itsight.signbox.domain.Estampa;
import com.itsight.signbox.generic.BaseService;

public interface EstampaService extends BaseService<Estampa, Integer> {
    boolean validarCodigo(String codigo);
}
