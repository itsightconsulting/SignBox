CREATE OR REPLACE PROCEDURE func_logs_notificaciones_q_dynamic_where (
    u_Limit          INT DEFAULT NULL ,
    u_Offset         INT DEFAULT 0,
    u_cuenta     VARCHAR(25) DEFAULT NULL,
    u_fechaInicio        VARCHAR(50)DEFAULT NULL,
    u_fechaFin     VARCHAR(50) DEFAULT NULL)
    DYNAMIC RESULT SETS 1
    LANGUAGE SQL
BEGIN

    DECLARE cursor1 CURSOR
        WITH RETURN FOR
        SELECT    LN.logId,
                  LN.numeroCuenta,
                  LN.fechaEvento,
                  LN.tipoEvento,
                  LN.resumen,
                  LN.detalle,
                  LN.idRetorno,
                  LN.descripcionRetorno,
                  LN.detalleRetorno,
                  LN.ROWS rows
        FROM (
                 SELECT row_number() OVER (ORDER BY FL.FIRMALINEAID) AS RID,
                        LF.LOGSFIRMAID  logId,
                        FL.NUMEROCUENTA numeroCuenta,
                        LF.FECHAOPERACION fechaEvento,
                        TE.DESCRIPCION tipoEvento,
                        LF.RESUMEN resumen,
                        LF.DETALLE detalle,
                        LF.IDRETORNO idRetorno,
                        CR.DESCRIPCION descripcionRetorno,
                        CR.DETALLE detalleRetorno,
                        CAST(COUNT(*) OVER() AS INT) AS rows
                 FROM  "VQJ33260"."FIRMALINEA" AS FL
                           INNER JOIN "VQJ33260"."LOGSFIRMA" as LF ON FL.IDTRANSACCION = LF.IDTRANSACCION
                           INNER JOIN "VQJ33260"."TIPOEVENTO" as TE ON LF.TIPOEVENTO = TE.TIPOEVENTOID
                           INNER JOIN "VQJ33260"."CERTIFICADOS" as  C ON FL.CERTIFICADO = C.CERTIFICADOSID
                           INNER JOIN "VQJ33260"."CODIGORETORNO" as CR ON LF.IDRETORNO = CR.IDRETORNO
                 WHERE ((u_cuenta IS NULL) OR  LOWER(FL.NUMEROCUENTA) LIKE LOWER(concat(concat('%', u_cuenta) , '%' ) )) AND
                     ( (u_fechaInicio IS NULL ) OR LF.FECHAOPERACION > to_date(u_fechaInicio, 'DD/MM/YYYY') )  AND
                     ( (u_fechaFin IS NULL) OR  FL.FECHAFIN < to_date(u_fechaFin, 'DD/MM/YYYY')) AND
                         FL.TIPOFIRMA = 0
                 ORDER BY FL.FIRMALINEAID asc
             ) LN
        WHERE (u_Limit IS NULL ) or   (LN.RID BETWEEN u_Offset + 1 AND u_Offset + u_Limit);
    OPEN cursor1;
END;