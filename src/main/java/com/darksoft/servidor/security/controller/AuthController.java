package com.darksoft.servidor.security.controller;

import com.darksoft.servidor.dto.Mensaje;
import com.darksoft.servidor.security.dto.JwtDto;
import com.darksoft.servidor.security.dto.LoginUsuario;
import com.darksoft.servidor.security.dto.NuevoUsuario;
import com.darksoft.servidor.security.entity.Rol;
import com.darksoft.servidor.security.entity.Usuario;
import com.darksoft.servidor.security.enums.RolNombre;
import com.darksoft.servidor.security.jwt.JwtProvider;
import com.darksoft.servidor.security.service.RolService;
import com.darksoft.servidor.security.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    //Crear usuario
    @PostMapping("/nuevo")
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos mal puestos"), HttpStatus.BAD_REQUEST);

        if (usuarioService.existsByUsuario(nuevoUsuario.getUsuario()))
            return new ResponseEntity(new Mensaje("Nombre de usuario existente"), HttpStatus.BAD_REQUEST);

        Usuario usuario = new Usuario(nuevoUsuario.getUsuario(), passwordEncoder.encode(nuevoUsuario.getPassword()), nuevoUsuario.getSocio());

        //Todos los usuarios por defecto seran usuarios BASICOS
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());

        //Verificamos como creamos el usuario como administrador
        if(nuevoUsuario.getRoles().contains("administrador"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMINISTRADOR).get());

        //Verificamos como creamos el usuario como cajero
        if(nuevoUsuario.getRoles().contains("cajero"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_CAJERO).get());

        //Verificamos como creamos el usuario como lecturador
        if(nuevoUsuario.getRoles().contains("lecturador"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_LECTURADOR).get());

        //Verificamos como creamos el usuario como plomero
        if(nuevoUsuario.getRoles().contains("plomero"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_PLOMERO).get());

        usuario.setRoles(roles);

        usuarioService.save(usuario);

        return new ResponseEntity(new Mensaje("Usuario creado"), HttpStatus.CREATED);

    }

    //Login
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Usuario o password incorrectos"), HttpStatus.BAD_REQUEST);

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getUsuario(), loginUsuario.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());

        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }
}
