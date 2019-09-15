package org.crip.comptabilite.comptabilitebackend.controllers;
import org.crip.comptabilite.comptabilitebackend.entities.CategorieCompte;
import org.crip.comptabilite.comptabilitebackend.entities.Monnaie;
import org.crip.comptabilite.comptabilitebackend.models.ApiResponse;
import org.crip.comptabilite.comptabilitebackend.repositories.MonnaieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/monnaie")
@CrossOrigin(origins = "*")

public class MonnaieController {
    @Autowired
    MonnaieRepository monnaieRepository;
    ApiResponse apiResponse = new ApiResponse();

    @CrossOrigin
    @PostMapping("/Enregistrer")
    public ResponseEntity<?> insert(@RequestBody Monnaie monnaie) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            monnaieRepository.save(monnaie);
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
            Monnaie categorieCompte=monnaieRepository.getOne(id);
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
            List<Monnaie> monnaies=monnaieRepository.findAll();
            apiResponse.setResponseCode("00");
            apiResponse.setData(monnaies);
        } catch (Exception ex){
            apiResponse.setResponseCode("001");
            apiResponse.setResponseMessage(ex.getMessage());
        }
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Monnaie monnaie){
        apiResponse=new ApiResponse();
        try {
            Monnaie currentMonnaie=monnaieRepository.getOne(id);
            currentMonnaie.setDesignation(monnaie.getDesignation());
            monnaieRepository.save(currentMonnaie);
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
            monnaieRepository.deleteById(id);
            apiResponse.setResponseCode("00");
            apiResponse.setResponseMessage("Suppression effectue");
        } catch (Exception ex){
            apiResponse.setResponseCode("01");
            apiResponse.setResponseMessage(ex.getMessage());
        }
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}
