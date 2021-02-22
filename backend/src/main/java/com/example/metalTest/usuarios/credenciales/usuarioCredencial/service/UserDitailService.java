package com.example.metalTest.usuarios.credenciales.usuarioCredencial.service;

import com.example.metalTest.usuarios.personal.domain.Personal;
import com.example.metalTest.usuarios.personal.service.PersonalService;
import com.example.metalTest.usuarios.credenciales.usuarioCredencial.controller.UsuarioCredencialController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDitailService implements UserDetailsService {
    @Autowired
    PersonalService personalService;

    @Override
    public UserDetails loadUserByUsername(String user_name) throws UsernameNotFoundException {
        Personal personal = personalService.findFyNombreUsuario(user_name);
        return UsuarioCredencialController.build(personal.getCredencial());
    }
}