package com.itsight.signbox.domain.dto;
import lombok.Data;

@Data
public class SecurityUserDTO {

    private Integer usuarioId;
    private String nombreUsuario;
    private String nombres;
    private String apellidos;
    private String contrasena;
    private String rol;
    private boolean flagActivo;

    public SecurityUserDTO(Integer usuarioId, String nombreUsuario, String contrasena, String rol, boolean flagActivo) {
        this.usuarioId = usuarioId;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.rol = rol;
        this.flagActivo = flagActivo;
    }

    public SecurityUserDTO(Integer usuarioId, String nombreUsuario, String nombres, String apellidos) {
        this.usuarioId = usuarioId;
        this.nombreUsuario = nombreUsuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }
}