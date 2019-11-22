package com.itsight.signbox.service;

import com.itsight.signbox.domain.pojo.LogsDetalladoFirmaPOJO;
import com.itsight.signbox.domain.pojo.LogsTrazabilidadFirmaPOJO;
import com.itsight.signbox.generic.BaseService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


public interface LogsTrazabilidadFirmaService  {

    Workbook generateExcel(List<LogsTrazabilidadFirmaPOJO> list) throws IllegalAccessException;

}
