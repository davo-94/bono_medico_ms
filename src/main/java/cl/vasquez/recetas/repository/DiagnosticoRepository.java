package cl.vasquez.recetas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cl.vasquez.recetas.model.Diagnostico;

@Repository
public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Integer>{

}
