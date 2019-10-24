package com.itsight.signbox.domain;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class TipoEvento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TIPOEVENTOID")
  private Integer tipoEventoId;


  @NotBlank
  @Column(nullable = false , name = "DESCRIPCION")
  private String descripcion;


}
