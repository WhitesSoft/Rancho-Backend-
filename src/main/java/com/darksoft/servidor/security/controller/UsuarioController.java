package com.darksoft.servidor.security.controller;

import com.darksoft.servidor.dto.Mensaje;
import com.darksoft.servidor.dto.SocioDto;
import com.darksoft.servidor.entity.Socio;
import com.darksoft.servidor.security.dto.NuevoUsuario;
import com.darksoft.servidor.security.entity.Rol;
import com.darksoft.servidor.security.entity.Usuario;
import com.darksoft.servidor.security.enums.RolNombre;
import com.darksoft.servidor.security.service.RolService;
import com.darksoft.servidor.security.service.UsuarioService;
import com.darksoft.servidor.service.SocioService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/usuario")
@CrossOrigin //(origins = "http://localhost:8100")//Puerto de angular http://localhost:4200
public class UsuarioController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    //Listar los usuarios
    @GetMapping("/lista") //Nos damos un url para acceder luego
    public ResponseEntity<List<Usuario>> listarSocios() {
        List<Usuario> listaUsuarios = usuarioService.getAllUsuarios();
        return new ResponseEntity<>(listaUsuarios, HttpStatus.OK);
    }

    //Ver socio por id
    @GetMapping("/detalles/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable("id") long id) {

        if (!usuarioService.existsById(id)) //Si no existe el socio retornamos un mensaje
            return new ResponseEntity(new Mensaje("No existe el usuario"), HttpStatus.NOT_FOUND);

        Usuario usuario = usuarioService.getUsuario(id).get();

        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }


    //Editar usuario
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable("id") long id, @RequestBody NuevoUsuario nuevoUsuario) {

        //primero verificamos si existe el usuario
        if (!usuarioService.existsById(id) ) //Si no existe retornamos un mensaje
            return new ResponseEntity(new Mensaje("No existe el usuario"), HttpStatus.NOT_FOUND);


        Usuario usuario = usuarioService.getUsuario(id).get();
        usuario.setUsuario(nuevoUsuario.getUsuario());
        usuario.setPassword(nuevoUsuario.getPassword());
        usuario.setEstadoPassword(nuevoUsuario.isEstadoPassword());

        //Todos los usuarios por defecto seran usuarios BASICOS
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());

        //Verificamos como creamos el usuario como administrador
        if (nuevoUsuario.getRoles().contains("administrador"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMINISTRADOR).get());

        //Verificamos como creamos el usuario como cajero
        if (nuevoUsuario.getRoles().contains("cajero"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_CAJERO).get());

        //Verificamos como creamos el usuario como lecturador
        if (nuevoUsuario.getRoles().contains("lecturador"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_LECTURADOR).get());

        //Verificamos como creamos el usuario como plomero
        if (nuevoUsuario.getRoles().contains("plomero"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_PLOMERO).get());

        usuario.setRoles(roles);

        usuarioService.save(usuario);

        return new ResponseEntity(new Mensaje("Usuario actualizado"), HttpStatus.OK);

    }

    //ActualizarPassword
    @PutMapping("/actualizarpassword/{id}")
    public ResponseEntity<?> updateUsuarioPassword(@PathVariable("id") long id, @RequestBody NuevoUsuario nuevoUsuario) {

        //primero verificamos si existe el usuario
        if (!usuarioService.existsById(id) ) //Si no existe retornamos un mensaje
            return new ResponseEntity(new Mensaje("No existe el usuario"), HttpStatus.NOT_FOUND);


        Usuario usuario = usuarioService.getUsuario(id).get();
        usuario.setUsuario(nuevoUsuario.getUsuario());
        usuario.setPassword(passwordEncoder.encode(nuevoUsuario.getPassword()));
        usuario.setEstadoPassword(nuevoUsuario.isEstadoPassword());

        //Todos los usuarios por defecto seran usuarios BASICOS
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());

        //Verificamos como creamos el usuario como administrador
        if (nuevoUsuario.getRoles().contains("administrador"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMINISTRADOR).get());

        //Verificamos como creamos el usuario como cajero
        if (nuevoUsuario.getRoles().contains("cajero"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_CAJERO).get());

        //Verificamos como creamos el usuario como lecturador
        if (nuevoUsuario.getRoles().contains("lecturador"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_LECTURADOR).get());

        //Verificamos como creamos el usuario como plomero
        if (nuevoUsuario.getRoles().contains("plomero"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_PLOMERO).get());

        usuario.setRoles(roles);

        usuarioService.save(usuario);

        return new ResponseEntity(new Mensaje("Usuario actualizado"), HttpStatus.OK);

    }

    //Eliminar usuario
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> deleteSocio(@PathVariable("id") long id) {

        //primero verificamos si existe el socio
        if (!usuarioService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe el usuario"), HttpStatus.NOT_FOUND);

        usuarioService.delete(id);

        return new ResponseEntity(new Mensaje("usuario eliminado"), HttpStatus.OK);
    }
}
