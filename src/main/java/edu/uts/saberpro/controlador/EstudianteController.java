package edu.uts.saberpro.controlador;

import edu.uts.saberpro.entidad.Calificacion;
import edu.uts.saberpro.entidad.Usuario;
import edu.uts.saberpro.servicio.CalificacionServicio;
import edu.uts.saberpro.servicio.UsuarioServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class EstudianteController {

    private final UsuarioServicio usuarioServicio;
    private final CalificacionServicio calificacionServicio;

    public EstudianteController(UsuarioServicio usuarioServicio, CalificacionServicio calificacionServicio) {
        this.usuarioServicio = usuarioServicio;
        this.calificacionServicio = calificacionServicio;
    }

    /* ===========================
       INICIO DEL ESTUDIANTE
       =========================== */
    @GetMapping("/estudiante/inicio")
    public String inicioEstudiante(Model model, Principal principal) {
        // Buscar estudiante logueado por correo
        Usuario estudiante = usuarioServicio.buscarPorCorreo(principal.getName())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        // Buscar calificación asociada
        Calificacion calificacion = calificacionServicio.buscarPorNumeroRegistro(estudiante.getNumeroRegistro())
                .orElse(new Calificacion());

        // Calcular beneficio según reglas UTS
        String beneficio = calcularBeneficio(calificacion.getPuntajeGlobal());

        model.addAttribute("estudiante", estudiante);
        model.addAttribute("calificacion", calificacion);
        model.addAttribute("beneficio", beneficio);

        return "estudiante/inicio-estudiante";
    }

    /* ===========================
       REGLAS DE BENEFICIOS
       =========================== */
    private String calcularBeneficio(int puntaje) {
        if (puntaje >= 180 && puntaje <= 210) {
            return "Exoneración de trabajo de grado (nota 4.5)";
        } else if (puntaje >= 211 && puntaje <= 240) {
            return "Exoneración (nota 4.7) + 50% beca en derechos de grado";
        } else if (puntaje >= 241) {
            return "Exoneración (nota 5.0) + 100% beca en derechos de grado";
        }
        return null;
    }
    
    @GetMapping("/estudiante/detalle/{numeroRegistro}")
    public String detalleEstudiante(@PathVariable String numeroRegistro, Model model, Principal principal) {
        Usuario estudiante = usuarioServicio.buscarPorCorreo(principal.getName())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        Calificacion calificacion = calificacionServicio.buscarPorNumeroRegistro(numeroRegistro)
                .orElseThrow(() -> new RuntimeException("Calificación no encontrada"));

        model.addAttribute("estudiante", estudiante);
        model.addAttribute("calificacion", calificacion);

        return "estudiante/detalle-estudiante";
    }

}
