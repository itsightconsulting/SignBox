package com.itsight.signbox.domain.pojo;

import lombok.Data;

@Data
public class TipoArchivoPOJO {

    private Integer tipoArchivoId;
    private String CodigoArchivo;
    private String Extensiones ;
    private String Descripcion ;
    private Integer IdFormatoFirma ;
    private String Detalle ;
    private Boolean flagActivo;
    private Integer rows;

   public TipoArchivoPOJO(Integer tipoArchivoId, String CodigoArchivo, String Extensiones ,
           String Descripcion, Integer IdFormatoFirma , String Detalle , Boolean flagActivo, Integer rows){
        this.tipoArchivoId = tipoArchivoId;
        this.CodigoArchivo = CodigoArchivo;
        this.Extensiones = Extensiones;
        this.Descripcion = Descripcion;
        this.IdFormatoFirma = IdFormatoFirma;
        this.Detalle = Detalle;
        this.flagActivo = flagActivo;
        this.rows = rows;
   }
}
