CREATE OR REPLACE PROCEDURE func_logs_admin_q_dynamic_where (
    u_Limit          INT DEFAULT NULL ,
    u_Offset         INT DEFAULT 0,
    u_entidad     VARCHAR(25) DEFAULT NULL,
    u_fechaInicio        VARCHAR(50)DEFAULT NULL,
    u_fechaFin     VARCHAR(50) DEFAULT NULL)
    DYNAMIC RESULT SETS 1
    LANGUAGE SQL
BEGIN

    DECLARE cursor1 CURSOR
WITH RETURN FOR
    SELECT    LG.LOGSID id,
              LG.IDENTIFICADOR identificador,
              LG.ACCION accion,
              LG.ENTIDAD entidad,
              LG.IDASOCIADO idAsociado,
              LG.CAMPO campo,
              LG.VALORANTERIOR valorAnterior,
              LG.VALORNUEVO valorNuevo,
              LG.USUARIO usuario,
              LG.FECHAHORA fechaHora,
              LG.ROWS rows
    FROM (
             SELECT row_number() OVER ( ORDER BY L.LOGSID ) AS LID,
                    L.LOGSID,
                    L.IDENTIFICADOR,
                    L.ACCION,
                    L.ENTIDAD,
                    L.IDASOCIADO,
                    L.CAMPO,
                    L.VALORANTERIOR,
                    L.VALORNUEVO,
                    L.USUARIO,
                    L.FECHAHORA ,
                    CAST(COUNT(*) OVER() AS INT) AS ROWS
             FROM  LOGS AS L
             WHERE ((u_entidad IS NULL) OR  LOWER(L.ENTIDAD) LIKE LOWER(concat(concat('%', u_entidad) , '%' ) )) AND
                   ( (u_fechaInicio IS NULL ) OR L.FECHAHORA > to_date(u_fechaInicio, 'DD/MM/YYYY') )  AND
                   ( (u_fechaFin IS NULL) OR  L.FECHAHORA < to_date(u_fechaFin, 'DD/MM/YYYY'))
             ORDER BY L.LOGSID DESC
         ) LG
    WHERE LG.LID BETWEEN u_Offset AND u_Offset + u_Limit;
  OPEN cursor1;
END;



