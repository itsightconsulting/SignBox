package com.itsight.signbox.service.serviceImpl;

import com.itsight.signbox.domain.pojo.ParametroPOJO;
import com.itsight.signbox.service.ParametroProcedureInvoker;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Component
public class ParametroProcedureInvokerImpl implements ParametroProcedureInvoker {

    private EntityManager entityManager;

    public ParametroProcedureInvokerImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<ParametroPOJO> getParametros(int limit , int offset) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("VQJ33260.FUNC_PARAMETRO_Q_DYNAMIC_WHERE", "ParametroGetAll");
        storedProcedureQuery.registerStoredProcedureParameter(0, Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        storedProcedureQuery.setParameter(0, limit);
        storedProcedureQuery.setParameter(1, offset);

        return storedProcedureQuery.getResultList();



    }
}
