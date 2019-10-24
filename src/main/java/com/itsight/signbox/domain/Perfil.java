package com.itsight.signbox.domain;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class Perfil {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "PERFILID")
  private Integer perfilId;

  @NotBlank
  @Column(nullable = false , name = "DESCRIPCION")
  private String descripcion;


}
