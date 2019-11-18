package com.itsight.util;

import lombok.Data;

import java.util.Arrays;
import java.util.Optional;

public class Enums {

    public enum ResponseCode {
        REGISTRO(-1),
        ACTUALIZACION(-2),
        ELIMINACION(-3),
        GENERIC_SUCCESS(-4),
        EX_VALIDATION_FAILED(-5),
        EMPTY_RESPONSE(-6),
        SESSION_VALUE_NOT_FOUND(-7),
        SUCCESS_QUERY(-8),
        EX_GENERIC(-9),
        EX_NULL_POINTER(-10),
        EX_JACKSON_INVALID_FORMAT(-11),
        NOT_FOUND_MATCHES(-12),
        EX_NUMBER_FORMAT(-99),
        EX_MAX_SIZE_MULTIPART(-100),
        EX_MAX_UPLOAD_SIZE(-101),
        EX_ARRAY_INDEX_OUT(-102),
        EX_SQL_EXCEPTION(-103),
        EX_SQL_GRAMMAR_EXCEPTION(-104),
        ILLEGAL_ARGUMENT_EXCEPTION(-105),
        VF_USUARIO_REPETIDO(-150);

        final int code;

        ResponseCode(int code) {
            this.code = code;
        }

        public String get() {
            return String.valueOf(code);
        }
    }


    public enum Error{
        ARCHIVO_EXCEDE_MAX_PERMITIDO("El archivo que ha intentado subir excede al límite permitido, por favor suba un archivo menor o igual a %s");

        final String msg;

        Error(String msg){
            this.msg = msg;
        }

        public String get(){
            return msg;
        }
    }

    public enum Msg{
        REGISTRO_EXITOSO("Se ha registrado correctamente"),
        RESOURCE_NOT_FOUND("No se ha encontrado el recurso solicitado");

        final String msg;

        Msg(String msg){
            this.msg = msg;
        }

        public String get(){
            return msg;
        }
    }

    public enum FileExt{
        JPEG(".jpg"),
        PDF(".pdf"),
        WEBM(".webm");

        final String id;

        FileExt(String id){this.id = id;}

        public String get(){return id;}
    }

    public enum ModosAcceso
    {
        ActiveDirectory("1", "Acceso por Active Directory"),
        PorFormulario("2", "Por Formulario");

        final String id;
        final String descripcion;

        ModosAcceso(String id, String descripcion) {
            this.id = id;
            this.descripcion = descripcion;
        }
        public String getId() {
            return id;
        }

        public String getDescripcion() {
            return descripcion;
        }


    }

    public enum Perfiles
    {
        Administracion("1" , "Administrador"),
        Operador("2" , "Operador"),
        Auditor("3", "Auditor");


        final String id;
        final String nombre;

        Perfiles(String id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }

        public String getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }

    }

    public enum Galletas{
        GLL_NOMBRE_COMPLETO
    }

    public enum TipoEnvio{
        SMS(1),
        EMAIL(2),
        SMS_EMAIL(3),
        UNKNOWN(4);

        final int id;

        TipoEnvio(int id){this.id = id;}

        public static TipoEnvio getTipoEnvioFromInt(int status) {
            for(TipoEnvio te : values()) {
                if(te.id == status) return te;
            }
            return UNKNOWN;
        }

        public final int get(){return id;}
    }

}
