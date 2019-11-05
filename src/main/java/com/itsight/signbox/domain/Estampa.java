package com.itsight.signbox.domain;

import com.itsight.signbox.domain.base.AuditingEntity;
import com.itsight.signbox.domain.pojo.EstampaPOJO;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@SqlResultSetMapping(
        name = "EstampaGetAll",
        classes = {
                @ConstructorResult(
                        targetClass = EstampaPOJO.class,
                        columns = {
                                @ColumnResult(name = "estampaid"),
                                @ColumnResult(name = "alias"),
                                @ColumnResult(name = "nombre"),
                                @ColumnResult(name = "descripcion"),
                                @ColumnResult(name = "FlagActivo"),
                                @ColumnResult(name = "rows")
                        }
                )
        }
)

@Entity
@Data
public class Estampa extends AuditingEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ESTAMPAID")
  private Integer estampaid;

  @NotBlank
  @Column(nullable = false , name = "ALIAS")
  private String alias;

  @NotBlank
  @Column(nullable = false , name = "NOMBRE")
  private String nombre;

  @NotBlank
  @Column(nullable = false , name = "DESCRIPCION")
  private String descripcion;

  @Column(nullable = false , name = "PAGINA")
  private Integer pagina;

  @Column(name = "FLAGACTIVO")
  private Boolean FlagActivo;
}
