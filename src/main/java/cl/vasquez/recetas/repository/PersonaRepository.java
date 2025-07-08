package cl.vasquez.recetas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.vasquez.recetas.model.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer>{

}
