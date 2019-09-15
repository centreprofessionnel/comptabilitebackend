package org.crip.comptabilite.comptabilitebackend.controllers;

import org.crip.comptabilite.comptabilitebackend.entities.*;
import org.crip.comptabilite.comptabilitebackend.models.ApiResponse;
import org.crip.comptabilite.comptabilitebackend.repositories.CategorieMarchandiseRepository;
import org.crip.comptabilite.comptabilitebackend.repositories.MarchandiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/marchandise")
@CrossOrigin(origins = "*")
public class MarchandiseController {
    @Autowired
    MarchandiseRepository marchandiseRepository;
    @Autowired
    CategorieMarchandiseRepository categorieMarchandiseRepository;
    ApiResponse apiResponse=new ApiResponse();
    @CrossOrigin
    @PostMapping("/Enregistrer/{idcatM}")
    public ResponseEntity<?> insert (@RequestBody Marchandise marchandise,@PathVariable Long idcatM){
        apiResponse=new ApiResponse();
        try {
            CategorieMarchandise categorieMarchandise=categorieMarchandiseRepository.getOne(idcatM);
            marchandise.setCategorieMarchandise(categorieMarchandise);
            marchandiseRepository.save(marchandise);
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
            Marchandise marchandise=marchandiseRepository.getOne(id);
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
            List<Marchandise> marchandises=marchandiseRepository.findAll();
            apiResponse.setResponseCode("00");
            apiResponse.setData(marchandises);
        } catch (Exception ex){
            apiResponse.setResponseCode("001");
            apiResponse.setResponseMessage(ex.getMessage());
        }
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Marchandise marchandise){
        apiResponse=new ApiResponse();
        try {
            Marchandise currentMarchandise=marchandiseRepository.getOne(id);
            currentMarchandise.setDesignation(marchandise.getDesignation());
            currentMarchandise.setPu(marchandise.getPu());
            marchandiseRepository.save(currentMarchandise);
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
            marchandiseRepository.deleteById(id);
            apiResponse.setResponseCode("00");
            apiResponse.setResponseMessage("Suppression effectue");
        } catch (Exception ex){
            apiResponse.setResponseCode("01");
            apiResponse.setResponseMessage(ex.getMessage());
        }
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }



}
