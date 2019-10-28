package com.itsight.signbox.service;

import com.itsight.signbox.domain.pojo.TipoArchivoPOJO;

import java.util.List;

public interface TipoArchivoProcedureInvoker {

     List<TipoArchivoPOJO> getTipoArchivos(int limit, int offset, String descripcion, boolean flafActivo, int idTipoFirma);

}
