package org.crip.comptabilite.comptabilitebackend.controllers;

import org.crip.comptabilite.comptabilitebackend.entities.CategorieCompte;
import org.crip.comptabilite.comptabilitebackend.entities.Company;
import org.crip.comptabilite.comptabilitebackend.entities.Operation;
import org.crip.comptabilite.comptabilitebackend.models.ApiResponse;
import org.crip.comptabilite.comptabilitebackend.repositories.CategorieCompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categorieCompte")
@CrossOrigin(origins = "*")
public class CategorieCompteController {
    @Autowired
    CategorieCompteRepository categorieCompteRepository;
    ApiResponse apiResponse = new ApiResponse();
    @CrossOrigin
    @PostMapping("/Enregistrer")
    public ResponseEntity<?> insert (@RequestBody CategorieCompte categorieCompte){
        apiResponse=new ApiResponse();
        try {
            categorieCompteRepository.save(categorieCompte);
            apiResponse.setResponseCode("00");
            apiResponse.setResponseMessage("Enregistrement effectué");
        } catch (Exception ex){
            apiResponse.setResponseCode("001");
            apiResponse.setResponseMessage(ex.getMessage());
        }

        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<?> read(@PathVariable Long id){
        apiResponse=new ApiResponse();
        try {
            apiResponse=new ApiResponse();
            CategorieCompte categorieCompte=categorieCompteRepository.getOne(id);
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
            List<CategorieCompte> categorieComptes=categorieCompteRepository.findAll();
            apiResponse.setResponseCode("00");
            apiResponse.setData(categorieComptes);
        } catch (Exception ex){
            apiResponse.setResponseCode("001");
            apiResponse.setResponseMessage(ex.getMessage());
        }
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody CategorieCompte categorieCompte){
        apiResponse=new ApiResponse();
        try {
            CategorieCompte currentCategorieCompte=categorieCompteRepository.getOne(id);
            currentCategorieCompte.setDesignation(categorieCompte.getDesignation());
            categorieCompteRepository.save(currentCategorieCompte);
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
            categorieCompteRepository.deleteById(id);
            apiResponse.setResponseCode("00");
            apiResponse.setResponseMessage("Suppression effectue");
        } catch (Exception ex){
            apiResponse.setResponseCode("01");
            apiResponse.setResponseMessage(ex.getMessage());
        }
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    }

