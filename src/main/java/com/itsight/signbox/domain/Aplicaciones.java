package com.sample;

import com.itsight.signbox.domain.base.AuditingEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class Aplicaciones extends AuditingEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "APLICACIONESID")
  private Integer aplicacionesId;

  @NotBlank
  @Column(nullable = false , name = "CODIGO")
  private String codigo;

  @NotBlank
  @Column(nullable = false , name = "NOMBRE")
  private String nombre;

  @NotBlank
  @Column(nullable = false , name = "DESCRIPCION")
  private String descripcion;

  @NotBlank
  @Column(nullable = false , name = "USUARIOLIDER")
  private String usuarioLider;

}
