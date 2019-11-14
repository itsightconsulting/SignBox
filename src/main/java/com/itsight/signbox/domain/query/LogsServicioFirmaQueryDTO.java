package com.itsight.signbox.domain.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogsServicioFirmaQueryDTO implements Serializable {

    private Integer limit;
    private Integer offset;
    private String archivo;
    private String fechaI;
    private String fechaF;
    private String transaccion;
    private Integer tipoDocumento;
    private String documento;
    private String numeroCuenta;

    public void getAll(){
        this.limit = 5;
        this.offset = 0;
        this.archivo = "";
        this.fechaI = "";
        this.fechaF = "";
        this.transaccion = "";
        this.tipoDocumento = 0;
        this.documento = "";
        this.numeroCuenta = "";
    }
}
