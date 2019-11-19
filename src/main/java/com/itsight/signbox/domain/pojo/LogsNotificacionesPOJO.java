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
public class LogsNotificacionesPOJO implements Serializable {

    private Integer logId;
    private String numeroCuenta;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonDateSimpleSerializer.class)
    @JsonDeserialize(using = JsonDateSimpleDeserializer.class)
    private Date fechaEvento;

    private String tipoEvento;
    private String resumen;
    private String detalle;
    private Integer idRetorno;
    private String descripcionRetorno;
    private String detalleRetorno;
    private Integer rows;


    public LogsNotificacionesPOJO( Integer logId,
                                   String numeroCuenta,
                                   Date fechaEvento,
                                   String tipoEvento,
                                   String resumen,
                                   String detalle,
                                   Integer idRetorno,
                                   String descripcionRetorno,
                                   String detalleRetorno,
                                   Integer rows
                                   )
    {

        this.logId = logId;
        this.numeroCuenta = numeroCuenta;
        this.fechaEvento = fechaEvento;
        this.tipoEvento = tipoEvento;
        this.resumen = resumen;
        this.detalle = detalle;
        this.idRetorno = idRetorno;
        this.detalleRetorno = detalleRetorno;
        this.descripcionRetorno = descripcionRetorno;
        this.rows = rows;

    }
}
