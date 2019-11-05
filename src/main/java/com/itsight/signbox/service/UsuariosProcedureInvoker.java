package com.itsight.signbox.service;

import com.itsight.signbox.domain.pojo.CertificadosPOJO;
import com.itsight.signbox.domain.pojo.UsuariosPOJO;
import com.itsight.signbox.domain.query.CertificadosQueryDTO;
import com.itsight.signbox.domain.query.UsuariosQueryDTO;

import java.util.List;

public interface UsuariosProcedureInvoker {

    List<UsuariosPOJO> getUsuarios(UsuariosQueryDTO usuariosQueryDTO);

}
