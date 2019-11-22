package com.itsight.signbox.service;


import com.itsight.signbox.domain.pojo.LogsNotificacionesPOJO;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public interface LogsNotificacionesService {

    Workbook generateExcel(List<LogsNotificacionesPOJO> lstLogsPortal) throws IllegalAccessException, InstantiationException;
}
