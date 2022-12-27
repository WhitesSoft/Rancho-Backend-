package com.darksoft.servidor.service;

import com.darksoft.servidor.entity.Cobros;
import com.darksoft.servidor.repository.CobrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CobrosService {

    @Autowired
    CobrosRepository cobrosRepository;

    //Listar todos los cobros
    public List<Cobros> getAllCobros(){
        return cobrosRepository.findAll();
    }

    //Obtener un cobro especifico por su id
    public Optional<Cobros> getCobro(long id){
        return cobrosRepository.findById(id);
    }

    //Borrar cobro
    public void delete(long id){
        cobrosRepository.deleteById(id);
    }

    //Guardar cobro
    public void save(Cobros cobros){
        cobrosRepository.save(cobros);
    }

    //Existe cobro por id?
    public boolean existsById(long id){
        return cobrosRepository.existsById(id);
    }

}
