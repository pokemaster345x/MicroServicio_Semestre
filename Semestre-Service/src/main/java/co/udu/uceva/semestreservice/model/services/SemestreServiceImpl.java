package co.udu.uceva.semestreservice.model.services;



import co.udu.uceva.semestreservice.model.entities.Semestre;
import co.udu.uceva.semestreservice.model.repositories.ISemestreRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SemestreServiceImpl implements ISemestreService {

    ISemestreRepository semestreRepository;

    public SemestreServiceImpl(ISemestreRepository semestreRepository) {
        this.semestreRepository = semestreRepository;
    }

     @Override
    public Semestre save(Semestre semestre) {
        return semestreRepository.save(semestre);
     }
     @Override
    public void delete(Semestre semestre) {
        semestreRepository.delete(semestre);
     }
     @Override
    public Semestre findById(long id) {
        return semestreRepository.findById(id).orElse(null);
     }
     @Override
    public Semestre update(Semestre semestre) {
        return semestreRepository.save(semestre);
     }
     @Override
    public List<Semestre> findAll(){
        return (List<Semestre>) semestreRepository.findAll();
     }
}
