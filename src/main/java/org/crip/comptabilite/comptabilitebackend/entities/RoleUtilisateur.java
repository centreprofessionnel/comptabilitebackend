package org.crip.comptabilite.comptabilitebackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "roleutilisateur")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Data
public class RoleUtilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String role;
}
