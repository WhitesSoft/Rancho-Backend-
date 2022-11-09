package com.darksoft.servidor.controller;

import com.darksoft.servidor.dto.ConsumosDto;
import com.darksoft.servidor.dto.MedidorDto;
import com.darksoft.servidor.dto.Mensaje;
import com.darksoft.servidor.entity.Consumos;
import com.darksoft.servidor.entity.Medidor;
import com.darksoft.servidor.repository.ConsumosRepository;
import com.darksoft.servidor.service.ConsumosService;
import com.darksoft.servidor.service.MedidorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumos")
@CrossOrigin
public class ConsumosController {

    @Autowired
    ConsumosService consumosService;

    @Autowired
    ConsumosRepository consumosRepository;

    @Autowired
    MedidorService medidorService;

    //Listar todos los consumos
    @PreAuthorize("hasRole('ADMINISTRADOR')") // SOLO UN ROL AUTORIZADO TIENE ACCESO
    @GetMapping("/lista") //Nos datos esta url?
    public ResponseEntity<List<Consumos>> listarConsumos(){
        List<Consumos> listaConsumos = consumosService.getAllConsumos();
        return new ResponseEntity<>(listaConsumos, HttpStatus.OK);
    }

    //Listar todos los consumos por id del medidor
    //@PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/medidor/{id}/listaconsumo")
    public ResponseEntity<List<Consumos>> listarConsumosByMedidor(@PathVariable("id") Long id){

        //Verificamos si existe el medidor
        if(!medidorService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el medidor"), HttpStatus.NOT_FOUND);

        List<Consumos> listaConsumosByMedidor = consumosService.getAllConsumosByMedidor(id);
        return new ResponseEntity<>(listaConsumosByMedidor, HttpStatus.OK);

    }

    //Ver consumo por id
    @GetMapping("/detalles/{id}")
    public ResponseEntity<Consumos> getById(@PathVariable("id") long id){

        if(!consumosService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe el consumo"), HttpStatus.NOT_FOUND);

        Consumos consumos = consumosService.getConsumo(id).get();

        return new ResponseEntity<>(consumos, HttpStatus.OK);
    }

    //Crear consumo para medidor
    @PostMapping("/medidor/{id}/crearconsumo")
    public ResponseEntity<Consumos> crearConsumo(@PathVariable("id") Long id, @RequestBody Consumos consumos) {

        //Verificamos si existe el medidor
        if(!medidorService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el medidor"), HttpStatus.NOT_FOUND);

        Consumos addConsumo = medidorService.getMedidor(id).map(medidor -> {
            consumos.setMedidor(medidor);
            return consumosRepository.save(consumos);
        }).orElseThrow();

        new Mensaje("Consumo creado al medidor con el id: " + id);

        return new ResponseEntity<>(addConsumo, HttpStatus.OK);
    }

    //Actualizar consumo
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarConsumo (@PathVariable("id") long id, @RequestBody ConsumosDto consumosDto){

        //primero verificamos si existe el consumo
        if(!consumosService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe el consumo"), HttpStatus.NOT_FOUND);

        Consumos consumos = consumosService.getConsumo(id).get();
        consumos.setFecha(consumosDto.getFecha());
        consumos.setLectura(consumosDto.getLectura());

        consumosService.save(consumos);

        return new ResponseEntity<>(new Mensaje("Consumo actualizado"), HttpStatus.OK);
    }

    //Eliminar consumo
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarConsumo (@PathVariable("id") long id){

        //primero verificamos si existe el consumo
        if(!consumosService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe el consumo"), HttpStatus.NOT_FOUND);

        consumosService.delete(id);

        return new ResponseEntity<>(new Mensaje("Consumo eliminado"), HttpStatus.OK);
    }

    //Eliminar todos los consumos del medidor
    @DeleteMapping("/medidor/{id}/eliminarconsumos")
    public ResponseEntity<List<Consumos>> eliminarConsumosByMedidor (@PathVariable("id") long id){

        //primero verificamos si existe el medidor
        if(!medidorService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe el medidor"), HttpStatus.NOT_FOUND);

        consumosService.deleteAllConsumosByMedidor(id);

        return new ResponseEntity(new Mensaje("Consumos borrados"), HttpStatus.OK);

    }


}
