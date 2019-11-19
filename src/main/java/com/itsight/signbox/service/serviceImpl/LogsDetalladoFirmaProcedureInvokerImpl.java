package com.itsight.signbox.service.serviceImpl;

import com.itsight.signbox.domain.pojo.LogsDetalladoFirmaPOJO;
import com.itsight.signbox.domain.query.LogsDetalladoFirmaQueryDTO;
import com.itsight.signbox.service.LogsDetalladoFirmaProcedureInvoker;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Component
public class LogsDetalladoFirmaProcedureInvokerImpl implements LogsDetalladoFirmaProcedureInvoker {
    private EntityManager entityManager;

    public LogsDetalladoFirmaProcedureInvokerImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<LogsDetalladoFirmaPOJO> getLogsDetalladoFirma(LogsDetalladoFirmaQueryDTO query) {

        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("VQJ33260.func_logs_detallado_firma_q_dynamic_where", "LogsDetalladoFirmaGetAll");
        storedProcedureQuery.registerStoredProcedureParameter("u_limit", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_offset", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_fechaI", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_fechaF", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_transaccion", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_tipo", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_documento", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_cuenta", String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("u_limit", query.getLimit());
        storedProcedureQuery.setParameter("u_offset", query.getOffset());
        storedProcedureQuery.setParameter("u_fechaI", query.getFechaI() == "" ? null : query.getFechaI());
        storedProcedureQuery.setParameter("u_fechaF", query.getFechaF() == "" ? null : query.getFechaF()) ;
        storedProcedureQuery.setParameter("u_transaccion",  query.getTransaccion());
        storedProcedureQuery.setParameter("u_tipo", query.getTipoDocumento().equals("") ? null : query.getTipoDocumento()) ;
        storedProcedureQuery.setParameter("u_documento", query.getDocumento()) ;
        storedProcedureQuery.setParameter("u_cuenta", query.getNumeroCuenta()) ;

        return storedProcedureQuery.getResultList();

    }


}