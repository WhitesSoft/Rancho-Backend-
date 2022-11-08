package com.darksoft.servidor.service;

import com.darksoft.servidor.entity.Solicitudes;
import com.darksoft.servidor.repository.SolicitudesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SolicitudesService {

    @Autowired
    SolicitudesRepository solicitudesRepository;

    //Listar todas las solicitudes
    public List<Solicitudes> getAllSolicitudes(){
        return solicitudesRepository.findAll();
    }

    //Listar todas los solicitudes del socio
    public List<Solicitudes> getAllSolicitudesBySocio(long id){
        return solicitudesRepository.findBySocio_IdSocio(id);
    }

    //Obtener una solicitud especifica
    public Optional<Solicitudes> getSolicitud(long id){
        return solicitudesRepository.findById(id);
    }

    //Guardar solicitud
    public void save(Solicitudes solicitudes){
        solicitudesRepository.save(solicitudes);
    }

    //Borrar solicitud
    public void delete(long id){
        solicitudesRepository.deleteById(id);
    }

    //Borrar todas las solicitudes del socio
    public void deleteAllSolicitudesBySocio(long id){
        solicitudesRepository.deleteBySocio_IdSocio(id);
    }

    //Existe solicitud por id?
    public boolean existsById(long id){
        return solicitudesRepository.existsById(id);
    }

}
