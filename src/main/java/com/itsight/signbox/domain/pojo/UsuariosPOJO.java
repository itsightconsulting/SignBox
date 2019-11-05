package com.itsight.signbox.domain.pojo;

import lombok.Data;

@Data
public class UsuariosPOJO {

    private Integer id;
    private String nombres;
    private String dni;
    private String perfilNombre;
    private String nombreUsuario;
    private Boolean flagActivo;
    private Integer rows;

    public UsuariosPOJO(Integer id, String nombres, String dni, String perfilNombre, String nombreUsuario, Boolean flagActivo, Integer rows) {
        this.id = id;
        this.nombres = nombres;
        this.dni = dni;
        this.perfilNombre = perfilNombre;
        this.nombreUsuario = nombreUsuario;
        this.flagActivo = flagActivo;
        this.rows = rows;
    }
}
