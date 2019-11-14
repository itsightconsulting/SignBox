package com.itsight.signbox.repository;

import com.itsight.signbox.domain.Usuarios;
import org.springframework.data.repository.CrudRepository;

public interface SecurityUserRepository extends CrudRepository<Usuarios , Integer> {

}
