package com.darksoft.servidor.service;

import com.darksoft.servidor.entity.Comunicados;
import com.darksoft.servidor.entity.Solicitudes;
import com.darksoft.servidor.repository.ComunicadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ComunicadosService {

    @Autowired
    ComunicadosRepository comunicadosRepository;

    //Listar todos los comunicados
    public List<Comunicados> getAllComunicados(){
        return comunicadosRepository.findAll();
    }

    //Listar todos los comunicados del usuario
    public List<Comunicados> getAllComunicadosByUsuario(long id){
        return comunicadosRepository.findByUsuario_Id(id);
    }

    //Obtener un comunicado especifica
    public Optional<Comunicados> getComunicado(long id){
        return comunicadosRepository.findById(id);
    }

    //Guardar comunicado
    public void save(Comunicados comunicados){
        comunicadosRepository.save(comunicados);
    }

    //Borrar comunicado
    public void delete(long id){
        comunicadosRepository.deleteById(id);
    }

    //Borrar todas los comunicados del usuario
    public void deleteAllComunicadosByUsuario(long id){
        comunicadosRepository.deleteByUsuario_Id(id);
    }

    //Existe comunicado por id?
    public boolean existsById(long id){
        return comunicadosRepository.existsById(id);
    }

}
