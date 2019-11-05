package com.itsight.signbox.service.serviceImpl;

import com.itsight.signbox.domain.pojo.EstampaPOJO;
import com.itsight.signbox.service.EstampaProcedureInvoker;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Component
public class EstampaProcedureInvokerImpl implements EstampaProcedureInvoker {

    private EntityManager entityManager;

    public EstampaProcedureInvokerImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<EstampaPOJO> getEstampa(int limit, int offset, String nombre, Boolean flafActivo, String tipoBusqueda) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("VQJ33260.FUNC_ESTAMPA_Q_DYNAMIC_WHERE", "EstampaGetAll");
        storedProcedureQuery.registerStoredProcedureParameter("u_Limit", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_Offset", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_nombre", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_flagActivo", Boolean.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_tipoBusqueda", String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("u_Limit", limit);
        storedProcedureQuery.setParameter("u_Offset", offset);
        storedProcedureQuery.setParameter("u_nombre", nombre);
        storedProcedureQuery.setParameter("u_flagActivo", flafActivo);
        storedProcedureQuery.setParameter("u_tipoBusqueda", tipoBusqueda);

        return storedProcedureQuery.getResultList();
    }
}
