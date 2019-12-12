package com.itsight.signbox.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.itsight.signbox.domain.pojo.*;
import com.itsight.signbox.json.JsonDateSimpleDeserializer;
import com.itsight.signbox.json.JsonDateSimpleSerializer;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@SqlResultSetMappings({
         @SqlResultSetMapping(
            name = "LogsTrazabilidadFirmaGetAll",
            classes = {
                  @ConstructorResult(
                          targetClass = LogsTrazabilidadFirmaPOJO.class,
                          columns = {

                                  @ColumnResult(name = "firmaLineaId"),
                                  @ColumnResult(name = "idTransaccion"),
                                  @ColumnResult(name = "codigoAplicacion"),
                                  @ColumnResult(name = "tipoArchivo"),
                                  @ColumnResult(name = "posFirma"),
                                  @ColumnResult(name = "alias"),
                                  @ColumnResult(name = "fechaInicio"),
                                  @ColumnResult(name = "fechaFin"),
                                  @ColumnResult(name = "codigoRespuesta"),
                                  @ColumnResult(name = "nombreArchivo"),
                                  @ColumnResult(name = "rutaArchivo"),
                                  @ColumnResult(name = "rutaArchivoFinal"),
                                  @ColumnResult(name = "numeroDocumento"),
                                  @ColumnResult(name = "numeroCuenta"),
                                  @ColumnResult(name = "logId"),
                                  @ColumnResult(name = "fechaEvento"),
                                  @ColumnResult(name = "tipoEvento"),
                                  @ColumnResult(name = "resumen"),
                                  @ColumnResult(name = "detalle"),
                                  @ColumnResult(name = "idRetorno"),
                                  @ColumnResult(name = "detalleRetorno"),
                                  @ColumnResult(name = "rows")
                          }

                  )
          }
),
        @SqlResultSetMapping(
        name = "LogsNotificacionesGetAll",
        classes = {
                @ConstructorResult(
                        targetClass = LogsNotificacionesPOJO.class,
                        columns = {

                                @ColumnResult(name = "logId"),
                                @ColumnResult(name = "numeroCuenta"),
                                @ColumnResult(name = "fechaEvento"),
                                @ColumnResult(name = "tipoEvento"),
                                @ColumnResult(name = "resumen"),
                                @ColumnResult(name = "detalle"),
                                @ColumnResult(name = "idRetorno"),
                                @ColumnResult(name = "detalleRetorno"),
                                @ColumnResult(name = "descripcionRetorno"),
                                @ColumnResult(name = "rows")
                        }

                )
        }
),

        @SqlResultSetMapping(
                name = "LogsDetalladoFirmaGetAll",
                classes = {
                        @ConstructorResult(
                                targetClass = LogsDetalladoFirmaPOJO.class,
                                columns = {
                                        @ColumnResult(name = "logsFirmaId"),
                                        @ColumnResult(name = "numeroCuenta"),
                                        @ColumnResult(name = "idTransaccion"),
                                        @ColumnResult(name = "tipoDocumento"),
                                        @ColumnResult(name = "numeroDocumento"),
                                        @ColumnResult(name = "codigoAplicacion"),
                                        @ColumnResult(name = "certificado"),
                                        @ColumnResult(name = "fechaEvento"),
                                        @ColumnResult(name = "tipoEvento"),
                                        @ColumnResult(name = "tipoFirma"),
                                        @ColumnResult(name = "resumen"),
                                        @ColumnResult(name = "detalle"),
                                        @ColumnResult(name = "idRetorno"),
                                        @ColumnResult(name = "detalleRetorno"),
                                        @ColumnResult(name = "descripcionRetorno"),
                                        @ColumnResult(name = "rows")
                                }

                        )
                })
  })
@Entity
@Data
public class FirmaLinea {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "FIRMALINEAID")
  private Integer firmaLineaId;

  @NotBlank
  @Column(nullable = false , name = "IDTRANSACCION")
  private String idTransaccion;

  @NotBlank
  @Column(nullable = false , name = "MSGID")
  private String msgId;

  @NotBlank
  @Column(nullable = false , name = "TIMESTAMP")
  private String timestamp;

  @NotBlank
  @Column(nullable = false , name = "USERID")
  private String userId;

  @NotBlank
  @Column(nullable = false , name = "ACCEPT")
  private String accept;

  @NotBlank
  @Column(nullable = false , name = "CODIGOAPLICACION")
  private String codigoAplicacion;

  @NotBlank
  @Column(nullable = false , name = "IPAPLICACION")
  private String ipAplicacion;

  @NotBlank
  @Column(nullable = false , name = "PLANTILLABRMS")
  private String plantillaBrms;

  @NotBlank
  @Column(nullable = false , name = "TIPOARCHIVO")
  private String tipoArchivo;

  @NotBlank
  @Column(nullable = false , name = "NUMEROARCHIVO")
  private String numeroArchivo;

  @NotBlank
  @Column(nullable = false , name = "NOMBREARCHIVO")
  private String nombreArchivo;

  @NotBlank
  @Column(nullable = false , name = "POSFIRMA")
  private String posFirma;

  @NotBlank
  @Column(nullable = false , name = "CERTIFICADO")
  private String certificado;

  @NotBlank
  @Column(nullable = false , name = "TIPOOPERACION")
  private String tipoOperacion;

  @NotBlank
  @Column(nullable = false , name = "NEGOCIO")
  private String negocio;

  @NotBlank
  @Column(nullable = false , name = "TIPOCONTRATO")
  private String tipoContrato;

  @NotBlank
  @Column(nullable = false , name = "SEGMENTOOFERTA")
  private String segmentoOferta;

  @NotBlank
  @Column(nullable = false , name = "TIPOFIRMA")
  private String tipoFirma;

  @NotBlank
  @Column(nullable = false , name = "CANALATENCION")
  private String canalAtencion;

  @NotBlank
  @Column(nullable = false , name = "NOMBREPDV")
  private String nombrePdv;

  @NotBlank
  @Column(nullable = false , name = "CODIGOPDV")
  private String codigoPdv;

  @NotBlank
  @Column(nullable = false , name = "ORIGENARCHIVO")
  private String origenArchivo;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "FECHAINICIO", nullable = false)
  @JsonSerialize(using = JsonDateSimpleSerializer.class)
  @JsonDeserialize(using = JsonDateSimpleDeserializer.class)
  private Date fechaInicio;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "FECHAFIN", nullable = false)
  @JsonSerialize(using = JsonDateSimpleSerializer.class)
  @JsonDeserialize(using = JsonDateSimpleDeserializer.class)
  private Date fechaFin;

  @NotBlank
  @Column(nullable = false , name = "CODIGORESPUESTA")
  private Integer codigoRespuesta;

  @NotBlank
  @Column(nullable = false , name = "IDTRABAJOSB")
  private String idTrabajosb;

  @NotBlank
  @Column(nullable = false , name = "IDLINEA")
  private Integer idLinea;

  @NotBlank
  @Column(nullable = false , name = "RUTAARCHIVOFINAL")
  private String rutaArchivoFinal;

  @NotBlank
  @Column(nullable = false , name = "RUTAARCHIVO")
  private String rutaArchivo;

  @Column(nullable = false , name = "TIPOFIRMAID")
  private Integer tipoFirmaId;

  @NotBlank
  @Column(nullable = false , name = "TIPODOCUMENTO")
  private String tipoDocumento;

  @NotBlank
  @Column(nullable = false , name = "NUMERODOCUMENTO")
  private String numeroDocumento;

  @NotBlank
  @Column(nullable = false , name = "NUMEROCUENTA")
  private String numeroCuenta;

}
