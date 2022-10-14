package com.darksoft.servidor.service;

import com.darksoft.servidor.entity.Medidor;
import com.darksoft.servidor.repository.MedidorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MedidorService {

    @Autowired
    MedidorRepository medidorRepository;

    //Listar todos los medidores
    public List<Medidor> getAllMedidores(){
        return medidorRepository.findAll();
    }

    //Listar todos los medidores del socio
    public List<Medidor> getAllMedidoresBySocio(long id){
        return medidorRepository.findBySocio_IdSocio(id);
    }

    //Obtener un medidor especifico
    public Optional<Medidor> getMedidor(long id){
        return medidorRepository.findById(id);
    }

    //Guardar medidor
    public void save(Medidor medidor){
        medidorRepository.save(medidor);
    }

    //Borrar medidor
    public void delete(long id){
        medidorRepository.deleteById(id);
    }

    //Borrar todos los medidores del socio
    public void deleteAllMedidoresBySocio(long id){
        medidorRepository.deleteBySocio_IdSocio(id);
    }

    //Existe medidor por id?
    public boolean existsById(long id){
        return medidorRepository.existsById(id);
    }
}
