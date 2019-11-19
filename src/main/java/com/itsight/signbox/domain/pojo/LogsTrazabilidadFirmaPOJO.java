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
public class LogsTrazabilidadFirmaPOJO implements Serializable {

    private Integer firmaLineaId;
    private String idTransaccion;
    private String codigoAplicacion;
    private String tipoArchivo;
    private String posFirma;
    private String alias;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonDateSimpleSerializer.class)
    @JsonDeserialize(using = JsonDateSimpleDeserializer.class)
    private Date fechaInicio;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonDateSimpleSerializer.class)
    @JsonDeserialize(using = JsonDateSimpleDeserializer.class)
    private Date fechaFin;

    private Integer codigoRespuesta;
    private String nombreArchivo;
    private String rutaArchivo;
    private String rutaArchivoFinal;
    private String numeroDocumento;
    private String numeroCuenta;

    private Integer logId;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonDateSimpleSerializer.class)
    @JsonDeserialize(using = JsonDateSimpleDeserializer.class)
    private Date fechaEvento;
    private String tipoEvento;
    private String resumen;
    private String detalle;
    private Integer idRetorno;
    private String detalleRetorno;
    private Integer rows;

    public LogsTrazabilidadFirmaPOJO(  Integer firmaLineaId,
                                       String idTransaccion,
                                       String codigoAplicacion,
                                       String tipoArchivo,
                                       String posFirma,
                                       String alias,
                                       Date fechaInicio,
                                       Date fechaFin,
                                       Integer codigoRespuesta,
                                       String nombreArchivo,
                                       String rutaArchivo,
                                       String rutaArchivoFinal,
                                       String numeroDocumento,
                                       String numeroCuenta,
                                       Integer logId,
                                       Date fechaEvento,
                                       String tipoEvento,
                                       String resumen,
                                       String detalle,
                                       Integer idRetorno,
                                       String detalleRetorno,
                                       Integer rows
                                       ) {

        this.firmaLineaId = firmaLineaId;
        this.idTransaccion = idTransaccion;
        this.codigoAplicacion = codigoAplicacion;
        this.tipoArchivo = tipoArchivo;
        this.posFirma = posFirma;
        this.alias = alias;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.codigoRespuesta = codigoRespuesta;
        this.nombreArchivo = nombreArchivo;
        this.rutaArchivo = rutaArchivo;
        this.rutaArchivoFinal = rutaArchivoFinal;
        this.numeroDocumento = numeroDocumento;
        this.numeroCuenta = numeroCuenta;
        this.logId = logId;
        this.fechaEvento = fechaEvento;
        this.tipoEvento = tipoEvento;
        this.resumen = resumen;
        this.detalle = detalle;
        this.idRetorno = idRetorno;
        this.detalleRetorno = detalleRetorno;
        this.rows = rows;

    }
}
