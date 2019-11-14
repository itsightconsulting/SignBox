package com.itsight.signbox.service;

import com.itsight.signbox.advice.NotFoundValidationException;
import com.itsight.signbox.domain.Persona;
import com.itsight.signbox.generic.BaseService;

public interface PersonaService extends BaseService<Persona, Integer> {
    void ActualizarContrasenia(int id, String pass, String guid) throws NotFoundValidationException;
    void ActualizarFlagCambio(int id) throws NotFoundValidationException;
}
