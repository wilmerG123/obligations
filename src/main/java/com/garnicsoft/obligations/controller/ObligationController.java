package com.garnicsoft.obligations.controller;


import com.garnicsoft.obligations.models.dtos.ObligationDTO;
import com.garnicsoft.obligations.models.entitys.Obligation;
import com.garnicsoft.obligations.models.enums.Status;
import com.garnicsoft.obligations.services.ObligationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/obligations")
public class ObligationController {

    @Autowired
    private ObligationServiceImpl service;

    @PostMapping("/create-obligation")
    public void createObligation(@RequestBody ObligationDTO obligation) {
       service.createObligation(obligation);
    }

    @GetMapping("/get-all-obligations")
    public ResponseEntity<List<Obligation>> getAllObligations(){
        List<Obligation>obligationsList = service.getAllObligation();
        return  new ResponseEntity<List<Obligation>>(obligationsList,HttpStatus.OK);
    }

    @PutMapping("/edit-obligation/{id}")
    public ResponseEntity<Obligation> editObligation(@RequestBody Obligation obligation, @PathVariable Long id){
        obligation.setStatus(Status.MORA);
        Obligation obligationModified = service.editObligation(obligation, id);
        return new ResponseEntity<Obligation>(obligation, HttpStatus.OK);
    }

    @DeleteMapping("/close-obligation/{id}")
    public void closeObligation (@PathVariable Long id){
        service.closeObligation(id);
    }

    @DeleteMapping("/delete-obligation/{id}")
    public void deleteObligation(@PathVariable Long id){
        service.deleteObligation(id);
    }

}
