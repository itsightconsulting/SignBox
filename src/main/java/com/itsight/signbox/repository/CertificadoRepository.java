package com.itsight.signbox.repository;

import com.itsight.signbox.domain.Certificados;
import org.hibernate.validator.internal.engine.resolver.JPATraversableResolver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificadoRepository extends JpaRepository<Certificados, Integer> {

    @Query("select case when count(c)> 0 then true else false end from Certificados c where lower(c.alias) like lower(:alias)")
    boolean existsAliasLike(String alias);


}
