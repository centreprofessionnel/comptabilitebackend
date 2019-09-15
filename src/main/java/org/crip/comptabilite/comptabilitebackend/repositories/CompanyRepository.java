package org.crip.comptabilite.comptabilitebackend.repositories;

import org.crip.comptabilite.comptabilitebackend.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
