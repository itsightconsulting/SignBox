package com.itsight.signbox.service;

import com.itsight.signbox.domain.pojo.LogsDetalladoFirmaPOJO;
import com.itsight.signbox.domain.query.LogsDetalladoFirmaQueryDTO;

import java.util.List;

public interface LogsDetalladoFirmaProcedureInvoker {

    List<LogsDetalladoFirmaPOJO> getLogsDetalladoFirma(LogsDetalladoFirmaQueryDTO logsDetalladoFirmaQueryDTO);

}
