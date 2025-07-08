package cl.vasquez.recetas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.vasquez.recetas.model.Receta;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Integer> {
// ¡No necesita código! Spring Data JPA provee métodos como save(entity), findById(id),findAll(), etc.


}

