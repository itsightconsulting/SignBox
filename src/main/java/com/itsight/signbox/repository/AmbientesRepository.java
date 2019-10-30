package com.itsight.signbox.repository;

import com.itsight.signbox.domain.Ambientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmbientesRepository extends JpaRepository<Ambientes, Integer> {


}
