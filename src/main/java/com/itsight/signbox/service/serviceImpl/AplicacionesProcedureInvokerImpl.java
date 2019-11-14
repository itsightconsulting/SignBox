package com.itsight.signbox.service.serviceImpl;

import com.itsight.signbox.domain.Persona;
import com.itsight.signbox.domain.pojo.AplicacionesPOJO;
import com.itsight.signbox.domain.pojo.PersonaPOJO;
import com.itsight.signbox.service.AplicacionesProcedureInvoker;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Component
public class AplicacionesProcedureInvokerImpl implements AplicacionesProcedureInvoker {

    private EntityManager entityManager;
    public AplicacionesProcedureInvokerImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<AplicacionesPOJO> getAplicaciones(int limit, int offset, String nombre, Boolean flagActivo) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("VQJ33260.FUNC_APLICACIONES_Q_DYNAMIC_WHERE", "AplicacionesGetAll");
        storedProcedureQuery.registerStoredProcedureParameter("u_Limit", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_Offset", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_nombre", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_flagActivo", Boolean.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("u_Limit", limit);
        storedProcedureQuery.setParameter("u_Offset", offset);
        storedProcedureQuery.setParameter("u_nombre", nombre);
        storedProcedureQuery.setParameter("u_flagActivo", flagActivo);

        return storedProcedureQuery.getResultList();
    }




}
