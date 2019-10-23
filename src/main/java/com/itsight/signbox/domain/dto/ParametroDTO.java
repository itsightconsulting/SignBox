package com.itsight.signbox.domain.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Data
public class ParametroDTO {

    private String nombre;
    private String valor;
    private String descripcion;

}
