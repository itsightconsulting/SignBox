package com.itsight.signbox.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class CodigoRetorno {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CODIGORETORNOID")
  private Integer codigoRetornoId;

  @NotBlank
  @Column(nullable = false , name = "DETALLE")
  private String detalle;

  @NotBlank
  @Column(nullable = false , name = "DESCRIPCION")
  private String descripcion;

  @Column(nullable = false , name = "IDRETORNO")
  private Integer idretorno;


}
