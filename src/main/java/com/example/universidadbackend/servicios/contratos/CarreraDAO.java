package com.example.universidadbackend.servicios.contratos;


import com.example.universidadbackend.model.entidades.Carrera;


public interface CarreraDAO extends GenericoDAO<Carrera> {

    Iterable<Carrera> findCarrerasByNombreContains(String nombre);
    Iterable<Carrera> findCarrerasByNombreContainsIgnoreCase(String nombre);
    Iterable<Carrera> findCarrerasByCantidadAniosAfter(Integer cantidadAnios);

}
