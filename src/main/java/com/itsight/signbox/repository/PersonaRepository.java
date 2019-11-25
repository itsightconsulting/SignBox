package com.itsight.signbox.repository;

import com.itsight.signbox.domain.Persona;
import com.itsight.signbox.domain.dto.SecurityUserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Transactional
@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {

   Persona findByCorreo(String correo);



}
