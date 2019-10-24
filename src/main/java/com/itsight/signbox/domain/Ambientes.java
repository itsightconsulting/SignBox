package com.itsight.signbox.domain;

import com.itsight.signbox.domain.base.AuditingEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
@Entity
@Data
public class Ambientes  {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "AMBIENTESID")
  private Integer ambientesId;

  @NotBlank
  @Column(nullable = false , name = "NOMBRE")
  private String nombre;

  @NotBlank
  @Column(nullable = false , name = "PREFIJO")
  private String prefijo;

}
