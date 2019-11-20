CREATE OR REPLACE PROCEDURE VQJ33260.func_logs_trazabilidad_firma_q_dynamic_where
(u_Limit INT DEFAULT NULL,
 u_Offset INT DEFAULT 0,
 u_transaccion VARCHAR(200) DEFAULT NULL,
 u_documento VARCHAR(200) DEFAULT NULL,
 u_cuenta VARCHAR(200) DEFAULT NULL)
    DYNAMIC RESULT SETS 1
    LANGUAGE SQL
BEGIN

    DECLARE cursor1 CURSOR
        WITH RETURN FOR SELECT
                               FLT.firmaLineaId,
                               FLT.idTransaccion,
                               FLT.codigoAplicacion,
                               FLT.tipoArchivo,
                               FLT.posFirma,
                               FLT.alias,
                               FLT.fechaInicio,
                               FLT.fechaFin,
                               FLT.codigoRespuesta,
                               FLT.nombreArchivo,
                               FLT.rutaArchivo,
                               FLT.rutaArchivoFinal,
                               FLT.numeroDocumento,
                               FLT.numeroCuenta,
                               FLT.logId,
                               FLT.fechaEvento,
                               FLT.tipoEvento,
                               FLT.resumen,
                               FLT.detalle,
                               FLT.idRetorno,
                               FLT.detalleRetorno,
                               FLT.rows

                        FROM (
                                 SELECT row_number() OVER (ORDER BY FL.FIRMALINEAID) AS RID,
                                        FL.FIRMALINEAID firmaLineaId,
                                        FL.IDTRANSACCION idTransaccion,
                                        FL.CODIGOAPLICACION codigoAplicacion,
                                        FL.TIPOARCHIVO tipoArchivo,
                                        FL.POSFIRMA posFirma,
                                        C.ALIAS alias,
                                        FL.FECHAINICIO fechaInicio,
                                        FL.FECHAFIN fechaFin,
                                        FL.CODIGORESPUESTA codigoRespuesta,
                                        FL.NOMBREARCHIVO nombreArchivo,
                                        FL.RUTAARCHIVO rutaArchivo,
                                        FL.RUTAARCHIVOFINAL rutaArchivoFinal,
                                        FL.NUMERODOCUMENTO numeroDocumento,
                                        FL.NUMEROCUENTA numeroCuenta,
                                        LF.LOGSFIRMAID  logId,
                                        LF.FECHAOPERACION fechaEvento,
                                        TE.DESCRIPCION tipoEvento,
                                        LF.RESUMEN resumen,
                                        LF.DETALLE detalle,
                                        LF.IDRETORNO idRetorno,
                                        CR.DETALLE detalleRetorno,
                                        CAST(COUNT(*) OVER() AS INT) AS rows
        FROM  "VQJ33260"."FIRMALINEA" AS FL
					INNER JOIN "VQJ33260"."LOGSFIRMA" as LF ON FL.IDTRANSACCION = LF.IDTRANSACCION
					    INNER JOIN "VQJ33260"."TIPOEVENTO" as TE ON LF.TIPOEVENTO = TE.TIPOEVENTOID
					INNER JOIN "VQJ33260"."CERTIFICADOS" as  C ON FL.CERTIFICADO = C.CERTIFICADOSID
                    INNER JOIN "VQJ33260"."CODIGORETORNO" as CR ON LF.IDRETORNO = CR.IDRETORNO
					WHERE 	(u_transaccion IS NULL OR  FL.IdTransaccion  LIKE concat(concat('%', trim(u_transaccion)) , '%' )) AND
    						(u_documento IS NULL OR  FL.NUMERODOCUMENTO LIKE concat(concat('%', trim(u_documento)) , '%' ) ) AND
							(u_cuenta IS NULL OR  FL.NUMEROCUENTA LIKE concat(concat('%', trim(u_cuenta)) , '%' ) )
					ORDER BY FL.FIRMALINEAID asc
                ) FLT
  WHERE (u_Limit IS NULL ) or   (FLT.RID BETWEEN u_Offset + 1 AND u_Offset + u_Limit);
    OPEN cursor1;
END;

