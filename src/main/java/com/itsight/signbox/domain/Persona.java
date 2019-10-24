package com.itsight.signbox.domain;

import com.itsight.signbox.domain.base.AuditingEntity;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class Persona extends AuditingEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "PERSONAID")
  private Integer personaId;

  @NotBlank
  @Column(nullable = false, name = "DOCUMENTO")
  private String documento;


  @Column(nullable = false , name = "TIPOPERSONAID")
  private Integer tipoPersonaId;

  @NotBlank
  @Column(nullable = false , name = "FOLDERBASE")
  private String folderBase;

  @NotBlank
  @Column(nullable = false , name = "HASHCONTRASENIA")
  private String hashContrasenia;

  @NotBlank
  @Column(nullable = false , name = "GUID")
  private String guid;

  @NotBlank
  @Column(nullable = false , name = "TELEFONO")
  private String telefono;

  @NotBlank
  @Column(nullable = false , name = "CORREO")
  private String correo;

  @NotBlank
  @Column(nullable = false , name = "FLAGCAMBIO")
  private String flagCambio;


}
