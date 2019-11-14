package com.itsight.signbox.service;

import com.itsight.signbox.domain.pojo.LogsServicioFirmaPOJO;
import com.itsight.signbox.domain.query.LogsServicioFirmaQueryDTO;

import java.util.List;

public interface LogsServicioFirmaProcedureInvoker {
    List<LogsServicioFirmaPOJO> getLogs(LogsServicioFirmaQueryDTO logsServicioFirmaQueryDTO);
}
