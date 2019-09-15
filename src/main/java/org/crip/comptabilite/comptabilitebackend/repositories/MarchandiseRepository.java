package org.crip.comptabilite.comptabilitebackend.repositories;

import org.crip.comptabilite.comptabilitebackend.entities.Marchandise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarchandiseRepository extends JpaRepository<Marchandise,Long> {
}
