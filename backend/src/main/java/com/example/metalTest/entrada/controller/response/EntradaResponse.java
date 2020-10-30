package com.example.metalTest.entrada.controller.response;

import com.example.metalTest.repuesto.controller.response.RepuestoReducidoResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EntradaResponse {
    private int id;
    private RepuestoReducidoResponse repuesto;
    private Date fecha;
    private int cantidad;
    private int precio;
    private String numeroOrdenCompra;
    private String proveedor;
}
