package com.itsight.signbox.service.serviceImpl;


import com.itsight.signbox.advice.CustomValidationException;
import com.itsight.signbox.advice.NotFoundValidationException;
import com.itsight.signbox.domain.LogsServicioFirma;
import com.itsight.signbox.domain.pojo.LogsServicioFirmaPOJO;
import com.itsight.signbox.generic.BaseServiceImpl;
import com.itsight.signbox.repository.LogsServicioFirmaRespository;
import com.itsight.signbox.service.LogsServicioFirmaService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LogsServicioFirmaServiceImpl  extends BaseServiceImpl<LogsServicioFirmaRespository> implements LogsServicioFirmaService {

    public LogsServicioFirmaServiceImpl(LogsServicioFirmaRespository repository) {
        super(repository);
    }

    @Override
    public Workbook generateExcel(List<LogsServicioFirmaPOJO> lstLogsPortal) {

        String[] columns = {"Identificador", "ID Transacción", "Tipo documento", "Documento", "Archivo" , "Tipo de firma" , "Detalle operación" , "Fecha de inicio", "Fecha de fin", "Número de cuenta"};


        Workbook workbook = new XSSFWorkbook();
        CreationHelper createHelper = workbook.getCreationHelper();
        Sheet sheet = workbook.createSheet("Logs servicio firma");
        sheet.setDefaultRowHeightInPoints(39);


        //FONT
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 10);
        headerFont.setColor(IndexedColors.WHITE.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headerCellStyle.setBorderBottom(BorderStyle.THIN);
        headerCellStyle.setBorderTop(BorderStyle.THIN);
        headerCellStyle.setBorderRight(BorderStyle.THIN);
        headerCellStyle.setBorderLeft(BorderStyle.THIN);

        Row titleRow = sheet.createRow(0);
        titleRow.setHeightInPoints(sheet.getDefaultRowHeightInPoints() + 20);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columns.length - 1));

        CellStyle titleCellStyle = workbook.createCellStyle();

        Font titleFont = workbook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short) 18);
        titleFont.setColor(IndexedColors.GREY_80_PERCENT.getIndex());

        titleCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
        titleCellStyle.setFont(titleFont);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("Reporte de logs del servicio de firma");
        titleCell.setCellStyle(titleCellStyle);

        Row headerRow = sheet.createRow(1);
        headerRow.setHeightInPoints(sheet.getDefaultRowHeightInPoints());
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy hh:mm:ss"));
        dateCellStyle.setBorderBottom(BorderStyle.THIN);
        dateCellStyle.setBorderTop(BorderStyle.THIN);
        dateCellStyle.setBorderRight(BorderStyle.THIN);
        dateCellStyle.setBorderLeft(BorderStyle.THIN);
        dateCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        dateCellStyle.setAlignment(HorizontalAlignment.LEFT);
        dateCellStyle.setWrapText(true); //Set wordwrap

        CellStyle valueCellStyle = workbook.createCellStyle();
        valueCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        valueCellStyle.setAlignment(HorizontalAlignment.LEFT);
        valueCellStyle.setBorderBottom(BorderStyle.THIN);
        valueCellStyle.setBorderTop(BorderStyle.THIN);
        valueCellStyle.setBorderRight(BorderStyle.THIN);
        valueCellStyle.setBorderLeft(BorderStyle.THIN);
        valueCellStyle.setWrapText(true); //Set wordwrap

        int rowNum = 2;
        for (LogsServicioFirmaPOJO logsPortal : lstLogsPortal) {
            Row row = sheet.createRow(rowNum++);
            row.setHeightInPoints(sheet.getDefaultRowHeightInPoints());

            Cell identificadorCell = row.createCell(0);
            identificadorCell.setCellValue(logsPortal.getLogEjecucionId());
            identificadorCell.setCellStyle(valueCellStyle);

            Cell fechaHoraCell = row.createCell(1);
            fechaHoraCell.setCellValue(logsPortal.getIdTransaccion());
            fechaHoraCell.setCellStyle(dateCellStyle);

            Cell accionCell = row.createCell(2);
            accionCell.setCellValue(logsPortal.getTipoDocumento());
            accionCell.setCellStyle(valueCellStyle);

            Cell entidadCell = row.createCell(3);
            entidadCell.setCellValue(logsPortal.getDocumento());
            entidadCell.setCellStyle(valueCellStyle);

            Cell valorAnteriorCell = row.createCell(4);
            valorAnteriorCell.setCellValue(logsPortal.getNombreArchivo());
            valorAnteriorCell.setCellStyle(valueCellStyle);

            Cell valorNuevoCell = row.createCell(5);
            valorNuevoCell.setCellValue(logsPortal.getTipoFirma());
            valorNuevoCell.setCellStyle(valueCellStyle);

            Cell ejecucionCell = row.createCell(6);
            ejecucionCell.setCellValue(logsPortal.getTipoEjecucion());
            ejecucionCell.setCellStyle(valueCellStyle);

            Cell fechainicioCell = row.createCell(7);
            fechainicioCell.setCellValue(logsPortal.getFechaInicioToString());
            fechainicioCell.setCellStyle(valueCellStyle);

            Cell fechafinCell = row.createCell(8);
            fechafinCell.setCellValue(logsPortal.getFechaFinToString());
            fechafinCell.setCellStyle(valueCellStyle);

            Cell nrocuentaCell = row.createCell(9);
            nrocuentaCell.setCellValue(logsPortal.getNumeroCuenta());
            nrocuentaCell.setCellStyle(valueCellStyle);
        }

        // Resize all columns to fit the content size
        for (int i = 0; i < columns.length; i++) {

            switch (i) {
                case 0:
                case 6:
                case 7:
                    sheet.setColumnWidth(i, 19 * 256);
                    break;
                default:
                    sheet.setColumnWidth(i, 25 * 256);
                    break;
            }
            //  sheet.autoSizeColumn(i);

        }

        return workbook;
    }

    @Override
    public LogsServicioFirma save(LogsServicioFirma entity) {
        return null;
    }

    @Override
    public LogsServicioFirma update(LogsServicioFirma entity) {
        return null;
    }

    @Override
    public LogsServicioFirma findOne(Integer id) throws NotFoundValidationException, NotFoundValidationException {
        return null;
    }

    @Override
    public LogsServicioFirma findOneWithFT(Integer id) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Integer> findIdsByFlagActivo(boolean flagActivo) {
        return null;
    }

    @Override
    public List<LogsServicioFirma> findAll() {
        return null;
    }

    @Override
    public List<LogsServicioFirma> findByNombre(String nombre) {
        return null;
    }

    @Override
    public List<LogsServicioFirma> findByNombreContainingIgnoreCase(String nombre) {
        return null;
    }

    @Override
    public List<LogsServicioFirma> findByDescripcionContainingIgnoreCase(String descripcion) {
        return null;
    }

    @Override
    public List<LogsServicioFirma> findByFlagActivo(boolean flagActivo) {
        return null;
    }

    @Override
    public List<LogsServicioFirma> findByFlagEliminado(boolean flagEliminado) {
        return null;
    }

    @Override
    public List<LogsServicioFirma> findByIdsIn(List<Integer> ids) {
        return null;
    }

    @Override
    public List<LogsServicioFirma> listarPorFiltro(String comodin, String estado, String fk) {
        return null;
    }

    @Override
    public String registrar(LogsServicioFirma entity, String wildcard) throws CustomValidationException {
        return null;
    }

    @Override
    public String actualizar(LogsServicioFirma entity, String wildcard) throws CustomValidationException, CustomValidationException {
        return null;
    }

    @Override
    public void actualizarFlagActivoById(Integer id, boolean flagActivo) {

    }
}
