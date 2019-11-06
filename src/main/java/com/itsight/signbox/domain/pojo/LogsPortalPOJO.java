package com.itsight.signbox.domain.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.itsight.signbox.json.JsonDateSimpleDeserializer;
import com.itsight.signbox.json.JsonDateSimpleSerializer;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Data
public class LogsPortalPOJO implements Serializable {

  private Integer id;

  private String identificador;

  private String accion;

  private String entidad;

  private String idAsociado;

  private String campo;

  private String valorAnterior;

  private String valorNuevo;

  private String usuario;

  @Temporal(TemporalType.TIMESTAMP)
  @JsonSerialize(using = JsonDateSimpleSerializer.class)
  @JsonDeserialize(using = JsonDateSimpleDeserializer.class)
  private Date fechaHora;

  private Integer rows;

  public LogsPortalPOJO(Integer id, String identificador, String accion, String entidad, String idAsociado, String campo, String valorAnterior, String valorNuevo, String usuario, Date fechaHora, Integer rows) {
     this.id = id;
     this.identificador = identificador;
     this.accion = accion;
     this.entidad = entidad;
     this.idAsociado = idAsociado;
     this.campo = campo;
     this.valorAnterior = valorAnterior;
     this.valorNuevo = valorNuevo;
     this.usuario = usuario;
     this.fechaHora = fechaHora;
     this.rows = rows;
  }


}
