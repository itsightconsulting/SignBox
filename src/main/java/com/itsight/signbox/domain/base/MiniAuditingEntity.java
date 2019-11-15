package com.itsight.signbox.domain.base;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.itsight.signbox.json.JsonDateSimpleDeserializer;
import com.itsight.signbox.json.JsonDateSimpleSerializer;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class MiniAuditingEntity  {

    @Column(name = "MODIFICADOPOR")
    private String modificadoPor;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHAMODIFICACION")
    @JsonSerialize(using = JsonDateSimpleSerializer.class)
    @JsonDeserialize(using = JsonDateSimpleDeserializer.class)
    private Date fechaModificacion;

    public MiniAuditingEntity(){

    }

    public MiniAuditingEntity(Date fechaModificacion, String modificadoPor) {
        this.fechaModificacion = fechaModificacion;
        this.modificadoPor = modificadoPor;
    }
}
