package com.example.universidadbackend;

import com.example.universidadbackend.model.entidades.Carrera;
import com.example.universidadbackend.repositories.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class CarreraCommand implements CommandLineRunner {
    @Autowired
    private CarreraRepository carreraRepository;

    @Override
    public void run(String... args) throws Exception {
        Carrera ingSistemas = new Carrera(null,"Ingeniería en Sistemas", 50, 5);
        System.out.println(carreraRepository.save(ingSistemas));
        Carrera lincSistemas = new Carrera(null,"Linceciatura en Sistemas", 40, 4);
        System.out.println(carreraRepository.save(lincSistemas));
        Carrera ingIndustrial = new Carrera(null,"Ingeniería Industrial", 55, 5);
        System.out.println(carreraRepository.save(ingIndustrial));
    }
}
