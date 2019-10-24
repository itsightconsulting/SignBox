package com.itsight.signbox.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class Usuarios {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "USUARIOID")
  private Integer usuarioId;

  @NotBlank
  @Column(nullable = false, name = "CONTRASENA")
  private String contrasena;

  @NotBlank
  @Column(nullable = false, name = "NOMBRES")
  private String nombres;

  @NotBlank
  @Column(nullable = false, name = "NOMBREUSUARIO")
  private String nombreUsuario;

  @NotBlank
  @Column(nullable = false, name = "PATERNO")
  private String paterno;

  @NotBlank
  @Column(nullable = false, name = "MATERNO")
  private String materno;

  @Column(nullable = false, name = "PERFILID")
  private Integer perfilId;

  @Column(nullable = false , name = "MODOACCESOID")
  private Integer modoAccesoId;

  @NotBlank
  @Column(nullable = false , name = "CORREOELECTRONICO")
  private String correoElectronico;

  @NotBlank
  @Column(nullable = false , name = "DNI")
  private String dni;


}
