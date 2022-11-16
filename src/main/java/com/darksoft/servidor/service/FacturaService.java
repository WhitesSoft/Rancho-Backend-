package com.darksoft.servidor.service;

import com.darksoft.servidor.entity.Factura;
import com.darksoft.servidor.entity.Multas;
import com.darksoft.servidor.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FacturaService {

    @Autowired
    FacturaRepository facturaRepository;

    //Listar todas las facturas
    public List<Factura> getAllFacturas(){
        return facturaRepository.findAll();
    }

    //Obtener una factura especifica
    public Optional<Factura> getFactura(long id){
        return facturaRepository.findById(id);
    }

    //Guarda factura
    public void save(Factura factura){
        facturaRepository.save(factura);
    }

    //Borrar factura
    public void delete(long id){
        facturaRepository.deleteById(id);
    }

    //Existe factura por id?
    public boolean existsById(long id){
        return facturaRepository.existsById(id);
    }


}
