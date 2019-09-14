package org.crip.comptabilite.comptabilitebackend.controllers;

import org.crip.comptabilite.comptabilitebackend.entities.RoleUtilisateur;
import org.crip.comptabilite.comptabilitebackend.repositories.RoleUtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleUtilisateurController {

    @Autowired
    RoleUtilisateurRepository roleUtilisateurRepository;

    @PostMapping("/enregistrer")
    public ResponseEntity<?> enregistrer(@RequestBody RoleUtilisateur roleUtilisateur){
        roleUtilisateurRepository.save(roleUtilisateur);
        return new ResponseEntity<>("Enregistrement effectué", HttpStatus.OK);
    }
}
