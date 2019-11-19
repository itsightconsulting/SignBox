package com.itsight.signbox.service.serviceImpl;

import com.itsight.signbox.domain.pojo.LogsNotificacionesPOJO;
import com.itsight.signbox.domain.query.LogsNotificacionesQueryDTO;
import com.itsight.signbox.service.LogsNotificacionesProcedureInvoker;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Component
public class LogsNotificacionesProcedureInvokerImpl implements LogsNotificacionesProcedureInvoker {
    private EntityManager entityManager;

    public LogsNotificacionesProcedureInvokerImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<LogsNotificacionesPOJO> getLogsNotificaciones(LogsNotificacionesQueryDTO query) {

        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("VQJ33260.func_logs_notificaciones_q_dynamic_where", "LogsNotificacionesGetAll");
        storedProcedureQuery.registerStoredProcedureParameter("u_Limit", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_Offset", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_fechaInicio", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_fechaFin", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_cuenta", String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("u_Limit", query.getLimit());
        storedProcedureQuery.setParameter("u_Offset", query.getOffset());
        storedProcedureQuery.setParameter("u_fechaInicio", query.getFechaInicio()) ;
        storedProcedureQuery.setParameter("u_fechaFin", query.getFechaFin()) ;
        storedProcedureQuery.setParameter("u_cuenta",  query.getNumeroCuenta());


        return storedProcedureQuery.getResultList();

    }


}