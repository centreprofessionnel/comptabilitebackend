package org.crip.comptabilite.comptabilitebackend.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "CATEGORIECOMPTE")
@JsonIgnoreProperties({"hybernateLazyInitializer","handler"})

public class CategorieCompte {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  String designation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
