CREATE OR REPLACE PROCEDURE func_usuarios_q_dynamic_where (
    u_Limit          INT DEFAULT NULL ,
    u_Offset         INT DEFAULT 0,
    u_documentoId    VARCHAR(15) DEFAULT NULL,
    u_perfilId       INT DEFAULT NULL,
    u_flagActivo     BOOLEAN DEFAULT NULL)
    DYNAMIC RESULT SETS 1
    LANGUAGE SQL
BEGIN

    DECLARE cursor1 CURSOR
WITH RETURN FOR
    SELECT     US.USUARIOID id,
               US.NOMBRES nombres,
               US.DNI dni,
               US.DESCRIPCION perfilNombre,
               US.NOMBREUSUARIO nombreUsuario,
               US.FLAGACTIVO flagActivo,
               US.ROWS rows
    FROM (
             SELECT row_number() OVER ( ORDER BY U.USUARIOID ) AS UID,
                    U.USUARIOID,
                    (U.NOMBRES concat ' ' concat  U.PATERNO concat ' ' concat U.MATERNO) AS NOMBRES,
                    U.DNI,
                    P.DESCRIPCION,
                    U.NOMBREUSUARIO,
                    U.FLAGACTIVO,
                    CAST(COUNT(*) OVER() AS INT) AS ROWS
             FROM  USUARIOS AS U
             INNER JOIN PERFIL P on U.PERFILID = P.PERFILID
             WHERE ((u_documentoId IS NULL) OR  LOWER(U.DNI) LIKE LOWER(concat(concat('%', u_documentoId) , '%' ) )) AND
                   ((u_perfilId IS NULL) OR  U.PERFILID = u_perfilId)  AND
                   (u_flagActivo IS NULL OR U.FLAGACTIVO = u_flagActivo)
             ORDER BY U.USUARIOID ASC
         ) US
    WHERE US.UID BETWEEN u_Offset AND u_Offset + u_Limit;
  OPEN cursor1;
END;


