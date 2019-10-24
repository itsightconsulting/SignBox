package com.itsight.signbox.domain;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class FormatoFirma {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "FORMATOFIRMAID")
  private Integer formatoFirmaId;

  @NotBlank
  @Column(nullable = false , name = "FORMATOFIRMADIGITAL")
  private String formatoFirmaDigital;

  @NotBlank
  @Column(nullable = false , name = "DESCRIPCION")
  private String descripcion;

}
