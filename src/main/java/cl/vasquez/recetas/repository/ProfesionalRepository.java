package cl.vasquez.recetas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.vasquez.recetas.model.Profesional;

@Repository
public interface ProfesionalRepository extends JpaRepository<Profesional, Integer> {

}
