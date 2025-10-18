package edu.uts.saberpro.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import edu.uts.saberpro.entidad.Usuario;
import edu.uts.saberpro.entidad.Rol;
import edu.uts.saberpro.servicio.UsuarioServicio;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class UsuarioController {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /* ===========================
       LISTAR USUARIOS
       =========================== */
    @GetMapping("/listar-coordinadores")
    public String listarCoordinadores(Model model) {
        List<Usuario> coordinadores = usuarioServicio.listarUsuarios().stream()
                .filter(u -> u.getRol() == Rol.COORDINADOR)
                .collect(Collectors.toList());
        model.addAttribute("coordinadores", coordinadores);
        return "admin/listar-coordinadores";
    }

    @GetMapping("/listar-estudiantes")
    public String listarEstudiantes(Model model) {
        List<Usuario> estudiantes = usuarioServicio.listarUsuarios().stream()
                .filter(u -> u.getRol() == Rol.ESTUDIANTE)
                .collect(Collectors.toList());
        model.addAttribute("estudiantes", estudiantes);
        return "admin/listar-estudiantes";
    }

    /* ===========================
       FORMULARIOS DE CREACIÓN
       =========================== */
    @GetMapping("/crear-coordinador")
    public String crearCoordinadorForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "admin/crear-coordinador";
    }

    @GetMapping("/crear-estudiante")
    public String crearEstudianteForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "admin/crear-estudiante";
    }

    /* ===========================
       GUARDAR NUEVO USUARIO
       =========================== */
    @PostMapping("/guardar-coordinador")
    public String guardarCoordinador(@ModelAttribute Usuario usuario) {
        usuario.setRol(Rol.COORDINADOR);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioServicio.guardar(usuario);
        return "redirect:/admin/listar-coordinadores";
    }

    @PostMapping("/guardar-estudiante")
    public String guardarEstudiante(@ModelAttribute Usuario usuario) {
        usuario.setRol(Rol.ESTUDIANTE);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioServicio.guardar(usuario);
        return "redirect:/admin/listar-estudiantes";
    }

    /* ===========================
       EDITAR ESTUDIANTE
       =========================== */
    @GetMapping("/editar-estudiante/{id}")
    public String editarEstudiante(@PathVariable String id, Model model) {
        Usuario usuario = usuarioServicio.obtenerPorId(id);
        if (usuario == null || usuario.getRol() != Rol.ESTUDIANTE) {
            return "redirect:/admin/listar-estudiantes";
        }
        model.addAttribute("usuario", usuario);
        return "admin/editar-estudiante";
    }

    @PostMapping("/actualizar-estudiante")
    public String actualizarEstudiante(@ModelAttribute Usuario usuario) {
        Usuario existente = usuarioServicio.obtenerPorId(usuario.getId());
        if (existente != null) {
            existente.setDocumento(usuario.getDocumento());
            existente.setPrimerApellido(usuario.getPrimerApellido());
            existente.setSegundoApellido(usuario.getSegundoApellido());
            existente.setPrimerNombre(usuario.getPrimerNombre());
            existente.setSegundoNombre(usuario.getSegundoNombre());
            existente.setCorreo(usuario.getCorreo());
            existente.setTelefono(usuario.getTelefono());
            existente.setNumeroRegistro(usuario.getNumeroRegistro());

            // Si el campo password no está vacío, actualiza la contraseña
            if (usuario.getPassword() != null && !usuario.getPassword().isBlank()) {
                existente.setPassword(passwordEncoder.encode(usuario.getPassword()));
            }

            usuarioServicio.guardar(existente);
        }
        return "redirect:/admin/listar-estudiantes";
    }

    /* ===========================
       EDITAR COORDINADOR
       =========================== */
    @GetMapping("/editar-coordinador/{id}")
    public String editarCoordinador(@PathVariable String id, Model model) {
        Usuario usuario = usuarioServicio.obtenerPorId(id);
        if (usuario == null || usuario.getRol() != Rol.COORDINADOR) {
            return "redirect:/admin/listar-coordinadores";
        }
        model.addAttribute("usuario", usuario);
        return "admin/editar-coordinador";
    }

    @PostMapping("/actualizar-coordinador")
    public String actualizarCoordinador(@ModelAttribute Usuario usuario) {
        Usuario existente = usuarioServicio.obtenerPorId(usuario.getId());
        if (existente != null) {
            existente.setDocumento(usuario.getDocumento());
            existente.setPrimerApellido(usuario.getPrimerApellido());
            existente.setSegundoApellido(usuario.getSegundoApellido());
            existente.setPrimerNombre(usuario.getPrimerNombre());
            existente.setSegundoNombre(usuario.getSegundoNombre());
            existente.setCorreo(usuario.getCorreo());
            existente.setTelefono(usuario.getTelefono());

            if (usuario.getPassword() != null && !usuario.getPassword().isBlank()) {
                existente.setPassword(passwordEncoder.encode(usuario.getPassword()));
            }

            usuarioServicio.guardar(existente);
        }
        return "redirect:/admin/listar-coordinadores";
    }

    /* ===========================
       ELIMINAR USUARIO
       =========================== */
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable String id) {
        Usuario usuario = usuarioServicio.obtenerPorId(id);
        if (usuario != null) {
            usuarioServicio.eliminar(id);

            if (usuario.getRol() == Rol.COORDINADOR) {
                return "redirect:/admin/listar-coordinadores";
            } else if (usuario.getRol() == Rol.ESTUDIANTE) {
                return "redirect:/admin/listar-estudiantes";
            }
        }
        return "redirect:/admin/inicio";
    }
}
