package com.itsight.signbox.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.itsight.signbox.domain.base.AuditingEntity;
import com.itsight.signbox.domain.pojo.AmbientesPOJO;
import com.itsight.signbox.json.JsonDateSimpleDeserializer;
import com.itsight.signbox.json.JsonDateSimpleSerializer;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@SqlResultSetMapping(
        name = "AmbientesGetAll",
        classes = {
                @ConstructorResult(
                        targetClass = AmbientesPOJO.class,
                        columns = {
                                @ColumnResult(name = "ambientesId"),
                                @ColumnResult(name = "nombre"),
                                @ColumnResult(name = "prefijo"),
                                @ColumnResult(name = "flagActivo"),
                                @ColumnResult(name = "rows")
                        }
                )
        }
)

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

  /*@Ignore
  @OneToMany(mappedBy = "ambienteAplicacion")
  private List<AmbienteAplicacion> ambienteAplicacion;*/

}
