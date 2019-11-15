package com.itsight.signbox.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.itsight.signbox.domain.base.AuditingEntity;
import com.itsight.signbox.domain.base.MiniAuditingEntity;
import com.itsight.signbox.domain.dto.ParametroDTO;
import com.itsight.signbox.domain.pojo.ParametroPOJO;
import com.itsight.signbox.json.JsonDateSimpleDeserializer;
import com.itsight.signbox.json.JsonDateSimpleSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class Parametro extends MiniAuditingEntity {

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


    public Parametro(){

    }

    public void setParametro(ParametroDTO parametro) {
        this.descripcion = parametro.getDescripcion();
        this.valor = parametro.getValor();
    }

}
