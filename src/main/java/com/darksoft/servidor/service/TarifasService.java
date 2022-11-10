package com.darksoft.servidor.service;

import com.darksoft.servidor.entity.Tarifas;
import com.darksoft.servidor.repository.TarifasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TarifasService {

    @Autowired
    TarifasRepository tarifasRepository;

    //Listar todas las tarifas
    public List<Tarifas> getAllTarifas(){
        return tarifasRepository.findAll();
    }

    //Obtener una tarifa especifica
    public Optional<Tarifas> getTarifa(long id){
        return tarifasRepository.findById(id);
    }

    //Guarda tarifa
    public void save(Tarifas tarifas){
        tarifasRepository.save(tarifas);
    }

    //Borrar tarifa
    public void delete(long id){
        tarifasRepository.deleteById(id);
    }

    //Existe tarifa por id?
    public boolean existsById(long id){
        return tarifasRepository.existsById(id);
    }

}
