package com.itsight.signbox.domain.pojo;

import lombok.Data;

@Data
public class ArchivoPOJO {

    private String rutaArchivoFinal;
    private String nombreArchivo;
    private String negocio;
    private String numeroCuenta;
    private Integer rows;


    public ArchivoPOJO(String rutaArchivoFinal, String nombreArchivo, String negocio, String numeroCuenta, Integer rows) {
        this.rutaArchivoFinal = rutaArchivoFinal;
        this.nombreArchivo = nombreArchivo;
        this.negocio = negocio;
        this.numeroCuenta = numeroCuenta;
        this.rows = rows;
    }
}
