package edu.uts.saberpro;

import edu.uts.saberpro.entidad.Rol;
import edu.uts.saberpro.entidad.Usuario;
import edu.uts.saberpro.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class MongoTestRunner implements CommandLineRunner {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        // ✅ Crear admin por defecto
        if (usuarioRepositorio.findByCorreo("admin@uts.edu.co").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setDocumento("1000000000");
            admin.setPrimerNombre("Admin");
            admin.setPrimerApellido("Principal");
            admin.setCorreo("admin@uts.edu.co");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRol(Rol.ADMIN);
            usuarioRepositorio.save(admin);
            System.out.println("✅ Administrador por defecto creado: admin@uts.edu.co / admin123");
        }

        // ✅ Insertar estudiantes si no existen
        if (usuarioRepositorio.count() <= 1) { // Solo el admin existe
            List<Usuario> estudiantes = Arrays.asList(

                crearEstudiante("1703165515","BARBOSA","Almansa","Daniel","Ruy","daniel.borrego@uts.edu.co","3743769005","EK20183007722"),
                crearEstudiante("1912704072","QUINTERO","Carrasco","Georgina","Paula","georgina.paz@uts.edu.co","3048274504","EK20183140703"),
                crearEstudiante("1235409644","PARRA","Segura","Octavia","Custodio","octavia.peña@uts.edu.co","3683379089","EK20183040545"),
                crearEstudiante("1998997640","ANAYA","Montserrat","Nidia","Gerónimo","nidia.juan@uts.edu.co","3720415448","EK20183025381"),
                crearEstudiante("1028932153","FLOR","Patiño","Alex","Matías","alex.aragonés@uts.edu.co","3980887697","EK20183025335"),
                crearEstudiante("1043941337","GARCIA","Blazquez","Ágata","Maximiano","ágata.alfonso@uts.edu.co","3273810606","EK20183122648"),
                crearEstudiante("1804983928","MANOSALVA","Tenorio","Isidora","Mauricio","isidora.conde@uts.edu.co","3169624316","EK20183064605"),
                crearEstudiante("1310919472","MENDOZA","Guardia","José Manuel","Gema","josé manuel.bernat@uts.edu.co","3347688805","EK20183187351"),
                crearEstudiante("1694887035","BELTRAN","Lloret","Calista","Delfina","calista.cabanillas@uts.edu.co","3390970987","EK20183233820"),
                crearEstudiante("1460081880","SANTAMARIA","Enríquez","Tecla","Pascual","tecla.cornejo@uts.edu.co","3548443831","EK20183030016"),
                crearEstudiante("1790729636","SANCHEZ","Aranda","Esperanza","Sabina","esperanza.osuna@uts.edu.co","3680309931","EK20183047073"),
                crearEstudiante("1726296302","ROMERO","Matas","Gerardo","Silvio","gerardo.vergara@uts.edu.co","3334382810","EK20183236451"),
                crearEstudiante("1367590542","LUNA","Salvà","Maura","Marcio","maura.agullo@uts.edu.co","3884149478","EK20183041714"),
                crearEstudiante("1616352546","TRIANA","Sabater","Manu","Socorro","manu.gutierrez@uts.edu.co","3795468125","EK20183187801"),
                crearEstudiante("1148671958","SUAREZ","Noriega","Eloy","Ruy","eloy.cuervo@uts.edu.co","3297435820","EK20183176566"),
                crearEstudiante("1113178803","GARCIA","Ariño","Ángela","Rufino","ángela.requena@uts.edu.co","3780868464","EK20183204427"),
                crearEstudiante("1062986746","PINZON","Vara","Rosa María","Merche","rosa maría.jara@uts.edu.co","3289593353","EK20183196280"),
                crearEstudiante("1935361704","JAIMES","Cáceres","Nuria","Mariana","nuria.lópez@uts.edu.co","3226856938","EK20183173799"),
                crearEstudiante("1544503135","NIÑO","Mas","María Del Carmen","Inés","maría del carmen.arroyo@uts.edu.co","3429526572","EK20183009565"),
                crearEstudiante("1623205563","FABIAN","Fabregat","Reinaldo","Mauricio","reinaldo.muñoz@uts.edu.co","3537326090","EK20183117756"),
                crearEstudiante("1059463886","HERNANDEZ","Juárez","Calisto","Salud","calisto.tormo@uts.edu.co","3686899101","EK20183044579"),
                crearEstudiante("1558949290","LARIOS","Meléndez","Amanda","Nayara","amanda.pallarès@uts.edu.co","3176088008","EK20183045760"),
                crearEstudiante("1208991610","CALDERON","Carretero","Flor","Severino","flor.baeza@uts.edu.co","3845934173","EK20183034044"),
                crearEstudiante("1685531642","VILLARREAL","Acosta","Silvia","Maximino","silvia.herrero@uts.edu.co","3185970094","EK20183041521"),
                crearEstudiante("1775525241","RESTREPO","Jaén","Nicodemo","Efraín","nicodemo.cámara@uts.edu.co","3563081261","EK20183027436"),
                crearEstudiante("1763513045","CACERES","Rico","Xavier","Trinidad","xavier.antúnez@uts.edu.co","3682092590","EK20183031592"),
                crearEstudiante("1605098460","TABARES","Sanz","José Mari","Mohamed","josé mari.garcés@uts.edu.co","3732980012","EK20183004153"),
                crearEstudiante("1235872332","NARANJO","Lastra","Pili","Borja","pili.otero@uts.edu.co","3673804098","EK20183030783"),
                crearEstudiante("1069394576","PRADA","Bonet","Manu","Noemí","manu.martorell@uts.edu.co","3958202823","EK20183024754"),
                crearEstudiante("1171565914","VARGAS","Sanjuan","Primitiva","Germán","primitiva.belmonte@uts.edu.co","3483427975","EK20183186200"),
                crearEstudiante("1152154048","TORRES","Sobrino","Ángela","Melchor","ángela.roldán@uts.edu.co","3380122354","EK20183182410"),
                crearEstudiante("1616233873","ORTIZ","Ibañez","Benigno","Leocadia","benigno.mármol@uts.edu.co","3548414845","EK20183213735"),
                crearEstudiante("1238583619","VILLAMIZAR","Tenorio","Carmelo","Olga","carmelo.valls@uts.edu.co","3150836309","EK20183065220"),
                crearEstudiante("1385231546","RESTREPO","Ríos","Ariel","Loida","ariel.manrique@uts.edu.co","3037721527","EK20183028123"),
                crearEstudiante("1215045750","HIGUERA","Busquets","Diana","Isaura","diana.badía@uts.edu.co","3618824110","EK20183207870"),
                crearEstudiante("1875351363","MATIZ","Melero","Ariel","Modesto","ariel.quevedo@uts.edu.co","3692408306","EK20183144329")
            );

            usuarioRepositorio.saveAll(estudiantes);
            System.out.println("✅ 36 estudiantes insertados correctamente en MongoDB.");
        } else {
            System.out.println("ℹ️ Los datos ya existen, no se insertaron duplicados.");
        }
    }

    private Usuario crearEstudiante(String doc, String ap1, String ap2, String nom1, String nom2,
                                    String correo, String tel, String reg) {
        Usuario e = new Usuario();
        e.setDocumento(doc);
        e.setPrimerApellido(ap1);
        e.setSegundoApellido(ap2);
        e.setPrimerNombre(nom1);
        e.setSegundoNombre(nom2);
        e.setCorreo(correo);
        e.setTelefono(tel);
        e.setNumeroRegistro(reg);
        e.setPassword(passwordEncoder.encode("12345"));
        e.setRol(Rol.ESTUDIANTE);
        return e;
    }
}
