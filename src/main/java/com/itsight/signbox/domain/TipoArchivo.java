package com.itsight.signbox.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.itsight.signbox.domain.base.AuditingEntity;
import com.itsight.signbox.domain.dto.TipoArchivoDTO;
import com.itsight.signbox.domain.pojo.TipoArchivoPOJO;
import com.itsight.signbox.json.JsonDateSimpleDeserializer;
import com.itsight.signbox.json.JsonDateSimpleSerializer;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@SqlResultSetMapping(
        name = "TipoArchivoGetAll",
        classes = {
                @ConstructorResult(
                        targetClass = TipoArchivoPOJO.class,
                        columns = {
                                @ColumnResult(name = "tipoArchivoId"),
                                @ColumnResult(name = "CodigoArchivo"),
                                @ColumnResult(name = "Extensiones"),
                                @ColumnResult(name = "Descripcion"),
                                @ColumnResult(name = "IdFormatoFirma"),
                                @ColumnResult(name = "Detalle"),
                                @ColumnResult(name = "flagActivo"),
                                @ColumnResult(name = "rows")
                        }
                )
        }
)

@Entity
@Data
@Table(name = "TIPOARCHIVO")
public class TipoArchivo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TIPOARCHIVOID")
  private Integer tipoArchivoId;

  @NotBlank
  @Column(nullable = false , name = "CODIGOARCHIVO")
  private String codigoArchivo;

  @NotBlank
  @Column(nullable = false , name = "EXTENSIONES")
  private String extensiones;

  @NotBlank
  @Column(nullable = false , name = "DESCRIPCION")
  private String descripcion;

  @NotNull(message = "Por favor elija un tipo")
  @Column(name = "IDFORMATOFIRMA")
  private Integer idFormatoFirma;

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

  public TipoArchivo(){

  }

}
