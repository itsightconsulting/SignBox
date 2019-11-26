package com.itsight.signbox.domain;

import com.itsight.signbox.domain.base.AuditingEntity;
import com.itsight.signbox.domain.pojo.PersonaPOJO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotBlank;


@SqlResultSetMapping(
        name = "PersonaGetAll",
        classes = {
                @ConstructorResult(
                        targetClass = PersonaPOJO.class,
                        columns = {
                                @ColumnResult(name = "personaId"),
                                @ColumnResult(name = "documento"),
                                @ColumnResult(name = "folderBase"),
                                @ColumnResult(name = "tipoPersona"),
                                @ColumnResult(name = "telefono"),
                                @ColumnResult(name = "correo"),
                                @ColumnResult(name = "fechaCreacion"),
                                @ColumnResult(name = "fechaUltimaModificacion"),
                                @ColumnResult(name = "rows")
                        }
                )
        }
)

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
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

  @Column(name = "FLAGACTIVO", nullable = false)
  private boolean flagActivo;

  @Column(name = "FLAGELIMINADO", nullable = false)
  private boolean flagEliminado;

  @Column(name = "FLAGCAMBIO", nullable = false)
  private boolean flagCambio;


}
