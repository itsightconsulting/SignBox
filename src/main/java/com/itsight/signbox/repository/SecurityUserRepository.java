package com.itsight.signbox.repository;

import com.itsight.signbox.domain.Usuarios;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface SecurityUserRepository extends CrudRepository<Usuarios , Integer> {

}
