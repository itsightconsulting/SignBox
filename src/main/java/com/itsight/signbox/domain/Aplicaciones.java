package com.itsight.signbox.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.itsight.signbox.domain.base.AuditingEntity;
import com.itsight.signbox.domain.pojo.AplicacionesPOJO;
import com.itsight.signbox.json.JsonDateSimpleDeserializer;
import com.itsight.signbox.json.JsonDateSimpleSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@SqlResultSetMapping(
        name = "AplicacionesGetAll",
        classes = {
                @ConstructorResult(
                        targetClass = AplicacionesPOJO.class,
                        columns = {
                                @ColumnResult(name = "aplicacionesId"),
                                @ColumnResult(name = "codigo"),
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
@EqualsAndHashCode(callSuper = false)
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

  @Column( nullable = false , name = "FLAGACTIVO")
  private Boolean flagActivo;

  @Column( nullable = false , name = "FLAGELIMINADO" )
  private Boolean flagEliminado;
}
