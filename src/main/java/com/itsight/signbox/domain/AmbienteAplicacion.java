package com.itsight.signbox.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class AmbienteAplicacion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "AMBIENTEAPLICACIONID")
  private Integer ambienteAplicacionid;

  @NotNull
  @Column(nullable = false , name = "IDAPLICACION")
  private Integer idAplicacion;

  @NotNull
  @Column(nullable = false , name = "IDAMBIENTE")
  private Integer idAmbiente;

}
