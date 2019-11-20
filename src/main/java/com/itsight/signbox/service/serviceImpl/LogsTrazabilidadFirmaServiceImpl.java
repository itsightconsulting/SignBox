package com.itsight.signbox.service.serviceImpl;

import com.itsight.signbox.domain.pojo.LogsDetalladoFirmaPOJO;
import com.itsight.signbox.domain.pojo.LogsTrazabilidadFirmaPOJO;
import com.itsight.signbox.service.LogsTrazabilidadFirmaService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LogsTrazabilidadFirmaServiceImpl implements LogsTrazabilidadFirmaService {

    public LogsTrazabilidadFirmaServiceImpl() {
    }


    @Override
    public Workbook generateExcel(List<LogsTrazabilidadFirmaPOJO> lstTrazabilidadFirma) {

        String[] columns = {"FirmaLineaId", "ID Transacción" , "Código aplicación","Tipo archivo" , "PosFirma" , "Certificado" , "Fecha de inicio" , "Fecha de fin" , "Código de respuesta" , "Nombre del archivo" ,"Ruta del archivo inicial", "Ruta del archivo final" , "Número documento", "Número de cuenta" , "Log Id", "Fecha del evento" , "Tipo de evento",   "Resumen" , "Detalle" , "Código retorno" , "Detalle retorno"};


        Workbook workbook = new XSSFWorkbook();
        CreationHelper createHelper = workbook.getCreationHelper();
        Sheet sheet = workbook.createSheet("Logs Trazabil. Firmas" +
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
        titleCell.setCellValue("Reporte de logs de trazabilidad del servicio de firma digital ");
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
        for (LogsTrazabilidadFirmaPOJO logsTrazabilidad : lstTrazabilidadFirma) {
            Row row = sheet.createRow(rowNum++);
            row.setHeightInPoints(sheet.getDefaultRowHeightInPoints());

            Cell identificadorCell = row.createCell(0);
            identificadorCell.setCellValue(logsTrazabilidad.getFirmaLineaId());
            identificadorCell.setCellStyle(valueCellStyle);

            Cell transaccionIdCell = row.createCell(1);
            transaccionIdCell.setCellValue(logsTrazabilidad.getIdTransaccion());
            transaccionIdCell.setCellStyle(valueCellStyle);

//            numeroCuentaCell.setCellStyle(dateCellStyle);

            Cell tipoArchivoCell = row.createCell(2);
            tipoArchivoCell.setCellValue(logsTrazabilidad.getTipoArchivo());
            tipoArchivoCell.setCellStyle(valueCellStyle);

            Cell posFirmaCell = row.createCell(3);
            posFirmaCell.setCellValue(logsTrazabilidad.getPosFirma());
            posFirmaCell.setCellStyle(valueCellStyle);

            Cell certificadoCell = row.createCell(4);
            certificadoCell.setCellValue(logsTrazabilidad.getAlias());
            certificadoCell.setCellStyle(valueCellStyle);

            Cell fechaInicioCell = row.createCell(5);
            fechaInicioCell.setCellValue(logsTrazabilidad.getFechaInicio());
            fechaInicioCell.setCellStyle(dateCellStyle);

            Cell fechaFinCell = row.createCell(6);
            fechaFinCell.setCellValue(logsTrazabilidad.getFechaFin());
            fechaFinCell.setCellStyle(dateCellStyle);


            Cell codigoRespuestaCell = row.createCell(7);
            codigoRespuestaCell.setCellValue(logsTrazabilidad.getCodigoRespuesta());
            codigoRespuestaCell.setCellStyle(valueCellStyle);

            Cell nombreArchivoCell = row.createCell(8);
            nombreArchivoCell.setCellValue(logsTrazabilidad.getNombreArchivo());
            nombreArchivoCell.setCellStyle(valueCellStyle);


            Cell rutaArchivoCell = row.createCell(9);
            rutaArchivoCell.setCellValue(logsTrazabilidad.getRutaArchivo());
            rutaArchivoCell.setCellStyle(valueCellStyle);



            Cell rutaArchivoFinalCell = row.createCell(10);
            rutaArchivoFinalCell.setCellValue(logsTrazabilidad.getRutaArchivoFinal());
            rutaArchivoFinalCell.setCellStyle(valueCellStyle);



            Cell nroDocumentoCell = row.createCell(11);
            nroDocumentoCell.setCellValue(logsTrazabilidad.getNumeroDocumento());
            nroDocumentoCell.setCellStyle(valueCellStyle);

            Cell nroCuentaCell = row.createCell(12);
            nroCuentaCell.setCellValue(logsTrazabilidad.getNumeroCuenta());
            nroCuentaCell.setCellStyle(valueCellStyle);


            Cell logIdCell = row.createCell(13);
            logIdCell.setCellValue(logsTrazabilidad.getLogId());
            logIdCell.setCellStyle(valueCellStyle);

            Cell fechaEventoCell = row.createCell(14);
            fechaEventoCell.setCellValue(logsTrazabilidad.getFechaEvento());
            fechaEventoCell.setCellStyle(valueCellStyle);

            Cell tipoEventoCell = row.createCell(15);
            tipoEventoCell.setCellValue(logsTrazabilidad.getTipoEvento());
            tipoEventoCell.setCellStyle(valueCellStyle);

            Cell resumenCell = row.createCell(16);
            resumenCell.setCellValue(logsTrazabilidad.getResumen());
            resumenCell.setCellStyle(valueCellStyle);

            Cell detalleCell = row.createCell(17);
            detalleCell.setCellValue(logsTrazabilidad.getDetalle());
            detalleCell.setCellStyle(valueCellStyle);

            Cell idRetornoCell = row.createCell(18);
            idRetornoCell.setCellValue(logsTrazabilidad.getIdRetorno());
            idRetornoCell.setCellStyle(valueCellStyle);


            Cell detalleRetornoCell = row.createCell(19);
            detalleRetornoCell.setCellValue(logsTrazabilidad.getDetalleRetorno());
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
