package org.crip.comptabilite.comptabilitebackend.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.crip.comptabilite.comptabilitebackend.repositories.CategorieCompteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity(name = "COMPTE")
@JsonIgnoreProperties({"hybernateLazyInitializer","handler"})
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String intitule;
    @ManyToOne
    private CategorieCompte categorieCompte;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public CategorieCompte getCategorieCompte() {
        return categorieCompte;
    }

    public void setCategorieCompte(CategorieCompte categorieCompte) {
        this.categorieCompte = categorieCompte;
    }
}
