package com.itsight.signbox.repository;

import com.itsight.signbox.domain.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuarios, Integer> {

    @Query("select case when count(u)> 0 then true else false end from Usuarios u where lower(u.nombreUsuario) like lower(?1) and u.modoAccesoId = ?2")
    boolean existsUsernameLike(String username, int modoAcceso);


    @Query("select case when count(u)> 0 then true else false end from Usuarios u where lower(u.dni) like lower(?1)" +
            " and ((?2 is null or u.usuarioId <> ?2))")
    boolean existsDNILike(String dni, Integer userId);


    @Query("select case when count(u)> 0 then true else false end from Usuarios u where lower(u.correoElectronico) like lower(?1)")
    boolean existsCorreoElectronicoLike(String correoElectronico);





}
