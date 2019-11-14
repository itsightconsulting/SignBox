package com.itsight.signbox.service;

import com.itsight.signbox.domain.LogsServicioFirma;
import com.itsight.signbox.domain.pojo.LogsServicioFirmaPOJO;
import com.itsight.signbox.generic.BaseService;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public interface LogsServicioFirmaService  extends BaseService<LogsServicioFirma, Integer> {

    Workbook generateExcel(List<LogsServicioFirmaPOJO> list);
}
