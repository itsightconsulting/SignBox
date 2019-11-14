package com.itsight.signbox.service;

import com.itsight.signbox.domain.Persona;
import com.itsight.signbox.domain.pojo.AplicacionesPOJO;
import com.itsight.signbox.domain.pojo.PersonaPOJO;

import java.util.List;

public interface AplicacionesProcedureInvoker {
    List<AplicacionesPOJO> getAplicaciones(int limit, int offset, String nombre, Boolean flagActivo);

}
