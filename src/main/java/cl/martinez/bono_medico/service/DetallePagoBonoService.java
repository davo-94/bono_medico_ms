package cl.martinez.bono_medico.service;

import cl.martinez.bono_medico.dto.DetallePagoBonoDTO;

public interface DetallePagoBonoService {
    DetallePagoBonoDTO crear(DetallePagoBonoDTO dto);
    DetallePagoBonoDTO obtenerPorIdBono(Integer idBono);

}
