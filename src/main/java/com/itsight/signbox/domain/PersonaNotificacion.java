package com.itsight.signbox.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class PersonaNotificacion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "PERSONANOTIFICACIONID")
  private Integer personaNotificacionId;

  @Column(nullable = false , name = "PERSONAID")
  private Integer personaId;

  @Column(nullable = false , name = "TIPOENVIO")
  private Integer tipoEnvio;

  @NotBlank
  @Column(nullable = false , name = "NUMEROCUENTA")
  private String numeroCuenta;

  @NotBlank
  @Column(nullable = false , name = "NUMEROCUENTACCI")
  private String numeroCuentaCci;

  @NotBlank
  @Column(nullable = false , name = "TIPODOCUMENTO")
  private String tipoDocumento;

  @NotBlank
  @Column(nullable = false , name = "NUMERODOCUMENTO")
  private String numeroDocumento;

  @NotBlank
  @Column(nullable = false , name = "NOMBRES")
  private String nombres;

  @NotBlank
  @Column(nullable = false , name = "APELLIDOPATERNO")
  private String apellidoPaterno;

  @NotBlank
  @Column(nullable = false , name = "APELLIDOMATERNO")
  private String apellidoMaterno;

  @NotBlank
  @Column(nullable = false , name = "TIPOMONEDA")
  private String tipoMoneda;

  @NotBlank
  @Column(nullable = false , name = "ENVIADO")
  private String enviado;

}
