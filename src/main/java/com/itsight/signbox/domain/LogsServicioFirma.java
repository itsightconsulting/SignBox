package com.itsight.signbox.domain;

import com.itsight.signbox.domain.pojo.LogsServicioFirmaPOJO;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@SqlResultSetMapping(
        name = "LogsGetAll",
        classes = {
                @ConstructorResult(
                        targetClass = LogsServicioFirmaPOJO.class,
                        columns = {
                                @ColumnResult(name = "logEjecucionId"),
                                @ColumnResult(name = "idTransaccion"),
                                @ColumnResult(name = "tipoDocumento"),
                                @ColumnResult(name = "documento"),
                                @ColumnResult(name = "nombreArchivo"),
                                @ColumnResult(name = "tipoFirma"),
                                @ColumnResult(name = "tipoEjecucion"),
                                @ColumnResult(name = "fechaInicioToString"),
                                @ColumnResult(name = "fechaFinToString"),
                                @ColumnResult(name = "numeroCuenta"),
                                @ColumnResult(name = "rows")
                        }

                )
        }
)
@Table(name = "LOGEJECUCION")
@Entity
@Data
public class LogsServicioFirma  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOGEJECUCIONID")
    private Integer LogEjecucionId 		;

    @Column(name = "IDTRANSACCION")
    private String IdTransaccion     ;

    @Column(name = "NOMBREARCHIVO")
    private String NombreArchivo     ;

    @Column(name = "IDTIPOFIRMA")
    private Integer IdTipoFirma          ;

    @Column(name = "FECHAINICIO")
    private Date FechaInicio     ;

    @Column(name = "FECHAFIN")
    private Date FechaFin        ;

    @Column(name = "FECHAREGISTRO")
    private Date FechaRegistro   ;

    @Column(name = "IDTIPOEJECUCION")
    private Integer IdTipoEjecucion      ;

    @Column(name = "DESCRIPCION")
    private String Descripcion       ;

}
