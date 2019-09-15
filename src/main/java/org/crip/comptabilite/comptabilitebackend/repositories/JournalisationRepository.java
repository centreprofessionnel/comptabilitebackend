package org.crip.comptabilite.comptabilitebackend.repositories;

import org.crip.comptabilite.comptabilitebackend.entities.Journalisation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalisationRepository extends JpaRepository<Journalisation,Long> {
}
