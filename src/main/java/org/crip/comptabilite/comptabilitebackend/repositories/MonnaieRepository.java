package org.crip.comptabilite.comptabilitebackend.repositories;

import org.crip.comptabilite.comptabilitebackend.entities.Monnaie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonnaieRepository extends JpaRepository<Monnaie,Long> {
}
