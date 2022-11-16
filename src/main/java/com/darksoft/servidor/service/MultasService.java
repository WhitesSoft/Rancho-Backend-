package com.darksoft.servidor.service;

import com.darksoft.servidor.entity.Multas;
import com.darksoft.servidor.repository.MultasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MultasService {

    @Autowired
    MultasRepository multasRepository;

    //Listar todas las multas
    public List<Multas> getAllMultas(){
        return multasRepository.findAll();
    }

    //Obtener una multa especifica
    public Optional<Multas> getMulta(long id){
        return multasRepository.findById(id);
    }

    //Guarda multa
    public void save(Multas multas){
        multasRepository.save(multas);
    }

    //Borrar multa
    public void delete(long id){
        multasRepository.deleteById(id);
    }

    //Existe multa por id?
    public boolean existsById(long id){
        return multasRepository.existsById(id);
    }

}
