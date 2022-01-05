package com.example.universidadbackend.controlador;

import com.example.universidadbackend.exception.BadRequestException;
import com.example.universidadbackend.model.entidades.Alumno;
import com.example.universidadbackend.model.entidades.Carrera;
import com.example.universidadbackend.model.entidades.Persona;
import com.example.universidadbackend.servicios.contratos.CarreraDAO;
import com.example.universidadbackend.servicios.contratos.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alumnos") //en plural
public class AlumnoController extends PersonaController{
    private final CarreraDAO carreraDAO;

    @Autowired
    public AlumnoController(@Qualifier("alumnoDAOImpl") PersonaDAO alumnoDAO, CarreraDAO carreraDAO) {
        super(alumnoDAO);
        nombreEntidad = "Alumno";
        this.carreraDAO = carreraDAO;
    }

    /**
     * Instanciamos una persona, pero la clase Persona es abstracta, por lo cual no se puede instanciar.
     * Para poder relacionar con la instancia alumno se utiliza @JsonTypeInfo y @JsonSubTypes. Ver en class Persona
     *
     * @param alumno
     * @return la persona guardada
     */
    @PostMapping
    public Persona altaAlumno(@RequestBody Persona alumno) {
        return service.save(alumno);
    }

    /*@GetMapping
    public List<Persona> obtenerTodos() {
        List<Persona> alumnos = (List<Persona>) alumnoDAO.findAll();
        if (alumnos.isEmpty()) {
            throw new BadRequestException("No existen alumnos");
        }

        return alumnos;
    }

    @GetMapping("/{id}")
    public Persona obtenerAlumnoPorId(@PathVariable(required = false) Integer id) {
        Optional<Persona> oAlumno = alumnoDAO.findById(id);
        if (!oAlumno.isPresent()) {
            throw new BadRequestException(String.format("Alumno con id %d no existe", id));
        }
        return oAlumno.get();
    }*/

    @PutMapping("/{id}") //put para actualizar
    public Persona actualizarALumno(@PathVariable Integer id, @RequestBody Persona alumno) {
        Persona alumnoUpdate = null;
        Optional<Persona> oAlumno = service.findById(id);
        if(!oAlumno.isPresent()){
            throw new BadRequestException(String.format("Alumno con id %d no existe", id));
        }
        alumnoUpdate = oAlumno.get();
        alumnoUpdate.setNombre(alumno.getNombre());
        alumnoUpdate.setApellido(alumno.getApellido());
        alumnoUpdate.setDireccion(alumno.getDireccion());
        return service.save(alumnoUpdate);
    }

    /*@DeleteMapping("/{id}")
    public void eliminarAlumno(@PathVariable Integer id){
        alumnoDAO.deleteById(id);
    }*/

    @PutMapping("/{idAlumno}/carrera/{idCarrera}")
    public Persona asignarCarreraAlumno(@PathVariable Integer idAlumno,@PathVariable Integer idCarrera){
        Optional<Persona> oAlumno = service.findById(idAlumno);
        if (!oAlumno.isPresent()) {
            throw new BadRequestException(String.format("Alumno con id %d no existe", idAlumno));
        }
        Optional<Carrera> oCarrera = carreraDAO.findById(idCarrera);
        if (!oCarrera.isPresent()) {
            throw new BadRequestException(String.format("la carrera con id %d no existe", idCarrera));
        }

        Persona alumno = oAlumno.get();
        Carrera carrera = oCarrera.get();

        ((Alumno)alumno).setCarrera(carrera);

        return service.save(alumno);
    }

}
