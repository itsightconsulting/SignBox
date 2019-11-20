package com.itsight.signbox.service;


import com.itsight.signbox.domain.pojo.LogsDetalladoFirmaPOJO;
import com.itsight.signbox.domain.pojo.LogsServicioFirmaPOJO;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public interface LogsDetalladoFirmaService {

    Workbook generateExcel(List<LogsDetalladoFirmaPOJO> list);

}
