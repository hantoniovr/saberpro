package edu.uts.saberpro.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import edu.uts.saberpro.entidad.Calificacion;
import java.util.Optional;
import java.util.List;

public interface CalificacionRepository extends MongoRepository<Calificacion, String> {
    Optional<Calificacion> findByNumeroRegistro(String numeroRegistro);
    List<Calificacion> findAllByNumeroRegistro(String numeroRegistro);
}
