package com.itsight.signbox.domain.dto;

import lombok.Data;

@Data
public class AplicacionesDTO {
    private String codigo;
    private String nombre;
    private String descripcion;
    private String usuarioLider;
    private Boolean FlagActivo;
}
