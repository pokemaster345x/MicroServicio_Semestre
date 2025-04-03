package co.edu.uceva.semestreservice.controllers;

import java.util.*;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import co.edu.uceva.semestreservice.model.entities.Semestre;
import co.edu.uceva.semestreservice.model.services.ISemestreService;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/semestre-service")
public class SemestreRestController {

    private final ISemestreService semestreService;


    private static final String ERROR="error";
    private static final String MENSAJE="mensaje";
    private static final String SEMESTRE="semestre";
    private static final String SEMESTRES="semestres";


    public SemestreRestController(ISemestreService semestreService) {
        this.semestreService = semestreService;
    }
    /*
        Listar Todos los semestres
    */
    @GetMapping("/semestres")
    public ResponseEntity<Map<String, Object>>getSemestres(){
        Map<String,Object> response=new HashMap<>();
        try{
            List<Semestre> semestres=semestreService.findAll();
            if(semestres.isEmpty()){
                response.put(MENSAJE,"No hay semestres en la base de datos");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            response.put(SEMESTRES,semestres);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch(DataAccessException e){
            response.put(MENSAJE,"Error al consultar la base de datos.");
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    /**
     * Listar productos con paginación.
     */


    @GetMapping("/semestres/page/{page}")
    public  ResponseEntity<Object> index(@PathVariable int page){
        Map<String,Object> response=new HashMap<>();
        Pageable pageable=PageRequest.of(page,4);
        try{
            Page<Semestre> semestres=semestreService.findAll(pageable);
            if(semestres.isEmpty()){
                response.put(MENSAJE,"No hay semestres en la pagina solicitada");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.ok(semestres);

        }catch(DataAccessException e){
            response.put(MENSAJE,"Error al consultar la base de datos.");
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }catch (IllegalArgumentException e){
            response.put(MENSAJE,"Numero de página inválido");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    /**
     * Crear un nuevo producto pasando el objeto en el cuerpo de la petición, usando validaciones
     */

    @PostMapping("/semestres")
    public ResponseEntity<Map<String ,Object>> save(@Valid @RequestBody Semestre semestre,BindingResult result){
        Map<String,Object> response=new HashMap<>();
        if(result.hasErrors()){
            List<String> errors=result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .toList();

            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try{
            Semestre nuevoSemestre=semestreService.save(semestre);
            response.put(MENSAJE,"El producto ha sido creado con éxito");
            response.put(SEMESTRE,nuevoSemestre);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch(DataAccessException e){
            response.put(MENSAJE,"Error al insertar el producto en la base de datos.");
            response.put(ERROR,e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Eliminar un producto pasando el objeto en el cuerpo de la petición.
     */
    @DeleteMapping("/semestres")
    public ResponseEntity<Map<String,Object>> delete(@RequestBody Semestre semestre){
        Map<String,Object> response=new HashMap<>();
        try{
            Semestre semestreExistente=semestreService.findById(semestre.getId());
            if(semestreExistente==null){
                response.put(MENSAJE,"El producto ID: "+semestre.getId()+" no existe en la base de datos.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            semestreService.delete(semestre);
            response.put(MENSAJE,"El semestre ha sido eliminado con éxito");
            return ResponseEntity.ok(response);
        }catch(DataAccessException e){
            response.put(MENSAJE,"Error al eliminar el semestre de la base de datos.");
            response.put(ERROR,e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/semestres")
    public ResponseEntity<Map<String ,Object>> update(@Valid @RequestBody Semestre semestre, BindingResult result){
        Map<String,Object> response=new HashMap<>();

        if(result.hasErrors()){
            List<String> errors=result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .toList();

            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            //verifica si el semestre existe antes de actualizar
            if(semestreService.findById(semestre.getId())==null){
                response.put(MENSAJE,"Error: Nose pudo editar, el semestre ID: "+semestre.getId()+" no existe en la base de datos");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            Semestre semestreActulizado=semestreService.save(semestre);
            response.put(MENSAJE, "El semestre ha sido actualizado con éxito");
            response.put(SEMESTRE,semestreActulizado);
            return ResponseEntity.ok(response);
        }catch(DataAccessException e){
            response.put(MENSAJE,"Error al actualizar el semestre de la base de datos");
            response.put(ERROR,e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @GetMapping("/semestres/{id}")
    public ResponseEntity<Map<String,Object>> findById(@PathVariable Long id){
        Map<String,Object>response=new HashMap<>();
        try{
            Semestre semestre = semestreService.findById(id);
            if(semestre==null){
                response.put(MENSAJE,"El producto ID: "+id+" no existe en la base de datos");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            response.put(MENSAJE,"el Producto ha sido actualizado con éxito");
            response.put(SEMESTRE,semestre);
            return ResponseEntity.ok(response);



        }catch(DataAccessException e){
            response.put(MENSAJE,"Error al consultar la base de datos.");
            response.put(ERROR,e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
