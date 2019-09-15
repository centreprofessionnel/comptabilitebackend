package org.crip.comptabilite.comptabilitebackend.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.crip.comptabilite.comptabilitebackend.repositories.CompteRepository;
import org.crip.comptabilite.comptabilitebackend.repositories.OperationRepository;
import javax.persistence.*;

@Entity(name = "JOURNALISATION")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Journalisation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double montant;
    private double taux;
    private String mouvement;
    @ManyToOne
    private Operation operation;
    @ManyToOne
    private Compte compte;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    public String getMouvement() {
        return mouvement;
    }

    public void setMouvement(String mouvement) {
        this.mouvement = mouvement;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }
}
