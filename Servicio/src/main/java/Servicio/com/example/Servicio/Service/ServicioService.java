package Servicio.com.example.Servicio.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Servicio.com.example.Servicio.Model.Servicio;
import Servicio.com.example.Servicio.Repository.ServicioRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ServicioService{
    @Autowired
    private ServicioRepository servicioRepository; 
    public List<Servicio> ObtenerLista(){
        return servicioRepository.findAll();
    }
    public Servicio guardarServicio(Servicio nuevo){
        return servicioRepository.save(nuevo);
    }

    public boolean eliminarServicio(long id) {
        if (servicioRepository.existsById(id)) {
            servicioRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }




}
