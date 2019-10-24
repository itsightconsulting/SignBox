package com.itsight.signbox.service.serviceImpl;

import com.itsight.signbox.domain.pojo.TipoArchivoPOJO;
import com.itsight.signbox.service.TipoArchivoProcedureInvoker;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Component
public class TipoArchivoProcedureInvokerImpl implements TipoArchivoProcedureInvoker {

    private EntityManager entityManager;

    public TipoArchivoProcedureInvokerImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<TipoArchivoPOJO> getTipoArchivos(int limit, int offset) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("VQJ33260.FUNC_TIPO_ARCHIVO_Q_DYNAMIC_WHERE", "TipoArchivoGetAll");
        storedProcedureQuery.registerStoredProcedureParameter(0, Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        storedProcedureQuery.setParameter(0, limit);
        storedProcedureQuery.setParameter(1, offset);

        return storedProcedureQuery.getResultList();
    }
}
