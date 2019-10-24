package com.itsight.signbox.domain;

import com.itsight.signbox.domain.base.AuditingEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

public class Estampa extends AuditingEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ESTAMPAID")
  private Integer estampaid;

  @NotBlank
  @Column(nullable = false , name = "ALIAS")
  private String alias;

  @NotBlank
  @Column(nullable = false , name = "NOMBRE")
  private String nombre;

  @NotBlank
  @Column(nullable = false , name = "DESCRIPCION")
  private String descripcion;

  @Column(nullable = false , name = "PAGINA")
  private Integer pagina;


}
