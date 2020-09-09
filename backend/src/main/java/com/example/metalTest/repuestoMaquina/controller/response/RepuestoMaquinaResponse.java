package com.example.metalTest.repuestoMaquina.controller.response;

import com.example.metalTest.maquina.controller.response.MaquinaReducidoResponse;
import com.example.metalTest.maquina.domain.Maquina;
import com.example.metalTest.repuesto.controller.response.RepuestoReducidoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RepuestoMaquinaResponse {

    private RepuestoReducidoResponse repuesto;
    private Integer cantidad_instalada;
    private MaquinaReducidoResponse maquina;

}