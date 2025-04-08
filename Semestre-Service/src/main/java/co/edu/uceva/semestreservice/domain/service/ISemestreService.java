package co.edu.uceva.semestreservice.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.edu.uceva.semestreservice.domain.model.Semestre;
import java.util.List;
import java.util.Optional;
public interface ISemestreService {
    Semestre save(Semestre semestre);
    void delete(Semestre semestre);
    Optional<Semestre> findById(long id);
    List<Semestre> findAll();
    Semestre update(Semestre semestre);
    Page<Semestre> findAll(Pageable pageable);
}