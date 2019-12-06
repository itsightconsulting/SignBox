package com.itsight.signbox.repository;

import com.itsight.signbox.domain.Parametro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParametroRepository extends JpaRepository<Parametro, Integer> {

    List<Parametro> findByNombre(String nombre);
}
