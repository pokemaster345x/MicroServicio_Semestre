package co.udu.uceva.semestreservice.model.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Semestre {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    private boolean activo;
    private LocalDate fechaFin;
    private LocalDate fechaInicio;
    private Long idPrograma;
    private long numeroSemestre;

}
