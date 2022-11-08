package com.darksoft.servidor.controller;

import com.darksoft.servidor.dto.ComunicadosDto;
import com.darksoft.servidor.dto.Mensaje;
import com.darksoft.servidor.entity.Comunicados;
import com.darksoft.servidor.repository.ComunicadosRepository;
import com.darksoft.servidor.security.service.UsuarioService;
import com.darksoft.servidor.service.ComunicadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comunicados")
@CrossOrigin
public class ComunicadosController {

    @Autowired
    ComunicadosService comunicadosService;

    @Autowired
    ComunicadosRepository comunicadosRepository;

    @Autowired
    UsuarioService usuarioService;

    //Listar todas las solicitudes
    //@PreAuthorize("hasRole('ADMINISTRADOR')") // SOLO UN ROL AUTORIZADO TIENE ACCESO
    @GetMapping("/lista") //Nos damos esta url
    public ResponseEntity<List<Comunicados>> listarComunicados(){
        List<Comunicados> listaComunicados = comunicadosService.getAllComunicados();
        return new ResponseEntity<>(listaComunicados, HttpStatus.OK);
    }

    //Listar todas las solicitudes por id del usuario
    @GetMapping("/usuario/{id}/listacomunicados")
    public ResponseEntity<List<Comunicados>> listarComunicadosByUsuario(@PathVariable("id") Long id){

        //Verificamos si existe el usuario
        if(!usuarioService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el usuario"), HttpStatus.NOT_FOUND);

        List<Comunicados> listaComunicadosByUsuario = comunicadosService.getAllComunicadosByUsuario(id);
        return new ResponseEntity<>(listaComunicadosByUsuario, HttpStatus.OK);

    }

    //Ver comunicado por id
    @GetMapping("/detalles/{id}")
    public ResponseEntity<Comunicados> getById(@PathVariable("id") long id){

        if(!comunicadosService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe el comunicado"), HttpStatus.NOT_FOUND);

        Comunicados comunicados = comunicadosService.getComunicado(id).get();

        return new ResponseEntity<>(comunicados, HttpStatus.OK);
    }

    //Crear comunicado para usuario
    @PostMapping("/usuario/{id}/crearcomunicado")
    public ResponseEntity<Comunicados> crearComunicado(@PathVariable("id") Long id, @RequestBody Comunicados comunicados) {

        //Verificamos si existe el socio
        if(!usuarioService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el usuario"), HttpStatus.NOT_FOUND);

        Comunicados addComunicado = usuarioService.getUsuario(id).map(usuario -> {
            comunicados.setUsuario(usuario);
            return comunicadosRepository.save(comunicados);
        }).orElseThrow();

        new Mensaje("Comunicado creado al usuario con el id: " + id);

        return new ResponseEntity<>(addComunicado, HttpStatus.OK);
    }

    //Actualizar comunicado
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarComunicado (@PathVariable("id") long id, @RequestBody ComunicadosDto comunicadosDto){

        //primero verificamos si existe la solicitud
        if(!comunicadosService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe el comunicado"), HttpStatus.NOT_FOUND);

        Comunicados comunicados = comunicadosService.getComunicado(id).get();
        comunicados.setDescripcion(comunicadosDto.getDescripcion());
        comunicados.setFechaInicio(comunicadosDto.getFechaInicio());
        comunicados.setVigencia(comunicadosDto.getVigencia());

        comunicadosService.save(comunicados);

        return new ResponseEntity<>(new Mensaje("Comunicado actualizado"), HttpStatus.OK);
    }

    //Eliminar comunicado
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarComunicado (@PathVariable("id") long id){

        //primero verificamos si existe el comunicado
        if(!comunicadosService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe el comunicado"), HttpStatus.NOT_FOUND);

        comunicadosService.delete(id);

        return new ResponseEntity<>(new Mensaje("Comunicado eliminado"), HttpStatus.OK);
    }

    //Eliminar todos los comunicado del usuario
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @DeleteMapping("/usuario/{id}/eliminarcomunicados")
    public ResponseEntity<List<Comunicados>> eliminarComunicadosByUsuario (@PathVariable("id") long id){

        //primero verificamos si existe el socio
        if(!usuarioService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe el usuario"), HttpStatus.NOT_FOUND);

        comunicadosService.deleteAllComunicadosByUsuario(id);

        return new ResponseEntity(new Mensaje("Comunicados eliminados"), HttpStatus.OK);

    }

}
