package cl.vasquez.recetas.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.vasquez.recetas.dto.DiagnosticoDTO;
import cl.vasquez.recetas.dto.MedicamentoDTO;
import cl.vasquez.recetas.dto.RecetaDTO;
import cl.vasquez.recetas.exception.ResourceNotFoundException;
import cl.vasquez.recetas.model.Diagnostico;
import cl.vasquez.recetas.model.Persona;
import cl.vasquez.recetas.model.Profesional;
import cl.vasquez.recetas.model.Receta;
import cl.vasquez.recetas.repository.DiagnosticoRepository;
import cl.vasquez.recetas.repository.MedicamentoRepository;
import cl.vasquez.recetas.repository.PersonaRepository;
import cl.vasquez.recetas.repository.ProfesionalRepository;
import cl.vasquez.recetas.repository.RecetaRepository;
import cl.vasquez.recetas.service.RecetaService;

@Service
public class RecetaServiceImpl implements RecetaService {

    @Autowired //-> Declarar el @Autowired para inyectar el repositorio
    private RecetaRepository recetaRepository; 

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private ProfesionalRepository profesionalRepository;

    @Autowired
    private DiagnosticoRepository diagnosticoRepository; 

    @Autowired
    private MedicamentoRepository medicamentoRepository; 

    @Override
    public RecetaDTO crearReceta(RecetaDTO recetaDTO){
        //Validar que el paciente exista
        Persona paciente = personaRepository.findById(recetaDTO.getIdPaciente())
            .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con id: "+ recetaDTO.getIdPaciente()));
        
        //Validar que el profesional exista
        Profesional medico = profesionalRepository.findById(recetaDTO.getIdMedico())
            .orElseThrow(() -> new ResourceNotFoundException("Médico no encontrado con id: " + recetaDTO.getIdMedico()));
        
        //Crear la receta
        Receta receta = new Receta(); 
        receta.setFechaEmision(LocalDate.now());
        receta.setEstado("PENDIENTE");
        receta.setPaciente(paciente);
        receta.setMedico(medico);
       
        //Primero persistimos la receta vacía
        receta = recetaRepository.save(receta);

        final Receta recetaFinal = receta;

        /*//Armamos los medicamentos
        List<Medicamento> medicamentos = recetaDTO.getMedicamentos().stream()
            .map(dto -> {
                Medicamento m = new Medicamento();
                m.setReceta(recetaFinal);
                return m;
            })
            .collect(Collectors.toList());*/

        
        //Armamos los diagnósticos 
        /*List<Diagnostico> diagnosticos = recetaDTO.getDiagnosticos().stream()
            .map(dto -> {
                Diagnostico d = new Diagnostico(dto.getCodigo(), dto.getDescripcion(), dto.getFechaDiagnostico());
                d.setReceta(recetaFinal);
                return d;
            })
            .collect(Collectors.toList());

        //Asignamos las listas a la receta (ya persistida)
        //receta.setMedicamentos(medicamentos);
        //receta.setDiagnosticos(diagnosticos);
        //receta.getMedicamentos().addAll(medicamentos);
        receta.getDiagnosticos().addAll(diagnosticos);

        //Volvemos a guardar, ahora con hijos
        receta = recetaRepository.save(receta);*/
        // Armamos los diagnósticos solo si vienen en el JSON
List<Diagnostico> diagnosticos = new ArrayList<>();
if (recetaDTO.getDiagnosticos() != null) {
    diagnosticos = recetaDTO.getDiagnosticos().stream()
        .map(dto -> {
            Diagnostico d = new Diagnostico(dto.getCodigo(), dto.getDescripcion(), dto.getFechaDiagnostico());
            d.setReceta(recetaFinal);
            return d;
        })
        .collect(Collectors.toList());
}

// Asignamos a la receta solo si hay diagnósticos
    receta.getDiagnosticos().addAll(diagnosticos);

// Guardamos la receta nuevamente
    receta = recetaRepository.save(receta);

        return convertirARecetaDTO(receta);


        /* 
        // Oye, estos medicamentos y diagnósticos pertenecen a esta receta que estoy por guardar ->
        for (Medicamento medicamento : medicamentos) {
            medicamento.setReceta(receta);
        }

        for (Diagnostico diagnostico : diagnosticos) {
            diagnostico.setReceta(receta);
        }

        //Guardar la receta en la base de datos
        receta = recetaRepository.save(receta);
        return convertirARecetaDTO(receta);*/
    }

    @Override
    public RecetaDTO obtenerPorId(Integer id) {
        Receta receta = recetaRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Receta no encontrada con id: "+id));
        return convertirARecetaDTO(receta);
    }

    @Override
    public List<RecetaDTO> obtenerTodas() {
        List<Receta> recetas = recetaRepository.findAll();
        return recetas.stream()
                .map(this::convertirARecetaDTO)
                .collect(Collectors.toList());
    }

    //Método de conversión Entity -> DTO (para no repetir código)

    private RecetaDTO convertirARecetaDTO(Receta receta){

        //Convertir lista de medicamentos
        List <MedicamentoDTO> medicamentosDTO = receta.getMedicamentos().stream()
            .map(med -> new MedicamentoDTO(
                med.getCatalogoMedicamento().getIdCatalogoMedicamento(),
                med.getCatalogoMedicamento().getNombre(),
                med.getDosis(), 
                med.getFrecuencia(), 
                med.getObservaciones()
            ))
            .collect(Collectors.toList());

        //Convertir lista de diagnósticos
        List<DiagnosticoDTO> diagnosticosDTO = receta.getDiagnosticos().stream()
            .map(diag -> new DiagnosticoDTO(
                diag.getCodigo(), 
                diag.getDescripcion(), 
                diag.getFechaDiagnostico(),
                receta.getIdReceta()
            ))
            .collect(Collectors.toList());
        
        //Retornar el DTO completo
        return new RecetaDTO(
            receta.getIdReceta(),
            receta.getFechaEmision(),
            receta.getEstado(),
            receta.getPaciente().getIdPersona(), 
            receta.getMedico().getIdProfesional(),
            medicamentosDTO,
            diagnosticosDTO
        );
    }

    @Override
    public RecetaDTO actualizarEstado(Integer id, String nuevoEstado){
        Receta receta = recetaRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Receta no encontrada con id: "+id));
        
        receta.setEstado(nuevoEstado);
        receta = recetaRepository.save(receta);
        return convertirARecetaDTO(receta);
    }

    @Override
    public void eliminarReceta (Integer id){
        Receta receta = recetaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Receta no encontradacon id_ "+ id));
            recetaRepository.delete(receta);
    }
}
