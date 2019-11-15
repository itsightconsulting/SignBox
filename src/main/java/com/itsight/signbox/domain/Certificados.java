package com.itsight.signbox.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.itsight.signbox.domain.base.AuditingEntity;
import com.itsight.signbox.domain.pojo.CertificadosPOJO;
import com.itsight.signbox.domain.pojo.ParametroPOJO;
import com.itsight.signbox.json.JsonDateSimpleDeserializer;
import com.itsight.signbox.json.JsonDateSimpleSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;


@SqlResultSetMapping(
        name = "CertificadosGetAll",
        classes = {
                @ConstructorResult(
                        targetClass = CertificadosPOJO.class,
                        columns = {

                                @ColumnResult(name = "id"),
                                @ColumnResult(name = "alias"),
                                @ColumnResult(name = "identificadorHsm"),
                                @ColumnResult(name = "responsable"),
                                @ColumnResult(name = "flagActivo"),
                                @ColumnResult(name = "rows")
                        }

                )
        }
)
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Certificados extends AuditingEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CERTIFICADOSID")
  private Integer certificadosId;

  @NotBlank
  @Column(nullable = false, name = "ALIAS")
  private String alias;

  @NotBlank
  @Column(nullable = false, name = "IDENTIFICADORHSM")
  private String identificadorHsm;

  @NotBlank
  @Column(nullable = false, name = "PINSEGURIDAD")
  private String pinSeguridad;

  @NotBlank
  @Column(nullable = false, name = "RESPONSABLE")
  private String responsable;


  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "FECHACADUCIDAD", nullable = false)
  @JsonSerialize(using = JsonDateSimpleSerializer.class)
  @JsonDeserialize(using = JsonDateSimpleDeserializer.class)
  private Date fechaCaducidad;

  @NotBlank
  @Column(nullable = false , name = "DESCRIPCION")
  private String descripcion;

  @Column(name = "FLAGACTIVO", nullable = false)
  private boolean flagActivo;


  @Column(name = "FLAGELIMINADO", nullable = false)
  private boolean flagEliminado;



  public void setCertificados(Certificados certificados){

    this.descripcion = certificados.getDescripcion();
    this.identificadorHsm = certificados.getIdentificadorHsm();
    this.pinSeguridad = certificados.getPinSeguridad();
    this.fechaCaducidad = certificados.getFechaCaducidad();
    this.responsable = certificados.getResponsable();
  }

}
