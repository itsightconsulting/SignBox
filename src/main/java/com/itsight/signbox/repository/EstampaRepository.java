package com.itsight.signbox.repository;

import com.itsight.signbox.domain.Estampa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstampaRepository extends JpaRepository<Estampa, Integer>{
}
