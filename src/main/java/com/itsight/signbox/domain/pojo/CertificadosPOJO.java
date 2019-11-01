package com.itsight.signbox.domain.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
public class CertificadosPOJO {

    private Integer id;

    private String alias;

    private String identificadorHsm;

    private String responsable;

    private Boolean flagActivo;

    private Integer rows;


    public CertificadosPOJO(Integer id, String alias, String identificadorHsm, String responsable, Boolean flagActivo, Integer rows) {
        this.id = id;
        this.alias = alias;
        this.identificadorHsm = identificadorHsm;
        this.responsable = responsable;
        this.flagActivo = flagActivo;
        this.rows = rows;
    }
}
