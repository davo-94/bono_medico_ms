package cl.vasquez.recetas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cl.vasquez.recetas.model.Medicamento;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer>{

    //Usamos JPA repository para manejar operaciones CRUD de la entidad Medicamento, 
    //donde Integer es el tipo del ID. 
}
