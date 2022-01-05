package com.example.universidadbackend.controlador;

import com.example.universidadbackend.exception.BadRequestException;
import com.example.universidadbackend.model.entidades.Persona;
import com.example.universidadbackend.servicios.contratos.PersonaDAO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public class PersonaController extends GenericController<Persona, PersonaDAO>{
    public PersonaController(PersonaDAO service) {
        super(service);
    }



    /** @GetMapping("/nombre-apellido/{nombre}/{apellido}"): generalmente se entraria asi, pero usaremos de la otra forma
     * @RequestParam: http://localhost:9081/alumnos/nombre-apellido?nombre=Jorge&apellido=Ortiz
     *  Para integrar los parametros se usaria la url ?nombre=Jorge&apellido=Ortiz y lo recuperaria con @RequestParam
     * @param nombre = se consigue a travez de @RequestParam
     * @param apellido = se consigue a traves de @RequestParam
     * @return
     */
    @GetMapping("/nombre-apellido")
    public Persona buscarPersonaPorNombreYApellido(@RequestParam String nombre, @RequestParam String apellido){
        Optional<Persona> oPersona = service.buscarPorNombreYApellido(nombre, apellido);
        if(!oPersona.isPresent()){
            throw new BadRequestException(String.format("No se encontro Persona con nombre %s y apellido %s", nombre, apellido));
        }

        return oPersona.get();
    }
}
