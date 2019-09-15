package org.crip.comptabilite.comptabilitebackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity(name = "MARCHANDISE")
@JsonIgnoreProperties({"hiberbanateLazyInitializer", "handler"})
public class Marchandise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String designation;
    private double pu;
    @ManyToOne
    private CategorieMarchandise categorieMarchandise;

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

    public double getPu() {
        return pu;
    }

    public void setPu(double pu) {
        this.pu = pu;
    }

    public CategorieMarchandise getCategorieMarchandise() {
        return categorieMarchandise;
    }

    public void setCategorieMarchandise(CategorieMarchandise categorieMarchandise) {
        this.categorieMarchandise = categorieMarchandise;
    }
}
