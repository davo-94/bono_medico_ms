package cl.vasquez.recetas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.vasquez.recetas.model.CatalogoMedicamento;

@Repository
public interface  CatalogoMedicamentoRepository extends JpaRepository <CatalogoMedicamento, Integer>  {
    

}
