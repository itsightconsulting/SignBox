package com.itsight.signbox.domain.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Data
public class TipoArchivoDTO {

    private String CodigoArchivo;

    private String Extensiones ;

    private String Descripcion ;

    private Integer IdFormatoFirma ;

}
