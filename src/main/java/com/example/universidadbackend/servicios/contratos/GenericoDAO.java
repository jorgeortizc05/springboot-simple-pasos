package com.example.universidadbackend.servicios.contratos;

import java.util.Optional;

/**
 * Interfaz generica para no repetir mucho codigo, se usa los metodos comunes
 * @param <E> Entidad ex: Carrera
 */
public interface GenericoDAO <E> {

    Optional<E> findById(Integer id);
    E save(E entidad);
    Iterable<E> findAll();
    void deleteById(Integer id);
}
