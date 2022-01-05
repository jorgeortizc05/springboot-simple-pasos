package com.example.universidadbackend.controlador;

import com.example.universidadbackend.exception.BadRequestException;
import com.example.universidadbackend.model.entidades.Carrera;
import com.example.universidadbackend.servicios.contratos.GenericoDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class GenericController <E, S extends GenericoDAO<E>>{

    protected final S service;
    protected String nombreEntidad;

    public GenericController(S service) {
        this.service = service;
    }
    @GetMapping
    public List<E> obtenerTodos(){
        List<E> listado = (List<E>) service.findAll();
        if(listado.isEmpty()){
            throw new BadRequestException(String.format("No se han encontrado %ss", nombreEntidad));
        }
        return listado;
    }
    @GetMapping("/{id}")
    public E obtenerPorId(@PathVariable(value = "id", required = false) Integer id) {
        Optional<E> oE = service.findById(id);
        if (!oE.isPresent()) {
            throw new BadRequestException(String.format("la %ss con id %d no existe",nombreEntidad, id));
        }
        return oE.get();
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.deleteById(id);
    }

    /*@PostMapping
    public E alta(@RequestBody E e) {
        if (e.getCantidadAnios() < 0) {
            throw new BadRequestException("El campo cantidad de años no puede ser negativo");
        }
        if (e.getCantidaMaterias() < 0) {
            throw new BadRequestException("El campo cantidad de materias no puede ser negativo");
        }
        return service.save(carrera);
    }*/
}
