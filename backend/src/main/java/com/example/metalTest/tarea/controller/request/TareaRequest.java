package com.example.metalTest.tarea.controller.request;

import com.example.metalTest.common.validator.ValidEntity;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class TareaRequest {
    private String tarea;
    @ValidEntity(repository = MaquinaRepository.class)
    private Integer maquina_cod;
    private Integer parte_cod;
    private int frecuencia;
    private Date inicio;
    private Short estado;
}
