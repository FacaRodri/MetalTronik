package com.example.metalTest.repuestoMaquina.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.repuestoMaquina.controller.request.RepuestoMaquinaRequest;
import com.example.metalTest.repuestoMaquina.controller.response.RepuestoMaquinaResponse;
import com.example.metalTest.repuestoMaquina.domain.RepuestoMaquina;

import java.util.List;

public interface RepuestoMaquinaService {
    List<RepuestoMaquinaResponse> vincular(List<RepuestoMaquinaRequest> repuestoMaquinaRequestList, Integer id) throws ValidateFieldException;
}