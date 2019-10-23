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

    @Column(name = "CREATED_BY", nullable = false, updatable = false)
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATION_DATE", nullable = false, updatable = false)
    @JsonSerialize(using = JsonDateSimpleSerializer.class)
    @JsonDeserialize(using = JsonDateSimpleDeserializer.class)
    private Date creationDate;

    @Column(name = "MODIFIED_BY")
    private String modifiedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFIED_DATE")
    @JsonSerialize(using = JsonDateSimpleSerializer.class)
    @JsonDeserialize(using = JsonDateSimpleDeserializer.class)
    private Date modifiedDate;

    @Column(name = "FLAG_ENABLED", nullable = false)
    private boolean flagEnabled;

    @Column(name = "FLAG_DELETED", nullable = false)
    private boolean flagDeleted;

    public AuditingEntity(){
        /*
         *
         */
    }

    public AuditingEntity(String createdBy, Date creationDate, String modifiedBy, Date modifiedDate, boolean flagEnabled, boolean flagDeleted) {
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
        this.flagEnabled = flagEnabled;
        this.flagDeleted = flagDeleted;
    }

}
