package com.itsight.signbox.domain.pojo;

import lombok.Data;

@Data
public class AplicacionesPOJO {

    private Integer aplicacionesId;
    private String codigo;
    private String nombre;
    private String descripcion;
    private Boolean FlagActivo;
    private Integer rows;

    public AplicacionesPOJO(Integer aplicacionesId, String codigo, String nombre, String descripcion, Boolean flagActivo, Integer rows) {
        this.aplicacionesId = aplicacionesId;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        FlagActivo = flagActivo;
        this.rows = rows;
    }
}
