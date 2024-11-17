package Plataforma.Models;

public class Docente {

    // Atributos
    private int id;
    private String nombre;
    private String apellido;
    private String cedula;
    private String email;
    private String departamento;
    private String usuario;
    private String clave;

    // Constructor vacío (para compatibilidad con ORMs u operaciones de serialización)
    public Docente() {}

    // Constructor completo
    public Docente(int id, String nombre, String apellido, String cedula, String email, String departamento, String usuario, String clave) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.email = email;
        this.departamento = departamento;
        this.usuario = usuario;
        this.clave = clave;
    }

    // Constructor sin ID (para nuevos registros)
    public Docente(String nombre, String apellido, String cedula, String email, String departamento, String usuario, String clave) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.email = email;
        this.departamento = departamento;
        this.usuario = usuario;
        this.clave = clave;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Override
    public String toString() {
        return "Docente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", cedula='" + cedula + '\'' +
                ", email='" + email + '\'' +
                ", departamento='" + departamento + '\'' +
                ", usuario='" + usuario + '\'' +
                '}';
    }
}
