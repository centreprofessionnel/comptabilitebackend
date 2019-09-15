package org.crip.comptabilite.comptabilitebackend.controllers;
import org.crip.comptabilite.comptabilitebackend.entities.Agent;
import org.crip.comptabilite.comptabilitebackend.entities.Company;
import org.crip.comptabilite.comptabilitebackend.models.ApiResponse;
import org.crip.comptabilite.comptabilitebackend.repositories.AgentRepository;
import org.crip.comptabilite.comptabilitebackend.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/agent")
@CrossOrigin(origins = "*")
public class AgentController {
    @Autowired
    AgentRepository agentRepository;
    @Autowired
    CompanyRepository companyRepository;
    ApiResponse apiResponse=new ApiResponse();
    @PostMapping("/Enregistrer/{idCompany}")
    public ResponseEntity<?> insert (@RequestBody Agent agent, @PathVariable("idCompany")Long idCompany){
         apiResponse=new ApiResponse();
        try {
            Company company=companyRepository.getOne(idCompany);
            agent.setCompany(company);
            agentRepository.save(agent);
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
            Agent agent=agentRepository.getOne(id);
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
            List<Agent> agents=agentRepository.findAll();
            apiResponse.setResponseCode("00");
            apiResponse.setData(agents);
        } catch (Exception ex){
            apiResponse.setResponseCode("001");
            apiResponse.setResponseMessage(ex.getMessage());
        }
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Agent agent){
        apiResponse=new ApiResponse();
        try {
            Agent currentAgent=agentRepository.getOne(id);
            currentAgent.setNom(agent.getNom());
            currentAgent.setEmail(agent.getEmail());
            currentAgent.setTel(agent.getTel());
            currentAgent.setAdresse(agent.getAdresse());
            currentAgent.setFonction(agent.getFonction());
            agentRepository.save(currentAgent);
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
            agentRepository.deleteById(id);
            apiResponse.setResponseCode("00");
            apiResponse.setResponseMessage("Suppression effectue");
        } catch (Exception ex){
            apiResponse.setResponseCode("01");
            apiResponse.setResponseMessage(ex.getMessage());
        }
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }




}
