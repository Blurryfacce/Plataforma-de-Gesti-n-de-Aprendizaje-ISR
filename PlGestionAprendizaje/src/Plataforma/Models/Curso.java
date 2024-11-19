package Plataforma.Models;

public class Curso {
    private int id;
    private String nombre;
    private String descripcion;
    private int idDocente; // Se almacenará el ID del docente asociado

    public Curso(int id, String nombre, String descripcion, int idDocente) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idDocente = idDocente;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion2) {
        this.descripcion = descripcion2;
    }

    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }
}
