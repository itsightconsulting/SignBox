package com.itsight.signbox.domain;

import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@Table(name = "AMBIENTEAPLICACION")
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

  /*@Ignore
  @ManyToOne
  private Ambientes ambientes;*/

}
