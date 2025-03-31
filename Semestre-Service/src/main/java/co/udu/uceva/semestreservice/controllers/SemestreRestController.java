package co.udu.uceva.semestreservice.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import co.udu.uceva.semestreservice.model.entities.Semestre;
import co.udu.uceva.semestreservice.model.services.ISemestreService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/semestre-service")
public class SemestreRestController {

    private ISemestreService semestreService;


    @Autowired
    public SemestreRestController(ISemestreService semestreService) {
        this.semestreService = semestreService;
    }

    @GetMapping("/semestres")
    public List<Semestre> getSemestres() {
        return semestreService.findAll();
    }

    @PostMapping("/semestres")
    public Semestre save(Semestre semestre) {
        return semestreService.save(semestre);
    }

    @DeleteMapping("/semestres")
    public void delete(Semestre semestre) {
        semestreService.delete(semestre);
    }
    @PutMapping("/semestres")
    public Semestre update(@RequestBody Semestre semestre) {
        return semestreService.update(semestre);
    }
    @GetMapping("/semestres/{id}")
    public Semestre findById(@PathVariable long id) {
        return semestreService.findById(id);
    }
}
