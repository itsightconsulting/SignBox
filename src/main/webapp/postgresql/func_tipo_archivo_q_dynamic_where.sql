CREATE OR REPLACE PROCEDURE FUNC_TIPO_ARCHIVO_Q_DYNAMIC_WHERE (
    u_Limit          INT DEFAULT NULL ,
    u_Offset         INT DEFAULT 0,
    u_nombre         VARCHAR(200),
    u_flagActivo     BOOLEAN,
    u_IDFORMATOFIRMA INT DEFAULT 0,
	u_tipoBusqueda	 VARCHAR(200))
    DYNAMIC RESULT SETS 1
    LANGUAGE SQL
BEGIN


	DECLARE cursor1 CURSOR
		WITH RETURN FOR SELECT PR.TIPOARCHIVOID,
                   PR.CODIGOARCHIVO,
                   PR.EXTENSIONES,
                   PR.DESCRIPCION,
                   PR.IDFORMATOFIRMA,
                   PR.DETALLE,
                   PR.FLAGACTIVO,
                   PR.ROWS
            FROM (
                     SELECT row_number() OVER ( ORDER BY p.TIPOARCHIVOID ) AS RID,
                            P.TIPOARCHIVOID,
                            P.CODIGOARCHIVO,
                            P.EXTENSIONES,
                            P.DESCRIPCION,
                            P.IDFORMATOFIRMA,
                            a.DETALLE,
                            P.FLAGACTIVO,
                            CAST(COUNT(*) OVER() AS INT) AS ROWS
                     FROM  TIPOARCHIVO AS P
                      inner join tipofirma as a on p.IDFORMATOFIRMA = a.tipofirmaid
                      WHERE P.DESCRIPCION LIKE concat(concat('%', trim(u_nombre)) , '%' ) AND
                            (P.IDFORMATOFIRMA = u_IDFORMATOFIRMA OR u_IDFORMATOFIRMA = 0) AND
						(P.FlagEliminado = 0)
                     ORDER BY P.TIPOARCHIVOID asc
                 ) PR
            WHERE PR.RID BETWEEN u_Offset AND u_Offset + u_Limit;
	DECLARE cursor2 CURSOR
		WITH RETURN FOR SELECT PR.TIPOARCHIVOID,
                   PR.CODIGOARCHIVO,
                   PR.EXTENSIONES,
                   PR.DESCRIPCION,
                   PR.IDFORMATOFIRMA,
                   PR.DETALLE,
                   PR.FLAGACTIVO,
                   PR.ROWS
            FROM (
                     SELECT row_number() OVER ( ORDER BY p.TIPOARCHIVOID ) AS RID,
                            P.TIPOARCHIVOID,
                            P.CODIGOARCHIVO,
                            P.EXTENSIONES,
                            P.DESCRIPCION,
                            P.IDFORMATOFIRMA,
                            a.DETALLE,
                            P.FLAGACTIVO,
                            CAST(COUNT(*) OVER() AS INT) AS ROWS
                     FROM  TIPOARCHIVO AS P
                      inner join tipofirma as a on p.IDFORMATOFIRMA = a.tipofirmaid
                      WHERE P.DESCRIPCION LIKE concat(concat('%', trim(u_nombre)) , '%' ) AND
                            P.FLAGACTIVO = u_flagActivo AND
                            (P.IDFORMATOFIRMA = u_IDFORMATOFIRMA OR u_IDFORMATOFIRMA = 0) AND
						(P.FlagEliminado = 0)
                     ORDER BY P.TIPOARCHIVOID asc
                 ) PR
            WHERE PR.RID BETWEEN u_Offset + 1 AND u_Offset + u_Limit;

	IF (u_tipoBusqueda = '') THEN
		OPEN cursor2;
	ELSE
		OPEN cursor1;
	END IF;


END