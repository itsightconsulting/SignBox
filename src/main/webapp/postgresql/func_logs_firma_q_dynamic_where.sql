
CREATE OR REPLACE PROCEDURE VQJ33260.func_logs_firma_q_dynamic_where
(u_Limit INT DEFAULT NULL,
 u_Offset INT DEFAULT 0,
 u_archivo VARCHAR(200),
 u_fechaI VARCHAR(200) DEFAULT NULL,
 u_fechaF VARCHAR(200) DEFAULT NULL,
 u_transaccion VARCHAR(200) DEFAULT NULL,
 u_tipo VARCHAR(200) DEFAULT NULL,
 u_documento VARCHAR(200),
 u_cuenta VARCHAR(200))

    DYNAMIC RESULT SETS 1
    LANGUAGE SQL
BEGIN

    DECLARE cursor1 CURSOR
        WITH RETURN FOR SELECT PR.LogEjecucionId logEjecucionId,
                               PR.IdTransaccion idTransaccion,
                               PR.TipoDocumento tipoDocumento,
                               PR.Documento documento,
                               PR.NombreArchivo nombreArchivo,
                               PR.TipoFirma tipoFirma,
                               PR.TipoEjecucion tipoEjecucion,
                               PR.FechaInicioToString fechaInicioToString,
                               PR.FechaFinToString fechaFinToString,
                               PR.NumeroCuenta numeroCuenta,
                               PR.rows
                        FROM (
                                 SELECT row_number() OVER ( ORDER BY p.LogEjecucionId ) AS RID,
                                        P.LogEjecucionId,
                                        P.IdTransaccion,
                                        F.TipoDocumento,
                                        F.NUMERODOCUMENTO AS Documento,
                                        P.NombreArchivo,
                                        TF.Detalle AS TipoFirma,
                                        TE.Descripcion as TipoEjecucion,
                                        P.FechaInicio as FechaInicioToString,
                                        P.FechaFin as FechaFinToString,
                                        F.NumeroCuenta,
                                        CAST(COUNT(*) OVER() AS INT) AS rows
                                 FROM  "VQJ33260"."LOGEJECUCION" AS P
                                           INNER JOIN "VQJ33260"."TIPOFIRMA" as TF ON P.IdTipoFirma = TF.TipoFirmaId
                                           INNER JOIN "VQJ33260"."TIPOEJECUCION" as TE ON P.IdTipoEjecucion = TE.TipoEjecucionId
                                           INNER JOIN "VQJ33260"."FIRMALINEA" as F ON P.IdTransaccion = F.IdTransaccion
                                 WHERE 	((u_archivo IS NULL) OR  P.NombreArchivo LIKE concat(concat('%', trim(u_archivo)) , '%' ) ) AND
                                     (u_fechaI IS NULL OR  P.FechaInicio >= to_date(u_fechaI, 'DD/MM/YYYY')) AND
                                     (u_fechaF IS NULL OR  P.FechaFin <= to_date(u_fechaF, 'DD/MM/YYYY')) AND
                                     (u_transaccion IS NULL OR  P.IdTransaccion  LIKE concat(concat('%', trim(u_transaccion)) , '%' )) AND
                                     (u_tipo  IS NULL OR  F.TipoDocumento = u_tipo) AND
                                     (u_documento IS NULL OR  F.NumeroDocumento LIKE concat(concat('%', trim(u_documento)) , '%' ) ) AND
                                     (u_cuenta IS NULL OR  F.NumeroCuenta LIKE concat(concat('%', trim(u_cuenta)) , '%' ) )
                                 ORDER BY P.FechaInicio desc
                             ) PR
                        WHERE (u_Limit IS NULL ) or   (PR.RID BETWEEN u_Offset + 1 AND u_Offset + u_Limit);
    OPEN cursor1;
END