package com.darksoft.servidor.service;

import com.darksoft.servidor.entity.Reclamos;
import com.darksoft.servidor.repository.ReclamosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReclamosService {

    @Autowired
    ReclamosRepository reclamosRepository;

    //Listar todos los reclamos
    public List<Reclamos> getAllReclamos(){
        return reclamosRepository.findAll();
    }

    //Listar todos los reclamos del socio
    public List<Reclamos> getAllReclamosBySocio(long id){
        return reclamosRepository.findBySocio_IdSocio(id);
    }

    //Obtener un reclamo especifico
    public Optional<Reclamos> getReclamo(long id){
        return reclamosRepository.findById(id);
    }

    //Guardar reclamo
    public void save(Reclamos reclamos){
        reclamosRepository.save(reclamos);
    }

    //Borrar reclamo
    public void delete(long id){
        reclamosRepository.deleteById(id);
    }

    //Borrar todos los reclamos del socio
    public void deleteAllReclamosBySocio(long id){
        reclamosRepository.deleteBySocio_IdSocio(id);
    }

    //Existe reclamo por id?
    public boolean existsById(long id){
        return reclamosRepository.existsById(id);
    }

}
