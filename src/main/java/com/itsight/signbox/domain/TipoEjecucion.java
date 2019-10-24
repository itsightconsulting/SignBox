package com.itsight.signbox.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class TipoEjecucion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TIPOEJECUCIONID")
  private Integer tipoEjecucionId;

  @NotBlank
  @Column(nullable = false , name = "DESCRIPCION")
  private String descripcion;

}
