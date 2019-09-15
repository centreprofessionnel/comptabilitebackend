package org.crip.comptabilite.comptabilitebackend.controllers;
import org.crip.comptabilite.comptabilitebackend.entities.Company;
import org.crip.comptabilite.comptabilitebackend.models.ApiResponse;
import org.crip.comptabilite.comptabilitebackend.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/company")
@CrossOrigin(origins = "*")

public class CompanyController {
    @Autowired
    CompanyRepository companyRepository;
    ApiResponse apiResponse=new ApiResponse();
    @PostMapping("/enregistrer")
    public ResponseEntity<?> insert(@RequestBody Company company){
        apiResponse=new ApiResponse();
        try {
            companyRepository.save(company);
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
            Company company=companyRepository.getOne(id);
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
            List<Company> companies=companyRepository.findAll();
            apiResponse.setResponseCode("00");
            apiResponse.setData(companies);
        } catch (Exception ex){
            apiResponse.setResponseCode("001");
            apiResponse.setResponseMessage(ex.getMessage());
        }
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Company company){
        apiResponse=new ApiResponse();
        try {
            Company currentCompany=companyRepository.getOne(id);
            currentCompany.setNomRaisonSoc(company.getNomRaisonSoc());
            currentCompany.setSigle(company.getSigle());
            currentCompany.setEmail(company.getEmail());
            currentCompany.setTel(company.getTel());
            currentCompany.setAdresse(company.getAdresse());
            currentCompany.setDomaine(company.getDomaine());
            companyRepository.save(currentCompany);
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
            companyRepository.deleteById(id);
            apiResponse.setResponseCode("00");
            apiResponse.setResponseMessage("Suppression effectue");
        } catch (Exception ex){
            apiResponse.setResponseCode("01");
            apiResponse.setResponseMessage(ex.getMessage());
        }
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }





}
