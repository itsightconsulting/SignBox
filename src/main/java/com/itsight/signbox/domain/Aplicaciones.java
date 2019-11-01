package com.itsight.signbox.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.itsight.signbox.domain.base.AuditingEntity;
import com.itsight.signbox.domain.pojo.AplicacionesPOJO;
import com.itsight.signbox.json.JsonDateSimpleDeserializer;
import com.itsight.signbox.json.JsonDateSimpleSerializer;
import lombok.Data;

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
                                @ColumnResult(name = "descripcion"),
                                @ColumnResult(name = "FlagActivo"),
                                @ColumnResult(name = "rows")
                        }
                )
        }
)

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

  @Column(name = "FLAGACTIVO")
  private Boolean FlagActivo;

  @Column(name = "FLAGELIMINADO")
  private Boolean FlagEliminado;

  @Column(name = "CREADOPOR")
  private String CreadoPor;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "FECHACREACION")
  @JsonSerialize(using = JsonDateSimpleSerializer.class)
  @JsonDeserialize(using = JsonDateSimpleDeserializer.class)
  private Date fechaCreacion;

  @Column(name = "MODIFICADOPOR")
  private String modificadoPor;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "FECHAMODIFICACION")
  @JsonSerialize(using = JsonDateSimpleSerializer.class)
  @JsonDeserialize(using = JsonDateSimpleDeserializer.class)
  private Date fechaModificacion;
}
