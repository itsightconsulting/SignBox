package com.itsight.signbox.domain.pojo;

import lombok.Data;

@Data
public class EstampaPOJO {

    private Integer estampaid;
    private String alias;
    private String nombre;
    private String descripcion;
    private Boolean FlagActivo;
    private Integer rows;

    public EstampaPOJO(Integer estampaid, String alias, String nombre, String descripcion, Boolean flagActivo, Integer rows) {
        this.estampaid = estampaid;
        this.alias = alias;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.FlagActivo = flagActivo;
        this.rows = rows;
    }
}
