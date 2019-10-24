package com.itsight.signbox.service;

import com.itsight.signbox.domain.pojo.ParametroPOJO;

import java.util.List;

public interface ParametroProcedureInvoker {

     List<ParametroPOJO> getParametros(int limit , int offset);

}
