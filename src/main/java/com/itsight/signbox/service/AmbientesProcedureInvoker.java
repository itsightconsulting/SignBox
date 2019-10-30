package com.itsight.signbox.service;

import com.itsight.signbox.domain.pojo.AmbientesPOJO;

import java.util.List;

public interface AmbientesProcedureInvoker {

    List<AmbientesPOJO> getAmbientes(int limit, int offset, String nombre, Boolean flafActivo);

}
