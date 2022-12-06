package com.darksoft.servidor.controller;

import com.darksoft.servidor.dto.Mensaje;
import com.darksoft.servidor.dto.ReclamosDto;
import com.darksoft.servidor.entity.Reclamos;
import com.darksoft.servidor.repository.ReclamosRepository;
import com.darksoft.servidor.service.ReclamosService;
import com.darksoft.servidor.service.SocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reclamos")
@CrossOrigin
public class ReclamosController {

    @Autowired
    ReclamosService reclamosService;

    @Autowired
    ReclamosRepository reclamosRepository;

    @Autowired
    SocioService socioService;

    //Listar todos los reclamos
    @PreAuthorize("hasRole('ADMINISTRADOR')") // SOLO UN ROL AUTORIZADO TIENE ACCESO
    @GetMapping("/lista") //Nos damos esta url
    public ResponseEntity<List<Reclamos>> listarReclamos(){
        List<Reclamos> listaReclamos = reclamosService.getAllReclamos();
        return new ResponseEntity<>(listaReclamos, HttpStatus.OK);
    }

    //Listar todos los reclamos por id del socio
    @GetMapping("/socio/{id}/listareclamos")
    public ResponseEntity<List<Reclamos>> listarReclamosBySocio(@PathVariable("id") Long id){

        //Verificamos si existe el socio
        if(!socioService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el socio"), HttpStatus.NOT_FOUND);

        List<Reclamos> listaReclamosBySocio = reclamosService.getAllReclamosBySocio(id);
        return new ResponseEntity<>(listaReclamosBySocio, HttpStatus.OK);

    }

    //Ver reclamo por id
    @GetMapping("/detalles/{id}")
    public ResponseEntity<Reclamos> getById(@PathVariable("id") long id){

        if(!reclamosService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe el reclamo"), HttpStatus.NOT_FOUND);

        Reclamos reclamos = reclamosService.getReclamo(id).get();

        return new ResponseEntity<>(reclamos, HttpStatus.OK);
    }

    //Crear reclamo para socio
    @PostMapping("/socio/{id}/crearreclamo")
    public ResponseEntity<Reclamos> crearReclamo (@PathVariable("id") Long id, @RequestBody Reclamos reclamos) {

        //Verificamos si existe el socio
        if(!socioService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el socio"), HttpStatus.NOT_FOUND);

        Reclamos addReclamo = socioService.getSocio(id).map(socio -> {
            reclamos.setSocio(socio);
            return reclamosRepository.save(reclamos);
        }).orElseThrow();

        new Mensaje("Reclamo creado al socio con el id: " + id);

        return new ResponseEntity<>(addReclamo, HttpStatus.OK);
    }

    //Actualizar reclamo
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarReclamo (@PathVariable("id") long id, @RequestBody ReclamosDto reclamosDto){

        //primero verificamos si existe el reclamo
        if(!reclamosRepository.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe el reclamo"), HttpStatus.NOT_FOUND);

        Reclamos reclamos = reclamosService.getReclamo(id).get();
        reclamos.setDetalle(reclamosDto.getDetalle());
        reclamos.setFecha(reclamosDto.getFecha());
        reclamos.setAtendido(reclamosDto.isAtendido());
        reclamos.setFechaAtencion(reclamosDto.getFechaAtencion());
        reclamos.setResultado(reclamosDto.getResultado());

        reclamosService.save(reclamos);

        return new ResponseEntity<>(new Mensaje("Reclamo actualizado"), HttpStatus.OK);
    }

    //Eliminar reclamo
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarReclamo (@PathVariable("id") long id){

        //primero verificamos si existe el reclamo
        if(!reclamosService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe el reclamo"), HttpStatus.NOT_FOUND);

        reclamosService.delete(id);

        return new ResponseEntity<>(new Mensaje("Solicitud eliminada"), HttpStatus.OK);
    }

    //Eliminar todos los reclamos del socio
    @DeleteMapping("/socio/{id}/eliminarreclamos")
    public ResponseEntity<List<Reclamos>> eliminarSolicitudesBySocio (@PathVariable("id") long id){

        //primero verificamos si existe el socio
        if(!socioService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe el socio"), HttpStatus.NOT_FOUND);

        reclamosService.deleteAllReclamosBySocio(id);

        return new ResponseEntity(new Mensaje("Reclamos eliminados"), HttpStatus.OK);

    }

}
