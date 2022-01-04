package com.example.universidadbackend.servicios.contratos;

import com.example.universidadbackend.model.entidades.Persona;
import com.example.universidadbackend.servicios.contratos.PersonaDAO;

public interface AlumnoDAO extends PersonaDAO {

    Iterable<Persona> buscarAlumnosPorNombreCarrera(String nombre);

}
