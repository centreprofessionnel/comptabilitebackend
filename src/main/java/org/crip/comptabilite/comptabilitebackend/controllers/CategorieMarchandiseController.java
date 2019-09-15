package org.crip.comptabilite.comptabilitebackend.controllers;

import org.crip.comptabilite.comptabilitebackend.entities.CategorieCompte;
import org.crip.comptabilite.comptabilitebackend.entities.CategorieMarchandise;
import org.crip.comptabilite.comptabilitebackend.models.ApiResponse;
import org.crip.comptabilite.comptabilitebackend.repositories.CategorieMarchandiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categoriemarchandise")
@CrossOrigin(origins = "*")
public class CategorieMarchandiseController {
    @Autowired
    CategorieMarchandiseRepository categorieMarchandiseRepository;
    ApiResponse apiResponse=new ApiResponse();
    @CrossOrigin
    @PostMapping("/Enregistrer")
    public ResponseEntity<?> insert (@RequestBody CategorieMarchandise categorieMarchandise){
        apiResponse=new ApiResponse();
        try {
            categorieMarchandiseRepository.save(categorieMarchandise);
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
            CategorieMarchandise categorieMarchandise=categorieMarchandiseRepository.getOne(id);
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
            List<CategorieMarchandise> categorieMarchandises=categorieMarchandiseRepository.findAll();
            apiResponse.setResponseCode("00");
            apiResponse.setData(categorieMarchandises);
        } catch (Exception ex){
            apiResponse.setResponseCode("001");
            apiResponse.setResponseMessage(ex.getMessage());
        }
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody CategorieMarchandise categorieMarchandise){
        apiResponse=new ApiResponse();
        try {
            CategorieMarchandise currentCategorieMarchandise=categorieMarchandiseRepository.getOne(id);
            currentCategorieMarchandise.setDesignation(categorieMarchandise.getDesignation());
            categorieMarchandiseRepository.save(currentCategorieMarchandise);
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
            categorieMarchandiseRepository.deleteById(id);
            apiResponse.setResponseCode("00");
            apiResponse.setResponseMessage("Suppression effectue");
        } catch (Exception ex){
            apiResponse.setResponseCode("01");
            apiResponse.setResponseMessage(ex.getMessage());
        }
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

}
