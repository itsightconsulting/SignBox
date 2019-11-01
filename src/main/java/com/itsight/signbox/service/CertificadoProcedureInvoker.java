package com.itsight.signbox.service;

import com.itsight.signbox.domain.pojo.CertificadosPOJO;
import com.itsight.signbox.domain.pojo.ParametroPOJO;
import com.itsight.signbox.domain.query.CertificadosQueryDTO;

import java.util.List;

public interface CertificadoProcedureInvoker {

     List<CertificadosPOJO> getCertificados(CertificadosQueryDTO certificadosQueryDTO);

}
