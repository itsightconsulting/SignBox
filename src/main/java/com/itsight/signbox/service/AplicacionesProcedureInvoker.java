package com.itsight.signbox.service;

import com.itsight.signbox.domain.pojo.AplicacionesPOJO;

import java.util.List;

public interface AplicacionesProcedureInvoker {
    List<AplicacionesPOJO> getAplicaciones(int limit, int offset, String nombre, Boolean flagActivo, String tipoBusqueda);

}
