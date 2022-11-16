package com.darksoft.servidor.service;

import com.darksoft.servidor.entity.Consumos;
import com.darksoft.servidor.repository.ConsumosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ConsumosService {

    @Autowired
    ConsumosRepository consumosRepository;

    //Listar todos los consumos
    public List<Consumos> getAllConsumos(){
        return consumosRepository.findAll();
    }

    //Listar todos los consumos del medidor
    public List<Consumos> getAllConsumosByMedidor(long id){
        return consumosRepository.findByMedidor_Id(id);
    }

    //Obtener un consumo especifico
    public Optional<Consumos> getConsumo(long id){
        return consumosRepository.findById(id);
    }

    //Guardar consumo
    public void save(Consumos consumos){
        consumosRepository.save(consumos);
    }

    //Borrar consumo
    public void delete(long id){
        consumosRepository.deleteById(id);
    }

    //Borrar todos los consumos del medidor
    public void deleteAllConsumosByMedidor(long id){
        consumosRepository.deleteByMedidor_Id(id);
    }

    //Existe consumo por id?
    public boolean existsById(long id){
        return consumosRepository.existsById(id);
    }


}
