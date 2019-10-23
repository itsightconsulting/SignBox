package com.itsight.signbox.domain;

import com.itsight.signbox.domain.base.AuditingEntity;
import com.itsight.signbox.domain.dto.ParametroDTO;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
public class Parametro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PARAMETROID")
    private Integer id;

    @NotBlank
    @Column(nullable = false , name = "nombre")
    private String nombre;

    @NotBlank
    @Column(nullable = false, name = "valor")
    private String valor;

    @NotBlank
    @Column(nullable = false , name = "descripcion")
    private String descripcion;

    @NotBlank
    @Column(nullable = false, name = "modificadopor")
    private String modificadoPor;

    @NotNull
    @Column(nullable = false , name = "fechamodificacion")
    private Date fechaModificacion;

    public Parametro(){

    }

    public void setParametro(ParametroDTO parametro) {
        this.descripcion = parametro.getDescripcion();
        this.valor = parametro.getValor();

    }

}
