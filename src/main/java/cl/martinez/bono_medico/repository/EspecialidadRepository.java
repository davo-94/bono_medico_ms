package cl.martinez.bono_medico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.martinez.bono_medico.model.Especialidad;

public interface EspecialidadRepository extends JpaRepository<Especialidad, Integer> {

}
