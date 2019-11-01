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
    public List<TipoArchivoPOJO> getTipoArchivos(int limit, int offset, String descripcion, boolean flafActivo, int idTipoFirma, String tipoBusqueda) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("VQJ33260.FUNC_TIPO_ARCHIVO_Q_DYNAMIC_WHERE", "TipoArchivoGetAll");
        storedProcedureQuery.registerStoredProcedureParameter("u_Limit", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_Offset", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_nombre", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_flagActivo", Boolean.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_IDFORMATOFIRMA", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("u_tipoBusqueda", String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("u_Limit", limit);
        storedProcedureQuery.setParameter("u_Offset", offset);
        storedProcedureQuery.setParameter("u_nombre", descripcion);
        storedProcedureQuery.setParameter("u_flagActivo", flafActivo);
        storedProcedureQuery.setParameter("u_IDFORMATOFIRMA", idTipoFirma);
        storedProcedureQuery.setParameter("u_tipoBusqueda", tipoBusqueda);

        return storedProcedureQuery.getResultList();
    }
}
