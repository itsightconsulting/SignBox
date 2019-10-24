package com.itsight.signbox.domain.base;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.itsight.signbox.json.JsonDateSimpleDeserializer;
import com.itsight.signbox.json.JsonDateSimpleSerializer;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
public class AuditingEntity {

    @Column(name = "CREADOPOR", nullable = false, updatable = false)
    private String creadoPor;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHACREACION", nullable = false, updatable = false)
    @JsonSerialize(using = JsonDateSimpleSerializer.class)
    @JsonDeserialize(using = JsonDateSimpleDeserializer.class)
    private Date fechaCreacion;

    @Column(name = "MODIFICADOPOR")
    private String modificadoPor;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHAMODIFICACION")
    @JsonSerialize(using = JsonDateSimpleSerializer.class)
    @JsonDeserialize(using = JsonDateSimpleDeserializer.class)
    private Date fechaModificacion;

    @Column(name = "FLAGACTIVO", nullable = false)
    private boolean flagActivo;

    @Column(name = "FLAGELIMINADO", nullable = false)
    private boolean flagEliminado;

    public AuditingEntity(){
        /*
         *
         */
    }

    public AuditingEntity(String creadoPor, Date fechaCreacion, String modificadoPor, Date fechaModificacion, boolean flagActivo, boolean flagEliminado) {
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.modificadoPor = modificadoPor;
        this.fechaModificacion = fechaModificacion;
        this.flagActivo = flagActivo;
        this.flagEliminado = flagEliminado;
    }

}
