package com.itsight.signbox.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.itsight.signbox.domain.pojo.LogsPortalPOJO;
import com.itsight.signbox.json.JsonDateSimpleDeserializer;
import com.itsight.signbox.json.JsonDateSimpleSerializer;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@SqlResultSetMapping(
        name = "LogsAdminGetAll",
        classes = {
                @ConstructorResult(
                        targetClass = LogsPortalPOJO.class,
                        columns = {
                                @ColumnResult(name = "id"),
                                @ColumnResult(name = "identificador"),
                                @ColumnResult(name = "accion"),
                                @ColumnResult(name = "entidad"),
                                @ColumnResult(name = "idAsociado"),
                                @ColumnResult(name = "campo"),
                                @ColumnResult(name = "valorAnterior"),
                                @ColumnResult(name = "valorNuevo"),
                                @ColumnResult(name = "usuario"),
                                @ColumnResult(name = "fechaHora"),
                                @ColumnResult(name = "rows")
                        }

                )
        }
)
@Table(name = "LOGS")
@Entity
@Data
public class LogsPortal implements Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOGSID")
    private Integer id;

    @NotBlank
    @Column(nullable = false , name = "ACCION")
    private String accion;


    @NotBlank
    @Column(nullable = false , name = "ENTIDAD")
    private String entidad;



    @NotBlank
    @Column(nullable = false , name = "IDASOCIADO")
    private String idAsociado;


    @NotBlank
    @Column(nullable = false , name ="CAMPO")
    private String campo;


    @Column(name ="VALORANTERIOR")
    private String valorAnterior;


    @Column(name ="VALORNUEVO")
    private String valorNuevo;

    @NotBlank
    @Column(nullable = false , name ="USUARIO")
    private String usuario;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false , name ="FECHAHORA")
    @JsonSerialize(using = JsonDateSimpleSerializer.class)
    @JsonDeserialize(using = JsonDateSimpleDeserializer.class)
    private Date fechaHora;

    private Integer rows;



    @NotBlank
    @Column(nullable = false , name = "IDENTIFICADOR")
    private String identificador;


}
