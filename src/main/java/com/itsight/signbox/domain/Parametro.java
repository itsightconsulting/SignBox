package com.itsight.signbox.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.itsight.signbox.domain.base.AuditingEntity;
import com.itsight.signbox.domain.dto.ParametroDTO;
import com.itsight.signbox.domain.pojo.ParametroPOJO;
import com.itsight.signbox.json.JsonDateSimpleDeserializer;
import com.itsight.signbox.json.JsonDateSimpleSerializer;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@SqlResultSetMapping(
        name = "ParametroGetAll",
        classes = {
                @ConstructorResult(
                        targetClass = ParametroPOJO.class,
                        columns = {
                                @ColumnResult(name = "id"),
                                @ColumnResult(name = "nombre"),
                                @ColumnResult(name = "valor"),
                                @ColumnResult(name = "descripcion"),
                                @ColumnResult(name = "rows"),
                        }

                )
        }
)
@Entity
@Data
public class Parametro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PARAMETROID")
    private Integer id;

    @NotBlank
    @Column(nullable = false , name = "NOMBRE")
    private String nombre;

    @NotBlank
    @Column(nullable = false, name = "VALOR")
    private String valor;

    @NotBlank
    @Column(nullable = false , name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "MODIFICADOPOR")
    private String modificadoPor;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHAMODIFICACION")
    @JsonSerialize(using = JsonDateSimpleSerializer.class)
    @JsonDeserialize(using = JsonDateSimpleDeserializer.class)
    private Date fechaModificacion;

    public Parametro(){

    }

    public void setParametro(ParametroDTO parametro) {
        this.descripcion = parametro.getDescripcion();
        this.valor = parametro.getValor();
    }

}
