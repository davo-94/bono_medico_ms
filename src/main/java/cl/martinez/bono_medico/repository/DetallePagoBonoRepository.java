package cl.martinez.bono_medico.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.martinez.bono_medico.model.DetallePagoBono;

public interface DetallePagoBonoRepository extends JpaRepository<DetallePagoBono, Integer> {
    Optional<DetallePagoBono> findByBono_IdBono(Integer idBono);
}
