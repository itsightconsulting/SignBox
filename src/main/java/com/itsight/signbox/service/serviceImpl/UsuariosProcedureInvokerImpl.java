package com.itsight.signbox.service.serviceImpl;

import com.itsight.signbox.domain.pojo.CertificadosPOJO;
import com.itsight.signbox.domain.pojo.UsuariosPOJO;
import com.itsight.signbox.domain.query.CertificadosQueryDTO;
import com.itsight.signbox.domain.query.UsuariosQueryDTO;
import com.itsight.signbox.service.UsuariosProcedureInvoker;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Component
public class UsuariosProcedureInvokerImpl implements UsuariosProcedureInvoker {

    private EntityManager entityManager;

    public UsuariosProcedureInvokerImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<UsuariosPOJO> getUsuarios(UsuariosQueryDTO query) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("VQJ33260.func_usuarios_q_dynamic_where", "UsuariosGetAll");
        storedProcedureQuery.registerStoredProcedureParameter(0, Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(4, Boolean.class, ParameterMode.IN);
        storedProcedureQuery.setParameter(0, query.getLimit());
        storedProcedureQuery.setParameter(1, query.getOffset());
        storedProcedureQuery.setParameter(2, query.getDocumentoId());
        storedProcedureQuery.setParameter(3, query.getPerfilId());
        storedProcedureQuery.setParameter(4, query.getFlagActivo());

        return storedProcedureQuery.getResultList();
    }
}
