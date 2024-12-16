package com.garnicsoft.obligations.controller;

import com.garnicsoft.obligations.models.entitys.Obligation;
import com.garnicsoft.obligations.models.entitys.Pago;
import com.garnicsoft.obligations.services.pagos.PagosServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;

import java.io.ByteArrayOutputStream;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/pago")
public class PagoController {

    @Autowired
    PagosServicesImpl pagosServices;

    @PostMapping("/create-pago")
    public ResponseEntity<byte[]> createPago(@RequestBody Obligation obligation) {
        ByteArrayOutputStream recibo = pagosServices.createPago(obligation);
        // Configurar el encabezado de la respuesta
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/pdf");
        headers.add("Content-Disposition", "attachment; filename=recibo_pago.pdf");

        return new ResponseEntity<>(recibo.toByteArray(), headers, HttpStatus.OK);
    }

    @GetMapping("/get-all-pagos")
    public ResponseEntity<List<Pago>> getAllPagos() {
        List<Pago> pagos = pagosServices.getAllPagos();
        return new ResponseEntity<>(pagos, HttpStatus.OK);
    }

    @GetMapping("/get-pago-by-id/{id}")
    public ResponseEntity<Pago> getPagoById(@PathVariable Long id) {
        Pago pagoResult = pagosServices.getPagoById(id);
        return new ResponseEntity<>(pagoResult, HttpStatus.OK);
    }

    @GetMapping("/get-pagos-by-player-name/{name}")
    public ResponseEntity<List<Pago>> getPagoByPlannerName(@PathVariable String name) {
        List<Pago> pagosResults = pagosServices.getPagosByPlayerName(name);
        return new ResponseEntity<>(pagosResults, HttpStatus.OK);
    }

    @PutMapping("/edit-pago/{id}")
    public ResponseEntity<Pago> editPago(@PathVariable Long id, @RequestBody Pago pago) {
        Pago pagoEdit = pagosServices.editPago(pago, id);
        return new ResponseEntity<>(pagoEdit, HttpStatus.OK);
    }

    @GetMapping("/get-recibo-pago/{id}")
    public ResponseEntity<byte[]> getReciboPagoById(@PathVariable Long id) {
        ByteArrayOutputStream recibo = pagosServices.createRecibo(id);
        // Configurar el encabezado de la respuesta
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/pdf");
        headers.add("Content-Disposition", "attachment; filename=recibo_pago.pdf");

        return new ResponseEntity<>(recibo.toByteArray(), headers, HttpStatus.OK);
    }

    @DeleteMapping("/delete-pago/{id}")
    public ResponseEntity deletePago(@PathVariable Long id) {
        pagosServices.deletePago(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
