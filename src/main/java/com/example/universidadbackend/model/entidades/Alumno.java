package com.example.universidadbackend.model.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "alumnos")
@PrimaryKeyJoinColumn(name = "persona_id") //me relaciono con mi clase padre Persona
public class Alumno extends Persona {

    @ManyToOne(
            optional = true,
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(name = "carrera_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "alumnos"}) //el atributo debe ser el que contenga en la clase Carrera que referencia a la clase Alumno, en este caso alumnos
    private Carrera carrera;//@JsonIgnorePorperties mas info en Class Carrera. Evitamos un bucle infinito, debido a que json sobrescribe el fetch lazy a eager

    public Alumno() {
    }

    public Alumno(Integer id, String nombre, String apellido, String dni, Direccion direccion) {
        super(id, nombre, apellido, dni, direccion);
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Alumno{}";
    }
}