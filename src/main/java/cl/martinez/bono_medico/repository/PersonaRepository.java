package cl.martinez.bono_medico.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.martinez.bono_medico.model.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Integer>{
     Optional<Persona> findByRut(Integer rut); 

}
