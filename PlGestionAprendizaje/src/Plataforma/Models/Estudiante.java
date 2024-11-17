package Plataforma.Models;

public class Estudiante {

    // Atributos
    private int id;               // ID único del estudiante
    private String nombre;        // Nombre del estudiante
    private String apellido;      // Apellido del estudiante
    private String cedula;        // Cédula de identidad del estudiante
    private String email;         // Correo electrónico del estudiante
    private String direccion;     // Dirección del estudiante
    private String usuario;       // Nombre de usuario del estudiante
    private String clave;         // Contraseña del estudiante

    // Constructor vacío (necesario para ciertas operaciones como ORM o serialización)
    public Estudiante() {}

    // Constructor completo
    public Estudiante(int id, String nombre, String apellido, String cedula, String email, String direccion, String usuario, String clave) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.email = email;
        this.direccion = direccion;
        this.usuario = usuario;
        this.clave = clave;
    }

    // Constructor sin el ID (por ejemplo, para crear un nuevo estudiante)
    public Estudiante(String nombre, String apellido, String cedula, String email, String direccion, String usuario, String clave) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.email = email;
        this.direccion = direccion;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    // Método para mostrar información básica del estudiante
    @Override
    public String toString() {
        return "Estudiante{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", cedula='" + cedula + '\'' +
                ", email='" + email + '\'' +
                ", direccion='" + direccion + '\'' +
                ", usuario='" + usuario + '\'' +
                '}';
    }

    // Método opcional para validar datos (puedes personalizarlo según reglas específicas)
    public boolean esValido() {
        return nombre != null && !nombre.isEmpty() &&
               apellido != null && !apellido.isEmpty() &&
               cedula != null && !cedula.isEmpty() &&
               email != null && !email.isEmpty() &&
               usuario != null && !usuario.isEmpty() &&
               clave != null && !clave.isEmpty();
    }
}
