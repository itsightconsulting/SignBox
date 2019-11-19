package com.itsight.signbox.domain.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogsDetalladoFirmaQueryDTO implements Serializable {

    private Integer limit;
    private Integer offset;
    private String fechaI;
    private String fechaF;
    private String transaccion;
    private String tipoDocumento;
    private String documento;
    private String numeroCuenta;

    public void getAll(){
        this.limit = 5;
        this.offset = 0;
        this.fechaI = "";
        this.fechaF = "";
        this.transaccion = "";
        this.tipoDocumento = "";
        this.documento = "";
        this.numeroCuenta = "";
    }
}
