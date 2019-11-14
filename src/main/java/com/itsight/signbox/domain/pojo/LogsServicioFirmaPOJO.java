package com.itsight.signbox.domain.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.itsight.signbox.json.JsonDateSimpleDeserializer;
import com.itsight.signbox.json.JsonDateSimpleSerializer;
import lombok.Data;
import java.io.Serializable;


@Data
public class LogsServicioFirmaPOJO implements Serializable {

    private Integer logEjecucionId;
    private String idTransaccion;
    private Integer tipoDocumento;
    private String documento;
    private String nombreArchivo;
    private String tipoFirma;
    private String tipoEjecucion;
    private String fechaInicioToString;
    private String fechaFinToString;
    private String numeroCuenta;
    private Integer rows;

    public LogsServicioFirmaPOJO(Integer logEjecucionId, String idTransaccion, Integer tipoDocumento, String documento, String nombreArchivo, String tipoFirma, String tipoEjecucion, String fechaInicioToString, String fechaFinToString, String numeroCuenta, Integer rows) {
        this.logEjecucionId = logEjecucionId;
        this.idTransaccion = idTransaccion;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.nombreArchivo = nombreArchivo;
        this.tipoFirma = tipoFirma;
        this.tipoEjecucion = tipoEjecucion;
        this.fechaInicioToString = fechaInicioToString;
        this.fechaFinToString = fechaFinToString;
        this.numeroCuenta = numeroCuenta;
        this.rows = rows;
    }
}
