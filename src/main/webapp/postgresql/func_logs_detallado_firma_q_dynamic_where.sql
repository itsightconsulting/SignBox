
CREATE OR REPLACE PROCEDURE VQJ33260.func_logs_detallado_firma_q_dynamic_where
(u_limit INT DEFAULT NULL,
 u_offset INT DEFAULT 0,
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
        WITH RETURN FOR  SELECT
                             LFD.logsFirmaId,
                             LFD.numeroCuenta,
                             LFD.idTransaccion,
                             LFD.tipoDocumento,
                             LFD.numeroDocumento,
                             LFD.codigoAplicacion,
                             LFD.certificado,
                             LFD.fechaEvento,
                             LFD.tipoEvento,
                             LFD.tipoFirma,
                             LFD.resumen,
                             LFD.detalle,
                             LFD.idRetorno,
                             LFD.detalleRetorno,
                             LFD.descripcionRetorno,
                             LFD.rows

                         FROM (
                                  SELECT row_number() OVER (ORDER BY FL.FIRMALINEAID) AS RID,
                                         LF.LOGSFIRMAID logsFirmaId,
                                         FL.NUMEROCUENTA numeroCuenta,
                                         FL.IDTRANSACCION idTransaccion,
                                         FL.TIPODOCUMENTO tipoDocumento,
                                         FL.NUMERODOCUMENTO numeroDocumento,
                                         FL.CODIGOAPLICACION codigoAplicacion,
                                         FL.CERTIFICADO certificado,
                                         LF.FECHAOPERACION fechaEvento,
                                         TE.DESCRIPCION tipoEvento,
                                         FL.TIPOFIRMA tipoFirma,
                                         LF.RESUMEN resumen,
                                         LF.DETALLE detalle,
                                         LF.IDRETORNO idRetorno,
                                         CR.DETALLE detalleRetorno,
                                         CR.DESCRIPCION descripcionRetorno,
                                         CAST(COUNT(*) OVER() AS INT) AS rows
                                  FROM  "VQJ33260"."FIRMALINEA" AS FL
                                            INNER JOIN "VQJ33260"."LOGSFIRMA" as LF ON FL.IDTRANSACCION = LF.IDTRANSACCION
                                            INNER JOIN "VQJ33260"."TIPOEVENTO" as TE ON LF.TIPOEVENTO = TE.TIPOEVENTOID
                                            INNER JOIN "VQJ33260"."CERTIFICADOS" as  C ON FL.CERTIFICADO = C.CERTIFICADOSID
                                            INNER JOIN "VQJ33260"."CODIGORETORNO" as CR ON LF.IDRETORNO = CR.IDRETORNO
                                WHERE
                                     (u_fechaI IS NULL OR  FL.FechaInicio >= to_date(u_fechaI, 'DD/MM/YYYY')) AND
                                     (u_fechaF IS NULL OR  FL.FechaFin <= to_date(u_fechaF, 'DD/MM/YYYY')) AND
                                     (u_transaccion IS NULL OR  FL.IdTransaccion  LIKE concat(concat('%', trim(u_transaccion)) , '%' )) AND
                                     (u_tipo  IS NULL OR  FL.TipoDocumento = u_tipo) AND
                                     (u_documento IS NULL OR  FL.NumeroDocumento LIKE concat(concat('%', trim(u_documento)) , '%' ) ) AND
                                     (u_cuenta IS NULL OR  FL.NumeroCuenta LIKE concat(concat('%', trim(u_cuenta)) , '%' ) )
                                 ORDER BY FL.FechaInicio desc
                             ) LFD
                        WHERE LFD.RID BETWEEN u_Offset + 1 AND u_Offset + u_Limit;
    OPEN cursor1;
END