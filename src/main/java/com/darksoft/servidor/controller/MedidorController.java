package com.darksoft.servidor.controller;

import com.darksoft.servidor.dto.MedidorDto;
import com.darksoft.servidor.dto.Mensaje;
import com.darksoft.servidor.entity.Medidor;
import com.darksoft.servidor.repository.MedidorRepository;
import com.darksoft.servidor.service.MedidorService;
import com.darksoft.servidor.service.SocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medidor")
@CrossOrigin
public class MedidorController {

    @Autowired
    MedidorService medidorService;

    @Autowired
    SocioService socioService;

    @Autowired
    MedidorRepository medidorRepository;

    //Listar todos los medidores
    @PreAuthorize("hasRole('ADMINISTRADOR')") // SOLO UN ROL AUTORIZADO TIENE ACCESO
    @GetMapping("/lista") //Nos datos esta url?
    public ResponseEntity<List<Medidor>> listarMedidores(){
        List<Medidor> listaMedidores = medidorService.getAllMedidores();
        return new ResponseEntity<>(listaMedidores, HttpStatus.OK);
    }

    //Listar todos los medidores por id del socio
    //@PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/socio/{idSocio}/listamedidores")
    public ResponseEntity<List<Medidor>> listarMedidoresBySocio(@PathVariable("idSocio") Long id){

        //Verificamos si existe el socio
        if(!socioService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el socio"), HttpStatus.NOT_FOUND);

        List<Medidor> listaMedidoresBySocio = medidorService.getAllMedidoresBySocio(id);
        return new ResponseEntity<>(listaMedidoresBySocio, HttpStatus.OK);
    }

    //Ver medidor por id
    @GetMapping("/detalles/{id}")
    public ResponseEntity<Medidor> getById(@PathVariable("id") long id){

        if(!medidorService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe el medidor"), HttpStatus.NOT_FOUND);

        Medidor medidor = medidorService.getMedidor(id).get();

        return new ResponseEntity<>(medidor, HttpStatus.OK);
    }

    //Crear medidor para socio
    @PostMapping("/socio/{idSocio}/crearmedidor")
    public ResponseEntity<Medidor> crearMedidor(@PathVariable("idSocio") Long id, @RequestBody Medidor medidor) {

        //Verificamos si existe el socio
        if(!socioService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el socio"), HttpStatus.NOT_FOUND);

        Medidor addMedidor = socioService.getSocio(id).map(socio -> {
            medidor.setSocio(socio);
            return medidorRepository.save(medidor);
        }).orElseThrow();

        new Mensaje("Medidor creado al socio con el id: " + id);

        return new ResponseEntity<>(addMedidor, HttpStatus.OK);
    }

    //Actualizar medidor
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarMedidor (@PathVariable("id") long id, @RequestBody MedidorDto medidorDto){

        //primero verificamos si existe el medidor
        if(!medidorService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe el medidor"), HttpStatus.NOT_FOUND);

        Medidor medidor = medidorService.getMedidor(id).get();
        medidor.setSerial(medidorDto.getSerial());
        medidor.setMarca(medidorDto.getMarca());
        medidor.setRegistroInicio(medidorDto.getRegistroInicio());
        medidor.setFechaInstalacion(medidorDto.getFechaInstalacion());

        medidorService.save(medidor);

        return new ResponseEntity<>(new Mensaje("Medidor actualizado"), HttpStatus.OK);
    }

    //Eliminar medidor
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarMedidor (@PathVariable("id") long id){

        //primero verificamos si existe el medidor
        if(!medidorService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe el medidor"), HttpStatus.NOT_FOUND);

        medidorService.delete(id);

        return new ResponseEntity<>(new Mensaje("Medidor eliminado"), HttpStatus.OK);
    }

    //Eliminar todos los medidores del socio
    @DeleteMapping("/socio/{idSocio}/eliminarmedidores")
    public ResponseEntity<List<Medidor>> eliminarMedidoresBySocio (@PathVariable("idSocio") long id){

        //primero verificamos si existe el socio
        if(!socioService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe el socio"), HttpStatus.NOT_FOUND);

        medidorService.deleteAllMedidoresBySocio(id);

        return new ResponseEntity(new Mensaje("Medidores borrados"), HttpStatus.OK);

    }
}
