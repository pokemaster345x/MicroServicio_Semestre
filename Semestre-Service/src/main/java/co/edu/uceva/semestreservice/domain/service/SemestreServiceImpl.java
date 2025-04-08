package co.edu.uceva.semestreservice.domain.service;

import co.edu.uceva.semestreservice.domain.model.Semestre;
import co.edu.uceva.semestreservice.domain.repository.ISemestreRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;


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
    public Optional<Semestre> findById(long id) {
        return semestreRepository.findById(id);
    }
    @Override
    @Transactional
    public Semestre update(Semestre semestre) {
        return semestreRepository.save(semestre);
    }
    @Override
    @Transactional(readOnly = true)
    public List<Semestre> findAll(){
        return semestreRepository.findAll();
    }
    @Override
    @Transactional(readOnly = true)
    public Page<Semestre> findAll(Pageable pageable) {
        return semestreRepository.findAll(pageable);
    }
}
