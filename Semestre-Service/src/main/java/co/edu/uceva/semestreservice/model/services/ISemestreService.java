package co.edu.uceva.semestreservice.model.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.edu.uceva.semestreservice.model.entities.Semestre;
import java.util.List;

public interface ISemestreService {
    Semestre save(Semestre semestre);
    void delete(Semestre semestre);
    Semestre findById(long id);
    List<Semestre> findAll();
    Semestre update(Semestre semestre);
    Page<Semestre> findAll(Pageable pageable);
}