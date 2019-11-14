package com.itsight.signbox.service.serviceImpl;

import com.itsight.signbox.domain.pojo.PersonaPOJO;
import com.itsight.signbox.service.PersonaProcedureInvoker;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Component
public class PersonaProcedureInvokerImpl implements PersonaProcedureInvoker {
    private EntityManager entityManager;

    public PersonaProcedureInvokerImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<PersonaPOJO> ListarPersonas(int limit, int offset, String dni, int tipo) {

        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("VQJ33260.FUNC_PERSONA_Q_DYNAMIC_WHERE", "PersonaGetAll");
        storedProcedureQuery.registerStoredProcedureParameter("u_Limit", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_Offset", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_dni", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_tipo", Integer.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("u_Limit", limit);
        storedProcedureQuery.setParameter("u_Offset", offset);
        storedProcedureQuery.setParameter("u_dni", dni);
        storedProcedureQuery.setParameter("u_tipo", tipo);

        return storedProcedureQuery.getResultList();
    }
}
