package com.darksoft.servidor.controller;

import com.darksoft.servidor.dto.Mensaje;
import com.darksoft.servidor.dto.TarifasDto;
import com.darksoft.servidor.entity.Tarifas;
import com.darksoft.servidor.repository.TarifasRepository;
import com.darksoft.servidor.service.TarifasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarifas")
@CrossOrigin
public class TarifasController {

    @Autowired
    TarifasService tarifasService;

    @Autowired
    TarifasRepository tarifasRepository;

    //Listar todas las  tarifas
    //@PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/lista")
    public ResponseEntity<List<Tarifas>> listarTarifas(){
        List<Tarifas> listaTarifas = tarifasService.getAllTarifas();
        return new ResponseEntity<>(listaTarifas, HttpStatus.OK);
    }

    //Ver tarifa por id
    @GetMapping("/detalles/{id}")
    public ResponseEntity<Tarifas> getById(@PathVariable("id") long id){

        if(!tarifasService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe la tarifa"), HttpStatus.NOT_FOUND);

        Tarifas tarifas = tarifasService.getTarifa(id).get();

        return new ResponseEntity<>(tarifas, HttpStatus.OK);
    }

    //Crear tarifa
    @PostMapping("/creartarifa")
    public ResponseEntity<?> crearTarifa(@RequestBody TarifasDto tarifasDto) {

        Tarifas tarifas =
                new Tarifas(tarifasDto.getFechaInicio(), tarifasDto.getConsumoMaximo(), tarifasDto.getCostoUnitario());

        tarifasService.save(tarifas);

        return new ResponseEntity<>(new Mensaje("Tarifa creada"), HttpStatus.OK);
    }

    //Actualizar tarifa
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarTarifa (@PathVariable("id") long id, @RequestBody TarifasDto tarifasDto){

        //primero verificamos si existe la tarifa
        if(!tarifasService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe la tarifa"), HttpStatus.NOT_FOUND);

        Tarifas tarifas = tarifasService.getTarifa(id).get();
        tarifas.setFechaInicio(tarifasDto.getFechaInicio());
        tarifas.setConsumoMaximo(tarifasDto.getConsumoMaximo());
        tarifas.setCostoUnitario(tarifasDto.getCostoUnitario());

        tarifasService.save(tarifas);

        return new ResponseEntity<>(new Mensaje("Tarifa actualizado"), HttpStatus.OK);
    }

    //Eliminar tarifa
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarTarifa (@PathVariable("id") long id){

        //primero verificamos si existe la tarifa
        if(!tarifasService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe la tarifa"), HttpStatus.NOT_FOUND);

        tarifasService.delete(id);

        return new ResponseEntity<>(new Mensaje("Tarifa eliminada"), HttpStatus.OK);
    }


}
