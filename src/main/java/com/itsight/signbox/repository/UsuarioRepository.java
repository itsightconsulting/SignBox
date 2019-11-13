package com.itsight.signbox.repository;

import com.itsight.signbox.domain.Usuarios;
import com.itsight.signbox.domain.dto.SecurityUserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuarios, Integer> {


    @Query(nativeQuery = true)
    SecurityUserDTO findByNombreUsuario(@Param("nombreUsuario") String nombreUsuario);

    @Query(nativeQuery = true)
    SecurityUserDTO getForCookieById(Integer usuarioId);

    @Query("select case when count(u)> 0 then false else true end from Usuarios u where lower(u.nombreUsuario) like lower(?1) and u.modoAccesoId = ?2")
    boolean isUsernameValid(String username, int modoAcceso);


    @Query("select case when count(u)> 0 then false else true end from Usuarios u where lower(u.dni) like lower(?1)" +
            " and ((?2 is null or u.usuarioId <> ?2))")
    boolean isDNIValid(String dni, Integer userId);


    @Query("select case when count(u)> 0 then false else true end from Usuarios u where lower(u.correoElectronico) like lower(?1)" +
            " and ((?2 is null or u.usuarioId <> ?2)) ")
    boolean isEmailValid(String email, Integer userId);


}
