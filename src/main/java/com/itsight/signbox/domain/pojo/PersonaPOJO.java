package com.itsight.signbox.domain.pojo;
import lombok.Data;

@Data
public class PersonaPOJO {

    private Integer personaId;

    private String documento;

    private String folderBase;

    private String tipoPersona;

    private String telefono;

    private String correo;

    private String fechaCreacion;

    private String fechaUltimaModificacion;

    private Integer rows;

    public PersonaPOJO(Integer personaId, String documento, String folderBase, String tipoPersona, String telefono, String correo, String fechaCreacion, String fechaUltimaModificacion, Integer rows) {
        this.personaId = personaId;
        this.documento = documento;
        this.folderBase = folderBase;
        this.tipoPersona = tipoPersona;
        this.telefono = telefono;
        this.correo = correo;
        this.fechaCreacion = fechaCreacion;
        this.fechaUltimaModificacion = fechaUltimaModificacion;
        this.rows = rows;
    }
}
