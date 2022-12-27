package com.darksoft.servidor.controller;

import com.darksoft.servidor.dto.Mensaje;
import com.darksoft.servidor.dto.SolicitudesDto;
import com.darksoft.servidor.entity.Solicitudes;
import com.darksoft.servidor.repository.SolicitudesRepository;
import com.darksoft.servidor.service.SocioService;
import com.darksoft.servidor.service.SolicitudesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solicitudes")
@CrossOrigin
public class SolicitudesController {

    @Autowired
    SolicitudesService solicitudesService;

    @Autowired
    SolicitudesRepository solicitudesRepository;

    @Autowired
    SocioService socioService;

    //Listar todas las solicitudes
    @PreAuthorize("hasRole('ADMINISTRADOR')") // SOLO UN ROL AUTORIZADO TIENE ACCESO
    @GetMapping("/lista") //Nos damos esta url
    public ResponseEntity<List<Solicitudes>> listarSolicitudes(){
        List<Solicitudes> listaSolicitudes = solicitudesService.getAllSolicitudes();
        return new ResponseEntity<>(listaSolicitudes, HttpStatus.OK);
    }

    //Listar todas las solicitudes por id del socio
    @GetMapping("/socio/{idSocio}/listasolicitudes")
    public ResponseEntity<List<Solicitudes>> listarSolicitudesBySocio(@PathVariable("idSocio") Long id){

        //Verificamos si existe el socio
        if(!socioService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el socio"), HttpStatus.NOT_FOUND);

        List<Solicitudes> listaSolicitudesBySocio = solicitudesService.getAllSolicitudesBySocio(id);
        return new ResponseEntity<>(listaSolicitudesBySocio, HttpStatus.OK);

    }

    //Ver solicitud por id
    @GetMapping("/detalles/{id}")
    public ResponseEntity<Solicitudes> getById(@PathVariable("id") long id){

        if(!solicitudesService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe la solicitud"), HttpStatus.NOT_FOUND);

        Solicitudes solicitudes = solicitudesService.getSolicitud(id).get();

        return new ResponseEntity<>(solicitudes, HttpStatus.OK);
    }

    //Crear solicitud para socio
    @PostMapping("/socio/{idSocio}/crearsolicitud")
    public ResponseEntity<Solicitudes> crearSolicitud(@PathVariable("idSocio") Long id, @RequestBody Solicitudes solicitudes) {

        //Verificamos si existe el socio
        if(!socioService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el socio"), HttpStatus.NOT_FOUND);

        Solicitudes addSolicitud = socioService.getSocio(id).map(socio -> {
            solicitudes.setSocio(socio);
            return solicitudesRepository.save(solicitudes);
        }).orElseThrow();

        new Mensaje("Solicitud creado al socio con el id: " + id);

        return new ResponseEntity<>(addSolicitud, HttpStatus.OK);
    }

    //Actualizar solicitud
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarSolicitud (@PathVariable("id") long id, @RequestBody SolicitudesDto solicitudesDto){

        //primero verificamos si existe la solicitud
        if(!solicitudesService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe la solicitud"), HttpStatus.NOT_FOUND);

        Solicitudes solicitudes = solicitudesService.getSolicitud(id).get();
        solicitudes.setDetalle(solicitudesDto.getDetalle());
        solicitudes.setFecha(solicitudesDto.getFecha());
        solicitudes.setAtendido(solicitudesDto.isAtendido());

        solicitudesService.save(solicitudes);

        return new ResponseEntity<>(new Mensaje("Solicitud actualizada"), HttpStatus.OK);
    }

    //Eliminar solicitud
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarSolicitud (@PathVariable("id") long id){

        //primero verificamos si existe la solicitud
        if(!solicitudesService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe la solicitud"), HttpStatus.NOT_FOUND);

        solicitudesService.delete(id);

        return new ResponseEntity<>(new Mensaje("Solicitud eliminada"), HttpStatus.OK);
    }

    //Eliminar todos las solicitudes del socio
    @DeleteMapping("/socio/{idSocio}/eliminarsolicitudes")
    public ResponseEntity<List<Solicitudes>> eliminarSolicitudesBySocio (@PathVariable("idSocio") long id){

        //primero verificamos si existe el socio
        if(!socioService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe el socio"), HttpStatus.NOT_FOUND);

        solicitudesService.deleteAllSolicitudesBySocio(id);

        return new ResponseEntity(new Mensaje("Solicitudes eliminados"), HttpStatus.OK);

    }

}
