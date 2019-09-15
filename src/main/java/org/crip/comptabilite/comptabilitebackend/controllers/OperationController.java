package org.crip.comptabilite.comptabilitebackend.controllers;
import org.crip.comptabilite.comptabilitebackend.entities.Agent;
import org.crip.comptabilite.comptabilitebackend.entities.Company;
import org.crip.comptabilite.comptabilitebackend.entities.Operation;
import org.crip.comptabilite.comptabilitebackend.models.ApiResponse;
import org.crip.comptabilite.comptabilitebackend.repositories.CompanyRepository;
import org.crip.comptabilite.comptabilitebackend.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/operation")
@CrossOrigin(origins = "*")
public class OperationController {
    @Autowired
    OperationRepository operationRepository;
    @Autowired
    CompanyRepository companyRepository;
    ApiResponse apiResponse = new ApiResponse();
    @CrossOrigin
    @PostMapping("/Enregistrer/{idComp}")
    public ResponseEntity<?> insert(@RequestBody Operation operation, @PathVariable("idComp") Long idComp) {
        apiResponse = new ApiResponse();
        try {
            Company company = companyRepository.getOne(idComp);
            operation.setCompany(company);
            operationRepository.save(operation);
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
            Operation operation=operationRepository.getOne(id);
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
            List<Operation> operations=operationRepository.findAll();
            apiResponse.setResponseCode("00");
            apiResponse.setData(operations);
        } catch (Exception ex){
            apiResponse.setResponseCode("001");
            apiResponse.setResponseMessage(ex.getMessage());
        }
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Operation operation){
        apiResponse=new ApiResponse();
        try {
            Operation currentOperation=operationRepository.getOne(id);
            currentOperation.setDateOp(operation.getDateOp());
            currentOperation.setDocRef(operation.getDocRef());
            currentOperation.setNumDoc(operation.getNumDoc());
            currentOperation.setLibelle(operation.getLibelle());
            operationRepository.save(currentOperation);
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
            operationRepository.deleteById(id);
            apiResponse.setResponseCode("00");
            apiResponse.setResponseMessage("Suppression effectue");
        } catch (Exception ex){
            apiResponse.setResponseCode("01");
            apiResponse.setResponseMessage(ex.getMessage());
        }
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}
