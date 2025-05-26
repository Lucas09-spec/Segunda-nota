package Estado.com.example.ESTADO.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Estado.com.example.ESTADO.Model.ESTADO;
import Estado.com.example.ESTADO.Repository.ESTADORepository;

@Service
public class ESTADOService {
    @Autowired 
    private ESTADORepository estadoRepository; 

    public List<ESTADO> obEstados(){
        return estadoRepository.findAll();
    }

    public ESTADO guardarEstado(ESTADO nuevo){
        return estadoRepository.save(nuevo);

    }

}
