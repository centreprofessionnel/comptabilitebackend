package org.crip.comptabilite.comptabilitebackend.repositories;

import org.crip.comptabilite.comptabilitebackend.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, Long> {
}
