package edu.uts.saberpro.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/inicio")
    public String inicioAdmin() {
        return "admin/inicio"; // templates/admin/inicio.html
    }
}
