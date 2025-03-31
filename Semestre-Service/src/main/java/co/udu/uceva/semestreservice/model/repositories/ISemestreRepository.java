package co.udu.uceva.semestreservice.model.repositories;

import org.springframework.data.repository.CrudRepository;
import co.udu.uceva.semestreservice.model.entities.Semestre;
public interface ISemestreRepository extends CrudRepository<Semestre, Long> {
}
