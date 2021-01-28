package com.example.metalTest.usuarios.rol.controller.request;
import com.example.metalTest.usuarios.permiso.request.PermisoRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;
@Getter
@Setter
public class RolRequest {
    @NotNull
    private String nombre;
    @NotNull
    private List<PermisoRequest> permisos;


}
