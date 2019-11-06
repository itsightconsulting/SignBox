package com.itsight.signbox.service;

import com.itsight.signbox.domain.LogsPortal;
import com.itsight.signbox.domain.pojo.LogsPortalPOJO;
import com.itsight.signbox.generic.BaseService;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public interface LogsPortalService extends BaseService<LogsPortal, Integer> {

    Workbook generateExcel(List<LogsPortalPOJO> list);
}
