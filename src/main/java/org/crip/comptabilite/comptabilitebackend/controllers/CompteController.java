package org.crip.comptabilite.comptabilitebackend.controllers;

import org.crip.comptabilite.comptabilitebackend.entities.CategorieCompte;
import org.crip.comptabilite.comptabilitebackend.entities.Company;
import org.crip.comptabilite.comptabilitebackend.entities.Compte;
import org.crip.comptabilite.comptabilitebackend.entities.Operation;
import org.crip.comptabilite.comptabilitebackend.models.ApiResponse;
import org.crip.comptabilite.comptabilitebackend.repositories.CategorieCompteRepository;
import org.crip.comptabilite.comptabilitebackend.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/compte")

public class CompteController {
    @Autowired
    CompteRepository compteRepository;
    @Autowired
    CategorieCompteRepository categorieCompteRepository;
    ApiResponse apiResponse = new ApiResponse();
    @CrossOrigin
    @PostMapping("/Enregistre/{idCatcompt}")
    public ResponseEntity<?> insert(@RequestBody Compte compte,@PathVariable Long idCatcompt){
        apiResponse = new ApiResponse();
        try {
            CategorieCompte categorieCompte = categorieCompteRepository.getOne(idCatcompt);
            compte.setCategorieCompte(categorieCompte);
            compteRepository.save(compte);
            apiResponse.setResponseCode("00");
            apiResponse.setResponseMessage("Enregistrement effectué");
        } catch (Exception ex) {
            apiResponse.setResponseCode("001");
            apiResponse.setResponseMessage(ex.getMessage());
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<?> read(@PathVariable Long id){
        apiResponse=new ApiResponse();
        try {
            apiResponse=new ApiResponse();
            Compte compte=compteRepository.getOne(id);
            apiResponse.setResponseCode("00");
        }catch (Exception ex){
            apiResponse.setResponseCode("001");
            apiResponse.setResponseMessage(ex.getMessage());
        }
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @GetMapping("/Afficher-tous")
    public ResponseEntity<?> readAll(){
        apiResponse=new ApiResponse();
        try {
            List<Compte> comptes=compteRepository.findAll();
            apiResponse.setResponseCode("00");
            apiResponse.setData(comptes);
        } catch (Exception ex){
            apiResponse.setResponseCode("001");
            apiResponse.setResponseMessage(ex.getMessage());
        }
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Compte compte){
        apiResponse=new ApiResponse();
        try {
            Compte currentCompte=compteRepository.getOne(id);
            currentCompte.setIntitule(compte.getIntitule());
            compteRepository.save(currentCompte);
            apiResponse.setResponseCode("00");
        }catch (Exception ex){
            apiResponse.setResponseCode("001");
            apiResponse.setResponseMessage(ex.getMessage());
        }

        return new ResponseEntity<>("Mise a jour effectue",HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        apiResponse = new ApiResponse();
        try {
            compteRepository.deleteById(id);
            apiResponse.setResponseCode("00");
            apiResponse.setResponseMessage("Suppression effectue");
        } catch (Exception ex){
            apiResponse.setResponseCode("01");
            apiResponse.setResponseMessage(ex.getMessage());
        }
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}

