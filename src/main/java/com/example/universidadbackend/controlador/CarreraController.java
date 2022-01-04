package com.example.universidadbackend.controlador;

import com.example.universidadbackend.exception.BadRequestException;
import com.example.universidadbackend.model.entidades.Carrera;
import com.example.universidadbackend.servicios.contratos.CarreraDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carreras")
public class CarreraController {

    private final CarreraDAO carreraDAO;

    public CarreraController(CarreraDAO carreraDAO) {
        this.carreraDAO = carreraDAO;
    }

    @GetMapping
    public List<Carrera> obtenerTodos() {
        List<Carrera> carreras = (List<Carrera>) carreraDAO.findAll();
        if (carreras.isEmpty()) {
            throw new BadRequestException("No existe carreras");
        }

        return carreras;
    }

    @GetMapping("/{id}") //PathVariable no es necesario poner value, porque se llama id, pero es un ejemplo
    public Carrera obtenerPorId(@PathVariable(value = "id", required = false) Integer id) {
        Optional<Carrera> oCarrera = carreraDAO.findById(id);
        if (!oCarrera.isPresent()) {
            throw new BadRequestException(String.format("la carrera con id %d no existe", id));
        }
        return oCarrera.get();
    }

    @PostMapping
    public Carrera altaCarrera(@RequestBody Carrera carrera) {
        if (carrera.getCantidadAnios() < 0) {
            throw new BadRequestException("El campo cantidad de años no puede ser negativo");
        }
        if (carrera.getCantidaMaterias() < 0) {
            throw new BadRequestException("El campo cantidad de materias no puede ser negativo");
        }
        return carreraDAO.save(carrera);
    }

    @PutMapping("/{id}")//PathVariable no es necesario poner value, porque se llama id
    public Carrera actualizarCarrera(@PathVariable Integer id, @RequestBody Carrera carrera) {
        Carrera carreraUpdate = null;
        Optional<Carrera> oCarrera = carreraDAO.findById(id);
        if (!oCarrera.isPresent()) {
            throw new BadRequestException(String.format("la carrera con id %d no existe", id));
        }
        carreraUpdate = oCarrera.get();
        carreraUpdate.setCantidadAnios(carrera.getCantidadAnios());
        carreraUpdate.setCantidaMaterias(carrera.getCantidaMaterias());
        return carreraDAO.save(carreraUpdate);
    }

    @DeleteMapping("/{id}")
    public void eliminarCarrera(@PathVariable Integer id) {
        carreraDAO.deleteById(id);
    }

}
