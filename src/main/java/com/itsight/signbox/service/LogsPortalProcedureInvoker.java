package com.itsight.signbox.service;

import com.itsight.signbox.domain.pojo.LogsPortalPOJO;
import com.itsight.signbox.domain.query.LogsPortalQueryDTO;

import java.util.List;

public interface LogsPortalProcedureInvoker {

    List<LogsPortalPOJO> getLogsAdmin(LogsPortalQueryDTO logsPortalQueryDTO);

}
