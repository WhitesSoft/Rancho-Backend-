package com.darksoft.servidor.controller;

import com.darksoft.servidor.dto.CobrosDto;
import com.darksoft.servidor.dto.Mensaje;
import com.darksoft.servidor.entity.Cobros;
import com.darksoft.servidor.service.CobrosService;
import com.darksoft.servidor.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cobros")
@CrossOrigin
public class CobrosController {

    @Autowired
    CobrosService cobrosService;

    @Autowired
    FacturaService facturaService;

    //Listar todos los cobros
    //@PreAuthorize("hasRole('ADMINISTRADOR')") // SOLO UN ROL AUTORIZADO TIENE ACCESO
    @GetMapping("/lista") //Nos datos esta url?
    public ResponseEntity<List<Cobros>> listarCobros(){
        List<Cobros> listaCobros = cobrosService.getAllCobros();
        return new ResponseEntity<>(listaCobros, HttpStatus.OK);
    }

    //Crear cobro
    @PostMapping("/crearcobro")
    public ResponseEntity<?> crearCobro(@Valid @RequestBody CobrosDto cobrosDto){

        Cobros cobros = new Cobros(cobrosDto.getFechaHora(), cobrosDto.getMonto(), cobrosDto.getFactura());

        cobrosService.save(cobros);

        return new ResponseEntity(new Mensaje("Cobro creado"), HttpStatus.CREATED);

    }

    //Ver cobro por id
    @GetMapping("/detalles/{id}")
    public ResponseEntity<Cobros> getById(@PathVariable("id") long id){

        if(!cobrosService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe el cobro"), HttpStatus.NOT_FOUND);

        Cobros cobros = cobrosService.getCobro(id).get();

        return new ResponseEntity<>(cobros, HttpStatus.OK);
    }


    //Actualizar cobro
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarCobro (@PathVariable("id") long id, @RequestBody CobrosDto cobrosDto){

        //primero verificamos si existe el cobro
        if(!cobrosService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe el cobro"), HttpStatus.NOT_FOUND);

        Cobros cobros = cobrosService.getCobro(id).get();

        cobros.setFechaHora(cobrosDto.getFechaHora());
        cobros.setMonto(cobrosDto.getMonto());
        cobros.setFactura(cobrosDto.getFactura());

        cobrosService.save(cobros);

        return new ResponseEntity<>(new Mensaje("Cobro actualizado"), HttpStatus.OK);
    }

    //Eliminar cobro
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarCobro (@PathVariable("id") long id){

        //primero verificamos si existe el cobro
        if(!cobrosService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe el cobro"), HttpStatus.NOT_FOUND);

        cobrosService.delete(id);

        return new ResponseEntity<>(new Mensaje("Consumo eliminado"), HttpStatus.OK);
    }

}
