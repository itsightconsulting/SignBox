package com.itsight.signbox.service.serviceImpl;

import com.itsight.signbox.domain.pojo.CertificadosPOJO;
import com.itsight.signbox.domain.pojo.ParametroPOJO;
import com.itsight.signbox.domain.query.CertificadosQueryDTO;
import com.itsight.signbox.service.CertificadoProcedureInvoker;
import com.itsight.signbox.service.ParametroProcedureInvoker;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Component
public class CertificadoProcedureInvokerImpl implements CertificadoProcedureInvoker {

    private EntityManager entityManager;

    public CertificadoProcedureInvokerImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<CertificadosPOJO> getCertificados(CertificadosQueryDTO query) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("VQJ33260.FUNC_CERTIFICADOS_Q_DYNAMIC_WHERE", "CertificadosGetAll");
        storedProcedureQuery.registerStoredProcedureParameter(0, Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(3, Boolean.class, ParameterMode.IN);
        storedProcedureQuery.setParameter(0, query.getLimit());
        storedProcedureQuery.setParameter(1, query.getOffset());
        storedProcedureQuery.setParameter(2, query.getAlias());
        storedProcedureQuery.setParameter(3, query.getFlagActivo());

        return storedProcedureQuery.getResultList();

    }
}
