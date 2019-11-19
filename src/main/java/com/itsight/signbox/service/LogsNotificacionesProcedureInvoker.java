package com.itsight.signbox.service;

import com.itsight.signbox.domain.pojo.LogsNotificacionesPOJO;
import com.itsight.signbox.domain.query.LogsNotificacionesQueryDTO;

import java.util.List;

public interface LogsNotificacionesProcedureInvoker {

    List<LogsNotificacionesPOJO> getLogsNotificaciones(LogsNotificacionesQueryDTO logsNotificacionesQueryDTO);

}
