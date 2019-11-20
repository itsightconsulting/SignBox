package com.itsight.signbox.service.serviceImpl;

import com.itsight.signbox.domain.pojo.LogsDetalladoFirmaPOJO;
import com.itsight.signbox.domain.pojo.LogsNotificacionesPOJO;
import com.itsight.signbox.service.LogsDetalladoFirmaService;
import com.itsight.signbox.service.LogsTrazabilidadFirmaService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LogsDetalladoFirmaServiceImpl implements LogsDetalladoFirmaService {

    public LogsDetalladoFirmaServiceImpl() {
    }


    @Override
    public Workbook generateExcel(List<LogsDetalladoFirmaPOJO> lstDetalladoFirma) {

        String[] columns = {"Identificador", "Número de cuenta", "ID Transacción" , "Tipo documento" , "Documento" , "Código aplicación" , "Código certificado" , "Fecha operación" , "Tipo de evento", "Tipo de firma" ,  "Resumen" , "Detalle" , "Código retorno" , "Descripción retorno" , "Detalle retorno"};


        Workbook workbook = new XSSFWorkbook();
        CreationHelper createHelper = workbook.getCreationHelper();
        Sheet sheet = workbook.createSheet("Logs Detall Firmas" +
                "");
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
        titleCell.setCellValue("Reporte de logs detallados de firma digital ");
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
        for (LogsDetalladoFirmaPOJO logsDetallado : lstDetalladoFirma) {
            Row row = sheet.createRow(rowNum++);
            row.setHeightInPoints(sheet.getDefaultRowHeightInPoints());

            Cell identificadorCell = row.createCell(0);
            identificadorCell.setCellValue(logsDetallado.getLogsFirmaId());
            identificadorCell.setCellStyle(valueCellStyle);

            Cell numeroCuentaCell = row.createCell(1);
            numeroCuentaCell.setCellValue(logsDetallado.getNumeroCuenta());
            numeroCuentaCell.setCellStyle(valueCellStyle);

//            numeroCuentaCell.setCellStyle(dateCellStyle);

            Cell transaccionIdCell = row.createCell(2);
            transaccionIdCell.setCellValue(logsDetallado.getIdTransaccion());
            transaccionIdCell.setCellStyle(valueCellStyle);

            Cell tipoDocumentoCell = row.createCell(3);
            tipoDocumentoCell.setCellValue(logsDetallado.getTipoDocumento());
            tipoDocumentoCell.setCellStyle(valueCellStyle);

            Cell numeroDocumentoCell = row.createCell(4);
            numeroDocumentoCell.setCellValue(logsDetallado.getNumeroDocumento());
            numeroDocumentoCell.setCellStyle(valueCellStyle);

            Cell codigoAplicacionCell = row.createCell(5);
            codigoAplicacionCell.setCellValue(logsDetallado.getCodigoAplicacion());
            codigoAplicacionCell.setCellStyle(valueCellStyle);

            Cell certificadoCell = row.createCell(6);
            certificadoCell.setCellValue(logsDetallado.getCertificado());
            certificadoCell.setCellStyle(valueCellStyle);


            Cell fechaEventoCell = row.createCell(7);
            fechaEventoCell.setCellValue(logsDetallado.getFechaEvento());
            fechaEventoCell.setCellStyle(dateCellStyle);

            Cell codigoAplicacion = row.createCell(8);
            codigoAplicacion.setCellValue(logsDetallado.getTipoEvento());
            codigoAplicacion.setCellStyle(valueCellStyle);


            Cell tipoFirmaCell = row.createCell(9);
            tipoFirmaCell.setCellValue(logsDetallado.getTipoFirma());
            tipoFirmaCell.setCellStyle(valueCellStyle);



            Cell resumenCell = row.createCell(10);
            resumenCell.setCellValue(logsDetallado.getResumen());
            resumenCell.setCellStyle(valueCellStyle);



            Cell detalleCell = row.createCell(11);
            detalleCell.setCellValue(logsDetallado.getDetalle());
            detalleCell.setCellStyle(valueCellStyle);

            Cell idRetornoCell = row.createCell(12);
            idRetornoCell.setCellValue(logsDetallado.getIdRetorno());
            idRetornoCell.setCellStyle(valueCellStyle);


            Cell descripcionRetornoCell = row.createCell(13);
            descripcionRetornoCell.setCellValue(logsDetallado.getDescripcionRetorno());
            descripcionRetornoCell.setCellStyle(valueCellStyle);

            Cell detalleRetornoCell = row.createCell(14);
            detalleRetornoCell.setCellValue(logsDetallado.getDetalleRetorno());
            detalleRetornoCell.setCellStyle(valueCellStyle);

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
