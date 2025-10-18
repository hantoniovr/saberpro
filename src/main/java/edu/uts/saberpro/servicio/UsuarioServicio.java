package edu.uts.saberpro.servicio;

import org.springframework.stereotype.Service;
import edu.uts.saberpro.entidad.Usuario;
import edu.uts.saberpro.repositorio.UsuarioRepositorio;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {
    private final UsuarioRepositorio repo;

    public UsuarioServicio(UsuarioRepositorio repo) {
        this.repo = repo;
    }

    public List<Usuario> listarUsuarios() {
        return repo.findAll();
    }

    public Optional<Usuario> buscarPorCorreo(String correo) {
        return repo.findByCorreo(correo);
    }

    public Usuario guardar(Usuario usuario) {
        return repo.save(usuario);
    }

    public void eliminar(String id) {
        repo.deleteById(id);
    }
    public Usuario obtenerPorId(String id) {
        return repo.findById(id).orElse(null);
    }
}
