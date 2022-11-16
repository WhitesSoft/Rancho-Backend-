package com.darksoft.servidor.controller;

import com.darksoft.servidor.dto.Mensaje;
import com.darksoft.servidor.dto.MultasDto;
import com.darksoft.servidor.entity.Multas;
import com.darksoft.servidor.repository.MultasRepository;
import com.darksoft.servidor.service.MultasService;
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

    //Listar todas las multas
    //@PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/lista")
    public ResponseEntity<List<Multas>> listarMultas(){
        List<Multas> listaMultas = multasService.getAllMultas();
        return new ResponseEntity<>(listaMultas, HttpStatus.OK);
    }

    //Ver multa por id
    @GetMapping("/detalles/{id}")
    public ResponseEntity<Multas> getById(@PathVariable("id") long id){

        if(!multasService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe la multa"), HttpStatus.NOT_FOUND);

        Multas multas = multasService.getMulta(id).get();

        return new ResponseEntity<>(multas, HttpStatus.OK);
    }

    //Crear multa
    @PostMapping("/crearmulta")
    public ResponseEntity<?> crearMulta(@RequestBody MultasDto multasDto) {

        Multas multas =
                new Multas(multasDto.getFechaVigencia(), multasDto.getMonto());

        multasService.save(multas);

        return new ResponseEntity<>(new Mensaje("Multa creada"), HttpStatus.OK);
    }

    //Actualizar multa
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarMulta (@PathVariable("id") long id, @RequestBody MultasDto multasDto){

        //primero verificamos si existe la multa
        if(!multasService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe la multa"), HttpStatus.NOT_FOUND);

        Multas multas = multasService.getMulta(id).get();
        multas.setFechaVigencia(multasDto.getFechaVigencia());
        multas.setMonto(multasDto.getMonto());

        multasService.save(multas);

        return new ResponseEntity<>(new Mensaje("Multa actualizada"), HttpStatus.OK);
    }

    //Eliminar multa
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarMulta(@PathVariable("id") long id){

        //primero verificamos si existe la multa
        if(!multasService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe la multa"), HttpStatus.NOT_FOUND);

        multasService.delete(id);

        return new ResponseEntity<>(new Mensaje("Multa eliminada"), HttpStatus.OK);
    }

}
