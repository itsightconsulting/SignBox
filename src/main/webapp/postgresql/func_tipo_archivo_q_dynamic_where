CREATE OR REPLACE FUNCTION func_tipo_archivo_q_dynamic_where (
    u_Limit          INT DEFAULT NULL ,
    u_Offset         INT DEFAULT 0)
    RETURNS TABLE ( tipoArchivoId INT,
                    CodigoArchivo VARCHAR(200),
                    Extensiones VARCHAR(200),
                    Descripcion VARCHAR(200),
                    IdFormatoFirma INT,
                    TipoFirmaToString VARCHAR(200),
                    rows INT)
    LANGUAGE SQL
BEGIN ATOMIC
RETURN
    SELECT PR.TIPOARCHIVOID,
           PR.CODIGOARCHIVO,
           PR.EXTENSIONES,
           PR.DESCRIPCION,
           PR.IDFORMATOFIRMA,
           PR.ROWS
    FROM (
             SELECT row_number() OVER ( ORDER BY p.TIPOARCHIVOID ) AS RID,
                    P.TIPOARCHIVOID,
                    P.CODIGOARCHIVO,
                    P.EXTENSIONES,
                    P.DESCRIPCION,
                    P.IDFORMATOFIRMA,
                    CAST(COUNT(*) OVER() AS INT) AS ROWS
             FROM  TIPOARCHIVO AS P
             ORDER BY P.TIPOARCHIVOID desc
         ) PR
    WHERE PR.RID BETWEEN u_Offset AND u_Offset + u_Limit;
END;


