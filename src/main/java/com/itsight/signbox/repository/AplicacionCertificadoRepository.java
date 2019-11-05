package com.itsight.signbox.repository;

import com.itsight.signbox.domain.AplicacionCertificado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AplicacionCertificadoRepository  extends JpaRepository<AplicacionCertificado, Integer> {
}
