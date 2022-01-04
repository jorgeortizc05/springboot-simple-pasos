package com.example.universidadbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSimplePasosApplication {

    /*@Autowired
    private AlumnoCrudDAO service;*/

    public static void main(String[] args) {
        String[] beanDefinitionNames = SpringApplication.run(SpringSimplePasosApplication.class, args).getBeanDefinitionNames();
        /*for(String s: beanDefinitionNames){
            System.out.println(s);
        }*/
    }


    //Para ejecutar comandos. Es como el main y realizo pruebas
    /*@Bean
    public CommandLineRunner runner(){
        return args -> {
            Direccion direccion = new Direccion("Paute", "345", "234","","","Paute");
            Persona alumno = new Alumno(null, "Jorge", "Ortiz", "0105182703", direccion);
            Persona save = service.save(alumno);
            System.out.println("save = " + save);

            List<Persona> alumnos = (List<Persona>) service.findAll();
            alumnos.forEach(a -> System.out.println("alumno = " + a));
        };
    }*/

}
