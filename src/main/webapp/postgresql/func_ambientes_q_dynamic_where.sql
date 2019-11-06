CREATE OR REPLACE PROCEDURE FUNC_AMBIENTES_Q_DYNAMIC_WHERE (
    u_Limit          INT DEFAULT NULL ,
    u_Offset         INT DEFAULT 0,
    u_nombre         VARCHAR(200),
    u_flagActivo     BOOLEAN,
	u_tipoBusqueda	 VARCHAR(200))
    DYNAMIC RESULT SETS 1
    LANGUAGE SQL
BEGIN

DECLARE cursor1 CURSOR
    WITH RETURN FOR SELECT PR.ambientesId,
                   PR.nombre,
                   PR.prefijo,
                   PR.FlagActivo,
                   PR.ROWS
            FROM (
					SELECT row_number() OVER ( ORDER BY p.ambientesId ) AS RID,
							P.ambientesId,
							P.nombre,
							P.prefijo,
							P.FlagActivo,
							CAST(COUNT(*) OVER() AS INT) AS ROWS
					FROM  VQJ33260.ambientes AS P
					WHERE P.nombre LIKE concat(concat('%', trim(u_nombre)) , '%' ) AND
						(P.FlagEliminado = 0)
					ORDER BY P.ambientesId desc
                 ) PR
            WHERE PR.RID BETWEEN u_Offset AND u_Offset + u_Limit;
DECLARE cursor2 CURSOR
    WITH RETURN FOR SELECT PR.ambientesId,
                   PR.nombre,
                   PR.prefijo,
                   PR.FlagActivo,
                   PR.ROWS
            FROM (
					SELECT row_number() OVER ( ORDER BY p.ambientesId ) AS RID,
							P.ambientesId,
							P.nombre,
							P.prefijo,
							P.FlagActivo,
							CAST(COUNT(*) OVER() AS INT) AS ROWS
					FROM  VQJ33260.ambientes AS P
					WHERE P.nombre LIKE concat(concat('%', trim(u_nombre)) , '%' ) AND
						P.FlagActivo = u_flagActivo AND
						(P.FlagEliminado = 0)
					ORDER BY P.ambientesId desc
                 ) PR
            WHERE PR.RID BETWEEN u_Offset AND u_Offset + u_Limit;

IF (u_tipoBusqueda = '') THEN
    OPEN cursor2;
ELSE
    OPEN cursor1;
END IF;


END