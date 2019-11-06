package com.itsight.signbox.service.serviceImpl;

import com.itsight.signbox.advice.CustomValidationException;
import com.itsight.signbox.advice.NotFoundValidationException;
import com.itsight.signbox.domain.LogsPortal;
import com.itsight.signbox.domain.pojo.LogsPortalPOJO;
import com.itsight.signbox.generic.BaseServiceImpl;
import com.itsight.signbox.repository.LogsPortalRepository;
import com.itsight.signbox.service.LogsPortalService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LogsPortalServiceImpl extends BaseServiceImpl<LogsPortalRepository> implements LogsPortalService {

    public LogsPortalServiceImpl(LogsPortalRepository repository) {
        super(repository);
    }

    @Override
    public LogsPortal save(LogsPortal entity) {
        return null;
    }

    @Override
    public LogsPortal update(LogsPortal entity) {
        return null;
    }

    @Override
    public LogsPortal findOne(Integer id) throws NotFoundValidationException, NotFoundValidationException {
        return null;
    }

    @Override
    public LogsPortal findOneWithFT(Integer id) {
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
    public List<LogsPortal> findAll() {
        return null;
    }

    @Override
    public List<LogsPortal> findByNombre(String nombre) {
        return null;
    }

    @Override
    public List<LogsPortal> findByNombreContainingIgnoreCase(String nombre) {
        return null;
    }

    @Override
    public List<LogsPortal> findByDescripcionContainingIgnoreCase(String descripcion) {
        return null;
    }

    @Override
    public List<LogsPortal> findByFlagActivo(boolean flagActivo) {
        return null;
    }

    @Override
    public List<LogsPortal> findByFlagEliminado(boolean flagEliminado) {
        return null;
    }

    @Override
    public List<LogsPortal> findByIdsIn(List<Integer> ids) {
        return null;
    }

    @Override
    public List<LogsPortal> listarPorFiltro(String comodin, String estado, String fk) {
        return null;
    }

    @Override
    public String registrar(LogsPortal entity, String wildcard) throws CustomValidationException {
        return null;
    }

    @Override
    public String actualizar(LogsPortal entity, String wildcard) throws CustomValidationException, CustomValidationException {
        return null;
    }

    @Override
    public void actualizarFlagActivoById(Integer id, boolean flagActivo) {

    }


    @Override
    public Workbook generateExcel(List<LogsPortalPOJO> lstLogsPortal) {

        String[] columns = {"Identificador", "Fecha y hora", "Acción", "Entidad", "Valor Anterior" , "Valor Nuevo" , "Id asociado" , "Campo"};


        Workbook workbook = new XSSFWorkbook();
        CreationHelper createHelper = workbook.getCreationHelper();
        Sheet sheet = workbook.createSheet("Logs del portal");
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
        titleCell.setCellValue("Reporte de logs de auditoría administrativa del portal");
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
        for (LogsPortalPOJO logsPortal : lstLogsPortal) {
            Row row = sheet.createRow(rowNum++);
            row.setHeightInPoints(sheet.getDefaultRowHeightInPoints());

            Cell identificadorCell = row.createCell(0);
            identificadorCell.setCellValue(logsPortal.getIdentificador());
            identificadorCell.setCellStyle(valueCellStyle);

            Cell fechaHoraCell = row.createCell(1);
            fechaHoraCell.setCellValue(logsPortal.getFechaHora());
            fechaHoraCell.setCellStyle(dateCellStyle);

            Cell accionCell = row.createCell(2);
            accionCell.setCellValue(logsPortal.getAccion());
            accionCell.setCellStyle(valueCellStyle);

            Cell entidadCell = row.createCell(3);
            entidadCell.setCellValue(logsPortal.getEntidad());
            entidadCell.setCellStyle(valueCellStyle);

            Cell valorAnteriorCell = row.createCell(4);
            valorAnteriorCell.setCellValue(logsPortal.getValorAnterior());
            valorAnteriorCell.setCellStyle(valueCellStyle);

            Cell valorNuevoCell = row.createCell(5);
            valorNuevoCell.setCellValue(logsPortal.getValorNuevo());
            valorNuevoCell.setCellStyle(valueCellStyle);

            Cell asociadoCell = row.createCell(6);
            asociadoCell.setCellValue(logsPortal.getIdAsociado());
            asociadoCell.setCellStyle(valueCellStyle);

            Cell campoCell = row.createCell(7);
            campoCell.setCellValue(logsPortal.getCampo());
            campoCell.setCellStyle(valueCellStyle);

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
}
