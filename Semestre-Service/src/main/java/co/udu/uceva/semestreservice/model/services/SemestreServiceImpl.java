package co.udu.uceva.semestreservice.model.services;



import co.udu.uceva.semestreservice.model.entities.Semestre;
import co.udu.uceva.semestreservice.model.repositories.ISemestreRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class SemestreServiceImpl implements ISemestreService {

    ISemestreRepository semestreRepository;

    public SemestreServiceImpl(ISemestreRepository semestreRepository) {
        this.semestreRepository = semestreRepository;
    }

     @Override
     @Transactional
    public Semestre save(Semestre semestre) {
        return semestreRepository.save(semestre);
     }
     @Override
     @Transactional
    public void delete(Semestre semestre) {
        semestreRepository.delete(semestre);
     }
     @Override
     @Transactional(readOnly = true)
    public Semestre findById(long id) {
        return semestreRepository.findById(id).orElse(null);
     }
     @Override
     @Transactional
    public Semestre update(Semestre semestre) {
        return semestreRepository.save(semestre);
     }
     @Override
     @Transactional(readOnly = true)
    public List<Semestre> findAll(){
        return (List<Semestre>) semestreRepository.findAll();
     }
     @Override
    @Transactional(readOnly = true)
    public Page<Semestre> findAll(Pageable pageable) {
        return semestreRepository.findAll(pageable);
     }
}
