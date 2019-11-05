package com.itsight.signbox.service;

import com.itsight.signbox.domain.Usuarios;
import com.itsight.signbox.generic.BaseService;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioService extends BaseService<Usuarios, Integer> {


    boolean existsUsernameLike(String username, int modoAcceso);


    boolean existsDNILike(String dni, Integer userId);


    boolean existsCorreoElectronicoLike(String correoElectronico);

}
