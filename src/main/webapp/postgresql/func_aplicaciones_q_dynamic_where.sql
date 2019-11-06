CREATE OR REPLACE PROCEDURE FUNC_APLICACIONES_Q_DYNAMIC_WHERE
    (u_Limit INT DEFAULT NULL,
     u_Offset INT DEFAULT 0,
     u_nombre VARCHAR(200) DEFAULT NULL,
     u_flagActivo BOOLEAN DEFAULT NULL
    )
        DYNAMIC RESULT SETS 1
    LANGUAGE SQL
BEGIN

DECLARE cursor1 CURSOR
    WITH RETURN FOR SELECT PR.aplicacionesId,
                   PR.codigo,
                   PR.nombre,
                   PR.descripcion,
                   PR.FlagActivo,
                   PR.rows
            FROM (
					SELECT row_number() OVER ( ORDER BY p.aplicacionesId ) AS RID,
							P.aplicacionesId,
							P.codigo,
							P.nombre,
							P.descripcion,
							P.FlagActivo,
							CAST(COUNT(*) OVER() AS INT) AS rows
					FROM  VQJ33260.aplicaciones AS P
					WHERE ( ( (u_nombre IS NULL) OR  P.codigo LIKE concat(concat('%', trim(u_nombre)) , '%' ) ) OR
                        ( (u_nombre IS NULL) OR  P.nombre LIKE concat(concat('%', trim(u_nombre)) , '%' ) ) ) AND
			            ( (u_flagActivo IS NULL) or P.FlagActivo = u_flagActivo) AND
                        (P.FlagEliminado = 0)
					ORDER BY P.aplicacionesId desc
                 ) PR
            WHERE PR.RID BETWEEN u_Offset AND u_Offset + u_Limit;
OPEN cursor1;
END;

