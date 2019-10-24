package com.itsight.signbox.repository;

import com.itsight.signbox.domain.TipoArchivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoArchivoRepository extends JpaRepository<TipoArchivo, Integer> {
}
