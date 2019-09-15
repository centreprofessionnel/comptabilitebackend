package org.crip.comptabilite.comptabilitebackend.controllers;

import org.crip.comptabilite.comptabilitebackend.entities.Compte;
import org.crip.comptabilite.comptabilitebackend.entities.Journalisation;
import org.crip.comptabilite.comptabilitebackend.entities.Operation;
import org.crip.comptabilite.comptabilitebackend.models.ApiResponse;
import org.crip.comptabilite.comptabilitebackend.repositories.CompteRepository;
import org.crip.comptabilite.comptabilitebackend.repositories.JournalisationRepository;
import org.crip.comptabilite.comptabilitebackend.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/journalisation")
@CrossOrigin(origins = "*")
public class JournalisationController {
    @Autowired
    JournalisationRepository journalisationRepository;
    @Autowired
    OperationRepository operationRepository;
    @Autowired
    CompteRepository compteRepository;
    ApiResponse apiResponse=new ApiResponse();
    @CrossOrigin
    @PostMapping("/Enregistrer/{idop}/{idcomp}")

    public ResponseEntity<?> insert(@RequestBody Journalisation journalisation, @PathVariable ("idop") Long idop, @PathVariable ("idcomp") Long idcomp){
        apiResponse=new ApiResponse();
        try {
            Operation operation=operationRepository.getOne(idop);
            journalisation.setOperation(operation);
            Compte compte=compteRepository.getOne(idcomp);
            journalisation.setCompte(compte);
            journalisationRepository.save(journalisation);
            apiResponse.setResponseCode("00");
            apiResponse.setResponseMessage("Enregistrement effectué");
        }catch (Exception ex){
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
            Journalisation journalisation=journalisationRepository.getOne(id);
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
            List<Journalisation> journalisations=journalisationRepository.findAll();
            apiResponse.setResponseCode("00");
            apiResponse.setData(journalisations);
        } catch (Exception ex){
            apiResponse.setResponseCode("001");
            apiResponse.setResponseMessage(ex.getMessage());
        }
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Journalisation journalisation){
        apiResponse=new ApiResponse();
        try {
            Journalisation currentJournalisation=journalisationRepository.getOne(id);
            currentJournalisation.setMontant(journalisation.getMontant());
            currentJournalisation.setTaux(journalisation.getTaux());
            currentJournalisation.setMouvement(journalisation.getMouvement());
            journalisationRepository.save(currentJournalisation);
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
            journalisationRepository.deleteById(id);
            apiResponse.setResponseCode("00");
            apiResponse.setResponseMessage("Suppression effectue");
        } catch (Exception ex){
            apiResponse.setResponseCode("01");
            apiResponse.setResponseMessage(ex.getMessage());
        }
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }





}

