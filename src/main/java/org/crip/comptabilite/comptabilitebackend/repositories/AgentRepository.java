package org.crip.comptabilite.comptabilitebackend.repositories;

import org.crip.comptabilite.comptabilitebackend.entities.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent,Long> {
}
