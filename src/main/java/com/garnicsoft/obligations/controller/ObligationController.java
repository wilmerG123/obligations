package com.garnicsoft.obligations.controller;


import com.garnicsoft.obligations.models.entitys.Obligation;
import com.garnicsoft.obligations.models.enums.Status;
import com.garnicsoft.obligations.services.ObligationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/obligations")
public class ObligationController {

    @Autowired
    private ObligationServiceImpl service;

    @PostMapping("/create-obligation")
    public ResponseEntity<Obligation> createObligation(@RequestBody Obligation obligation){
        Obligation obligationCreated = service.createObligation(obligation);
        return new ResponseEntity<Obligation>(obligationCreated, HttpStatus.CREATED);
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
