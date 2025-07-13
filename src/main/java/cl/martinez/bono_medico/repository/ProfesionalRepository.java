package cl.martinez.bono_medico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.martinez.bono_medico.model.Profesional;

public interface ProfesionalRepository extends JpaRepository<Profesional, Integer> {

}
