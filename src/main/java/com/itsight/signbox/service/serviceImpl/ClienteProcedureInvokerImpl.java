package com.itsight.signbox.service.serviceImpl;

import com.itsight.signbox.domain.pojo.ArchivoPOJO;
import com.itsight.signbox.domain.query.ArchivoQueryDTO;
import com.itsight.signbox.service.ClienteProcedureInvoker;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Component
public class ClienteProcedureInvokerImpl implements ClienteProcedureInvoker {

    EntityManager entityManager;

    public ClienteProcedureInvokerImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<ArchivoPOJO> getArchivosByClienteNumDoc(ArchivoQueryDTO query) {

        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("func_archivos_clientes_q_dynamic_where" , "ArchivosGetAll");
        storedProcedureQuery.registerStoredProcedureParameter(0, Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter(0, query.getLimit());
        storedProcedureQuery.setParameter(1, query.getOffset());
        storedProcedureQuery.setParameter(2, query.getDocumentoId());

        return storedProcedureQuery.getResultList();
    }
}
