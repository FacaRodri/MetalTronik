package com.example.metalTest.mantenimientoCorrectivo.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.common.ordenes.EstadoOrden;
import com.example.metalTest.mantenimientoCorrectivo.controller.request.MantenimientoCorrectivoRequest;
import com.example.metalTest.mantenimientoCorrectivo.domain.MantenimientoCorrectivo;
import com.example.metalTest.mantenimientoCorrectivo.mapper.MantenimientoCorrectivoMapper;
import com.example.metalTest.mantenimientoCorrectivo.repository.MantenimientoCorrectivoRepository;
import com.example.metalTest.mantenimientoCorrectivo.service.MantenimientoCorrectivoService;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import com.example.metalTest.ordenestrabajo.domain.OrdenesTrabajo;
import com.example.metalTest.ordenestrabajo.repository.OrdenesTrabajoRepository;
import com.example.metalTest.sector.repository.SectorRepository;
import com.example.metalTest.usuario.domain.Usuario;
import com.example.metalTest.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MantenimientoCorrectivoServiceImpl implements MantenimientoCorrectivoService {

    @Autowired
    MantenimientoCorrectivoRepository mantenimientoCorrectivoRepository;

    @Autowired
    MantenimientoCorrectivoMapper mantenimientoCorrectivoMapper;

    @Autowired
    MaquinaRepository maquinaRepository;

    @Autowired
    SectorRepository sectorRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    OrdenesTrabajoRepository ordenesTrabajoRepository;

    @Override
    public List<MantenimientoCorrectivo> getAll() {
        return mantenimientoCorrectivoRepository.findAll();
    }

    @Override
    public MantenimientoCorrectivo create(MantenimientoCorrectivoRequest mantenimientoCorrectivoRequest) {
        MantenimientoCorrectivo mantenimientoCorrectivo = mantenimientoCorrectivoMapper.mantenimientoCorrectivoRequestToMantenimientoCorrectivo(mantenimientoCorrectivoRequest);
        mantenimientoCorrectivo.setMaquina(maquinaRepository.findById(mantenimientoCorrectivoRequest.getMaquina_cod()).get());
        mantenimientoCorrectivo.setEncargo1(usuarioRepository.findById(mantenimientoCorrectivoRequest.getEncargo1_cod()).get());
        Optional<Usuario> optionalUsuario2 = usuarioRepository.findById(mantenimientoCorrectivoRequest.getEncargo2_cod());
        if (optionalUsuario2.isPresent()){
            Usuario encargo2 = optionalUsuario2.get();
            mantenimientoCorrectivo.setEncargo2(encargo2);
        }
        Optional<Usuario> optionalUsuario3 = usuarioRepository.findById(mantenimientoCorrectivoRequest.getEncargo3_cod());
        if (optionalUsuario3.isPresent()){
            Usuario encargo3 = optionalUsuario2.get();
            mantenimientoCorrectivo.setEncargo3(encargo3);
        }
        Optional<OrdenesTrabajo> optionalOrdenesTrabajo = ordenesTrabajoRepository.findById(mantenimientoCorrectivoRequest.getOrdenTrabajo_cod());
        if (optionalOrdenesTrabajo.isPresent()){
            OrdenesTrabajo ordenesTrabajo = optionalOrdenesTrabajo.get();
            ordenesTrabajo.setEstado(EstadoOrden.OK.getValue());
            mantenimientoCorrectivo.setOrdenTrabajo(ordenesTrabajo);
        }
        return mantenimientoCorrectivoRepository.save(mantenimientoCorrectivo);
    }

    @Override
    public MantenimientoCorrectivo update(MantenimientoCorrectivoRequest mantenimientoCorrectivoRequest, Integer id) throws ValidateFieldException {

        Optional<MantenimientoCorrectivo> opt = mantenimientoCorrectivoRepository.findById(id);

        if (!opt.isPresent()){
            throw new ValidateFieldException("El mantenimiento correctivo que desea acceder no existe","id",String.valueOf(id));

        }

        MantenimientoCorrectivo old = opt.get();

        MantenimientoCorrectivo mantenimientoCorrectivo = mantenimientoCorrectivoMapper.mantenimientoCorrectivoRequestToMantenimientoCorrectivo(mantenimientoCorrectivoRequest);

        mantenimientoCorrectivo.setMaquina(maquinaRepository.findById(mantenimientoCorrectivoRequest.getMaquina_cod()).get());
        mantenimientoCorrectivo.setOrdenTrabajo(ordenesTrabajoRepository.findById(mantenimientoCorrectivoRequest.getOrdenTrabajo_cod()).get());
        mantenimientoCorrectivo.setEncargo1(usuarioRepository.findById(mantenimientoCorrectivoRequest.getEncargo1_cod()).get());

        Optional<Usuario> optionalUsuario2 = usuarioRepository.findById(mantenimientoCorrectivoRequest.getEncargo2_cod());
        if (optionalUsuario2.isPresent()){
            Usuario encargo2 = optionalUsuario2.get();
            mantenimientoCorrectivo.setEncargo2(encargo2);
        }

        Optional<Usuario> optionalUsuario3 = usuarioRepository.findById(mantenimientoCorrectivoRequest.getEncargo3_cod());
        if (optionalUsuario3.isPresent()){
            Usuario encargo3 = optionalUsuario2.get();
            mantenimientoCorrectivo.setEncargo3(encargo3);
        }

        Optional<OrdenesTrabajo> optionalOrdenesTrabajo = ordenesTrabajoRepository.findById(mantenimientoCorrectivoRequest.getOrdenTrabajo_cod());
        if (optionalOrdenesTrabajo.isPresent()){
            OrdenesTrabajo oldOrden = old.getOrdenTrabajo();
            oldOrden.setEstado(EstadoOrden.PENDIENTE.getValue());
            OrdenesTrabajo ordenesTrabajo = optionalOrdenesTrabajo.get();
            ordenesTrabajo.setEstado(EstadoOrden.OK.getValue());
            mantenimientoCorrectivo.setOrdenTrabajo(ordenesTrabajo);
        }
        mantenimientoCorrectivo.setId(id);
        return mantenimientoCorrectivoRepository.save(mantenimientoCorrectivo);
    }

    @Override
    public MantenimientoCorrectivo getById(Integer id) throws ValidateFieldException {
        Optional<MantenimientoCorrectivo> opt = mantenimientoCorrectivoRepository.findById(id);
        if (!opt.isPresent()){
            throw new ValidateFieldException("El mantenimiento correctivo que desea acceder no existe","id",String.valueOf(id));
        }
        return opt.get();
    }
}