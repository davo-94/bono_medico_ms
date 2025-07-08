/*package cl.vasquez.recetas.repository;

import cl.vasquez.recetas.model.Diagnostico;
import cl.vasquez.recetas.model.Receta;
import cl.vasquez.recetas.repository.DiagnosticoRepository;
import cl.vasquez.recetas.repository.RecetaRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import static org.assertj.core.api.Assertions.assertThat;
@Disabled
@DataJpaTest
public class DiagnosticoRepositoryTest {

    @Autowired 
    private DiagnosticoRepository diagnosticoRepository; 

    @Autowired
    private RecetaRepository recetaRepository; 

    @Test
    public void testGuardarYListarDiagnostico() {
        // Arrange: Crear receta para asociar el diagnóstico
        Receta receta = new Receta(); 
        receta.setFechaEmision(LocalDate.now());
        receta.setEstado("PENDIENTE");
        receta = recetaRepository.save(receta); 

        Diagnostico diagnostico = new Diagnostico(); 
        diagnostico.setCodigo("J20");
        diagnostico.setDescripcion("Bronquitis aguda");
        diagnostico.setFechaDiagnostico(LocalDate.now());
        diagnostico.setReceta(receta);

        // Act: Guardar diagnóstico
        diagnosticoRepository.save(diagnostico);

        // Assert: Consultar todos y verificar que no esté vacío 
        List<Diagnostico> diagnosticos = diagnosticoRepository.findAll(); 
        assertThat(diagnosticos).isNotEmpty(); 
        assertThat(diagnosticos.get(0).getCodigo()).isEqualTo("J20");
        assertThat(diagnosticos.get(0).getDescripcion()).isEqualTo("Bronquitis aguda"); 
        assertThat(diagnosticos.get(0).getReceta().getIdReceta()).isEqualTo(receta.getIdReceta());
    }
}
*/