package com.itsight.signbox.service;

import com.itsight.signbox.domain.pojo.EstampaPOJO;

import java.util.List;

public interface EstampaProcedureInvoker {
    List<EstampaPOJO> getEstampa(int limit, int offset, String nombre, Boolean flafActivo, String tipoBusqueda);

}
