package edu.uts.saberpro.controlador;

import edu.uts.saberpro.entidad.Rol;
import edu.uts.saberpro.entidad.Usuario;
import edu.uts.saberpro.servicio.UsuarioServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/coordinador")
public class CoordinadorController {

    private final UsuarioServicio usuarioServicio;

    public CoordinadorController(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    // Inicio del coordinador
    @GetMapping("/inicio")
    public String inicio() {
        return "coordinador/inicio"; // templates/coordinador/inicio.html
    }

    // Listar estudiantes (solo consulta)
    @GetMapping("/listar-estudiantes")
    public String listarEstudiantes(Model model) {
        List<Usuario> estudiantes = usuarioServicio.listarUsuarios().stream()
                .filter(u -> u.getRol() == Rol.ESTUDIANTE)
                .toList();
        model.addAttribute("estudiantes", estudiantes);
        return "coordinador/listar-estudiantes";
    }
}
