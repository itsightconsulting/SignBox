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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
                                @ColumnResult(name = "TipoFirmaToString"),
                                @ColumnResult(name = "rows"),
                        }

                )
        }
)
@Entity
@Data
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

  @NotBlank
  @Column(nullable = false , name = "IDFORMATOFIRMA")
  private Integer idFormatoFirma;

  public TipoArchivo(){

  }

}
