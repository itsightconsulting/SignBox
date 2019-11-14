package com.itsight.signbox.service.serviceImpl;

import com.itsight.signbox.domain.pojo.LogsServicioFirmaPOJO;
import com.itsight.signbox.domain.query.LogsServicioFirmaQueryDTO;
import com.itsight.signbox.service.LogsServicioFirmaProcedureInvoker;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Component
public class LogsServicioFirmaProcedureInvokerImpl implements LogsServicioFirmaProcedureInvoker {

    private EntityManager entityManager;

    public LogsServicioFirmaProcedureInvokerImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<LogsServicioFirmaPOJO> getLogs(LogsServicioFirmaQueryDTO query) {

        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("VQJ33260.FUNC_LOGS_FIRMA_Q_DYNAMIC_WHERE", "LogsGetAll");
        storedProcedureQuery.registerStoredProcedureParameter("u_Limit", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_Offset", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_archivo", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_fechaI", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_fechaF", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_transaccion", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_tipo", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_documento", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_cuenta", String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("u_Limit", query.getLimit());
        storedProcedureQuery.setParameter("u_Offset", query.getOffset());
        storedProcedureQuery.setParameter("u_archivo", query.getArchivo());
        storedProcedureQuery.setParameter("u_fechaI", query.getFechaI() == "" ? null : query.getFechaI());
        storedProcedureQuery.setParameter("u_fechaF", query.getFechaF() == "" ? null : query.getFechaF()) ;
        storedProcedureQuery.setParameter("u_transaccion", query.getTransaccion()) ;
        storedProcedureQuery.setParameter("u_tipo", query.getTipoDocumento()) ;
        storedProcedureQuery.setParameter("u_documento", query.getDocumento()) ;
        storedProcedureQuery.setParameter("u_cuenta", query.getNumeroCuenta()) ;


        return storedProcedureQuery.getResultList();
    }
}
