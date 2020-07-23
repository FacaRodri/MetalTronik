package com.example.metalTest.repuesto.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.repuesto.controller.request.RepuestoMaquinaRequest;
import com.example.metalTest.repuesto.controller.request.RepuestoRequest;
import com.example.metalTest.repuesto.domain.Repuesto;

import java.util.List;

public interface RepuestoService {
    List<Repuesto> getAll();

    Repuesto getById(Integer id) throws ValidateFieldException;

    Repuesto create(RepuestoRequest repuestoRequest);

    Repuesto update(RepuestoRequest repuestoRequest, Integer id) throws ValidateFieldException;

    List<Repuesto> getByMaquina(Integer id) throws ValidateFieldException;

    Repuesto vincular(RepuestoMaquinaRequest repuestoMaquinaRequest, Integer id) throws ValidateFieldException;
}