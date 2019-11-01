package com.itsight.signbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.itsight.signbox.domain.AmbienteAplicacion;

@Repository
public interface AmbienteAplicacionRepository extends JpaRepository<AmbienteAplicacion, Integer> {
}
