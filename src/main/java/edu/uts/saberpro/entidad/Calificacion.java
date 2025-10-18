package edu.uts.saberpro.entidad;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "calificaciones")
public class Calificacion {

    @Id
    private String id;

    // Relación con el estudiante
    private String numeroRegistro; // clave de relación con Usuario

    // Puntajes
    private int puntajeGlobal;
    private int comunicacionEscrita;
    private int razonamientoCuantitativo;
    private int lecturaCritica;
    private int competenciasCiudadanas;
    private int ingles;
    private int formulacionProyectosIngenieria;
    private int pensamientoCientifico;
    private int disenoSoftware;

    // Niveles
    private int nivelGlobal;
    private int nivelComunicacionEscrita;
    private int nivelRazonamientoCuantitativo;
    private int nivelLecturaCritica;
    private int nivelCompetenciasCiudadanas;
    private int nivelIngles;
    private int nivelFormulacionProyectosIngenieria;
    private int nivelPensamientoCientifico;
    private int nivelDisenoSoftware;

    // ====== Métodos de negocio ======

    /**
     * Calcula el nivel según el puntaje.
     * Rango:
     * -1 → Anulado
     * 0 - 125 → Nivel 1
     * 126 - 155 → Nivel 2
     * 156 - 190 → Nivel 3
     * 191 - 300 → Nivel 4
     */
    public static int calcularNivel(int valor) {
        if (valor == -1) return -1; // anulado
        if (valor >= 191) return 4;
        if (valor >= 156) return 3;
        if (valor >= 126) return 2;
        if (valor >= 0) return 1;
        return 0;
    }

    /**
     * Calcula todos los niveles en base a los puntajes actuales.
     */
    public void calcularNiveles() {
        this.nivelGlobal = calcularNivel(puntajeGlobal);
        this.nivelComunicacionEscrita = calcularNivel(comunicacionEscrita);
        this.nivelRazonamientoCuantitativo = calcularNivel(razonamientoCuantitativo);
        this.nivelLecturaCritica = calcularNivel(lecturaCritica);
        this.nivelCompetenciasCiudadanas = calcularNivel(competenciasCiudadanas);
        this.nivelIngles = calcularNivel(ingles);
        this.nivelFormulacionProyectosIngenieria = calcularNivel(formulacionProyectosIngenieria);
        this.nivelPensamientoCientifico = calcularNivel(pensamientoCientifico);
        this.nivelDisenoSoftware = calcularNivel(disenoSoftware);
    }

    // ====== Getters y Setters ======

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNumeroRegistro() { return numeroRegistro; }
    public void setNumeroRegistro(String numeroRegistro) { this.numeroRegistro = numeroRegistro; }

    public int getPuntajeGlobal() { return puntajeGlobal; }
    public void setPuntajeGlobal(int puntajeGlobal) { this.puntajeGlobal = puntajeGlobal; }

    public int getComunicacionEscrita() { return comunicacionEscrita; }
    public void setComunicacionEscrita(int comunicacionEscrita) { this.comunicacionEscrita = comunicacionEscrita; }

    public int getRazonamientoCuantitativo() { return razonamientoCuantitativo; }
    public void setRazonamientoCuantitativo(int razonamientoCuantitativo) { this.razonamientoCuantitativo = razonamientoCuantitativo; }

    public int getLecturaCritica() { return lecturaCritica; }
    public void setLecturaCritica(int lecturaCritica) { this.lecturaCritica = lecturaCritica; }

    public int getCompetenciasCiudadanas() { return competenciasCiudadanas; }
    public void setCompetenciasCiudadanas(int competenciasCiudadanas) { this.competenciasCiudadanas = competenciasCiudadanas; }

    public int getIngles() { return ingles; }
    public void setIngles(int ingles) { this.ingles = ingles; }

    public int getFormulacionProyectosIngenieria() { return formulacionProyectosIngenieria; }
    public void setFormulacionProyectosIngenieria(int formulacionProyectosIngenieria) { this.formulacionProyectosIngenieria = formulacionProyectosIngenieria; }

    public int getPensamientoCientifico() { return pensamientoCientifico; }
    public void setPensamientoCientifico(int pensamientoCientifico) { this.pensamientoCientifico = pensamientoCientifico; }

    public int getDisenoSoftware() { return disenoSoftware; }
    public void setDisenoSoftware(int disenoSoftware) { this.disenoSoftware = disenoSoftware; }

    public int getNivelGlobal() { return nivelGlobal; }
    public void setNivelGlobal(int nivelGlobal) { this.nivelGlobal = nivelGlobal; }

    public int getNivelComunicacionEscrita() { return nivelComunicacionEscrita; }
    public void setNivelComunicacionEscrita(int nivelComunicacionEscrita) { this.nivelComunicacionEscrita = nivelComunicacionEscrita; }

    public int getNivelRazonamientoCuantitativo() { return nivelRazonamientoCuantitativo; }
    public void setNivelRazonamientoCuantitativo(int nivelRazonamientoCuantitativo) { this.nivelRazonamientoCuantitativo = nivelRazonamientoCuantitativo; }

    public int getNivelLecturaCritica() { return nivelLecturaCritica; }
    public void setNivelLecturaCritica(int nivelLecturaCritica) { this.nivelLecturaCritica = nivelLecturaCritica; }

    public int getNivelCompetenciasCiudadanas() { return nivelCompetenciasCiudadanas; }
    public void setNivelCompetenciasCiudadanas(int nivelCompetenciasCiudadanas) { this.nivelCompetenciasCiudadanas = nivelCompetenciasCiudadanas; }

    public int getNivelIngles() { return nivelIngles; }
    public void setNivelIngles(int nivelIngles) { this.nivelIngles = nivelIngles; }

    public int getNivelFormulacionProyectosIngenieria() { return nivelFormulacionProyectosIngenieria; }
    public void setNivelFormulacionProyectosIngenieria(int nivelFormulacionProyectosIngenieria) { this.nivelFormulacionProyectosIngenieria = nivelFormulacionProyectosIngenieria; }

    public int getNivelPensamientoCientifico() { return nivelPensamientoCientifico; }
    public void setNivelPensamientoCientifico(int nivelPensamientoCientifico) { this.nivelPensamientoCientifico = nivelPensamientoCientifico; }

    public int getNivelDisenoSoftware() { return nivelDisenoSoftware; }
    public void setNivelDisenoSoftware(int nivelDisenoSoftware) { this.nivelDisenoSoftware = nivelDisenoSoftware; }
}
