package co.edu.uceva.semestreservice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.uceva.semestreservice.domain.model.Semestre;
public interface ISemestreRepository extends JpaRepository<Semestre, Long> {
}
