package Plataforma.Models;

public class Curso {
    private int id;
    private String nombre;
    private String descripcion;
    private String docente; // Asegúrate de tener este atributo.

    // Constructor completo
    public Curso(int id, String nombre, String descripcion, String docente) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.docente = docente;
    }

    // Constructor vacío (opcional, pero útil para frameworks)
    public Curso() {
    }

    // Getters y Setters
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

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getid_Docente() { // Este método debe existir.
        return docente;
    }

    public void setid_Docente(String docente) {
        this.docente = docente;
    }
}
