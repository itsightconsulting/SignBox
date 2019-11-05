package com.itsight.signbox.service;

import com.itsight.signbox.domain.Usuarios;
import com.itsight.signbox.generic.BaseService;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioService extends BaseService<Usuarios, Integer> {


    boolean isUsernameValid(String username, int modoAcceso);


    boolean isDNIValid(String dni, Integer userId);


    boolean isEmailValid(String email, Integer userId);

}
