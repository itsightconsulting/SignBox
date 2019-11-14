package com.itsight.signbox.domain.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.itsight.signbox.json.JsonDateSimpleDeserializer;
import com.itsight.signbox.json.JsonDateSimpleSerializer;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;


@Data
public class LogsServicioFirmaPOJO implements Serializable {

    private Integer logEjecucionId;
    private String idTransaccion;
    private String tipoDocumento;
    private String documento;
    private String nombreArchivo;
    private String tipoFirma;
    private String tipoEjecucion;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonDateSimpleSerializer.class)
    @JsonDeserialize(using = JsonDateSimpleDeserializer.class)
    private Date fechaInicioToString;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonDateSimpleSerializer.class)
    @JsonDeserialize(using = JsonDateSimpleDeserializer.class)
    private Date fechaFinToString;

  //  private String fechaInicioToString;
 //   private String fechaFinToString;
    private String numeroCuenta;
    private Integer rows;

    public LogsServicioFirmaPOJO(Integer logEjecucionId, String idTransaccion, String tipoDocumento, String documento, String nombreArchivo, String tipoFirma, String tipoEjecucion, Date fechaInicioToString, Date fechaFinToString, String numeroCuenta, Integer rows) {
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
