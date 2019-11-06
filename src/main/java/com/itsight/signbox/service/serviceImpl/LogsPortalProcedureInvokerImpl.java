package com.itsight.signbox.service.serviceImpl;

import com.itsight.signbox.domain.pojo.LogsPortalPOJO;
import com.itsight.signbox.domain.query.LogsPortalQueryDTO;
import com.itsight.signbox.service.LogsPortalProcedureInvoker;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Component
public class LogsPortalProcedureInvokerImpl implements LogsPortalProcedureInvoker {

    private EntityManager entityManager;

    public LogsPortalProcedureInvokerImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<LogsPortalPOJO> getLogsAdmin(LogsPortalQueryDTO query) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("VQJ33260.func_logs_admin_q_dynamic_where", "LogsAdminGetAll");
        storedProcedureQuery.registerStoredProcedureParameter(0, Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter(0, query.getLimit());
        storedProcedureQuery.setParameter(1, query.getOffset());
        storedProcedureQuery.setParameter(2, query.getEntidad());
        storedProcedureQuery.setParameter(3, query.getFechaInicio() == "" ? null : query.getFechaInicio());
        storedProcedureQuery.setParameter(4, query.getFechaFin() == "" ? null : query.getFechaFin()) ;

        int s = storedProcedureQuery.getResultList().size();

        return storedProcedureQuery.getResultList();
    }
}
