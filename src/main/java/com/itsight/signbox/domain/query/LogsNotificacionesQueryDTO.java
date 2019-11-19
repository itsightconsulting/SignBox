package com.itsight.signbox.domain.query;

import lombok.Data;

@Data
public class LogsNotificacionesQueryDTO {
    private Integer limit;
    private Integer offset;
    private String fechaInicio;
    private String fechaFin;
      private String numeroCuenta;
}
