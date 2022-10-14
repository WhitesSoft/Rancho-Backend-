package com.darksoft.servidor.util;

import com.darksoft.servidor.security.entity.Rol;
import com.darksoft.servidor.security.enums.RolNombre;
import com.darksoft.servidor.security.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * MUY IMPORTANTE: ESTA CLASE SÓLO SE EJECUTARÁ UNA VEZ PARA CREAR LOS ROLES.
 * UNA VEZ CREADOS SE DEBERÁ ELIMINAR O BIEN COMENTAR EL CÓDIGO
 *
 */

@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RolService rolService;

    @Override
    public void run(String... args) throws Exception {
//        Rol rolAdmin = new Rol(RolNombre.ROLE_ADMINISTRADOR);
//        Rol rolCajero = new Rol(RolNombre.ROLE_CAJERO);
//        Rol rolLecturador = new Rol(RolNombre.ROLE_LECTURADOR);
//        Rol rolPlomero = new Rol(RolNombre.ROLE_PLOMERO);
//        Rol rolUser = new Rol(RolNombre.ROLE_USER);
//
//        rolService.save(rolAdmin);
//        rolService.save(rolCajero);
//        rolService.save(rolLecturador);
//        rolService.save(rolPlomero);
//        rolService.save(rolUser);
    }
}