package com.darksoft.servidor.controller;

import com.darksoft.servidor.dto.FacturaDto;
import com.darksoft.servidor.dto.Mensaje;
import com.darksoft.servidor.entity.Factura;
import com.darksoft.servidor.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facturas")
@CrossOrigin
public class FacturaController {

    @Autowired
    FacturaService facturaService;

    //Listar todas las facturas
    //@PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/lista")
    public ResponseEntity<List<Factura>> listarFacturas(){
        List<Factura> listaFacturas = facturaService.getAllFacturas();
        return new ResponseEntity<>(listaFacturas, HttpStatus.OK);
    }

    //Ver factura por id
    @GetMapping("/detalles/{id}")
    public ResponseEntity<Factura> getById(@PathVariable("id") long id){

        if(!facturaService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe la factura"), HttpStatus.NOT_FOUND);

        Factura factura = facturaService.getFactura(id).get();

        return new ResponseEntity<>(factura, HttpStatus.OK);
    }

    //Crear factura
    @PostMapping("/crearfactura")
    public ResponseEntity<?> crearFactura(@RequestBody FacturaDto facturaDto) {

        Factura factura =
                new Factura(facturaDto.getRazonSocial(), facturaDto.getNit(), facturaDto.getPeriodo(),
                        facturaDto.getMonto(), facturaDto.isEstado());

        facturaService.save(factura);

        return new ResponseEntity<>(new Mensaje("Factura creada"), HttpStatus.OK);
    }

    //Actualizar multa
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarFactura (@PathVariable("id") long id, @RequestBody FacturaDto facturaDto){

        //primero verificamos si existe la factura
        if(!facturaService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe la factura"), HttpStatus.NOT_FOUND);

        Factura factura = facturaService.getFactura(id).get();
        factura.setRazonSocial(facturaDto.getRazonSocial());
        factura.setNit(facturaDto.getNit());
        factura.setPeriodo(facturaDto.getPeriodo());
        factura.setMonto(facturaDto.getMonto());
        factura.setEstado(facturaDto.isEstado());

        facturaService.save(factura);

        return new ResponseEntity<>(new Mensaje("Factura actualizada"), HttpStatus.OK);
    }

    //Eliminar multa
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarFactura(@PathVariable("id") long id){

        //primero verificamos si existe la factura
        if(!facturaService.existsById(id)) //Si no existe returnamos un mensaje
            return new ResponseEntity(new Mensaje("No existe la factura"), HttpStatus.NOT_FOUND);

        facturaService.delete(id);

        return new ResponseEntity<>(new Mensaje("Factura eliminada"), HttpStatus.OK);
    }

}
