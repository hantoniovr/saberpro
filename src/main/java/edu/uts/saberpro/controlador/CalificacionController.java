package edu.uts.saberpro.controlador;

import edu.uts.saberpro.entidad.Calificacion;
import edu.uts.saberpro.entidad.Rol;
import edu.uts.saberpro.entidad.Usuario;
import edu.uts.saberpro.servicio.CalificacionServicio;
import edu.uts.saberpro.servicio.UsuarioServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/coordinador")
public class CalificacionController {

    private final CalificacionServicio calificacionServicio;
    private final UsuarioServicio usuarioServicio;

    public CalificacionController(CalificacionServicio calificacionServicio, UsuarioServicio usuarioServicio) {
        this.calificacionServicio = calificacionServicio;
        this.usuarioServicio = usuarioServicio;
    }

    /* ===========================
       LISTAR CALIFICACIONES
       =========================== */
    @GetMapping("/listar-calificaciones")
    public String listarCalificaciones(Model model) {
        // Obtener todos los estudiantes
        List<Usuario> estudiantes = usuarioServicio.listarUsuarios()
                .stream()
                .filter(u -> u.getRol() == Rol.ESTUDIANTE)
                .toList();

        // Crear un mapa numeroRegistro -> Calificacion
        Map<String, Calificacion> mapaCalificaciones = calificacionServicio.listarTodas()
                .stream()
                .collect(Collectors.toMap(
                        Calificacion::getNumeroRegistro,
                        c -> c,
                        (c1, c2) -> c1 // en caso de duplicados, se queda con el primero
                ));

        model.addAttribute("estudiantes", estudiantes);
        model.addAttribute("mapaCalificaciones", mapaCalificaciones);

        return "coordinador/listar-calificaciones";
    }

    /* ===========================
       FORMULARIO REGISTRAR/EDITAR
       =========================== */
    @GetMapping("/registrar-calificacion/{numeroRegistro}")
    public String mostrarFormulario(@PathVariable String numeroRegistro, Model model) {
        Calificacion calificacion = calificacionServicio.buscarPorNumeroRegistro(numeroRegistro)
                .orElseGet(() -> {
                    Calificacion nueva = new Calificacion();
                    nueva.setNumeroRegistro(numeroRegistro);
                    return nueva;
                });

        model.addAttribute("calificacion", calificacion);
        return "coordinador/registrar-calificacion";
    }


    /* ===========================
    GUARDAR CALIFICACIÓN
    =========================== */
    @PostMapping("/guardar-calificacion")
    public String guardarCalificacion(@ModelAttribute Calificacion calificacion) {
        Calificacion existente = calificacionServicio.buscarPorNumeroRegistro(calificacion.getNumeroRegistro())
                .orElse(null);

        if (existente != null) {
            calificacion.setId(existente.getId()); // reutiliza el id real
        }

        calificacionServicio.guardar(calificacion);
        return "redirect:/coordinador/listar-calificaciones";
    }


    /* ===========================
       ELIMINAR CALIFICACIÓN
       =========================== */
    @GetMapping("/eliminar-calificacion/{id}")
    public String eliminarCalificacion(@PathVariable String id) {
        calificacionServicio.eliminar(id);
        return "redirect:/coordinador/listar-calificaciones";
    }
}
