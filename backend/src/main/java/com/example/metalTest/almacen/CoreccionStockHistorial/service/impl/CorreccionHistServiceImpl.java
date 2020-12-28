package com.example.metalTest.almacen.CoreccionStockHistorial.service.impl;

import com.example.metalTest.almacen.CoreccionStockHistorial.controller.request.CorrHistRequest;
import com.example.metalTest.almacen.CoreccionStockHistorial.controller.response.CorrHistResponse;
import com.example.metalTest.almacen.CoreccionStockHistorial.domain.CorreccionHistorial;
import com.example.metalTest.almacen.CoreccionStockHistorial.mapper.CorreccionMapper;
import com.example.metalTest.almacen.CoreccionStockHistorial.repository.CorreccionHistorialRepository;
import com.example.metalTest.almacen.CoreccionStockHistorial.service.CorreccionHistService;
import com.example.metalTest.almacen.repuesto.domain.Repuesto;
import com.example.metalTest.almacen.repuesto.repository.RepuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorreccionHistServiceImpl implements CorreccionHistService {

    @Autowired
    CorreccionHistorialRepository correccionHistorialRepository;
    @Autowired
    CorreccionMapper correccionMapper;
    @Autowired
    RepuestoRepository repuestoRepository;
    @Override
    public List<CorreccionHistorial> getAll() {
        return correccionHistorialRepository.findAll();
    }

    @Override
    public CorrHistResponse create(CorrHistRequest correccionHistorial, Integer repuesto_id) {
        CorreccionHistorial correccion = correccionMapper.corrHistRequestToCorreccionHistorial(correccionHistorial);
        Repuesto repuesto = repuestoRepository.findById(repuesto_id).get();
        repuesto.setExistencia(correccionHistorial.getStock());
        repuesto.setId(repuesto_id);
        repuestoRepository.save(repuesto);
        correccionHistorialRepository.save(correccion);
        return correccionMapper.correccionHistorialToCorrHistResponse(correccion);
    }
}