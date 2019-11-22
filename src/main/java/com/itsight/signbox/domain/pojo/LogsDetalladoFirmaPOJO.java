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
public class LogsDetalladoFirmaPOJO implements Serializable {

    private Integer logsFirmaId;
    private String numeroCuenta;
    private String idTransaccion;
    private String tipoDocumento;
    private String numeroDocumento;
    private String codigoAplicacion;
    private String certificado;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonDateSimpleSerializer.class)
    @JsonDeserialize(using = JsonDateSimpleDeserializer.class)
    private Date fechaEvento;

    private String tipoEvento;
    private String tipoFirma;
    private String resumen;
    private String detalle;
    private Integer idRetorno;
    private String descripcionRetorno;
    private String detalleRetorno;
    private Integer rows;




    public LogsDetalladoFirmaPOJO(
                                     Integer logsFirmaId,
                                     String numeroCuenta,
                                     String idTransaccion,
                                     String tipoDocumento,
                                     String numeroDocumento,
                                     String codigoAplicacion,
                                     String certificado,
                                     Date fechaEvento,
                                     String tipoEvento,
                                     String tipoFirma,
                                     String resumen,
                                     String detalle,
                                     Integer idRetorno,
                                     String detalleRetorno,
                                     String descripcionRetorno,
                                     Integer rows

                                     ) {

        this.logsFirmaId = logsFirmaId;
        this.numeroCuenta = numeroCuenta;
        this.idTransaccion = idTransaccion;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.codigoAplicacion = codigoAplicacion;
        this.certificado = certificado;
        this.fechaEvento = fechaEvento;
        this.tipoEvento = tipoEvento;
        this.tipoFirma = tipoFirma;
        this.resumen = resumen;
        this.detalle = detalle;
        this.idRetorno = idRetorno;
        this.detalleRetorno = detalleRetorno;
        this.descripcionRetorno = descripcionRetorno;
        this.rows = rows;

    }
}
