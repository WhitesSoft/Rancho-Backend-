package com.darksoft.servidor.service;

import com.darksoft.servidor.entity.Socio;
import com.darksoft.servidor.repository.SocioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SocioService {

    @Autowired
    SocioRepository socioRepository;

    //Listar todos los socios
    public List<Socio> getAllSocios(){
        return socioRepository.findAll();
    }

    //Obtener un socio especifico por su id
    public Optional<Socio> getSocio(long id){
        return socioRepository.findById(id);
    }

    //Guardar socio
    public void save(Socio socio){
        socioRepository.save(socio);
    }

    //Borrar socio
    public void delete(long id){
        socioRepository.deleteById(id);
    }

    //Existe socio por id?
    public boolean existsById(long id){
        return socioRepository.existsById(id);
    }

}
