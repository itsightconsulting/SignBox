package com.itsight.signbox.domain;

import com.itsight.signbox.domain.base.AuditingEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class TipoArchivo extends AuditingEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TIPOARCHIVOID")
  private Integer tipoArchivoId;

  @NotBlank
  @Column(nullable = false , name = "CODIGOARCHIVO")
  private String codigoArchivo;

  @NotBlank
  @Column(nullable = false , name = "EXTENSIONES")
  private String extensiones;

  @NotBlank
  @Column(nullable = false , name = "DESCRIPCION")
  private String descripcion;

  @NotBlank
  @Column(nullable = false , name = "IDFORMATOFIRMA")
  private Integer idFormatoFirma;


}
