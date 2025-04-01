package co.udu.uceva.semestreservice.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import co.udu.uceva.semestreservice.model.entities.Semestre;
public interface ISemestreRepository extends JpaRepository<Semestre, Long> {
}
