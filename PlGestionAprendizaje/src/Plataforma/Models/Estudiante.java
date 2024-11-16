package Plataforma.Models;

public class Estudiante {
    private String nombre;
    private String apellido;
    private String cedula;
    private String email;
    private String direccion;

    public Estudiante(String nombre, String apellido, String cedula, String email, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.email = email;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public String getEmail() {
        return email;
    }

    public String getDireccion() {
        return direccion;
    }

    @Override
    public String toString() {
        return "Estudiante [nombre=" + nombre + ", apellido=" + apellido + ", cedula=" + cedula 
                + ", email=" + email + ", direccion=" + direccion + "]";
    }
}
