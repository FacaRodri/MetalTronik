package com.example.metalTest.correctivo.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.common.ordenes.EstadoOrden;
import com.example.metalTest.correctivo.controller.request.MantenimientoCorrectivoRequest;
import com.example.metalTest.correctivo.controller.response.MantenimientoCorrectivoResponse;
import com.example.metalTest.correctivo.domain.MantenimientoCorrectivo;
import com.example.metalTest.correctivo.mapper.MantenimientoCorrectivoMapper;
import com.example.metalTest.correctivo.repository.MantenimientoCorrectivoRepository;
import com.example.metalTest.correctivo.service.MantenimientoCorrectivoService;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import com.example.metalTest.ordenestrabajo.domain.OrdenesTrabajo;
import com.example.metalTest.ordenestrabajo.repository.OrdenesTrabajoRepository;
import com.example.metalTest.parte.repository.ParteRepository;
import com.example.metalTest.parte.service.impl.ParteBuscador;
import com.example.metalTest.tipo.repository.TipoRepository;
import com.example.metalTest.usuarios.personal.domain.Personal;
import com.example.metalTest.usuarios.personal.repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class MantenimientoCorrectivoServiceImpl implements MantenimientoCorrectivoService {

    @Autowired
    MantenimientoCorrectivoRepository mantenimientoCorrectivoRepository;

    @Autowired
    MantenimientoCorrectivoMapper mantenimientoCorrectivoMapper;

    @Autowired
    MaquinaRepository maquinaRepository;

    @Autowired
    PersonalRepository personalRepository;

    @Autowired
    OrdenesTrabajoRepository ordenesTrabajoRepository;
    @Autowired
    TipoRepository tipoRepository;
    @Autowired
    ParteRepository parteRepository;

    ParteBuscador parteBuscador = new ParteBuscador();

    @Override
    public List<MantenimientoCorrectivoResponse> getAll() {
        return mantenimientoCorrectivoMapper.toMantenimientoCorrectivoResponseList(mantenimientoCorrectivoRepository.findAll());
    }

    @Override
    public MantenimientoCorrectivoResponse create(MantenimientoCorrectivoRequest mantenimientoCorrectivoRequest) throws ValidateFieldException {
        Integer maquinaCod = mantenimientoCorrectivoRequest.getMaquina_id();
        MantenimientoCorrectivo mantenimientoCorrectivo = mantenimientoCorrectivoMapper.mantenimientoCorrectivoRequestToMantenimientoCorrectivo(mantenimientoCorrectivoRequest);
        mantenimientoCorrectivo.setTipo(tipoRepository.findById(mantenimientoCorrectivoRequest.getTipo_id()).get());
        mantenimientoCorrectivo.setMaquina(maquinaRepository.findById(maquinaCod).get());
        mantenimientoCorrectivo.setEncargo1(personalRepository.findById(mantenimientoCorrectivoRequest.getEncargo1_id()).get());
        mantenimientoCorrectivo.setParte(parteBuscador.getParte(mantenimientoCorrectivoRequest.getParte_id(), parteRepository.getAllByMaquina(maquinaCod)));
        Optional<Personal> optionalUsuario2 = personalRepository.findById(mantenimientoCorrectivoRequest.getEncargo2_id());
        if (optionalUsuario2.isPresent()) {
            Personal encargo2 = optionalUsuario2.get();
            mantenimientoCorrectivo.setEncargo2(encargo2);
        }
        Optional<Personal> optionalUsuario3 = personalRepository.findById(mantenimientoCorrectivoRequest.getEncargo3_id());
        if (optionalUsuario3.isPresent()) {
            Personal encargo3 = optionalUsuario2.get();
            mantenimientoCorrectivo.setEncargo3(encargo3);
        }
        Optional<OrdenesTrabajo> optionalOrdenesTrabajo = ordenesTrabajoRepository.findById(mantenimientoCorrectivoRequest.getOrdenTrabajo_id());
        if (optionalOrdenesTrabajo.isPresent()) {
            OrdenesTrabajo ordenesTrabajo = optionalOrdenesTrabajo.get();
            ordenesTrabajo.setEstado(EstadoOrden.OK.getValue());
            mantenimientoCorrectivo.setOrdenTrabajo(ordenesTrabajo);
        }
        if (mantenimientoCorrectivoRequest.getFechainicio().after(mantenimientoCorrectivoRequest.getFechaFin())) {
            throw new ValidateFieldException("La fecha de fin no puede ser menor que la fecha de inicio", "Fecha de entrega", String.valueOf(mantenimientoCorrectivoRequest.getFechaFin()));
        }
        mantenimientoCorrectivo.setTiempoReparacion(getTiempoReparacion(mantenimientoCorrectivoRequest.getFechaFin(), mantenimientoCorrectivoRequest.getFechainicio()));
        return mantenimientoCorrectivoMapper.toMantenimientoCorrectivoResponse(mantenimientoCorrectivoRepository.save(mantenimientoCorrectivo));
    }

    @Override
    public MantenimientoCorrectivoResponse update(MantenimientoCorrectivoRequest mantenimientoCorrectivoRequest, Integer id) throws ValidateFieldException {

        Integer maquinaCod = mantenimientoCorrectivoRequest.getMaquina_id();
        Optional<MantenimientoCorrectivo> opt = mantenimientoCorrectivoRepository.findById(id);

        if (!opt.isPresent()) {
            throw new ValidateFieldException("El mantenimiento correctivo que desea acceder no existe", "id", String.valueOf(id));

        }
        MantenimientoCorrectivo mantenimientoCorrectivo = mantenimientoCorrectivoMapper.mantenimientoCorrectivoRequestToMantenimientoCorrectivo(mantenimientoCorrectivoRequest);

        mantenimientoCorrectivo.setMaquina(maquinaRepository.findById(maquinaCod).get());
        mantenimientoCorrectivo.setParte(parteBuscador.getParte(mantenimientoCorrectivoRequest.getParte_id(), parteRepository.getAllByMaquina(maquinaCod)));

        mantenimientoCorrectivo.setOrdenTrabajo(ordenesTrabajoRepository.findById(mantenimientoCorrectivoRequest.getOrdenTrabajo_id()).get());
        mantenimientoCorrectivo.setEncargo1(personalRepository.findById(mantenimientoCorrectivoRequest.getEncargo1_id()).get());
        Optional<Personal> optionalUsuario2 = personalRepository.findById(mantenimientoCorrectivoRequest.getEncargo2_id());
        if (optionalUsuario2.isPresent()) {
            Personal encargo2 = optionalUsuario2.get();
            mantenimientoCorrectivo.setEncargo2(encargo2);
        }

        Optional<Personal> optionalUsuario3 = personalRepository.findById(mantenimientoCorrectivoRequest.getEncargo3_id());
        if (optionalUsuario3.isPresent()) {
            Personal encargo3 = optionalUsuario2.get();
            mantenimientoCorrectivo.setEncargo3(encargo3);
        }

        Optional<OrdenesTrabajo> optionalOrdenesTrabajo = ordenesTrabajoRepository.findById(mantenimientoCorrectivoRequest.getOrdenTrabajo_id());
        if (optionalOrdenesTrabajo.isPresent()) {
            OrdenesTrabajo ordenesTrabajo = optionalOrdenesTrabajo.get();
            ordenesTrabajo.setEstado(EstadoOrden.OK.getValue());
            mantenimientoCorrectivo.setOrdenTrabajo(ordenesTrabajo);
        }
        if (mantenimientoCorrectivoRequest.getFechainicio().after(mantenimientoCorrectivoRequest.getFechaFin())) {
            throw new ValidateFieldException("La fecha de fin no puede ser menor que la fecha de inicio", "Fecha de entrega", String.valueOf(mantenimientoCorrectivoRequest.getFechaFin()));
        }

        mantenimientoCorrectivo.setTiempoReparacion(getTiempoReparacion(mantenimientoCorrectivoRequest.getFechaFin(), mantenimientoCorrectivoRequest.getFechainicio()));
        mantenimientoCorrectivo.setId(id);
        return mantenimientoCorrectivoMapper.toMantenimientoCorrectivoResponse(mantenimientoCorrectivoRepository.save(mantenimientoCorrectivo));
    }

    @Override
    public MantenimientoCorrectivoResponse getById(Integer id) throws ValidateFieldException {
        Optional<MantenimientoCorrectivo> opt = mantenimientoCorrectivoRepository.findById(id);
        if (!opt.isPresent()) {
            throw new ValidateFieldException("El mantenimiento correctivo que desea acceder no existe", "id", String.valueOf(id));
        }
        return mantenimientoCorrectivoMapper.toMantenimientoCorrectivoResponse(opt.get());
    }


    private int getTiempoReparacion(Date inicio, Date fin){
        long diffInMillies = Math.abs(fin.getTime() - inicio.getTime());
        long diff = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return (int)diff;
    }


}