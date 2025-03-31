package co.udu.uceva.semestreservice.model.services;


import co.udu.uceva.semestreservice.model.entities.Semestre;

import java.util.List;

public interface ISemestreService {
    Semestre save(Semestre semestre);
    void delete(Semestre semestre);
    Semestre findById(long id);
    List<Semestre> findAll();
    Semestre update(Semestre semestre);

}
