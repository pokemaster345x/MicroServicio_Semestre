package co.edu.uceva.semestreservice.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.uceva.semestreservice.model.entities.Semestre;
public interface ISemestreRepository extends JpaRepository<Semestre, Long> {
}
