package com.itsight.signbox.service;

import com.itsight.signbox.domain.pojo.ArchivoPOJO;
import com.itsight.signbox.domain.query.ArchivoQueryDTO;

import java.util.List;

public interface ClienteProcedureInvoker {

    public List<ArchivoPOJO> getArchivosByClienteNumDoc(ArchivoQueryDTO query);
}
