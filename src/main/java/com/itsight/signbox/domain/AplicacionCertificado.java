package com.itsight.signbox.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class AplicacionCertificado {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "APLICACIONCERTIFICADOID")
  private Integer aplicacionCertificadoid;

  @NotNull
  @Column(name = "IDAPLICACION")
  private Integer idAplicacion;

  @Column(name = "IDCERTIFICADO")
  private Integer idCertificado;

}
