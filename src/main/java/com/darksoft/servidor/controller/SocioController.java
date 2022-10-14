package com.darksoft.servidor.controller;

import com.darksoft.servidor.dto.Mensaje;
import com.darksoft.servidor.dto.SocioDto;
import com.darksoft.servidor.entity.Socio;
import com.darksoft.servidor.service.SocioService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/socio")
@CrossOrigin(origins = "http://localhost:8100")//Puerto de angular http://localhost:4200
public class SocioController {

    @Autowired
    SocioService socioService;

    //Listar los socios
    @GetMapping("/lista") //Nos damos un url para acceder luego
    public ResponseEntity<List<Socio>> listarSocios(){
        List<Socio> listaSocios = socioService.getAllSocios();
        return new ResponseEntity<>(listaSocios, HttpStatus.OK);
    }

    //Ver socio por id
    @GetMapping("/detalles/{id}")
    public ResponseEntity<Socio> getById(@PathVariable("id") long id){

        if(!socioService.existsById(id)) //Si no existe el socio retornamos un mensaje
            return new ResponseEntity(new Mensaje("No existe el socio"), HttpStatus.NOT_FOUND);

        Socio socio = socioService.getSocio(id).get();

        return new ResponseEntity<>(socio, HttpStatus.OK);
    }

    //Crear socio
    @PostMapping("/crear")
    public ResponseEntity<?> createSocio (@RequestBody SocioDto socioDto){

        //Aqui deberiamos validar que todos los campos existan pero por el momento, solo valido el nombre
        if(StringUtils.isBlank(socioDto.getNombres()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        Socio socio = new Socio(socioDto.getNombres(), socioDto.getApellidos(), socioDto.getCorreo(),
                socioDto.getFechaNacimiento(), socioDto.getDireccion(), socioDto.isActivo());

        socioService.save(socio);

        return new ResponseEntity<>(new Mensaje("Socio creado"), HttpStatus.OK);
    }

    //Editar socio
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> updateSocio (@PathVariable("id") long id, @RequestBody SocioDto socioDto){

        //primero verificamos si existe el socio
        if(!socioService.existsById(id)) //Si no existe retornamos un mensaje
            return new ResponseEntity(new Mensaje("No existe el socio"), HttpStatus.NOT_FOUND);

        //Comprpbamos que no esten en nulos los campos
        if(StringUtils.isBlank(socioDto.getNombres()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        Socio socio = socioService.getSocio(id).get();
        socio.setNombres(socioDto.getNombres());
        socio.setApellidos(socioDto.getApellidos());
        socio.setCorreo(socioDto.getCorreo());
        socio.setFechaNacimiento(socioDto.getFechaNacimiento());
        socio.setDireccion(socioDto.getDireccion());
        socio.setActivo(socioDto.isActivo());

        socioService.save(socio);

        return new ResponseEntity<>(new Mensaje("Socio actualizado"), HttpStatus.OK);
    }

    //Eliminar socio
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> deleteSocio (@PathVariable("id") long id){

        //primero verificamos si existe el socio
        if(!socioService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe el socio"), HttpStatus.NOT_FOUND);

        socioService.delete(id);

        return new ResponseEntity<>(new Mensaje("Socio eliminado"), HttpStatus.OK);
    }
}

