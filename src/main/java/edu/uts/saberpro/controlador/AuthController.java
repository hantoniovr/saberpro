package edu.uts.saberpro.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class AuthController {

    // Página de login
	@GetMapping("/login")
	public String login(@RequestParam(value = "logout", required = false) String logout,
	                    Model model) {
	    if (logout != null) {
	        model.addAttribute("mensaje", "Sesión cerrada correctamente");
	    }
	    return "login"; // templates/login.html
	}

    @GetMapping("/")
    public String login1() {
        return "login"; // templates/login.html
    }

    // Redirección automática después del login
    @GetMapping("/redirect")
    public String redirectAfterLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String rol = auth.getAuthorities().toString();

        if (rol.contains("ADMIN")) {
            return "redirect:/admin/inicio";
        } else if (rol.contains("COORDINADOR")) {
            return "redirect:/coordinador/inicio";
        } else {
            return "redirect:/estudiante/inicio";
        }
    }
}
