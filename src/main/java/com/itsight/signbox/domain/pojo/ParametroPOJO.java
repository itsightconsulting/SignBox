package com.itsight.signbox.domain.pojo;

import lombok.Data;

@Data
public class ParametroPOJO {

    private Integer id;
    private String nombre;
    private String valor;
    private String descripcion;
    private Integer rows;

    public ParametroPOJO(Integer id, String nombre, String valor, String descripcion, Integer rows) {
        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
        this.descripcion = descripcion;
        this.rows = rows;
    }
}
