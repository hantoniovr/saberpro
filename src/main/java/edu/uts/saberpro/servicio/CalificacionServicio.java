package edu.uts.saberpro.servicio;

import org.springframework.stereotype.Service;
import edu.uts.saberpro.entidad.Calificacion;
import edu.uts.saberpro.repositorio.CalificacionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CalificacionServicio {

    private final CalificacionRepository repo;

    public CalificacionServicio(CalificacionRepository repo) {
        this.repo = repo;
    }

    public List<Calificacion> listarTodas() {
        return repo.findAll();
    }

    public Optional<Calificacion> buscarPorId(String id) {
        return repo.findById(id);
    }

    public Optional<Calificacion> buscarPorNumeroRegistro(String numeroRegistro) {
        return repo.findByNumeroRegistro(numeroRegistro);
    }

    public Calificacion guardar(Calificacion calificacion) {
        calificacion.calcularNiveles();

        // Si el id viene vacío, lo tratamos como null
        if (calificacion.getId() != null && calificacion.getId().isBlank()) {
            calificacion.setId(null);
        }

        // Si no tiene id pero ya existe por numeroRegistro, reutilizamos el id
        if (calificacion.getId() == null) {
            repo.findByNumeroRegistro(calificacion.getNumeroRegistro())
                .ifPresent(c -> calificacion.setId(c.getId()));
        }

        return repo.save(calificacion);
    }


    public void eliminar(String id) {
        repo.deleteById(id);
    }
}
