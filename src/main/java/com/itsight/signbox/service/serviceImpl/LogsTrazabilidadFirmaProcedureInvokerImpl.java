package com.itsight.signbox.service.serviceImpl;

import com.itsight.signbox.domain.pojo.LogsServicioFirmaPOJO;
import com.itsight.signbox.domain.pojo.LogsTrazabilidadFirmaPOJO;
import com.itsight.signbox.domain.query.LogsServicioFirmaQueryDTO;
import com.itsight.signbox.domain.query.LogsTrazabilidadFirmaQueryDTO;
import com.itsight.signbox.service.LogsTrazabilidadFirmaProcedureInvoker;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class LogsTrazabilidadFirmaProcedureInvokerImpl implements LogsTrazabilidadFirmaProcedureInvoker {
    private EntityManager entityManager;

    public LogsTrazabilidadFirmaProcedureInvokerImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<LogsTrazabilidadFirmaPOJO> getLogsTrazabilidadFirma(LogsTrazabilidadFirmaQueryDTO query) {

        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("VQJ33260.func_logs_trazabilidad_firma_q_dynamic_where", "LogsTrazabilidadFirmaGetAll");
        storedProcedureQuery.registerStoredProcedureParameter("u_Limit", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_Offset", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_transaccion", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_documento", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_cuenta", String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("u_Limit", query.getLimit());
        storedProcedureQuery.setParameter("u_Offset", query.getOffset());
        storedProcedureQuery.setParameter("u_transaccion",  query.getTransaccion());
        storedProcedureQuery.setParameter("u_documento", query.getDocumento()) ;
        storedProcedureQuery.setParameter("u_cuenta", query.getNumeroCuenta()) ;

        return storedProcedureQuery.getResultList();

    }


}