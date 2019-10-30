package com.itsight.signbox.domain.pojo;

import lombok.Data;

@Data
public class AmbientesPOJO {

    private Integer ambientesId;
    private String nombre;
    private String prefijo;
    private Boolean FlagActivo;
    private Integer rows;

    public AmbientesPOJO(Integer ambientesId, String nombre,
            String prefijo, Boolean FlagActivo,Integer rows)
    {
        this.ambientesId = ambientesId;
        this.nombre = nombre;
        this.prefijo = prefijo;
        this.FlagActivo = FlagActivo;
        this.rows = rows;
    }

}
