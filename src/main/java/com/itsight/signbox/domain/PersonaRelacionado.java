package com.itsight.signbox.domain;

import com.itsight.signbox.domain.base.AuditingEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PersonaRelacionado extends AuditingEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "PERSONARELACIONADOID")
  private Integer personaRelacionadoId;

  @Column(nullable = false , name = "PERSONABASEID")
  private Integer personaBaseId;

  @Column(nullable = false , name = "PERSONARELACIONID")
  private Integer personaRelacionId;


}
