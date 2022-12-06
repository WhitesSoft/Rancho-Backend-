package com.darksoft.servidor.controller;

import com.darksoft.servidor.dto.Mensaje;
import com.darksoft.servidor.dto.MultasDto;
import com.darksoft.servidor.entity.Multas;
import com.darksoft.servidor.repository.MultasRepository;
import com.darksoft.servidor.service.MultasService;
import com.darksoft.servidor.service.SocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/multas")
@CrossOrigin
public class MultasController {

    @Autowired
    MultasService multasService;

    @Autowired
    MultasRepository multasRepository;

    @Autowired
    SocioService socioService;

    //Listar todas las multas
    //@PreAuthorize("hasRole('ADMINISTRADOR')") // SOLO UN ROL AUTORIZADO TIENE ACCESO
    @GetMapping("/lista") //Nos datos esta url?
    public ResponseEntity<List<Multas>> listarMultas(){
        List<Multas> listaMultas = multasService.getAllMultas();
        return new ResponseEntity<>(listaMultas, HttpStatus.OK);
    }

    //Listar todas las multas por id del socio
    //@PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/socio/{idSocio}/listamedidores")
    public ResponseEntity<List<Multas>> listarMultasBySocio(@PathVariable("idSocio") Long id){

        //Verificamos si existe el socio
        if(!socioService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el socio"), HttpStatus.NOT_FOUND);

        List<Multas> listaMultasBySocio = multasService.getAllMultasBySocio(id);
        return new ResponseEntity<>(listaMultasBySocio, HttpStatus.OK);

    }

    //Ver multa por id
    @GetMapping("/detalles/{id}")
    public ResponseEntity<Multas> getById(@PathVariable("id") long id){

        if(!multasService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe la multa"), HttpStatus.NOT_FOUND);

        Multas multas = multasService.getMulta(id).get();

        return new ResponseEntity<>(multas, HttpStatus.OK);
    }

    //Crear multa para socio
    @PostMapping("/socio/{idSocio}/crearmulta")
    public ResponseEntity<Multas> crearMulta (@PathVariable("idSocio") Long id, @RequestBody Multas multas) {

        //Verificamos si existe el socio
        if(!socioService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el socio"), HttpStatus.NOT_FOUND);

        Multas addMulta = socioService.getSocio(id).map(socio -> {
            multas.setSocio(socio);
            return multasRepository.save(multas);
        }).orElseThrow();

        new Mensaje("Multa creado al socio con el id: " + id);

        return new ResponseEntity<>(addMulta, HttpStatus.OK);
    }

    //Actualizar medidor
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarMulta (@PathVariable("id") long id, @RequestBody MultasDto multasDto){

        //primero verificamos si existe el medidor
        if(!multasService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe la multa"), HttpStatus.NOT_FOUND);

        Multas multas = multasService.getMulta(id).get();
        multas.setMonto(multasDto.getMonto());
        multas.setFechaVigencia(multasDto.getFechaVigencia());

        multasService.save(multas);

        return new ResponseEntity<>(new Mensaje("Multa actualizada"), HttpStatus.OK);
    }

    //Eliminar multa
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarMulta (@PathVariable("id") long id){

        //primero verificamos si existe el medidor
        if(!multasService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe la multa"), HttpStatus.NOT_FOUND);

        multasService.delete(id);

        return new ResponseEntity<>(new Mensaje("Multa eliminada"), HttpStatus.OK);
    }

    //Eliminar todas las multas del socio
    @DeleteMapping("/socio/{idSocio}/eliminarmultas")
    public ResponseEntity<List<Multas>> eliminarMultasBySocio (@PathVariable("idSocio") long id){

        //primero verificamos si existe el socio
        if(!socioService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe el socio"), HttpStatus.NOT_FOUND);

        multasService.deleteAllMultasBySocio(id);

        return new ResponseEntity(new Mensaje("Multas borradas"), HttpStatus.OK);

    }

}
