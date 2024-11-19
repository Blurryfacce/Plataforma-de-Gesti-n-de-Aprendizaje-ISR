package Plataforma.Models;

import java.sql.Date;

public class Inscripcion {
    private int id;
    private int estudianteId;
    private int cursoId;
    private Date fechaInscripcion;
    
    public Inscripcion(int id, int estudianteId, int cursoId, Date fechaInscripcion) {
        this.id = id;
        this.estudianteId = estudianteId;
        this.cursoId = cursoId;
        this.fechaInscripcion = fechaInscripcion;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getEstudianteId() {
        return estudianteId;
    }
    public void setEstudianteId(int estudianteId) {
        this.estudianteId = estudianteId;
    }
    public int getCursoId() {
        return cursoId;
    }
    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }
    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }
    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

}
