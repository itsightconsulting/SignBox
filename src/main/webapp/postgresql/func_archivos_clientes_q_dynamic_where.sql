CREATE OR REPLACE PROCEDURE func_archivos_clientes_q_dynamic_where(
    u_Limit          INT DEFAULT NULL ,
    u_Offset         INT DEFAULT 0,
    u_Documento     VARCHAR(13) DEFAULT NULL
)
    DYNAMIC RESULT SETS 1
    LANGUAGE SQL
BEGIN
    DECLARE cursor1 CURSOR
        WITH RETURN FOR
        SELECT  FLR.RUTAARCHIVOFINAL rutaArchivoFinal,
                FLR.NOMBREARCHIVO nombreArchivo,
                FLR.NEGOCIO negocio,
                FLR.NUMEROCUENTA numeroCuenta,
                FLR.ROWS rows
        FROM (
                 SELECT row_number() OVER (ORDER BY FL.FIRMALINEAID) AS RID,
                        FL.RUTAARCHIVOFINAL,
                        FL.NOMBREARCHIVO,
                        FL.NEGOCIO,
                        FL.NUMEROCUENTA,
                        CAST(COUNT(*) OVER() AS INT) AS rows
                 FROM  "VQJ33260"."FIRMALINEA" AS FL
                 WHERE FL.NUMERODOCUMENTO = u_Documento
                 ORDER BY FL.FIRMALINEAID asc
             ) FLR
        WHERE (u_Limit IS NULL ) or   (FLR.RID BETWEEN u_Offset + 1 AND u_Offset + u_Limit);
    OPEN cursor1;
END;