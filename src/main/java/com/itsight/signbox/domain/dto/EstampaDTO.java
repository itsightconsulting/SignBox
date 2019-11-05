package com.itsight.signbox.domain.dto;

import lombok.Data;

@Data
public class EstampaDTO {

    private String alias;
    private String nombre;
    private String descripcion;
    private Integer pagina;
    private Boolean FlagActivo;
}
