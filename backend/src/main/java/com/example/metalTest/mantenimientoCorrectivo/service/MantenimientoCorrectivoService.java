package com.example.metalTest.mantenimientoCorrectivo.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.mantenimientoCorrectivo.controller.request.MantenimientoCorrectivoRequest;
import com.example.metalTest.mantenimientoCorrectivo.controller.response.MantenimientoCorrectivoResponse;
import com.example.metalTest.indicadores.controller.response.IndicatorResponse;

import java.util.List;

public interface MantenimientoCorrectivoService {
    List<MantenimientoCorrectivoResponse> getAll();

    MantenimientoCorrectivoResponse create(MantenimientoCorrectivoRequest mantenimientoCorrectivoRequest) throws ValidateFieldException;

    MantenimientoCorrectivoResponse update(MantenimientoCorrectivoRequest mantenimientoCorrectivoRequest, Integer id) throws ValidateFieldException;

    MantenimientoCorrectivoResponse getById(Integer id) throws ValidateFieldException;

    List<IndicatorResponse> getIndicatorsManCorUsuario();
}
