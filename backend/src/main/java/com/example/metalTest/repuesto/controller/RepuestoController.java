package com.example.metalTest.repuesto.controller;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.repuesto.controller.request.RepuestoMaquinaRequest;
import com.example.metalTest.repuesto.controller.request.RepuestoRequest;
import com.example.metalTest.repuesto.domain.Repuesto;
import com.example.metalTest.repuesto.service.RepuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/repuesto-maquina")
public class RepuestoController {

    @Autowired
    RepuestoService repuestoService;

    @GetMapping
    public ResponseEntity<List<Repuesto>> getAll() {
        return new ResponseEntity<>(repuestoService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Repuesto> getById(@PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(repuestoService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/maquina/{id}")
    public ResponseEntity<List<Repuesto>> getByMaquina(@PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(repuestoService.getByMaquina(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Repuesto> create(@Valid @RequestBody RepuestoRequest repuestoRequest) {
        return new ResponseEntity<>(repuestoService.create(repuestoRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Repuesto> update(@Valid @RequestBody RepuestoRequest repuestoRequest, @PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(repuestoService.update(repuestoRequest, id), HttpStatus.OK);
    }

    @PutMapping("/{id}/vincular")
    public ResponseEntity<Repuesto> vincular(@Valid @RequestBody RepuestoMaquinaRequest repuestoMaquinaRequest, @PathVariable Integer id) throws ValidateFieldException {
        return new ResponseEntity<>(repuestoService.vincular(repuestoMaquinaRequest,id), HttpStatus.OK);
    }


}