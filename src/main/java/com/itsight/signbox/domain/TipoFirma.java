package com.itsight.signbox.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Table(name = "TIPOFIRMA")
public class TipoFirma {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TIPOFIRMAID")
  private Integer tipoFirmaId;

  @NotBlank
  @Column(nullable = false , name = "DETALLE")
  private String detalle;

}
