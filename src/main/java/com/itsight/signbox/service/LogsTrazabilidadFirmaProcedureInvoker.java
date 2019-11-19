package com.itsight.signbox.service;

import com.itsight.signbox.domain.pojo.LogsServicioFirmaPOJO;
import com.itsight.signbox.domain.pojo.LogsTrazabilidadFirmaPOJO;
import com.itsight.signbox.domain.query.LogsServicioFirmaQueryDTO;
import com.itsight.signbox.domain.query.LogsTrazabilidadFirmaQueryDTO;

import java.util.List;

public interface LogsTrazabilidadFirmaProcedureInvoker {

    List<LogsTrazabilidadFirmaPOJO> getLogsTrazabilidadFirma(LogsTrazabilidadFirmaQueryDTO logsTrazabilidadFirmaQueryDTO);

}
