package com.itsight.signbox.service;

import com.itsight.signbox.domain.pojo.PersonaPOJO;

import java.util.List;

public interface PersonaProcedureInvoker {
    List<PersonaPOJO> ListarPersonas(int limit, int offset, String dni, int tipo);
}
