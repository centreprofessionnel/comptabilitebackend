package org.crip.comptabilite.comptabilitebackend.controllers;
import org.crip.comptabilite.comptabilitebackend.entities.*;
import org.crip.comptabilite.comptabilitebackend.models.ApiResponse;
import org.crip.comptabilite.comptabilitebackend.repositories.MarchandiseRepository;
import org.crip.comptabilite.comptabilitebackend.repositories.OperationRepository;
import org.crip.comptabilite.comptabilitebackend.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/stock")
@CrossOrigin(origins = "*")
public class StockController {
    @Autowired
    StockRepository stockRepository;
    @Autowired
    OperationRepository operationRepository;
    @Autowired
    MarchandiseRepository marchandiseRepository;
    ApiResponse apiResponse=new ApiResponse();
    @CrossOrigin
    @PostMapping("/Enregistrer/{idop}/{idmarch}")
    public ResponseEntity<?> insert (@RequestBody Stock stock, @PathVariable("idop") Long idop, @PathVariable("idmarch") Long idmarch){
        apiResponse=new ApiResponse();
        try {
            Operation operation=operationRepository.getOne(idop);
            stock.setOperation(operation);
            Marchandise marchandise=marchandiseRepository.getOne(idmarch);
            stock.setMarchandise(marchandise);
            stockRepository.save(stock);
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
            Stock stock=stockRepository.getOne(id);
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
            List<Stock> stocks=stockRepository.findAll();
            apiResponse.setResponseCode("00");
            apiResponse.setData(stocks);
        } catch (Exception ex){
            apiResponse.setResponseCode("001");
            apiResponse.setResponseMessage(ex.getMessage());
        }
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Stock stock){
        apiResponse=new ApiResponse();
        try {
            Stock currentStock=stockRepository.getOne(id);
            currentStock.setMouvement(stock.getMouvement());
            currentStock.setQte(stock.getQte());
            currentStock.setPrix(stock.getPrix());
            currentStock.setMonnaie(stock.getMonnaie());
            currentStock.setObservation(stock.getObservation());
            stockRepository.save(currentStock);
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
            stockRepository.deleteById(id);
            apiResponse.setResponseCode("00");
            apiResponse.setResponseMessage("Suppression effectue");
        } catch (Exception ex){
            apiResponse.setResponseCode("01");
            apiResponse.setResponseMessage(ex.getMessage());
        }
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}
