package edu.uts.saberpro.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import edu.uts.saberpro.entidad.Usuario;
import java.util.Optional;

public interface UsuarioRepositorio extends MongoRepository<Usuario, String> {
    Optional<Usuario> findByCorreo(String correo);
}
