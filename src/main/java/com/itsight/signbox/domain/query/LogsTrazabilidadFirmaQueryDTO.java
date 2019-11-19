package com.itsight.signbox.domain.query;

import lombok.Data;

@Data
public class LogsTrazabilidadFirmaQueryDTO {
    private Integer limit;
    private Integer offset;
    private String transaccion;
    private String documento;
    private String numeroCuenta;
}
